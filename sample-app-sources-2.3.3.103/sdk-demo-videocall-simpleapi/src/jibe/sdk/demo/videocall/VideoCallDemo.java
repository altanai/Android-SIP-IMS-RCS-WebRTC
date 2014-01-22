package jibe.sdk.demo.videocall;

import java.io.IOException;

import jibe.sdk.client.JibeServiceException;
import jibe.sdk.client.JibeServiceListener.ConnectFailedReason;
import jibe.sdk.client.events.JibeSessionEvent;
import jibe.sdk.client.simple.SimpleApi;
import jibe.sdk.client.simple.SimpleConnectionStateListener;
import jibe.sdk.client.simple.videocall.VideoCallConnection;
import jibe.sdk.client.video.CameraMediaSource;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Looper;
import android.text.InputType;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * LiveVideoConnection Demo
 */
public class VideoCallDemo extends Activity {

	private static final String LOG_TAG = VideoCallDemo.class.getCanonicalName();
	
	private static final String EXTRA_CONNECT_FAILED_REASON = "LOG_TAG"+"extra.ConnectFailedReason";
	
	private static final int DIALOG_INITIALIZATION_ERROR = 1;

	private EditText mRemoteURIText;
	private Button mOpenButton;
	private Button mCloseButton;
	private Button mSwitchCamButton;

	private SurfaceView mLocalViewSurface;
	private SurfaceView mRemoteViewSurface;
	private CameraMediaSource mCameraMediaSource;

	private VideoCallConnection mConnection;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mOpenButton = (Button) findViewById(R.id.btn_open);
		mOpenButton.setOnClickListener(mButtonClickListener);
		mOpenButton.setEnabled(false);

		mCloseButton = (Button) findViewById(R.id.btn_close);
		mCloseButton.setOnClickListener(mButtonClickListener);
		mCloseButton.setEnabled(false);

		mSwitchCamButton = (Button) findViewById(R.id.btn_camera);
		mSwitchCamButton.setOnClickListener(mButtonClickListener);
		mSwitchCamButton.setEnabled(false);

		mLocalViewSurface = (SurfaceView) findViewById(R.id.video_local);
		mCameraMediaSource = new CameraMediaSource(
				CameraMediaSource.CAMERA_FRONT, mLocalViewSurface);

		mRemoteViewSurface = (SurfaceView) findViewById(R.id.video_remote);

		mRemoteURIText = (EditText) findViewById(R.id.edit_uri);
		mRemoteURIText.setText("");
		mRemoteURIText.setHint(R.string.remote_user_hint);
		mRemoteURIText.setInputType(InputType.TYPE_CLASS_PHONE);
		
		createConnection();
	}

	@Override
	protected void onDestroy() {
		if (mConnection != null) {
			mConnection.dispose();
		}
		super.onDestroy();
	}

	private OnClickListener mButtonClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (v == mOpenButton) {
				String remoteUserId = mRemoteURIText.getText().toString();
				openConnection(remoteUserId);
			} else if (v == mCloseButton) {
				resetConnection();
			} else if (v == mSwitchCamButton) {
				if (mCameraMediaSource.getCameraId() == CameraMediaSource.CAMERA_FRONT)
					mCameraMediaSource
							.switchCamera(CameraMediaSource.CAMERA_BACK);
				else
					mCameraMediaSource
							.switchCamera(CameraMediaSource.CAMERA_FRONT);
			}
		}
	};

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

	private void createConnection() {
		if (mConnection != null) {
			try {
				mConnection.stop();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JibeServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			mConnection = new VideoCallConnection(this, mConnStateListener,
					mCameraMediaSource, mRemoteViewSurface);
		}
		mConnection.setAutoAccept(true);
	}

	private void resetConnection() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				resetUiButtons();
			}
		});

		createConnection();
	}

	private void setUiButtonsForActiveConnection() {
		mOpenButton.setEnabled(false);
		mCloseButton.setEnabled(true);
		mSwitchCamButton.setEnabled(true);
	}

	private void resetUiButtons() {
		mCloseButton.setEnabled(false);
		mSwitchCamButton.setEnabled(false);
		mOpenButton.setEnabled(true);
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
	protected Dialog onCreateDialog(int id, Bundle args) {
		if (id == DIALOG_INITIALIZATION_ERROR) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Jibe Connection initialization failed: "+ (ConnectFailedReason) args.getSerializable(EXTRA_CONNECT_FAILED_REASON))
			       .setCancelable(false)
			       .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			        	   	mConnection.dispose();
			        	   	mConnection = null;
			        	   	createConnection();
			           }
			       })
			       .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			        	   VideoCallDemo.this.finish();
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
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					setUiButtonsForActiveConnection();
				}
			});
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
