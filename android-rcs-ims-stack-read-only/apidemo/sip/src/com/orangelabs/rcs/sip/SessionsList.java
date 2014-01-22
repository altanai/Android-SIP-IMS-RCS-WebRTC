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

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.orangelabs.rcs.service.api.client.ClientApiListener;
import com.orangelabs.rcs.service.api.client.sip.ISipSession;
import com.orangelabs.rcs.service.api.client.sip.SipApi;
import com.orangelabs.rcs.sip.R;
import com.orangelabs.rcs.sip.utils.Utils;

/**
 * Display list of pending sessions
 * 
 * @author jexa7410
 */
public class SessionsList extends ListActivity implements ClientApiListener {
	/**
	 * UI handler
	 */
	private final Handler handler = new Handler();

	/**
	 * SIP API
	 */
	private SipApi sipApi;
	
	/**
	 * List of sessions
	 */
	private List<ISipSession> sipSessions = new ArrayList<ISipSession>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.sessions_list);

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
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		// Display the selected session
		try {
			Intent intent = new Intent(this,SessionInProgress.class);
			String sessionId = sipSessions.get(position).getSessionID();
			intent.putExtra("sessionId", sessionId);
			startActivity(intent);
		} catch (RemoteException e) {
			Utils.showMessageAndExit(SessionsList.this, getString(R.string.label_session_failed));
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.refresh_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.refresh) {
			// Refresh the list of sessions
			updateList();
		}
		return true;
	}
	
	 /**
     * API is disabled (e.g. server not started)
     */
    public void handleApiDisabled() {
		handler.post(new Runnable(){
			public void run(){
		    	Utils.showMessageAndExit(SessionsList.this, getString(R.string.label_api_disabled));
			}
		});				
    }

    /**
     * API is connected to the server
     */
    public void handleApiConnected() {
		handler.post(new Runnable(){
			public void run(){
		    	// Display the list of sessions
				updateList();
			}
		});				
    }
    
    /**
     * Update the displayed list
     */
    private void updateList() {
		try {
	    	// Reset the list
			sipSessions.clear();
	    	
	    	// Get list of pending sessions
	    	List<IBinder> sessions = sipApi.getSessions();
			for (IBinder session : sessions) {
				ISipSession sipSession = ISipSession.Stub.asInterface(session);
				sipSessions.add(sipSession);
			}
			if (sipSessions.size() > 0){
		        String[] items = new String[sipSessions.size()];    
		        for (int i = 0; i < items.length; i++) {
					items[i]=sipSessions.get(i).getSessionID();
		        }
				setListAdapter(new ArrayAdapter<String>(SessionsList.this, android.R.layout.simple_list_item_1, items));
			} else {
				setListAdapter(null);
			}
		} catch (Exception e) {
			Utils.showMessageAndExit(SessionsList.this, getString(R.string.label_session_failed));
		}
    }

    /**
     * API is disconnected from the server
     */
    public void handleApiDisconnected() {
		handler.post(new Runnable(){
			public void run(){
				Utils.showMessageAndExit(SessionsList.this, getString(R.string.label_api_disconnected));
			}
		});				
    }
}