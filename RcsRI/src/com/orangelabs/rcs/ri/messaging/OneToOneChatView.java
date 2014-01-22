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

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.Toast;

import com.orangelabs.rcs.provider.messaging.RichMessagingData;
import com.orangelabs.rcs.provider.settings.RcsSettings;
import com.orangelabs.rcs.ri.R;
import com.orangelabs.rcs.ri.utils.Utils;
import com.orangelabs.rcs.service.api.client.eventslog.EventsLogApi;
import com.orangelabs.rcs.service.api.client.messaging.IChatSession;

/**
 * 1-1 chat view
 */
public class OneToOneChatView extends ChatView {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Set title
		setTitle(getString(R.string.title_chat_view_oneone) + " " +	getIntent().getStringExtra("contact"));	

        // Set the message composer max length
		InputFilter[] filterArray = new InputFilter[1];
		filterArray[0] = new InputFilter.LengthFilter(RcsSettings.getInstance().getMaxChatMessageLength());
		composeText.setFilters(filterArray);
    }

    /**
     * Init session
     */
    public void initSession() {
    	// Nothing to do
    }    

    /**
     * Load history
     * 
     * @param session Chat session
     */
    public void loadHistory(IChatSession session) {
    	try {
	    	EventsLogApi log = new EventsLogApi(this);
	    	Uri uri = log.getOneToOneChatLogContentProviderUri();
	    	Cursor cursor = getContentResolver().query(uri, 
	    			new String[] {
	    				RichMessagingData.KEY_CONTACT,
	    				RichMessagingData.KEY_DATA,
	    				RichMessagingData.KEY_TIMESTAMP,
	    				RichMessagingData.KEY_STATUS,
	    				RichMessagingData.KEY_TYPE
	    				},
	    			RichMessagingData.KEY_CHAT_SESSION_ID + "='" + session.getSessionID() + "'", 
	    			null, 
	    			RichMessagingData.KEY_TIMESTAMP + " DESC");
	    	
	    	// The system message are not loaded
	    	while(cursor.moveToNext()) {
				int messageMessageType = cursor.getInt(EventsLogApi.TYPE_COLUMN);
				switch (messageMessageType) {
					case EventsLogApi.TYPE_OUTGOING_CHAT_MESSAGE:
					case EventsLogApi.TYPE_INCOMING_CHAT_MESSAGE:
					case EventsLogApi.TYPE_OUTGOING_GEOLOC:
					case EventsLogApi.TYPE_INCOMING_GEOLOC:
						updateView(cursor);
						break;
				}
	    	}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }    
    
    /***
     * Send message
     * 
     * @param msg Message
     */
    public void sendMessage(final String msg) {
        // Test if the session has been created or not
		if (chatSession == null) {
			// Initiate the chat session in background
	        Thread thread = new Thread() {
	        	public void run() {
	            	try {
            			chatSession = messagingApi.initiateOne2OneChatSession(participants.get(0), msg);
	            		chatSession.addSessionListener(chatSessionListener);
	            	} catch(Exception e) {
	            		handler.post(new Runnable(){
	            			public void run(){
	            				Utils.showMessageAndExit(OneToOneChatView.this, getString(R.string.label_invitation_failed));		
	            			}
	            		});
	            	}
	        	}
	        };
	        thread.start();

	        // Display a progress dialog
	        progressDialog = Utils.showProgressDialog(OneToOneChatView.this, getString(R.string.label_command_in_progress));
	        progressDialog.setOnCancelListener(new OnCancelListener() {
				public void onCancel(DialogInterface dialog) {
					Toast.makeText(OneToOneChatView.this, getString(R.string.label_chat_initiation_canceled), Toast.LENGTH_SHORT).show();
					quitSession();
				}
			});
        } else {
    		// Send message
        	super.sendMessage(msg);
        }    	
    }
}
