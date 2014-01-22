package com.orangelabs.rcs.sip;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.orangelabs.rcs.service.api.client.ClientApiListener;
import com.orangelabs.rcs.service.api.client.capability.CapabilityApiIntents;
import com.orangelabs.rcs.service.api.client.sip.SipApi;
import com.orangelabs.rcs.sip.utils.Utils;

/**
 * Send an instant message
 * 
 * @author jexa7410
 */
public class SendInstantMessage extends Activity implements ClientApiListener {
	/**
	 * SIP API
	 */
	private SipApi sipApi;

	/**
	 * UI handler
	 */
	private final Handler handler = new Handler();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Set layout
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.send_im);

		// Set title
		setTitle(R.string.title_send_im);
		
		// Set contact selector
		Spinner spinner = (Spinner)findViewById(R.id.contact);
		spinner.setAdapter(Utils.createContactListAdapter(this));

		// Set buttons callback
		Button sendBtn = (Button)findViewById(R.id.send_btn);
		sendBtn.setOnClickListener(btnSendListener);
		if (spinner.getAdapter().getCount() == 0) {
			sendBtn.setEnabled(false);
		}

		// Connect to API
		sipApi = new SipApi(getApplicationContext());
		sipApi.addApiEventListener(this);
		sipApi.connectApi();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();

		// Disconnect from API
        sipApi.removeApiEventListener(this);
		sipApi.disconnectApi();
	}

	/**
	 * API is disabled (e.g. server not started)
	 */
	public void handleApiDisabled() {
		handler.post(new Runnable(){
			public void run(){
				Utils.showMessageAndExit(SendInstantMessage.this, getString(R.string.label_api_disabled));
			}
		});
	}

	/**
	 * API is connected to the server
	 */
	public void handleApiConnected() {
		handler.post(new Runnable(){
			public void run(){
				Button sendBtn = (Button)findViewById(R.id.send_btn);
				sendBtn.setEnabled(true);
			}
		});
	}

	/**
	 * API is disconnected from the server
	 */
	public void handleApiDisconnected() {
		handler.post(new Runnable(){
			public void run(){
				Utils.showMessageAndExit(SendInstantMessage.this, getString(R.string.label_api_disconnected));
			}
		});
	}

	/**
	 * Send button callback
	 */
	private OnClickListener btnSendListener = new OnClickListener() {
		public void onClick(View v) {
			// Get remote contact
			Spinner spinner = (Spinner)findViewById(R.id.contact);
			MatrixCursor cursor = (MatrixCursor) spinner.getSelectedItem();
			final String remote = cursor.getString(1);

			// Get content
			TextView msgView = (TextView)findViewById(R.id.message);
			final String content = msgView.getText().toString();
			
			// Initiate session in background
			Thread thread = new Thread() {
				public void run() {
					try {
						// Get feature tag
						final String featureTag = CapabilityApiIntents.RCSE_EXTENSION_BASE + "=\"" + CapabilityApiIntents.RCSE_EXTENSION_PREFIX + ".orange.imdemo\"";

						// Initiate session
						final boolean result = sipApi.sendSipInstantMessage(remote, featureTag, content, "text/plain");
						handler.post(new Runnable(){
							public void run(){
								Utils.displayToast(SendInstantMessage.this, getString(R.string.label_send_im));
								if (result) {
									Utils.displayToast(SendInstantMessage.this, getString(R.string.label_send_im_success));
								} else {
									Utils.displayToast(SendInstantMessage.this, getString(R.string.label_send_im_failed));
								}
							}
						});
					} catch (Exception e) {
					}
				}
			};
			thread.start();
		}
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			finish();
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}
}