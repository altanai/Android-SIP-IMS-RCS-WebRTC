package jibe.sdk.demo.capabilities;

import java.io.IOException;

import jibe.sdk.client.JibeServiceException;
import jibe.sdk.client.JibeServiceListener.ConnectFailedReason;
import jibe.sdk.client.contacts.ImsCapabilities;
import jibe.sdk.client.simple.SimpleApi;
import jibe.sdk.client.simple.SimpleApiStateListener;
import jibe.sdk.client.simple.contacts.CapabilitiesHelper;
import jibe.sdk.client.simple.contacts.CapabilitiesHelper.CapabilitiesUpdateListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
 * CapabilitiesCheck Demo
 */
public class CapabilitiesCheckDemo extends Activity {
	
	private static final String LOG_TAG = CapabilitiesCheckDemo.class.getName();
	
	private static final String EXTRA_CONNECT_FAILED_REASON = "LOG_TAG"+"extra.ConnectFailedReason";
	
	private static final int DIALOG_INITIALIZATION_ERROR = 1;
	
	private EditText mRemoteURIText;
	private Button mCheckButton;

	private CapabilitiesHelper mCapHelper;
	
	
	

        

	
	
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.main);

		mCheckButton = (Button) findViewById(R.id.btn_open);
		mCheckButton.setEnabled(false);
		mCheckButton.setOnClickListener(mButtonClickListener);

		mRemoteURIText = (EditText) findViewById(R.id.edit_uri);
		mRemoteURIText.setText("");
		mRemoteURIText.setHint(R.string.remote_user_hint);
		mRemoteURIText.setInputType(InputType.TYPE_CLASS_PHONE);

		mCapHelper = new CapabilitiesHelper(this, mSimpleApiListener, mCapabilitiesListener);
	}

	@Override
	protected void onDestroy() {
		mCapHelper.dispose();
		super.onDestroy();
	}

	private OnClickListener mButtonClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (v == mCheckButton) {
				String remoteUserId = mRemoteURIText.getText().toString();
				requestCapabilitiesUpdate(remoteUserId);
			}
		}
	};

	private void requestCapabilitiesUpdate(final String remoteUserId) {
		try {
			mCapHelper.requestCapabilitiesUpdate(remoteUserId);
		} catch (IOException e) {
			showMessage("Failed to send capabilities request");
		} catch (JibeServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			        	   	mCapHelper.dispose();
			        	   	mCapHelper = new CapabilitiesHelper(CapabilitiesCheckDemo.this, mSimpleApiListener, mCapabilitiesListener);
			           }
			       })
			       .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			        	   CapabilitiesCheckDemo.this.finish();
			           }
			       });
			return builder.create();	
		}
		
		return super.onCreateDialog(id, args);
	}

	private SimpleApiStateListener mSimpleApiListener = new SimpleApiStateListener() {

		@Override
		public void onInitialized(SimpleApi source) {
			Log.v(LOG_TAG, "CapabilitiesHelper onReady()");
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					mCheckButton.setEnabled(true);
				}
			});
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
			Log.v(LOG_TAG, "onError(). Reason: " + reason);
		}
	};

	private CapabilitiesUpdateListener mCapabilitiesListener = new CapabilitiesUpdateListener() {

		@Override
		public void onCapabilitiesUpdate(String msisdn,
				ImsCapabilities capabilities) {
			showMessage("remote user " + msisdn + " is online:"
					+ capabilities.isOnline() + "\n" + "VoIP available:"
					+ true + "\n"
					/*+ capabilities.isAudioSupported() + "\n"*/
					+ "Video sharing available:"
					+ capabilities.isVideoSharingSupported() + "\n"
					+ "File transfer available:"
					+ capabilities.isFileTransferSupported() + "\n"
					+ "Chat available:" + capabilities.isChatSupported());
		}
	};

}