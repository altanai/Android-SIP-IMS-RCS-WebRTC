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
package com.orangelabs.rcs.ri.capabilities;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.orangelabs.rcs.ri.R;
import com.orangelabs.rcs.service.api.client.contacts.ContactsApi;

/**
 * Block/unblock contact for chat features
 */
public class BlockCapablities extends ListActivity implements OnItemClickListener {
    /**
	 * List of contacts supporting the chat feature
	 */
	private List<ContactElement> contactCapables = new ArrayList<ContactElement>();
	
	/**
	 * Layout
	 */
	private LayoutInflater layoutInflater;
	
	/**
	 * Contacts API
	 */
    private ContactsApi contactsApi;
    
    /**
	 * List adapter
	 */
	private ContactListAdapter contactListAdapter; 	
	
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        // Set layout
		layoutInflater = LayoutInflater.from(getApplicationContext());

		// Set UI title
        setTitle(getString(R.string.menu_block_capabilities));

        // Instantiate contacts API
        contactsApi = new ContactsApi(getApplicationContext());
        
        // Set list adapter
		contactListAdapter = new ContactListAdapter();
		setListAdapter(contactListAdapter);
		getListView().setOnItemClickListener(this);
		
		// Update data list
		updateDataSet();
	}
		
	/**
	 * Contact list adapter
	 */
	private class ContactListAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return contactCapables.size();
		}

		@Override
		public Object getItem(int position) {
			return contactCapables.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = (TextView)layoutInflater.inflate(android.R.layout.simple_list_item_1, null);
			}
			((TextView)convertView).setText(contactCapables.get(position).contact);
			return convertView;
		}
	}
	
	/**
	 * Update data
	 */
	private void updateDataSet(){
		// Reset lists
		contactCapables.clear();
		
		List<String> contacts = contactsApi.getRcsContacts();
		for (String  contact : contacts) {
			boolean[] blockedItem = {
					contactsApi.isContactImBlocked(contact),
					contactsApi.isContactFtBlocked(contact)
			};
			contactCapables.add(new ContactElement(contact, blockedItem));
		}
		
		// Notify list adapter
		contactListAdapter.notifyDataSetChanged();
	}
	
	@Override
	public void onItemClick(AdapterView<?> parentView, View view, int position, long id) {
		final ContactElement contact = (ContactElement) getListView().getItemAtPosition(position);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.menu_block_capabilities);
        builder.setCancelable(false);
        builder.setMultiChoiceItems(R.array.blocked_capabilities, contact.blockedCapabilities, new DialogInterface.OnMultiChoiceClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {			
				switch(which) {
					case 0:  						
						contact.blockedCapabilities[0]=isChecked;
						break;
					case 1:  
						contact.blockedCapabilities[1]=isChecked;
						break;
				}
			}
		});
        builder.setPositiveButton(getString(R.string.label_ok), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				contactsApi.setImBlockedForContact(contact.contact, contact.blockedCapabilities[0]);
				contactsApi.setFtBlockedForContact(contact.contact, contact.blockedCapabilities[1]);
			}
		});
		builder.setNegativeButton(getString(R.string.label_cancel), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
    	AlertDialog alert = builder.create();
    	alert.show();
	}

	/**
	 * Contact list element
	 */
	private class ContactElement{
		private String contact;
		private boolean[] blockedCapabilities;
		public ContactElement(String contact, boolean[] blockedCapabilities) {
			this.contact = contact;
			this.blockedCapabilities = blockedCapabilities;
		}
	}
}