package jibe.sdk.demo.datagramsocket;

import jibe.sdk.client.JibeIntents;
import jibe.sdk.client.JibeServiceListener.ConnectFailedReason;
import jibe.sdk.client.events.JibeSessionEvent;
import jibe.sdk.client.simple.SimpleApi;
import jibe.sdk.client.simple.SimpleConnectionStateListener;
import jibe.sdk.client.simple.session.DatagramSocketConnection;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * DatagramSocketConnection Demo
 */
public class DatagramSocketConnectionDemo extends Activity {

	private static final String LOG_TAG = DatagramSocketConnectionDemo.class.getName();

	private static final String EXTRA_CONNECT_FAILED_REASON = "LOG_TAG"+"extra.ConnectFailedReason";

	private static final int DIALOG_INITIALIZATION_ERROR = 1;
	
	private EditText mRemoteURIText;
	private Button mOpenButton;
	private Button mAcceptButton;
	private Button mRejectButton;
	private Button mCloseButton;

	private DummyPacketGenerator mPacketGenerator = new DummyPacketGenerator();

	private DatagramSocketConnection mConnection;

	private Intent mIncomingSessionIntent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mCloseButton = (Button) findViewById(R.id.btn_close);
		mCloseButton.setOnClickListener(mButtonClickListener);
		mCloseButton.setEnabled(false);

		mOpenButton = (Button) findViewById(R.id.btn_open);
		mOpenButton.setEnabled(false);
		mOpenButton.setOnClickListener(mButtonClickListener);

		mAcceptButton = (Button) findViewById(R.id.btn_accept);
		mAcceptButton.setOnClickListener(mButtonClickListener);
		mAcceptButton.setEnabled(false);

		mRejectButton = (Button) findViewById(R.id.btn_reject);
		mRejectButton.setOnClickListener(mButtonClickListener);
		mRejectButton.setEnabled(false);

		mRemoteURIText = (EditText) findViewById(R.id.edit_uri);
		mRemoteURIText.setText("");
		mRemoteURIText.setHint(R.string.remote_user_hint);
		mRemoteURIText.setInputType(InputType.TYPE_CLASS_PHONE);

		// app may have been launched by an incoming call. grab the intent here.
		// Process when authentication is done
		// and connection is ready.
		mIncomingSessionIntent = getIntent();

		mConnection = new DatagramSocketConnection(this, mConnStateListener);
		mPacketGenerator.setConnection(mConnection);
	}

	@Override
	protected void onDestroy() {
		mConnection.dispose();
		super.onDestroy();
	}

	@Override
	public void onNewIntent(Intent intent) {
		// if connection is not ready yet, intent will be handled in onReady()
		// callback.
		// if it is ready, need to handle now.
		if (mConnection != null && mConnection.isInitialized()) {
			if (mConnection.canProcessIntent(intent)) {
				mIncomingSessionIntent = intent;
			}
			handleIncomingSessionIntent();
		}
	}

	private OnClickListener mButtonClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (v == mOpenButton) {
				String remoteUserId = mRemoteURIText.getText().toString();
				openConnection(remoteUserId);
			} else if (v == mAcceptButton) {
				acceptIncomingConnection();
			} else if (v == mRejectButton) {
				rejectIncomingConnection();
			} else if (v == mCloseButton) {
				resetConnection();
			}
		}

	};

	private void setUiButtonsForActiveConnection() {
		mCloseButton.setEnabled(true);
		mAcceptButton.setEnabled(false);
		mRejectButton.setEnabled(false);
		mOpenButton.setEnabled(false);
	}

	private void setUiButtonsForIncomingConnection() {
		mOpenButton.setEnabled(false);
		mAcceptButton.setEnabled(true);
		mRejectButton.setEnabled(true);
	}

	private void resetUiButtons() {
		mCloseButton.setEnabled(false);
		mAcceptButton.setEnabled(false);
		mRejectButton.setEnabled(false);
		mOpenButton.setEnabled(true);
	}

	private void openConnection(String receiverUserId) {
		mPacketGenerator.setIsSender(true);
		try {
			mConnection.start(receiverUserId);
			setUiButtonsForActiveConnection();
		} catch (Exception e) {
			Log.w(LOG_TAG, "Failed to open connection.");
			e.printStackTrace();
			showMessage(e.getMessage());
			resetConnection();
		}
	}

	private void acceptIncomingConnection() {
		mPacketGenerator.setIsSender(false);
		try {
			mConnection.start(mIncomingSessionIntent);
			setUiButtonsForActiveConnection();
		} catch (Exception e) {
			Log.w(LOG_TAG, "Failed to accept connection.");
			e.printStackTrace();
			showMessage(e.getMessage());
			resetConnection();
		}
	}

	private void rejectIncomingConnection() {
		try {
			mConnection.reject(mIncomingSessionIntent);
		} catch (Exception e) {
			Log.w(LOG_TAG, "Failed to reject connection.");
			e.printStackTrace();
		} finally {
			resetConnection();
		}
	}

	private boolean handleIncomingSessionIntent() {
		if (!mConnection.canProcessIntent(mIncomingSessionIntent)) {
			return false;
		}
		Log.w(LOG_TAG,
				"Incoming data session from: "
						+ mIncomingSessionIntent.getStringExtra(JibeIntents.EXTRA_USERID));
		try {
			mConnection.monitor(mIncomingSessionIntent);
		} catch (Exception e) {
			 Log.e(LOG_TAG, "Failed to monitor connection. ", e);
			 resetUiButtons();
			 return false;
		}
		setUiButtonsForIncomingConnection();
		return true;
	}

	private void startDummyPacketGeneration() {

		mPacketGenerator.startReceivingPackets();

		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				setUiButtonsForActiveConnection();

				int delay = Integer
						.parseInt(((EditText) findViewById(R.id.edit_packetintervall))
								.getText().toString());
				int packetsize = Integer
						.parseInt(((EditText) findViewById(R.id.edit_packetsize))
								.getText().toString());

				mPacketGenerator.startSendingPackets(packetsize, delay);
			}
		});

	}

	private void resetConnection() {
		/*
		 * This may be called by callbacks, and may therefore not necessarily be
		 * run within the UI context. In order to be able to update the screen,
		 * force this to run as part of the UI thread.
		 */
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				resetUiButtons();
			}
		});

		mPacketGenerator.stop();
		try {
			mConnection.stop();
		} catch (Exception e) {
			Log.w(LOG_TAG, "Failed to stop connection.", e);
		}
		mIncomingSessionIntent = null;
	}

	private void showMessage(final String message) {
		if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					showMessage(message);
				}
			});

			return;
		}

		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == R.id.menu_exit)
			finish();

		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {
		if (id == DIALOG_INITIALIZATION_ERROR) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Jibe Connection initialization failed: "+ (ConnectFailedReason) args.getSerializable(EXTRA_CONNECT_FAILED_REASON))
			       .setCancelable(false)
			       .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			       			mConnection = new DatagramSocketConnection(DatagramSocketConnectionDemo.this, mConnStateListener);
			       			mPacketGenerator.setConnection(mConnection);
			           }
			       })
			       .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			        	   DatagramSocketConnectionDemo.this.finish();
			           }
			       });
			return builder.create();	
		}
		
		return super.onCreateDialog(id, args);
	}

	private SimpleConnectionStateListener mConnStateListener = new SimpleConnectionStateListener() {

		@Override
		public void onInitialized(SimpleApi source) {
			Log.v(LOG_TAG, "onReady()");
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					resetUiButtons();
				}
			});
		}

		@Override
		public void onStarted(SimpleApi source) {
			Log.v(LOG_TAG, "onStarted()");
			showMessage("Connection started");
			startDummyPacketGeneration();
		}

		@Override
		public void onStartFailed(SimpleApi source, int info) {
			Log.v(LOG_TAG, "onStartFailed(). Info:" + info);

			switch (info) {
			case JibeSessionEvent.INFO_SESSION_CANCELLED:
				showMessage("The sender has canceled the connection");
				break;
			case JibeSessionEvent.INFO_SESSION_REJECTED:
				showMessage("The receiver has rejected the connection/is busy");
				break;
			case JibeSessionEvent.INFO_SESSION_TIMEOUT:
				showMessage("The receiver did not accept the connection");
				break;
			case JibeSessionEvent.INFO_USER_NOT_ONLINE:
				showMessage("Receiver is not online");
				break;
			case JibeSessionEvent.INFO_USER_UNKNOWN:
				showMessage("This phone number is not known");
				break;
			default:
				showMessage("Connection start failed.");
				break;
			}

			resetConnection();
		}

		@Override
		public void onTerminated(SimpleApi source, int info) {
			Log.v(LOG_TAG, "onTerminated(). Info:" + info);
			switch (info) {
			case JibeSessionEvent.INFO_GENERIC_EVENT:
				showMessage("You terminated the connection");
				break;
			case JibeSessionEvent.INFO_SESSION_TERMINATED_BY_REMOTE:
				showMessage("The remote party terminated the connection");
				break;
			default:
				showMessage("Connection terminated.");
				break;
			}

			resetConnection();
		}

		@Override
		public void onInitializationFailed(SimpleApi source, ConnectFailedReason reason) {
			final ConnectFailedReason theReason = reason; 
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Bundle bundle = new Bundle();
					bundle.putSerializable(EXTRA_CONNECT_FAILED_REASON, theReason);
					showDialog(DIALOG_INITIALIZATION_ERROR, bundle);
				}
			});
			Log.v(LOG_TAG, "onError(). Reason: " + reason);
		}
	};
}
