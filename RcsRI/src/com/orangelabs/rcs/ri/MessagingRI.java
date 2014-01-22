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

package com.orangelabs.rcs.ri;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.orangelabs.rcs.ri.messaging.BlockContact;
import com.orangelabs.rcs.ri.messaging.ChatList;
import com.orangelabs.rcs.ri.messaging.InitiateChat;
import com.orangelabs.rcs.ri.messaging.InitiateFileTransfer;
import com.orangelabs.rcs.ri.messaging.InitiateGroupChat;
import com.orangelabs.rcs.ri.messaging.ShowUsInMap;
import com.orangelabs.rcs.ri.messaging.SpamBox;
import com.orangelabs.rcs.ri.utils.Utils;
import com.orangelabs.rcs.service.api.client.contacts.ContactsApi;


/**
 * Messaging RI
 * 
 * @author jexa7410
 */
public class MessagingRI extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Set layout
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Set items
        String[] items = {
    		getString(R.string.menu_sms_mms),
    		getString(R.string.menu_transfer_file),
    		getString(R.string.menu_one_to_one_chat),
    		getString(R.string.menu_adhoc_group_chat),
    		getString(R.string.menu_chat_list),
    		getString(R.string.menu_block_contact),
    		getString(R.string.menu_spambox),
    		getString(R.string.menu_showus_map)
        };
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch(position) {
	        case 0:
	        	try {
		            Intent intent = new Intent(Intent.ACTION_VIEW);
		            intent.setType("vnd.android-dir/mms-sms");
		            startActivity(intent);
		    	} catch(ActivityNotFoundException e) {
		    		Utils.showMessage(this, getString(R.string.label_ab_not_found));
		    	}
                break;
                
	        case 1:
            	startActivity(new Intent(this, InitiateFileTransfer.class));
	            break;
	            
	        case 2:
            	startActivity(new Intent(this, InitiateChat.class));
	            break;
	            
	        case 3:
            	startActivity(new Intent(this, InitiateGroupChat.class));
	            break;
	            
	        case 4: 
	        	startActivity(new Intent(this, ChatList.class));
	            break;
	            
	        case 5:
	        	startActivity(new Intent(this, BlockContact.class));
	        	break;

	        case 6:
	        	startActivity(new Intent(this, SpamBox.class));
	        	break;

	        case 7:
	    		ContactsApi contactApi = new  ContactsApi(this);
	    		List<String> contacts = contactApi.getRcsContacts();
	        	Intent intent = new Intent(this, ShowUsInMap.class);
	        	ArrayList<String> list = new ArrayList<String>(contacts); 
	        	intent.putStringArrayListExtra("contacts", list);
	        	startActivity(intent);
	        	break;
        }
    }
}
