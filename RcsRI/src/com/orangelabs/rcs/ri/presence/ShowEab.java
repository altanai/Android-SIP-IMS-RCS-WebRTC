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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Data;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.orangelabs.rcs.ri.R;
import com.orangelabs.rcs.ri.utils.Utils;
import com.orangelabs.rcs.service.api.client.capability.Capabilities;
import com.orangelabs.rcs.service.api.client.contacts.ContactInfo;
import com.orangelabs.rcs.service.api.client.contacts.ContactsApi;
import com.orangelabs.rcs.service.api.client.presence.PresenceInfo;

/**
 * Show RCS contacts content provider
 * 
 * @author jexa7410
 */
public class ShowEab extends Activity {
    
	/**
	 * Contacts API
	 */
    private ContactsApi contactsApi; 
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Set layout
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.presence_eab);
        
        // Set title
        setTitle(R.string.menu_eab);
        
        // Set the contact selector
        Spinner spinner = (Spinner)findViewById(R.id.contact);
        spinner.setAdapter(Utils.createContactListAdapter(this));
        spinner.setOnItemSelectedListener(listChangeListener);
        
        // Instanciate contacts API
        contactsApi = new ContactsApi(getApplicationContext());
        
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
     * Display the contact info of the selected RCS contact
     */
    private void displayContactInfo() {
    	try {
	        String contact = getSelectedContact();
	        ContactInfo contactInfo = contactsApi.getContactInfo(contact);

	        TextView msisdn = (TextView)findViewById(R.id.phone_number);
	        TextView type = (TextView)findViewById(R.id.type);
	        TextView lastModified = (TextView)findViewById(R.id.last_modified);
	        ImageView photoView = (ImageView)findViewById(R.id.photo);
	        TextView status = (TextView)findViewById(R.id.presence_status);
	        TextView freetextEdit = (TextView)findViewById(R.id.freetext);
	        TextView favoritelinkEdit = (TextView)findViewById(R.id.favoritelink);
	        CheckBox imageCSh = (CheckBox)findViewById(R.id.image_sharing);
	        CheckBox videoCSh = (CheckBox)findViewById(R.id.video_sharing);
	        CheckBox ft = (CheckBox)findViewById(R.id.file_transfer);
	        CheckBox im = (CheckBox)findViewById(R.id.im);
	        CheckBox csVideo = (CheckBox)findViewById(R.id.cs_video);
	        
	        // Show general info
	        msisdn.setText(contact);
	        lastModified.setText(Utils.formatDateToString(contactInfo.getRcsStatusTimestamp()));
	        int rcsStatus = contactInfo.getRcsStatus();
	        if (rcsStatus==ContactInfo.NOT_RCS){
	        	type.setText(getString(R.string.label_normal_contact));
	        }else{
	        	type.setText(getString(R.string.label_rcs_contact));	
	        }

	        photoView.setVisibility(View.GONE);
	    	status.setVisibility(View.GONE);
	    	freetextEdit.setVisibility(View.GONE);
			favoritelinkEdit.setVisibility(View.GONE);
			imageCSh.setVisibility(View.GONE);
			videoCSh.setVisibility(View.GONE);
			ft.setVisibility(View.GONE);
			im.setVisibility(View.GONE);
			csVideo.setVisibility(View.GONE);
	        
	        // Show presence info
	        PresenceInfo presenceInfo = contactInfo.getPresenceInfo();
	        if ((presenceInfo != null) && (contactInfo.getRcsStatus()==ContactInfo.RCS_ACTIVE)) {
		        photoView.setVisibility(View.VISIBLE);
		    	status.setVisibility(View.VISIBLE);
		    	freetextEdit.setVisibility(View.VISIBLE);
				favoritelinkEdit.setVisibility(View.VISIBLE);

				if (presenceInfo.getPhotoIcon() != null) {
		            byte[] data = presenceInfo.getPhotoIcon().getContent();
		            Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
		        	photoView.setImageBitmap(bmp);
		        } else {
		        	photoView.setImageResource(R.drawable.ri_default_portrait_icon);
		        }
		        
		    	status.setText(presenceInfo.getPresenceStatus());
		    	freetextEdit.setText(presenceInfo.getFreetext());
				favoritelinkEdit.setText(presenceInfo.getFavoriteLinkUrl());
	        }
	        
	        // Show capabilities info
			Capabilities capabilities = contactInfo.getCapabilities();
			if (capabilities != null) {
				imageCSh.setVisibility(View.VISIBLE);
				videoCSh.setVisibility(View.VISIBLE);
				ft.setVisibility(View.VISIBLE);
				im.setVisibility(View.VISIBLE);
				csVideo.setVisibility(View.VISIBLE);
				
				imageCSh.setChecked(capabilities.isImageSharingSupported());
				videoCSh.setChecked(capabilities.isVideoSharingSupported());
				ft.setChecked(capabilities.isFileTransferSupported());
				im.setChecked(capabilities.isImSessionSupported());
				csVideo.setChecked(capabilities.isCsVideoSupported());
	        }
		} catch(Exception e) {
			e.printStackTrace();
			Utils.showMessageAndExit(ShowEab.this, getString(R.string.label_read_eab_ko));
		}
    }

    /**
     * Contact selector listener
     */
    private OnItemSelectedListener listChangeListener = new OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        	ShowEab.this.displayContactInfo();
        }
        
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };
}
