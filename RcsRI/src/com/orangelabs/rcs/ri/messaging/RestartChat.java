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

package com.orangelabs.rcs.ri.messaging;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import com.orangelabs.rcs.ri.R;
import com.orangelabs.rcs.ri.utils.Utils;
import com.orangelabs.rcs.service.api.client.messaging.GeolocMessage;
import com.orangelabs.rcs.service.api.client.messaging.IChatEventListener;
import com.orangelabs.rcs.service.api.client.messaging.IChatSession;
import com.orangelabs.rcs.service.api.client.messaging.InstantMessage;
import com.orangelabs.rcs.service.api.client.messaging.MessagingApi;

/**
 * Restart a group chat session
 */
public class RestartChat {
    /**
     * UI handler
     */
    private Handler handler = new Handler();

    /**
     * Progress dialog
     */
    private Dialog progressDialog = null;

	/**
     * Activity
     */
    private Activity activity;
    
    /**
	 * Messaging API
	 */
    private MessagingApi messagingApi;
    
    /**
	 * Chat ID to restart
	 */
	private String chatId;

	/**
	 * Restarted chat session
	 */
	private IChatSession chatSession = null; 

	/**
     * Constructor
     * 
     * @param context Context
     * @param messagingApi Messaging API
     * @param chatId Chat ID
     */
	public RestartChat(Activity activity, MessagingApi messagingApi, String chatId) {
		this.activity = activity;
		this.messagingApi = messagingApi;
		this.chatId = chatId;
	}
    
    /**
     * Start restart session
     */
    public synchronized void start() {
    	if (chatSession != null) {
    		return;
    	}
    	
    	// Initiate the session in background
        Thread thread = new Thread() {
        	public void run() {
            	try {
            		chatSession = messagingApi.restartGroupChatSession(chatId);
            		chatSession.addSessionListener(chatSessionListener);
            	} catch(Exception e) {
            		handler.post(new Runnable(){
            			public void run(){
        					// Hide progress dialog
        					hideProgressDialog();

        					// Show error
        					Utils.showMessage(activity, activity.getString(R.string.label_restart_chat_exception));		
            			}
            		});
            	}
        	}
        };
        thread.start();
        
        // Display a progress dialog
        progressDialog = Utils.showProgressDialog(activity, activity.getString(R.string.label_command_in_progress));
        progressDialog.setOnCancelListener(new OnCancelListener() {
			public void onCancel(DialogInterface dialog) {
				// Stop session
				stop();

				// Hide progress dialog
				hideProgressDialog();

				// Display feedback info
				Toast.makeText(activity,activity.getString(R.string.label_chat_restart_canceled), Toast.LENGTH_SHORT).show();
			}
		});
    }    

    /**
     * Stop restart session
     */
    public synchronized void stop() {
    	if (chatSession == null) {
    		return;
    	}

    	// Stop session
        Thread thread = new Thread() {
        	public void run() {
            	try {
                    if (chatSession != null) {
                		chatSession.removeSessionListener(chatSessionListener);
                		chatSession.cancelSession();
                    }
            	} catch(Exception e) {
            	}
            	chatSession = null;
        	}
        };
        thread.start();
    }    
    
    /**
     * Chat session event listener
     */
    private IChatEventListener chatSessionListener = new IChatEventListener.Stub() {
		// Session is started
		public void handleSessionStarted() {
			handler.post(new Runnable() { 
				public void run() {
					try {
	                    // Hide progress dialog
						hideProgressDialog();
	
						// Remove listener now
                		chatSession.removeSessionListener(chatSessionListener);

						// Display chat view
                		Intent intent = new Intent(activity, GroupChatView.class);
			        	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		            	intent.putExtra("subject", chatSession.getSubject());
			    		intent.putExtra("sessionId", chatSession.getSessionID());
			    		activity.startActivity(intent);
					} catch(Exception e) {
						Utils.showMessage(activity, activity.getString(R.string.label_api_failed));
					}
				}
			});
		}
	
		// Chat error
		public void handleImError(final int error) {
			handler.post(new Runnable() {
				public void run() {
					// Hide progress dialog
					hideProgressDialog();

					// Display error
					Utils.showMessage(activity, activity.getString(R.string.label_restart_chat_failed, error));
				}
			});
		}	
		
		// Session has been aborted
		public void handleSessionAborted(int reason) {
			handler.post(new Runnable() {
				public void run() {
					// Hide progress dialog
					hideProgressDialog();
				}
			});
		}
	    
		// Session has been terminated by remote
		public void handleSessionTerminatedByRemote() {
			handler.post(new Runnable() {
				public void run() {
					// Hide progress dialog
					hideProgressDialog();

					// Display error
					Utils.showMessage(activity, activity.getString(R.string.label_restart_chat_terminated_by_remote));
				}
			});
		}

		// New text message received
		public void handleReceiveMessage(InstantMessage msg) {
			// Not used here
		}		

		// Is composing event
		public void handleIsComposingEvent(String contact, boolean isComposing) {
			// Not used here
		}

		// Conference event
	    public void handleConferenceEvent(String contact, String contactDisplayname, String state) {
			// Not used here
		}
	    
		// Message delivery status
		public void handleMessageDeliveryStatus(String msgId, String status) {
			// Not used here
		}
		
		// Request to add participant is successful
		public void handleAddParticipantSuccessful() {
			// Not used here
		}
	    
		// Request to add participant has failed
		public void handleAddParticipantFailed(String reason) {
			// Not yet used here
		}
		
		// New geoloc message received
		public void handleReceiveGeoloc(GeolocMessage geoloc) {
			// Not yet used here
		}
    };

	/**
	 * Hide progress dialog
	 */
    public void hideProgressDialog() {
    	if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
			progressDialog = null;
		}
    }
}
