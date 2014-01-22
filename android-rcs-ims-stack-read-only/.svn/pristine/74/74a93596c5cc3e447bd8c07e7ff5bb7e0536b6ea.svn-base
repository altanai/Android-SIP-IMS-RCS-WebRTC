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
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Data;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;

import com.orangelabs.rcs.ri.R;
import com.orangelabs.rcs.ri.utils.Utils;
import com.orangelabs.rcs.service.api.client.presence.PresenceApi;

/**
 * Manage the RCS contacts (invite, block, unblock, revoke, .etc)
 * 
 * @author jexa7410
 */
public class ManageContactList extends Activity {

	/**
	 * Presence API
	 */
    private PresenceApi presenceApi;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Set layout
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.presence_manage_contacts);
        
        // Set title
        setTitle(R.string.menu_manage_contacts);
        
        // Set the contact selector
        Spinner spinner = (Spinner)findViewById(R.id.contact);
        spinner.setAdapter(Utils.createContactListAdapter(this));

        // Set buttons callbacks
        Button inviteBtn = (Button)findViewById(R.id.invite_btn);
        inviteBtn.setOnClickListener(btnInviteListener);
        Button blockBtn = (Button)findViewById(R.id.block_btn);
        blockBtn.setOnClickListener(btnBlockListener);
        Button revokeBtn = (Button)findViewById(R.id.revoke_btn);
        revokeBtn.setOnClickListener(btnRevokeListener);
        Button unblockBtn = (Button)findViewById(R.id.unblock_btn);
        unblockBtn.setOnClickListener(btnUnblockListener);
        Button unrevokeBtn = (Button)findViewById(R.id.unrevoke_btn);
        unrevokeBtn.setOnClickListener(btnUnrevokeListener);
        
        // Disable buttons if no contact available
        if (spinner.getAdapter().getCount() == 0) {
        	inviteBtn.setEnabled(false);
        	blockBtn.setEnabled(false);
        	revokeBtn.setEnabled(false);
        	unblockBtn.setEnabled(false);
        	unrevokeBtn.setEnabled(false);
        }
        
        // Instanciate presence API
        presenceApi = new PresenceApi(getApplicationContext());
        presenceApi.connectApi();
        
        // Select the corresponding contact from the intent
        Intent intent = getIntent();
        Uri contactUri = intent.getData();
    	if (contactUri != null) {
	        Cursor cursor = managedQuery(contactUri, null, null, null, null);
	        if (cursor.moveToNext()) {
	        	String selectedContact = cursor.getString(cursor.getColumnIndex(Data.DATA1));
	            if (selectedContact != null) {
	    	        for (int i=0;i<spinner.getAdapter().getCount();i++) {
	    	        	MatrixCursor cursor2 = (MatrixCursor)spinner.getAdapter().getItem(i);
	    	        	if (selectedContact.equalsIgnoreCase(cursor2.getString(1))) {
	    	        		// Select contact
	    	                spinner.setSelection(i);
	    	                spinner.setEnabled(false);
	    	                
	    	                // Enable only the invite action
	    	            	inviteBtn.setEnabled(true);
	    	            	blockBtn.setEnabled(false);
	    	            	revokeBtn.setEnabled(false);
	    	            	unblockBtn.setEnabled(false);
	    	            	unrevokeBtn.setEnabled(false);
	    	                break;
	    	        	}
	    	        }
	            }
	        }
	        cursor.close();
        }        
    }

    @Override
    public void onDestroy() {
    	super.onDestroy();
    	
        // Disconnect presence API
    	presenceApi.disconnectApi();
    }

    /**
     * Returns the selected contact
     * 
     * @return Contact
     */
    private String getSelectedContact() {
	    Spinner spinner = (Spinner)findViewById(R.id.contact);
	    MatrixCursor cursor = (MatrixCursor)spinner.getSelectedItem();
	    return cursor.getString(1);
    }
    
    /**
     * Invite button listener
     */
    private OnClickListener btnInviteListener = new OnClickListener() {
        public void onClick(View v) {
        	try {


                // Invite the contact
                if (presenceApi.inviteContact(getSelectedContact())) {
                	Utils.displayToast(ManageContactList.this, getString(R.string.label_invite_contact_ok));
        		} else {
    				Utils.showMessageAndExit(ManageContactList.this, getString(R.string.label_invite_contact_ko));
        		}
        	} catch(Exception e) {
				Utils.showMessageAndExit(ManageContactList.this, getString(R.string.label_invite_contact_ko));
        	}
        }
    };

    /**
     * Revoke button listener
     */
    private OnClickListener btnRevokeListener = new OnClickListener() {
        public void onClick(View v) {
        	try {
                // Revoke the contact
                if (presenceApi.revokeContact(getSelectedContact())) {
                	Utils.displayToast(ManageContactList.this, getString(R.string.label_revoke_contact_ok));
        		} else {
    				Utils.showMessageAndExit(ManageContactList.this, getString(R.string.label_revoke_contact_ko));
        		}
        	} catch(Exception e) {
				Utils.showMessageAndExit(ManageContactList.this, getString(R.string.label_revoke_contact_ko));
        	}
        }
    };
   
    /**
     * Block button listener
     */
    private OnClickListener btnBlockListener = new OnClickListener() {
        public void onClick(View v) {
        	try {
                // Block the conatct
                if (presenceApi.rejectSharingInvitation(getSelectedContact())) {
                	Utils.displayToast(ManageContactList.this, getString(R.string.label_block_contact_ok));
        		} else {
    				Utils.showMessageAndExit(ManageContactList.this, getString(R.string.label_block_contact_ko));
        		}
        	} catch(Exception e) {
				Utils.showMessageAndExit(ManageContactList.this, getString(R.string.label_block_contact_ko));
        	}
        }
    };

    /**
     * Unblock button listener
     */
    private OnClickListener btnUnblockListener = new OnClickListener() {
        public void onClick(View v) {
        	try {
                // Unblock the contact
                if (presenceApi.unblockContact(getSelectedContact())) {
                	Utils.displayToast(ManageContactList.this, getString(R.string.label_unblock_contact_ok));
        		} else {
    				Utils.showMessageAndExit(ManageContactList.this, getString(R.string.label_unblock_contact_ko));
        		}
        	} catch(Exception e) {
				Utils.showMessageAndExit(ManageContactList.this, getString(R.string.label_unblock_contact_ko));
        	}
        }
    };

    /**
     * Unrevoke button listener
     */
    private OnClickListener btnUnrevokeListener = new OnClickListener() {
        public void onClick(View v) {
        	try {
                // Unrevoke the contact
                if (presenceApi.unrevokeContact(getSelectedContact())) {
                	Utils.displayToast(ManageContactList.this, getString(R.string.label_unrevoke_contact_ok));
        		} else {
    				Utils.showMessageAndExit(ManageContactList.this, getString(R.string.label_unrevoke_contact_ko));
        		}
        	} catch(Exception e) {
				Utils.showMessageAndExit(ManageContactList.this, getString(R.string.label_unrevoke_contact_ko));
        	}
        }
    };
}
