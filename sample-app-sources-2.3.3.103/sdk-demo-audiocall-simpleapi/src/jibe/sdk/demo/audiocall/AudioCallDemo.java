package jibe.sdk.demo.audiocall;

import java.io.IOException;

import jibe.sdk.client.JibeIntents;
import jibe.sdk.client.JibeServiceException;
import jibe.sdk.client.JibeServiceListener.ConnectFailedReason;
import jibe.sdk.client.call.AudioEncodingOption;
import jibe.sdk.client.events.JibeSessionEvent;
import jibe.sdk.client.simple.SessionNotFoundException;
import jibe.sdk.client.simple.SimpleApi;
import jibe.sdk.client.simple.SimpleConnectionStateListener;
import jibe.sdk.client.simple.call.AudioCallConnection;
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
 * LiveAudioConnection Demo
 * 
 * Simplified example, showing the use of the high-level Jibe APIs to manage a
 * live audio connection. This sample app also illustrates app wake-up upon an
 * incoming VoIP connection by using a BroadcastReceiver.
 */
public class AudioCallDemo extends Activity {
	private static final String LOG_TAG = AudioCallDemo.class.getName();
	
	private static final String EXTRA_CONNECT_FAILED_REASON = "LOG_TAG"+"extra.ConnectFailedReason";
	
	private static final int DIALOG_INITIALIZATION_ERROR = 1;

	private EditText mRemoteURIText;
	private Button mOpenButton;
	private Button mAcceptButton;
	private Button mRejectButton;
	private Button mCloseButton;

	private AudioCallConnection mConnection;

	private Intent mIncomingCallIntent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// set up the buttons for the UI (all disabled until the connection has
		// been initialized)
		mOpenButton = (Button) findViewById(R.id.btn_open);
		mOpenButton.setOnClickListener(mButtonClickListener);
		mOpenButton.setEnabled(false);

		mCloseButton = (Button) findViewById(R.id.btn_close);
		mCloseButton.setOnClickListener(mButtonClickListener);
		mCloseButton.setEnabled(false);

		mAcceptButton = (Button) findViewById(R.id.btn_accept);
		mAcceptButton.setOnClickListener(mButtonClickListener);
		mAcceptButton.setEnabled(false);

		mRejectButton = (Button) findViewById(R.id.btn_reject);
		mRejectButton.setOnClickListener(mButtonClickListener);
		mRejectButton.setEnabled(false);

		// set up the input box for entering the remote user's URI
		mRemoteURIText = (EditText) findViewById(R.id.edit_uri);
		mRemoteURIText.setText("");
		mRemoteURIText.setHint(R.string.remote_user_hint);
		mRemoteURIText.setInputType(InputType.TYPE_CLASS_PHONE);

		// app may have been launched by an incoming call. grab the intent here.
		// Process when connection has been initialized.
		mIncomingCallIntent = getIntent();

		mConnection = new AudioCallConnection(this, mConnStateListener);
	}

	@Override
	protected void onDestroy() {
		mConnection.dispose();
		super.onDestroy();
	}

	@Override
	public void onNewIntent(Intent intent) {
		// if connection is not ready yet, intent will be handled in
		// onInitialized()
		// callback. if it is ready, we can handle it now.
		if (mConnection != null && mConnection.isInitialized()) {
			if (mConnection.canProcessIntent(intent)) {
				mIncomingCallIntent = intent;
			}
			handleIncomingCallIntent();
		}
	}

	// The handler for the UI buttons
	private OnClickListener mButtonClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (v == mOpenButton) {
				// Open a new connection to the specified URI
				String remoteUserId = mRemoteURIText.getText().toString();
				openConnection(remoteUserId);
			} else if (v == mAcceptButton) {
				// Accept the incoming connection
				acceptIncomingConnection();
			} else if (v == mRejectButton) {
				// Reject the incoming connection
				rejectIncomingConnection();
			} else if (v == mCloseButton) {
				// Close the current connection
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
		try {
			// Try to open the connection and pass in the phone number of
			// of the remote user
			mConnection.start(receiverUserId);
			// Set the UI buttons appropriately for this state
			setUiButtonsForActiveConnection();
		} catch (Exception e) {
			// There was a failure, so tell the user, and reset the
			// connection
			Log.w(LOG_TAG, "Failed to open connection.");
			safeShowToast(e.getMessage());
			resetConnection();
		}
	}

	private void acceptIncomingConnection() {
		try {
			// Try to accept the incoming connection
			mConnection.start(mIncomingCallIntent);
			setUiButtonsForActiveConnection();
		} catch (IOException e) {
			// There was a failure, so tell the user, and reset the
			// connection
			Log.w(LOG_TAG, "Failed to accept connection.");
			safeShowToast(e.getMessage());
			resetConnection();
		} catch (JibeServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void rejectIncomingConnection() {
		try {
			// Try to reject the incoming connection
			mConnection.reject(mIncomingCallIntent);
		} catch (SessionNotFoundException snf) {
			// The session wasn't found, maybe the remote user hung up before we
			// rejected the call
			Log.w(LOG_TAG, "Failed to reject connection. Session not found");
		} catch (IOException e) {
			// There was a lower-level failure, so tell the user, and reset the
			// connection
			Log.w(LOG_TAG, "Failed to reject connection.");
			e.printStackTrace();
		} catch (JibeServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Reset the connection, regardless of whether the close succeeded
			// or failed
			resetConnection();
		}
	}

	private boolean handleIncomingCallIntent() {
		if (!mConnection.canProcessIntent(mIncomingCallIntent)) {
			return false;
		}

		Log.w(LOG_TAG,
				"Incoming call from: "
						+ mIncomingCallIntent
								.getStringExtra(JibeIntents.EXTRA_USERID));

		try {
			mConnection.monitor(mIncomingCallIntent);
		} catch (Exception e) {
			Log.e(LOG_TAG, "Failed to monitor connection. ", e);
			resetUiButtons();
			return false;
		}

		setUiButtonsForIncomingConnection();

		return true;
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
			Log.w(LOG_TAG, "Failed to close connection.");
			e.printStackTrace();
		} catch (JibeServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mIncomingCallIntent = null;
	}

	private void safeShowToast(final String message) {
		/*
		 * This may be called by callbacks, and may therefore not necessarily be
		 * run within the UI context, so check whether this is the UI thread
		 * that is running.
		 */
		if (Looper.getMainLooper().getThread() != Thread.currentThread()) {

			/*
			 * This is not the UI thread, so force this to run as part of the UI
			 * thread.
			 */
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					safeShowToast(message);
				}
			});

			return;
		}

		// Show the message to the user
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
			       			mConnection = new AudioCallConnection(AudioCallDemo.this, mConnStateListener);
			           }
			       })
			       .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			        	   AudioCallDemo.this.finish();
			           }
			       });
			return builder.create();	
		}
		
		return super.onCreateDialog(id, args);
	}

	private SimpleConnectionStateListener mConnStateListener = new SimpleConnectionStateListener() {

		@Override
		public void onInitialized(SimpleApi source) {
			Log.v(LOG_TAG, "onInitialized()");

			// Set preferred codec. uncompressed audio (PCM) in this case.
			try {
				mConnection.setAudioEncodingOption(AudioEncodingOption.MIN_CPU_USAGE);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// check if the app was launched
			if (!handleIncomingCallIntent()) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						resetUiButtons();
					}
				});
			}
		}

		@Override
		public void onStarted(SimpleApi source) {
			Log.v(LOG_TAG, "onStarted()");
			safeShowToast("Connection started.");
		}

		@Override
		public void onStartFailed(SimpleApi source, int info) {
			// We failed to start the connection, so tell the user, and reset
			// the connection
			Log.v(LOG_TAG, "onStartFailed(). Info:" + info);
			switch (info) {
			case JibeSessionEvent.INFO_SESSION_CANCELLED:
				safeShowToast("The sender has canceled the connection");
				break;
			case JibeSessionEvent.INFO_SESSION_REJECTED:
				safeShowToast("The receiver has rejected the connection/is busy");
				break;
			case JibeSessionEvent.INFO_SESSION_TIMEOUT:
				safeShowToast("The receiver did not accept the connection");
				break;
			case JibeSessionEvent.INFO_USER_NOT_ONLINE:
				safeShowToast("Receiver is not online");
				break;
			case JibeSessionEvent.INFO_USER_UNKNOWN:
				safeShowToast("This phone number is not known");
				break;
			default:
				safeShowToast("Connection start failed.");
				break;
			}

			resetConnection();
		}

		@Override
		public void onTerminated(SimpleApi source, int info) {
			// The connection was terminated, so tell the user, and reset the
			// connection
			Log.v(LOG_TAG, "onTerminated(). Info:" + info);
			switch (info) {
			case JibeSessionEvent.INFO_GENERIC_EVENT:
				safeShowToast("You terminated the connection");
				break;
			case JibeSessionEvent.INFO_SESSION_TERMINATED_BY_REMOTE:
				safeShowToast("The remote party terminated the connection");
				break;
			default:
				safeShowToast("Connection terminated.");
				break;
			}

			resetConnection();
		}

		@Override
		public void onInitializationFailed(SimpleApi source,
				ConnectFailedReason reason) {
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
