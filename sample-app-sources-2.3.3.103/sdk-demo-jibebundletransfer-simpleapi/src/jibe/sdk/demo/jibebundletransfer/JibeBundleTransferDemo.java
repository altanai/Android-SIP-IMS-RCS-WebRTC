package jibe.sdk.demo.jibebundletransfer;

import java.io.IOException;

import jibe.sdk.client.JibeIntents;
import jibe.sdk.client.JibeServiceException;
import jibe.sdk.client.JibeServiceListener.ConnectFailedReason;
import jibe.sdk.client.events.JibeSessionEvent;
import jibe.sdk.client.simple.SimpleApi;
import jibe.sdk.client.simple.SimpleConnectionStateListener;
import jibe.sdk.client.simple.session.JibeBundle;
import jibe.sdk.client.simple.session.JibeBundleTransferConnection;
import jibe.sdk.client.simple.session.JibeBundleTransferConnection.JibeBundleTransferConnectionListener;
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
public class JibeBundleTransferDemo extends Activity {
	private static final String LOG_TAG = JibeBundleTransferDemo.class.getName();
	private static final String KEY_TEXT = "Text";
	private static final String KEY_TIMESTAMP = "Timestamp";
	
	private static final String EXTRA_CONNECT_FAILED_REASON = "LOG_TAG"+"extra.ConnectFailedReason";
	
	private static final int DIALOG_INITIALIZATION_ERROR = 1;

	private EditText mRemoteURIText;
	private EditText mSendText;
	private Button mOpenButton;
	private Button mAcceptButton;
	private Button mRejectButton;
	private Button mCloseButton;
	private Button mSendButton;

	private JibeBundleTransferConnection mConnection;

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
		
		mSendButton = (Button) findViewById(R.id.btn_send);
		mSendButton.setOnClickListener(mButtonClickListener);
		mSendButton.setEnabled(false);
		
		mSendText = (EditText) findViewById(R.id.sendText);

		// app may have been launched by an incoming call. grab the intent here.
		// Process when authentication is done
		// and connection is ready.
		mIncomingSessionIntent = getIntent();

		mConnection = new JibeBundleTransferConnection(this, mConnStateListener, mJibeBundleListener);
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
			} else if (v == mSendButton) {
				sendData();
			}
		}

	};

	private void setUiButtonsForActiveConnection() {
		mCloseButton.setEnabled(true);
		mAcceptButton.setEnabled(false);
		mRejectButton.setEnabled(false);
		mOpenButton.setEnabled(false);
		mSendButton.setEnabled(true);
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
		mSendButton.setEnabled(false);
	}

	private void openConnection(String receiverUserId) {
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
		try {
			mConnection.start(mIncomingSessionIntent);
			setUiButtonsForActiveConnection();
		} catch (IOException e) {
			Log.w(LOG_TAG, "Failed to accept connection.");
			e.printStackTrace();
			showMessage(e.getMessage());
			resetConnection();
		} catch (JibeServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void rejectIncomingConnection() {
		try {
			mConnection.reject(mIncomingSessionIntent);
		} catch (IOException e) {
			Log.w(LOG_TAG, "Failed to reject connection.");
			e.printStackTrace();
		} catch (JibeServiceException e) {
			// TODO Auto-generated catch block
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
	
	private void sendData() {
		JibeBundle bundle = new JibeBundle();
		
		String sendText = mSendText.getText().toString();
		long timestamp = System.currentTimeMillis();
		
		bundle.putString(KEY_TEXT, sendText);
		bundle.putLong(KEY_TIMESTAMP, timestamp);
		
		Log.d(LOG_TAG, "Sending " + sendText + ", " + timestamp);
		
		try {
			int packetNumber = mConnection.send(bundle);
			Log.d(LOG_TAG, "Sent packet number: " + packetNumber);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateButtonsForActiveConnectionOnUiThread() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				setUiButtonsForActiveConnection();
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

		try {
			mConnection.stop();
		} catch (IOException e) {
			Log.w(LOG_TAG, "Failed to stop connection.", e);
		} catch (JibeServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			        	   	mConnection.dispose();
			        	   	mConnection = new JibeBundleTransferConnection(JibeBundleTransferDemo.this, mConnStateListener, mJibeBundleListener);
			           }
			       })
			       .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			        	   JibeBundleTransferDemo.this.finish();
			           }
			       });
			return builder.create();	
		}
		
		return super.onCreateDialog(id, args);
	}
	
	private JibeBundleTransferConnectionListener mJibeBundleListener = new JibeBundleTransferConnectionListener() {
		@Override
		public void jibeBundleReceived(JibeBundle bundle) {
			long timestamp = bundle.getLong(KEY_TIMESTAMP, -1);
			String text = bundle.getString(KEY_TEXT);
			
			showMessage("Received message:\n " + text
					+ "\n sent at \n"
					+ timestamp);
		}

		@Override
		public void acknowledgeReceived(int bundleNumber) {
			Log.d(LOG_TAG, "Received ack for bundle number: " + bundleNumber);
		}
	};

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
			updateButtonsForActiveConnectionOnUiThread();
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
			Log.v(LOG_TAG, "onInitializationFailed(). Reason: " + reason);
		}
	};
}
