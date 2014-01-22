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
 * Group chat view
 */
public class GroupChatView extends ChatView {
	/**
	 * Subject
	 */
	private String subject;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get subject
        subject = getIntent().getStringExtra("subject");
        
        // Set title
        if ((subject == null) || (subject.length() == 0)) {
        	setTitle(getString(R.string.title_chat_view_group));
        } else  {
        	setTitle(getString(R.string.title_chat_view_group) + " " + subject);
        }
        
        // Set the message composer max length
		InputFilter[] filterArray = new InputFilter[1];
		filterArray[0] = new InputFilter.LengthFilter(RcsSettings.getInstance().getMaxGroupChatMessageLength());
		composeText.setFilters(filterArray);
    }
    
    /**
     * Init session
     */
    public void initSession() {
		// Initiate the chat session in background
        Thread thread = new Thread() {
        	public void run() {
            	try {
        			chatSession = messagingApi.initiateAdhocGroupChatSession(participants, subject);
            		chatSession.addSessionListener(chatSessionListener);
            	} catch(Exception e) {
            		handler.post(new Runnable(){
            			public void run(){
            				Utils.showMessageAndExit(GroupChatView.this, getString(R.string.label_invitation_failed));		
            			}
            		});
            	}
        	}
        };
        thread.start();

        // Display a progress dialog
        progressDialog = Utils.showProgressDialog(GroupChatView.this, getString(R.string.label_command_in_progress));
        progressDialog.setOnCancelListener(new OnCancelListener() {
			public void onCancel(DialogInterface dialog) {
				Toast.makeText(GroupChatView.this, getString(R.string.label_chat_initiation_canceled), Toast.LENGTH_SHORT).show();
				quitSession();
			}
		});
    }
    
    /**
     * Load history
     * 
     * @param session Chat session
     */
    public void loadHistory(IChatSession session) {
    	try {
	    	EventsLogApi log = new EventsLogApi(this);
	    	Uri uri = log.getGroupChatLogContentProviderUri();
	    	Cursor cursor = getContentResolver().query(uri, 
	    			new String[] {
	    				RichMessagingData.KEY_CONTACT,
	    				RichMessagingData.KEY_DATA,
	    				RichMessagingData.KEY_TIMESTAMP,
	    				RichMessagingData.KEY_STATUS,
	    				RichMessagingData.KEY_TYPE
	    				},
	    			RichMessagingData.KEY_CHAT_ID + "='" + session.getChatID() + "'", 
	    			null, 
	    			RichMessagingData.KEY_TIMESTAMP + " DESC");
	    	
	    	// The system message are not loaded
	    	while(cursor.moveToNext()) {
				int messageMessageType = cursor.getInt(EventsLogApi.TYPE_COLUMN);
				switch (messageMessageType) {
					case EventsLogApi.TYPE_OUTGOING_GROUP_CHAT_MESSAGE:
					case EventsLogApi.TYPE_INCOMING_GROUP_CHAT_MESSAGE:
					case EventsLogApi.TYPE_OUTGOING_GROUP_GEOLOC:
					case EventsLogApi.TYPE_INCOMING_GROUP_GEOLOC:
						updateView(cursor);
						break;
				}
	    	}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}
