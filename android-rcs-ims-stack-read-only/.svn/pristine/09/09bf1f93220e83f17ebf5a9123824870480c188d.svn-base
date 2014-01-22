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

import java.util.List;
import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.orangelabs.rcs.provider.messaging.RichMessaging;
import com.orangelabs.rcs.provider.messaging.RichMessagingData;
import com.orangelabs.rcs.ri.R;
import com.orangelabs.rcs.ri.utils.Utils;
import com.orangelabs.rcs.service.api.client.ClientApiListener;
import com.orangelabs.rcs.service.api.client.eventslog.EventsLogApi;
import com.orangelabs.rcs.service.api.client.messaging.IChatSession;
import com.orangelabs.rcs.service.api.client.messaging.MessagingApi;

/**
 * List of current chat sessions and blocked contacts
 */
public class ChatList extends Activity implements ClientApiListener {
	/**
	 * Messaging API
	 */
	private MessagingApi messagingApi;
    
	/**
	 * Rejoin chat manager
	 */
	private RejoinChat rejoinChat = null;

	/**
	 * Restart chat manager
	 */
	private RestartChat restartChat = null;
	
	/**
	 * API connection state
	 */
	private boolean apiEnabled = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Set layout
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.messaging_chat_list);

		// Set UI title
        setTitle(getString(R.string.menu_chat_list));

        // Instantiate messaging API
        RichMessaging.createInstance(getApplicationContext());
		messagingApi = new MessagingApi(getApplicationContext());
		messagingApi.addApiEventListener(this);
		messagingApi.connectApi();
        
        // Set list adapter
        ListView view = (ListView)findViewById(android.R.id.list);
        TextView emptyView = (TextView)findViewById(android.R.id.empty);
        view.setEmptyView(emptyView);
        view.setAdapter(createChatListAdapter());		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		if (rejoinChat != null) {
			rejoinChat.stop();
		}
		
		if (restartChat != null) {
			restartChat.stop();
		}

		if (messagingApi != null) {
			messagingApi.removeApiEventListener(this);
			messagingApi.disconnectApi();
		}
	}
		
	/**
	 * Create chat list adapter with unique chat ID entries
	 */
	private ChatListAdapter createChatListAdapter() {
		Uri uri = RichMessagingData.CONTENT_URI;
	    String[] PROJECTION = new String[] {
	    		RichMessagingData.KEY_ID,
	    		RichMessagingData.KEY_CHAT_ID,
	    		RichMessagingData.KEY_CHAT_SESSION_ID,
	    		RichMessagingData.KEY_TYPE,
	    		RichMessagingData.KEY_CONTACT,
	    		RichMessagingData.KEY_DATA,
	    		RichMessagingData.KEY_TIMESTAMP
	    };
        String sortOrder = RichMessagingData.KEY_TIMESTAMP + " DESC ";
		String where = "(" + RichMessagingData.KEY_TYPE + "=" + EventsLogApi.TYPE_GROUP_CHAT_SYSTEM_MESSAGE + " OR " + RichMessagingData.KEY_TYPE + "=" + EventsLogApi.TYPE_CHAT_SYSTEM_MESSAGE + ") AND (" +
			RichMessagingData.KEY_STATUS + "=" + EventsLogApi.EVENT_INITIATED + " OR " + RichMessagingData.KEY_STATUS + "=" + EventsLogApi.EVENT_INVITED + ")";
		Cursor cursor = getContentResolver().query(uri, PROJECTION, where, null, sortOrder);

		Vector<String> items = new Vector<String>();
		MatrixCursor matrix = new MatrixCursor(PROJECTION);
		while (cursor.moveToNext()){
    		String chatId = cursor.getString(1);
			if (!items.contains(chatId)) {
				matrix.addRow(new Object[]{
						cursor.getInt(0), 
						cursor.getString(1), 
						cursor.getString(2),
						cursor.getInt(3),
						cursor.getString(4),
						cursor.getString(5),
						cursor.getString(6)});
				items.add(chatId);
			}
		}
		cursor.close();

		return new ChatListAdapter(this, matrix);
	}
	
    /**
     * Chat list adapter
     */
    private class ChatListAdapter extends CursorAdapter {
    	private Drawable mDrawableChat;

    	/**
    	 * Constructor
    	 * 
    	 * @param context Context
    	 * @param c Cursor
    	 */
    	public ChatListAdapter(Context context, Cursor c) {
            super(context, c);

    		// Load the drawables
    		mDrawableChat = context.getResources().getDrawable(R.drawable.ri_eventlog_chat);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.messaging_chat_list_item, parent, false);
            view.setOnClickListener(clickItemListener);
            
    		ChatListItemCache cache = new ChatListItemCache();
    		cache.chatId = cursor.getString(1);
    		cache.sessionId = cursor.getString(2);
    		cache.type = cursor.getInt(3);
    		cache.contact = cursor.getString(4);
    		cache.data = cursor.getString(5);
    		cache.date = cursor.getLong(6);
            view.setTag(cache);
            
            return view;
        }
        
    	@Override
    	public void bindView(View view, Context context, Cursor cursor) {
    		TextView line1View = (TextView)view.findViewById(R.id.line1); 
    		TextView numberView = (TextView)view.findViewById(R.id.number);
    		TextView dateView = (TextView)view.findViewById(R.id.date);
    		ImageView eventIconView = (ImageView)view.findViewById(R.id.call_icon);            
    		ChatListItemCache cache = (ChatListItemCache)view.getTag();

    		// Set icon
			eventIconView.setImageDrawable(mDrawableChat);

			// Set the date/time field by mixing relative and absolute times
		
    		dateView.setText(DateUtils.getRelativeTimeSpanString(cache.date,
    				System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS,
    				DateUtils.FORMAT_ABBREV_RELATIVE));
    		
			// Set the label
    		switch(cache.type) {
    			case EventsLogApi.TYPE_CHAT_SYSTEM_MESSAGE:
    			case EventsLogApi.TYPE_OUTGOING_CHAT_MESSAGE:
	    		case EventsLogApi.TYPE_INCOMING_CHAT_MESSAGE:
	    			line1View.setText(R.string.label_eventlog_chat);
	        		numberView.setText(cache.contact);
	        		numberView.setVisibility(View.VISIBLE);
	    			break;
				case EventsLogApi.TYPE_GROUP_CHAT_SYSTEM_MESSAGE:
	    		case EventsLogApi.TYPE_INCOMING_GROUP_CHAT_MESSAGE:
	    		case EventsLogApi.TYPE_OUTGOING_GROUP_CHAT_MESSAGE:
	    			line1View.setText(R.string.label_eventlog_group_chat);
	        		numberView.setText(cache.data);
	        		numberView.setVisibility(View.VISIBLE);
	    			break;
    		}
    	}
    }

    /**
     * Chat list item in cache
     */
	private class ChatListItemCache {
		private String chatId;
		private String sessionId;
		private int type;
		private String contact;
		private long date;
		private String data;
		
		public boolean isGroupChat() {
			return (type == EventsLogApi.TYPE_GROUP_CHAT_SYSTEM_MESSAGE) ||
				(type == EventsLogApi.TYPE_INCOMING_GROUP_CHAT_MESSAGE) ||
					(type == EventsLogApi.TYPE_OUTGOING_GROUP_CHAT_MESSAGE);
		}
	}    
    
    /**
     * API disabled
     */
	public void handleApiDisabled() {
		apiEnabled = false;
	}

    /**
     * API connected
     */
	public void handleApiConnected() {
		apiEnabled = true;
	}

    /**
     * API disconnected
     */
	public void handleApiDisconnected() {
		apiEnabled = false;
	}
    
    /**
     * Is group chat active
     * 
     * @param chatId Chat ID
     * @return Boolean
     */
    private IChatSession isGroupChatActive(String chatId) {
		try {
			List<IBinder> chatSessionsBinder = messagingApi.getGroupChatSessions();
			for (IBinder binder : chatSessionsBinder) {
				IChatSession chatSession = IChatSession.Stub.asInterface(binder);
				if (chatSession.getChatID().equals(chatId)) {
					return chatSession;
				}	
			}
			return null;
		} catch(Exception e) {
			return null;
		}
    }
    
    /**
     * Is chat session active
     * 
     * @param sessionId Session ID
     * @return Boolean
     */
    private IChatSession isChatSessionActive(String sessionId) {
		try {
			return messagingApi.getChatSession(sessionId);
		} catch(Exception e) {
			return null;
		}
    }

    /**
     * Is RCS service available
     * 
     * @return Boolean
     */
    private boolean isServiceAvailable() {
    	boolean result = false;
    	try {
			if (apiEnabled && messagingApi.isImsConnected(getApplicationContext())) {
				result = true;
			}
    	} catch(Exception e) {
        	result = false;
    	}
    	return result;
    }
    
    /**
     * Onclick list listener
     */
    private OnClickListener clickItemListener = new OnClickListener() {
		public void onClick(View v) {
			if (!isServiceAvailable()) {
				Utils.showMessage(ChatList.this, getString(R.string.label_continue_chat_failed));
				return;
			}
			
			// Get selected item
			ChatListItemCache cache = (ChatListItemCache)v.getTag();
			if (cache.isGroupChat()) {
				// Group chat
				IChatSession session = isGroupChatActive(cache.chatId);
				if (session != null) {
					// Session already active on the device: just reload it in the UI
					try {
						Intent intent = new Intent(ChatList.this, GroupChatView.class);
			        	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		            	intent.putExtra("subject", session.getSubject());
			    		intent.putExtra("sessionId", session.getSessionID());
			    		startActivity(intent);				
					} catch(Exception e) {
						Utils.showMessage(ChatList.this, getString(R.string.label_api_failed));
					}
				} else {
					// Test if the session may be rejoined or not
					int status = RichMessaging.getInstance().getGroupChatStatus(cache.chatId);
					if (status == EventsLogApi.STATUS_TERMINATED_BY_USER) {
						// The session was terminated by user itself: rejoin or restart are not authorized
						Utils.showMessage(ChatList.this, getString(R.string.label_rejoin_unauthorized));
					} else
					if (status == EventsLogApi.STATUS_TERMINATED_BY_REMOTE) {
						// The session was terminated: only a restart may be done
						restartChat = new RestartChat(ChatList.this, messagingApi, cache.chatId);
						restartChat.start();
					} else {					
						// Session terminated on the device: try to rejoin the session
						rejoinChat = new RejoinChat(ChatList.this, messagingApi, cache.chatId);
						rejoinChat.start();
					}
				}
			} else {
				// 1-1 chat
				IChatSession session = isChatSessionActive(cache.sessionId);
				if (session != null) {
					// Session already active on the device: just reload it in the UI
					try {
			    		Intent intent = new Intent(ChatList.this, OneToOneChatView.class);
			        	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		            	intent.putExtra("contact", session.getRemoteContact());
			    		intent.putExtra("sessionId", session.getSessionID());
			    		startActivity(intent);
					} catch(Exception e) {
						Utils.showMessage(ChatList.this, getString(R.string.label_api_failed));
					}
				} else {
					// Session terminated on the device: create a new one on the first message
		    		Intent intent = new Intent(ChatList.this, OneToOneChatView.class);
		        	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            	intent.putExtra("contact", cache.contact);
		    		startActivity(intent);
				}
			}
		}
    };
}