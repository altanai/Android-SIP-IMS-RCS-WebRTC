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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.orangelabs.rcs.ri.R;
import com.orangelabs.rcs.service.api.client.eventslog.EventsLogApi;

/**
 * Event log
 */
public class SpamBox extends Activity{
	
	/**
	 * Spam box Adapter based on a ResourceCursorAdapter which gets its data from 
	 * the EventLogProvider.
	 * 
	 * The EventLogProvider is a virtual provider that aggregates RichMessagingProvider,
	 * RichCallProvider, Android CallLogProvider, Android SMS Provider and Android MMS Provider. 
	 */
	private SpamBoxResourceCursorAdapter resourceCursorAdapter;
	
	/**
	 * Events log API
	 */
	private EventsLogApi eventsLogApi;
	
	/**
	 * Delete spam menu id
	 */
	private final static int MENU_ITEM_DELETE_SPAM = 0;

	/**
	 * Mark as non-spam menu id
	 */
	private final static int MENU_ITEM_MARK_NO_SPAM = 1;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Set layout
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.messaging_spambox);
        
        // Set title
        setTitle(R.string.menu_spambox);
        
        resourceCursorAdapter = new SpamBoxResourceCursorAdapter(this);
        ListView view = (ListView)findViewById(android.R.id.list);
        TextView emptyView = (TextView)findViewById(android.R.id.empty);
        view.setEmptyView(emptyView);
        view.setAdapter(resourceCursorAdapter);
        view.setOnCreateContextMenuListener(this);
        
        // Instanciate events log API
        eventsLogApi = new EventsLogApi(this);
        
        startQuery();
	}
	
	private void startQuery(){
		try{
			Cursor result = getContentResolver().query(
					eventsLogApi.getSpamBoxLogContentProviderUri(), 
					null, null, null, null);
			resourceCursorAdapter.changeCursor(result);
		}catch(IllegalArgumentException e){
		}
	}
	
	private void startDelete(){
		eventsLogApi.deleteAllSpams();
	}
		
	private void confirmDeleteDialog(OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.title_eventlog_confirm_dialog_delete_title);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setCancelable(true);
        builder.setMessage(R.string.label_eventlog_confirm_delete_message);
        builder.setPositiveButton(R.string.label_delete, listener);
        builder.setNegativeButton(R.string.label_cancel, null);
        builder.show();
    }
	
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater=new MenuInflater(getApplicationContext());
		inflater.inflate(R.menu.menu_spambox, menu);
		return true;
	}
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_clear_log:
				OnClickListener l = new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						startDelete();
					}
				};
				confirmDeleteDialog(l);
				break;
		}
		return true;
	}
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
        // Delete spam message
        menu.add(0, MENU_ITEM_DELETE_SPAM, 0, R.string.menu_delete_spam);

        // Mark as no-spam
        menu.add(0, MENU_ITEM_MARK_NO_SPAM, 0, R.string.menu_unspam);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info;
        try {
             info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        } catch (ClassCastException e) {
            return false;
        }

        Cursor cursor = (Cursor) resourceCursorAdapter.getItem(info.position);
        
        if (cursor == null) {
            // For some reason the requested item isn't available, do nothing
            return false;
        }
        
        switch (item.getItemId()) {
            case MENU_ITEM_DELETE_SPAM: {
                // Delete the selected spam
            	long rowId = cursor.getLong(EventsLogApi.ID_COLUMN);
            	eventsLogApi.deleteImEntry(rowId);
                return true;
            }

            case MENU_ITEM_MARK_NO_SPAM: {
            	// Unspam the message
            	String msgId = cursor.getString(EventsLogApi.MESSAGE_ID_COLUMN);
            	eventsLogApi.markChatMessageAsSpam(msgId, false);
                return true;
            }
        }

        return super.onContextItemSelected(item);
    }
}
