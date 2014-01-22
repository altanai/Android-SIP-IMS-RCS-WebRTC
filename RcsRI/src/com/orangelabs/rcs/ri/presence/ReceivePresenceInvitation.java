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

package com.orangelabs.rcs.ri.presence;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.orangelabs.rcs.provider.settings.RcsSettings;
import com.orangelabs.rcs.ri.R;
import com.orangelabs.rcs.ri.utils.Utils;
import com.orangelabs.rcs.service.api.client.presence.PresenceApi;

/**
 * Receive presence invitation
 */
public class ReceivePresenceInvitation extends Activity {
    /**
     * UI handler
     */
    private Handler handler = new Handler();
    
	/**
	 * Presence API
	 */
    private PresenceApi presenceApi;
    
	/**
	 * Notification ID
	 */
	private final static int PRESENCE_INVITATION_NOTIFICATION = 1002;
    
    /**
     * Remote contact
     */
    private String contact;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		// Get invitation info
		contact = getIntent().getStringExtra("contact");
              
		// Instanciate presence API
		presenceApi = new PresenceApi(getApplicationContext());
		presenceApi.connectApi();

		// Display 
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.title_presence_invitation);
		builder.setMessage(getString(R.string.label_from) + " " + contact);
		builder.setCancelable(false);
		builder.setIcon(R.drawable.ri_notif_presence_icon);
		builder.setPositiveButton(getString(R.string.label_accept), acceptBtnListener);
		builder.setNeutralButton(getString(R.string.label_decline), declineBtnListener);
		builder.setNegativeButton(getString(R.string.label_ignore), ignoreBtnListener);
		builder.show();
    }

    @Override
    public void onDestroy() {
    	super.onDestroy();
    	
		// Disconnect presence API
    	presenceApi.disconnectApi();
    }
    
    /**
     * Accept button listener
     */
    private OnClickListener acceptBtnListener = new OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			Thread thread = new Thread() {
            	public void run() {
                	try {
                		// Remove notification
                		ReceivePresenceInvitation.removeSharingInvitationNotification(ReceivePresenceInvitation.this);

                    	// Accept the invitation
                		presenceApi.acceptSharingInvitation(contact);
	            	} catch(Exception e) {
	        			handler.post(new Runnable() { 
	            			public void run() {
	            				Utils.showMessageAndExit(ReceivePresenceInvitation.this, getString(R.string.label_invitation_failed));
	            			}
	        			});
	            	}

	            	// Exit activity
        			handler.post(new Runnable() { 
            			public void run() {
            				finish();
            			}
        			});
            	}
            };
            thread.start();
		}
    };

    /**
     * Reject button listener
     */
    private OnClickListener declineBtnListener = new OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            Thread thread = new Thread() {
            	public void run() {
                	try {
                		// Remove notification
                		ReceivePresenceInvitation.removeSharingInvitationNotification(ReceivePresenceInvitation.this);

                		// Decline the invitation
                		presenceApi.rejectSharingInvitation(contact);
	            	} catch(Exception e) {
	        			handler.post(new Runnable() { 
	            			public void run() {
	            				Utils.showMessageAndExit(ReceivePresenceInvitation.this, getString(R.string.label_invitation_failed));
	            			}
	        			});
	            	}

	            	// Exit activity
        			handler.post(new Runnable() { 
            			public void run() {
            				finish();
            			}
        			});
            	}
            };
            thread.start();
        }
    };

    /**
     * Ignore button listener
     */
    private OnClickListener ignoreBtnListener = new OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            Thread thread = new Thread() {
            	public void run() {
                	try {
                		// Ignore the invitation
                		presenceApi.ignoreSharingInvitation(contact);
	            	} catch(Exception e) {
	        			handler.post(new Runnable() { 
	            			public void run() {
	            				Utils.showMessageAndExit(ReceivePresenceInvitation.this, getString(R.string.label_invitation_failed));
	            			}
	        			});
	            	}

	            	// Exit activity
        			handler.post(new Runnable() { 
            			public void run() {
            				finish();
            			}
        			});
            	}
            };
            thread.start();
        }
    };
            
    /**
     * Add presence sharing notification
     * 
     * @param context Context
     * @param invitation Intent invitation
     */
    public static void addSharingInvitationNotification(Context context, Intent invitation) {
        // Initialize settings
		RcsSettings.createInstance(context);
		
    	// Create notification
		Intent intent = new Intent(invitation);
		intent.setClass(context, ReceivePresenceInvitation.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);		
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notif = new Notification(R.drawable.ri_notif_presence_icon,
        		context.getString(R.string.title_presence_invitation),
        		System.currentTimeMillis());
        notif.flags = Notification.FLAG_AUTO_CANCEL;
        notif.setLatestEventInfo(context,
        		context.getString(R.string.title_presence_invitation),
        		context.getString(R.string.label_from) + " " + invitation.getStringExtra("contact"),
        		contentIntent);

        // Set ringtone
        String ringtone = RcsSettings.getInstance().getPresenceInvitationRingtone();
        if (!TextUtils.isEmpty(ringtone)) {
			notif.sound = Uri.parse(ringtone);
        }
        
        // Set vibration
        if (RcsSettings.getInstance().isPhoneVibrateForPresenceInvitation()) {
        	notif.defaults |= Notification.DEFAULT_VIBRATE;
        }
        
        // Send notification
		NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(PRESENCE_INVITATION_NOTIFICATION, notif);
    }

    /**
     * Remove presence sharing notification
     * 
     * @param context Context
     */
    public static void removeSharingInvitationNotification(Context context) {
		NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(PRESENCE_INVITATION_NOTIFICATION);
    }
}

