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

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import com.orangelabs.rcs.ri.R;
import com.orangelabs.rcs.ri.utils.Utils;
import com.orangelabs.rcs.service.api.client.contacts.ContactsApi;

/**
 * Block/unblock contact for chat features
 * 
 * @author jexa7410
 */
public class BlockContact extends ListActivity implements OnItemClickListener {
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
        setTitle(getString(R.string.menu_block_contact));

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
				convertView = (CheckedTextView)layoutInflater.inflate(android.R.layout.simple_list_item_checked, null);
			}
			((CheckedTextView)convertView).setText(contactCapables.get(position).contact);
			((CheckedTextView)convertView).setChecked(contactCapables.get(position).blocked);
			return convertView;
		}
	}
	
	/**
	 * Update data
	 */
	private void updateDataSet(){
		// Reset lists
		contactCapables.clear();

		// Get blocked contacts
		List<String> blockedContacts = contactsApi.getBlockedContactsForIm();
		
		// Get chat capable contacts
		List<String> chatCapableContacts = contactsApi.getImSessionCapableContacts();
		for (String  contact : chatCapableContacts) {
			if (blockedContacts.contains(contact)){
				contactCapables.add(new ContactElement(contact, true));
			} else {
				contactCapables.add(new ContactElement(contact, false));
			}
		}
		
		// Notify list adapter
		contactListAdapter.notifyDataSetChanged();
	}
	
	@Override
	public void onItemClick(AdapterView<?> parentView, View view, int position, long id) {
		ContactElement contact = (ContactElement) getListView().getItemAtPosition(position);
		if (contact.blocked) {
			contactsApi.setImBlockedForContact(contact.contact, false);
			((CheckedTextView)view).setChecked(false);
			contact.blocked = false;
			Utils.displayToast(this, getString(R.string.label_contact_unblocked, contact.contact));
		} else {
			contactsApi.setImBlockedForContact(contact.contact, true);
			((CheckedTextView)view).setChecked(true);
			contact.blocked = true;
			Utils.displayToast(this, getString(R.string.label_contact_blocked, contact.contact));
		}
	}

	/**
	 * Contact list element
	 */
	private class ContactElement{
		private String contact;
		private boolean blocked;
		public ContactElement(String contact, boolean blocked) {
			this.contact = contact;
			this.blocked = blocked;
		}
	}
}