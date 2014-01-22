package jibe.sdk.demo.viewme;

import jibe.sdk.client.JibeServiceException;
import jibe.sdk.client.JibeServiceListener.ConnectFailedReason;
import jibe.sdk.client.simple.SimpleApi;
import jibe.sdk.client.simple.SimpleApiStateListener;
import jibe.sdk.client.simple.arena.ArenaHelper;
import jibe.sdk.client.simple.myprofile.MyProfileHelper;
import jibe.sdk.client.simple.myprofile.MyProfileHelper.OnlineStateListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SplashActivity extends Activity {

	private static final String TAG = SplashActivity.class.getSimpleName();

	private MyProfileHelper mMyProfileHelper;
	private ArenaHelper mArenaHelper;
	private AlertDialog mDownloadDialog;
	private boolean mIsEngineInstalled;

	private static final int MSG_CONNECTION_ONLINE = 1;
	private static final int MSG_CONNECTION_OFFLINE = 2;
	private static final int MSG_CONNECTION_TIMEOUT = 3;
	private static final int JIBE_CONNECTION_STARTUP_TIMEOUT_MILLIS = 10000;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
	}

	public void onStart() {
		super.onStart();

		mIsEngineInstalled = ArenaHelper
				.isCompatibleRealtimeEngineInstalled(this);

		if (mIsEngineInstalled) {
			if (mDownloadDialog != null) {
				destroyDownloadDialog();
			}

			if (mMyProfileHelper == null) {
				// connecting to the MyProfileHelper will establish a service connection to the Jibe RTE
				// if the user has not already signed up/logged in this (or any other feature requiring
				// a connection to the Jibe engine) will AUTOMATICALLY trigger the jibe login flow.
				mMyProfileHelper = new MyProfileHelper(this,
						mMyProfileHelperListener, mOnlineStateListener);
			}
		} else {
			if (mDownloadDialog == null) {
				createDownloadDialog();
			}
		}

	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		if (mMyProfileHelper != null) {
			mMyProfileHelper.dispose();
		}
		if (mArenaHelper != null) {
			mArenaHelper.dispose();
		}
	}

	private void createDownloadDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.dlg_download_title);
		builder.setMessage(R.string.dlg_download_message);
		builder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				ArenaHelper.openGooglePlayPage(SplashActivity.this);
			}
		});

		builder.setNegativeButton(R.string.btn_cancel,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						SplashActivity.this.finish();
					}
				});

		builder.setCancelable(false);

		mDownloadDialog = builder.create();
		mDownloadDialog.show();
	}

	private void destroyDownloadDialog() {
		if (mDownloadDialog.isShowing()) {
			mDownloadDialog.dismiss();
		}
		mDownloadDialog = null;
	}

	public void onClickLaunchFriendPicker(View view) {
		mArenaHelper = new ArenaHelper(this, mArenaHelperListener);
	}

	private SimpleApiStateListener mMyProfileHelperListener = new SimpleApiStateListener() {

		@Override
		public void onInitialized(SimpleApi source) {
			try {
				if (mMyProfileHelper.isOnline()) {
					handleOnlineStateChange(true);
				} else {
					mMyProfileHelper.startMonitoringOnlineState();
					startConnectionStartupTimer();
				}
			} catch (Exception e) {
				Log.e(TAG, "Failed to start MyProfileHelper", e);
			}
		}

		@Override
		public void onInitializationFailed(SimpleApi source,
				ConnectFailedReason reason) {
			Log.e(TAG, "Initialization of MyProfileHelper has failed. Reason="
					+ reason);
		}
	};

	private OnlineStateListener mOnlineStateListener = new OnlineStateListener() {
		@Override
		public void onOnlineStateChanged(boolean isOnline) {
			handleOnlineStateChange(isOnline);
		}
	};

	private void handleOnlineStateChange(boolean isOnline) {
		if (isOnline) {
			try {
				mMyProfileHelper.stopMonitoringOnlineState();
			} catch (Exception e) {
				e.printStackTrace();
			}
			mHandler.sendEmptyMessage(MSG_CONNECTION_ONLINE);
		} else {
			mHandler.sendEmptyMessage(MSG_CONNECTION_OFFLINE);
		}
	}

	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if (isFinishing()) {
				return;
			}

			switch (msg.what) {
			case MSG_CONNECTION_ONLINE:
				((Button) SplashActivity.this
						.findViewById(R.id.friendPickerButton))
						.setEnabled(true);
				Toast.makeText(SplashActivity.this, R.string.connection_online,
						Toast.LENGTH_LONG).show();
				break;
			case MSG_CONNECTION_OFFLINE:
				((Button) SplashActivity.this
						.findViewById(R.id.friendPickerButton))
						.setEnabled(false);
				Toast.makeText(SplashActivity.this,
						R.string.connection_offline, Toast.LENGTH_LONG).show();
				break;
			case MSG_CONNECTION_TIMEOUT:
				try {
					if (!mMyProfileHelper.isOnline()) {
						((Button) SplashActivity.this
								.findViewById(R.id.friendPickerButton))
								.setEnabled(false);
						Toast.makeText(SplashActivity.this,
								R.string.connection_failed, Toast.LENGTH_LONG)
								.show();
					}
				} catch (Exception ex) {
				}
				break;
			}
		}
	};

	private void startConnectionStartupTimer() {
		mHandler.sendEmptyMessageAtTime(MSG_CONNECTION_TIMEOUT,
				SystemClock.uptimeMillis()
						+ JIBE_CONNECTION_STARTUP_TIMEOUT_MILLIS);
	}

	private SimpleApiStateListener mArenaHelperListener = new SimpleApiStateListener() {
		@Override
		public void onInitialized(SimpleApi source) {
			try {
				mArenaHelper.startFriendPicker();
			} catch (JibeServiceException e) {
				Log.e(TAG, "Failed to startFriendPicker()", e);
			}
			mArenaHelper.dispose();
			mArenaHelper = null;
		}

		@Override
		public void onInitializationFailed(SimpleApi source,
				ConnectFailedReason reason) {
			Log.e(TAG, "Failed to initialize ArenaHelper. Reason=" + reason);
		}
	};
}
