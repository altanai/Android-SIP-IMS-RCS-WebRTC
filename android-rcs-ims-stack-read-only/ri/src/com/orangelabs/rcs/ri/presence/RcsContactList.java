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

import java.util.List;

import android.app.ListActivity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;

import com.orangelabs.rcs.ri.R;
import com.orangelabs.rcs.ri.utils.Utils;
import com.orangelabs.rcs.service.api.client.ClientApiException;
import com.orangelabs.rcs.service.api.client.ClientApiListener;
import com.orangelabs.rcs.service.api.client.presence.PresenceApi;

/**
 * RCS contacts list
 * 
 * @author jexa7410
 */
public abstract class RcsContactList extends ListActivity implements ClientApiListener {
	/**
	 * UI handler
	 */
	private final Handler handler = new Handler();

	/**
	 * Presence API
	 */
    private PresenceApi presenceApi;
    
    /**
     * Adapter of the contact list
     */
    private ArrayAdapter<String> mAdapter;
    
    /**
     * State of the adapter to manage empty result of XDM request
     */
    private boolean isLoading = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                     
        // Set layout
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.presence_lists);
        
        mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1){
        	public boolean isEmpty() {
        		if(isLoading){
        			return false;
        		}
        		return super.isEmpty();
        	};
        };
        setListAdapter(mAdapter);
        // Instanciate presence API
        presenceApi = new PresenceApi(getApplicationContext());
        presenceApi.addApiEventListener(this);
        presenceApi.connectApi();
    }

    @Override
    public void onDestroy() {
    	super.onDestroy();

        // Disconnect presence API
    	presenceApi.disconnectApi();        
    }

    /**
     * Get presence API
     * 
     * @return API
     */
    public PresenceApi getPresenceApi() {
    	return presenceApi;
    }
    
    /**
     * Get the RCS list
     * 
     * @return List
     * @throws ClientApiException
     */
    public abstract List<String> getRcsContactList() throws ClientApiException;
    	
    /**
     * API disabled
     */
    public void handleApiDisabled() {
		handler.post(new Runnable() { 
			public void run() {
				Utils.showMessageAndExit(RcsContactList.this, getString(R.string.label_api_disabled));
			}
		});
    }
    
    /**
     * API connected
     */
    public void handleApiConnected() {
    	isLoading = true;
		Thread t = new Thread() {
        	public void run() {
				try {
			        // Query XDM server to get the list of contacts
        			final List<String> list = getRcsContactList();
        			// Display result
        			handler.post(new Runnable() { 
            			public void run() {
            				for(String s : list)
            					mAdapter.add(s);
		    		        getListView().setTextFilterEnabled(true);
		    		        isLoading = false;
		        			mAdapter.notifyDataSetChanged();
            			}
                	});
        		} catch(Exception e) {
            		handler.post(new Runnable() { 
            			public void run() {
            				Utils.showMessageAndExit(RcsContactList.this, getString(R.string.label_contact_list_failed));
            			}
                	});
        		}
	    	}
	    };
	    t.start();
    }

    /**
     * API disconnected
     */
    public void handleApiDisconnected() {
		handler.post(new Runnable(){
			public void run(){
				Utils.showMessage(RcsContactList.this, getString(R.string.label_api_disconnected));
			}
		});
    }
}
