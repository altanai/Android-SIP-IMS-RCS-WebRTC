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
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

import com.orangelabs.rcs.service.api.client.ClientApiListener;
import com.orangelabs.rcs.service.api.client.sip.ISipSession;
import com.orangelabs.rcs.service.api.client.sip.ISipSessionEventListener;
import com.orangelabs.rcs.service.api.client.sip.SipApi;
import com.orangelabs.rcs.sip.utils.Utils;

/**
 * Terminating side
 * 
 * @author jexa7410
 */
public class TerminatingSide extends Activity implements ClientApiListener {
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
     * Remote contact
     */
    private String remoteContact;
    
    /**
	 * SIP session
	 */
	private ISipSession sipSession = null;
	
	/**
	 * SDP
	 */
	private String sdp;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Set layout
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Set title
        setTitle(R.string.title_sip_terminating);

        // Get the session ID
		sessionId = getIntent().getStringExtra("sessionId");

		// Get the remote contact
		remoteContact = getIntent().getStringExtra("contact");
		
		// Get local SDP
		sdp = SessionSettings.getLocalSdp(this);
		
		// Connect to API
        sipApi = new SipApi(getApplicationContext());
        sipApi.addApiEventListener(this);
        sipApi.connectApi();
    }
    
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		// Disconnect from API
		sipApi.removeApiEventListener(this);
		sipApi.disconnectApi();
	}
    
    /**
     * Add invitation notification
     * 
     * @param context Context
     * @param invitation Intent invitation
     */
    public static void addInvitationNotification(Context context, Intent invitation) {
    	// Create notification
		Intent intent = new Intent(invitation);
		intent.setClass(context, TerminatingSide.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notif = new Notification(R.drawable.app_icon,
        		context.getString(R.string.title_recv_session_invitation),
        		System.currentTimeMillis());
        notif.flags = Notification.FLAG_AUTO_CANCEL;
        notif.setLatestEventInfo(context,
        		context.getString(R.string.title_recv_session_invitation),
        		context.getString(R.string.label_from) + " " + Utils.formatCallerId(invitation),
        		contentIntent);

        // Set vibration
    	notif.defaults |= Notification.DEFAULT_VIBRATE;
        
        // Send notification
		String sessionId = invitation.getStringExtra("sessionId");
		NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify((int)Long.parseLong(sessionId), notif);
    }
    
	/**
     * Remove invitation notification
     * 
     * @param context Context
     * @param sessionId Session ID
     */
    public static void removeInvitationNotification(Context context, String sessionId) {
		NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.cancel((int)Long.parseLong(sessionId));
    }
    
    /**
     * API is disabled (e.g. server not started)
     */
    public void handleApiDisabled() {
		handler.post(new Runnable() { 
			public void run() {
				Utils.showMessageAndExit(TerminatingSide.this, getString(R.string.label_api_disabled));
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
				Utils.showMessageAndExit(TerminatingSide.this, getString(R.string.label_session_has_expired));
				return;
			}
			sipSession.addSessionListener(sipSessionListener);
			
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.title_recv_session_invitation);
			StringBuffer buffer = new StringBuffer();
			buffer.append(getString(R.string.label_from) + remoteContact);
			buffer.append("\n");
			buffer.append(getString(R.string.label_feature_tag) + sipSession.getFeatureTag());
			builder.setMessage(buffer.toString());
			builder.setCancelable(false);
			builder.setIcon(R.drawable.app_icon);
			builder.setPositiveButton(getString(R.string.label_accept),	acceptBtnListener);
			builder.setNegativeButton(getString(R.string.label_decline), declineBtnListener);
			builder.show();		
		} catch(Exception e) {
			Utils.showMessageAndExit(TerminatingSide.this, getString(R.string.label_api_failed));
		}
    }

    /**
     * API is disconnected from the server
     */
    public void handleApiDisconnected() {
    	// Service has been disconnected
		handler.post(new Runnable(){
			public void run(){
				Utils.showMessageAndExit(TerminatingSide.this, getString(R.string.label_api_disconnected));
			}
		});
    }
    
    /**
     * Accept button listener
     */
    private DialogInterface.OnClickListener acceptBtnListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
	    	Thread thread = new Thread() {
            	public void run() {
                	try {
                		// Accept the invitation
            			sipSession.acceptSession(sdp);
	            	} catch(Exception e) {
	        			handler.post(new Runnable() { 
	        				public void run() {
	        					Utils.showMessageAndExit(TerminatingSide.this, getString(R.string.label_invitation_failed));
							}
		    			});
	            	}
            	}
            };
            thread.start();
        }
    };

    /**
     * Reject button listener
     */    
    private DialogInterface.OnClickListener declineBtnListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            Thread thread = new Thread() {
            	public void run() {
                	try {
                		// Reject the invitation
            			sipSession.rejectSession();
	            	} catch(Exception e) {
	            	}
            	}
            };
            thread.start();

            // Exit activity
			finish();
        }
    };

    /**
     * SIP session event listener
     */
    private ISipSessionEventListener sipSessionListener = new ISipSessionEventListener.Stub() {
		// Session is started
		public void handleSessionStarted() {
			try {
				// Display session
				Intent intent = new Intent(TerminatingSide.this, SessionInProgress.class);
				intent.putExtra("sessionId", sipSession.getSessionID());
				startActivity(intent);
				
	        	// Exit activity
	        	finish();     
			} catch(Exception e) {
				handler.post(new Runnable(){
					public void run(){
						Utils.showMessageAndExit(TerminatingSide.this, getString(R.string.label_invitation_failed));
					}
				});
			}
		}
	
		// Session has been aborted
		public void handleSessionAborted(int reason) {
			handler.post(new Runnable(){
				public void run(){
					Utils.showMessageAndExit(TerminatingSide.this, getString(R.string.label_session_aborted));
				}
			});
		}
	    
		// Session has been terminated by remote
		public void handleSessionTerminatedByRemote() {
			handler.post(new Runnable(){
				public void run(){
					Utils.showMessageAndExit(TerminatingSide.this, getString(R.string.label_session_terminated_by_remote));
				}
			});
		}
	
		// Session error
		public void handleSessionError(final int error) {
			handler.post(new Runnable(){
				public void run(){
					Utils.showMessageAndExit(TerminatingSide.this, getString(R.string.label_session_failed));
				}
			});
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
    				builder.setPositiveButton(getString(R.string.label_ok),
    						new DialogInterface.OnClickListener() {
    							public void onClick(DialogInterface dialog,
    									int which) {
    								// Quit the session
    								quitSession();
    							}
    						});
    				builder.setNegativeButton(getString(R.string.label_cancel),
    						new DialogInterface.OnClickListener() {
    							public void onClick(DialogInterface dialog,
    									int which) {
    								// Exit activity
    								finish();
    							}
    						});
    				builder.setCancelable(true);
    				builder.show();
    			}else {
    				finish();
    			}
                return true;
        }

        return super.onKeyDown(keyCode, event);
    }    
}