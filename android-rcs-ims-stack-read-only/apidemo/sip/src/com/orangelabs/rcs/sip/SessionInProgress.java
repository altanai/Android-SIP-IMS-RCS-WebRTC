/*******************************************************************************
 * Software Name : RCS IMS Stack
 *
 * Copyright (C) 2010 France Telecom S.A.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.orangelabs.rcs.sip;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.orangelabs.rcs.service.api.client.ClientApiListener;
import com.orangelabs.rcs.service.api.client.sip.ISipSession;
import com.orangelabs.rcs.service.api.client.sip.ISipSessionEventListener;
import com.orangelabs.rcs.service.api.client.sip.SipApi;
import com.orangelabs.rcs.sip.R;
import com.orangelabs.rcs.sip.utils.Utils;
import com.orangelabs.rcs.utils.PhoneUtils;

/**
 * Session in progress
 * 
 * @author jexa7410
 */
public class SessionInProgress extends Activity implements ClientApiListener {
	/**
     * UI handler
     */
    private final Handler handler = new Handler();

    /**
	 * SIP API
	 */
	private SipApi sipApi;

	/**
	 * Session ID
	 */
    private String sessionId;
    
    /**
	 * SIP session
	 */
	private ISipSession sipSession = null;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Set layout
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.session_in_progress);

        // Set title
        setTitle(R.string.title_sip_in_progress);

        // Set button listener
        Button closeBtn = (Button) findViewById(R.id.close_btn);
        closeBtn.setOnClickListener(btnCloseListener);
        
        // Get session-ID
    	sessionId = getIntent().getStringExtra("sessionId");

    	// Connect to API
        sipApi = new SipApi(getApplicationContext());
        sipApi.addApiEventListener(this);
        sipApi.connectApi();
    }
    
	@Override
	protected void onDestroy() {
		super.onDestroy();

		// Disconnect from the API
		sipApi.removeApiEventListener(this);
		sipApi.disconnectApi();
	}
    
    /**
     * API is disabled (e.g. server not started)
     */
    public void handleApiDisabled() {
		handler.post(new Runnable() { 
			public void run() {
				Utils.showMessageAndExit(SessionInProgress.this, getString(R.string.label_api_disabled));
			}
		});
    }

    /**
     * API is connected to the server
     */
    public void handleApiConnected() {
		try {
			// Get the SIP session
    		sipSession = sipApi.getSession(sessionId);
			if (sipSession == null) {
				// Session not found or expired
				Utils.showMessageAndExit(SessionInProgress.this, getString(R.string.label_session_has_expired));
				return;
			}
			
			// Display session info
			sipSession.addSessionListener(sipSessionListener);
	    	TextView featureTagEdit = (TextView)findViewById(R.id.feature_tag);
	    	featureTagEdit.setText(sipSession.getFeatureTag());
	    	TextView contactEdit = (TextView)findViewById(R.id.contact);
	    	contactEdit.setText(PhoneUtils.extractNumberFromUri(sipSession.getRemoteContact()));
	    	TextView localSdpEdit = (TextView)findViewById(R.id.local_sdp);
	    	localSdpEdit.setText(sipSession.getLocalSdp());
			TextView remoteSdpEdit = (TextView)findViewById(R.id.remote_sdp);
			remoteSdpEdit.setText(sipSession.getRemoteSdp());
		} catch(Exception e) {
			Utils.showMessageAndExit(SessionInProgress.this, getString(R.string.label_api_failed));
		}
    }

    /**
     * API is disconnected from the server
     */
    public void handleApiDisconnected() {
    	// Service has been disconnected
		handler.post(new Runnable(){
			public void run(){
				Utils.showMessageAndExit(SessionInProgress.this, getString(R.string.label_api_disconnected));
			}
		});
    }

    /**
     * SIP session event listener
     */
    private ISipSessionEventListener sipSessionListener = new ISipSessionEventListener.Stub() {
		// Session is started
		public void handleSessionStarted() {
		}
	
		// Session has been aborted
		public void handleSessionAborted(int reason) {
			handler.post(new Runnable(){
				public void run(){
					Utils.showMessageAndExit(SessionInProgress.this, getString(R.string.label_session_aborted));
				}
			});
		}
	    
		// Session has been terminated by remote
		public void handleSessionTerminatedByRemote() {
			handler.post(new Runnable(){
				public void run(){
					Utils.showMessageAndExit(SessionInProgress.this, getString(R.string.label_session_terminated_by_remote));
				}
			});
		}
	
		// Session error
		public void handleSessionError(final int error) {
			handler.post(new Runnable(){
				public void run(){
					Utils.showMessageAndExit(SessionInProgress.this, getString(R.string.label_session_failed));
				}
			});
		}
    };
    
    /**
	 * Close button callback
	 */
	private OnClickListener btnCloseListener = new OnClickListener() {
		public void onClick(View v) {
			if (sipSession != null) {
				AlertDialog.Builder builder = new AlertDialog.Builder(SessionInProgress.this);
				builder.setTitle(getString(R.string.label_confirm_close));
				builder.setPositiveButton(getString(R.string.label_ok), new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
		            	// Quit the session
		            	quitSession();
					}
				});
				builder.setNegativeButton(getString(R.string.label_cancel), null);
				builder.setCancelable(true);
				builder.show();
			} else {
            	// Exit activity
				finish();
			}
		}
	};
    
	/**
     * Quit the session
     */
    private void quitSession() {
		// Stop session
        Thread thread = new Thread() {
        	public void run() {
            	try {
                    if (sipSession != null) {
                    	try {
                    		sipSession.removeSessionListener(sipSessionListener);
                    		sipSession.cancelSession();
                    	} catch(Exception e) {
                    	}
                    	sipSession = null;
                    }
            	} catch(Exception e) {
            	}
        	}
        };
        thread.start();
    	
        // Exit activity
		finish();
    }    

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
    			if (sipSession != null) {
    				AlertDialog.Builder builder = new AlertDialog.Builder(this);
    				builder.setTitle(getString(R.string.label_confirm_close));
    				builder.setPositiveButton(getString(R.string.label_ok), new DialogInterface.OnClickListener() {
    					public void onClick(DialogInterface dialog, int which) {
    		            	// Quit the session
    		            	quitSession();
    					}
    				});
    				builder.setNegativeButton(getString(R.string.label_cancel), new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// Exit activity
    		                finish();
						}
					});
    				builder.setCancelable(true);
    				builder.show();
    			} else {
                	// Exit activity
    				finish();
    			}
                return true;
        }

        return super.onKeyDown(keyCode, event);
    }    
}