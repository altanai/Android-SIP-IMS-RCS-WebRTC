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

import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.QuickContactBadge;
import android.widget.Spinner;
import android.widget.TextView;

import com.orangelabs.rcs.ri.R;
import com.orangelabs.rcs.ri.utils.Utils;
import com.orangelabs.rcs.service.api.client.capability.Capabilities;
import com.orangelabs.rcs.service.api.client.capability.CapabilityApi;
import com.orangelabs.rcs.service.api.client.capability.CapabilityApiIntents;
import com.orangelabs.rcs.utils.PhoneUtils;

/**
 * Anonymous fetch contacts capabilities of a given contact
 * 
 * @author jexa7410
 */
public class CapabilitiesDiscovery extends Activity {
	/**
	 * UI handler
	 */
	private final Handler handler = new Handler();
	
    /**
	 * Capability API
	 */
    private CapabilityApi capabilityApi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Set layout
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.capabilities_request);
        
        // Set title
        setTitle(R.string.menu_capabilities);
        
        // Set the contact selector
        Spinner spinner = (Spinner)findViewById(R.id.contact);
        spinner.setAdapter(Utils.createContactListAdapter(this));
        spinner.setOnItemSelectedListener(listenerContact);
        
    	// Register intent receiver to receive the query result
        IntentFilter filter = new IntentFilter(CapabilityApiIntents.CONTACT_CAPABILITIES);
		registerReceiver(capabilitiesIntentReceiver, filter, null, handler);

		// Set button callback
        Button refreshBtn = (Button)findViewById(R.id.refresh_btn);
        refreshBtn.setOnClickListener(btnRefreshListener);
        
        // Disable button if no contact available
        if (spinner.getAdapter().getCount() == 0) {
            refreshBtn.setEnabled(false);
        }
        
        // Instanciate capability API
        capabilityApi = new CapabilityApi(getApplicationContext());
        capabilityApi.connectApi();
    }

    @Override
    public void onDestroy() {
    	super.onDestroy();
    	
    	// Unregister intent receiver
    	try {
    		unregisterReceiver(capabilitiesIntentReceiver);
        } catch (IllegalArgumentException e) {
        	// Nothing to do
        }

		// Disconnect contacts API
    	capabilityApi.disconnectApi();
    }
    
    /**
     * Spinner contact listener
     */
    private OnItemSelectedListener listenerContact = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			String contact = getContactAtPosition(position);
			
			// Get current capabilities
			Capabilities currentCapabilities = capabilityApi.getContactCapabilities(contact);

			// Display default capabilities
	        displayCapabilities(currentCapabilities);
			
			// Update capabilities
			updateCapabilities(contact);
			
			// Display quick contact badge
			updateQuickContactBadge(contact);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
	};
	
    /**
     * Returns the contact at given position
     * 
     * @param position Position in the adapter
     * @return Contact
     */
    private String getContactAtPosition(int position) {
	    Spinner spinner = (Spinner)findViewById(R.id.contact);
	    MatrixCursor cursor = (MatrixCursor)spinner.getItemAtPosition(position);
	    return cursor.getString(1);
    }
    
    /**
     * Returns the selected contact
     * 
     * @param position Position in the adapter
     * @return Contact
     */
    private String getSelectedContact() {
	    Spinner spinner = (Spinner)findViewById(R.id.contact);
	    MatrixCursor cursor = (MatrixCursor)spinner.getSelectedItem();
	    return cursor.getString(1);
    }

    /**
     * Request button callback
     */
    private OnClickListener btnRefreshListener = new OnClickListener() {
        public void onClick(View v) {
    		// Update capabilities
    		updateCapabilities(getSelectedContact());
        }
    };

    /**
     * Update capabilities of a given contact
     * 
     * @param contact Contact
     */
    private void updateCapabilities(final String contact) {
        // Display info
        Utils.displayLongToast(CapabilitiesDiscovery.this, getString(R.string.label_request_in_background, contact));

        Thread t = new Thread() {
    		public void run() {
		    	try {
			        // Request new capabilities 
			        capabilityApi.requestCapabilities(contact);
		    	} catch(Exception e) {
		    		// Display error
					handler.post(new Runnable(){
						public void run(){
					    	Utils.showMessage(CapabilitiesDiscovery.this, getString(R.string.label_request_ko));
						}
					});
		    	}
    		}
    	};
    	t.start();
    }
    
    /**
     * Display the capabilities
     * 
     * @param capabilities Capabilities
     */
    private void displayCapabilities(Capabilities capabilities) {
    	TextView lastCapabilitiesModified = (TextView)findViewById(R.id.last_capabilities_modified);
    	CheckBox imageCSh = (CheckBox)findViewById(R.id.image_sharing);
    	CheckBox videoCSh = (CheckBox)findViewById(R.id.video_sharing);
    	CheckBox ft = (CheckBox)findViewById(R.id.file_transfer);
    	CheckBox ftHttp = (CheckBox)findViewById(R.id.file_transfer_http);
    	CheckBox im = (CheckBox)findViewById(R.id.im);
    	CheckBox csVideo = (CheckBox)findViewById(R.id.cs_video);
    	CheckBox presenceDiscovery = (CheckBox)findViewById(R.id.presence_discovery);
    	CheckBox socialPresence = (CheckBox)findViewById(R.id.social_presence);
        CheckBox geolocationPush = (CheckBox)findViewById(R.id.geolocation_push);
        CheckBox ftThumbnail = (CheckBox)findViewById(R.id.file_transfer_thumbnail);
        CheckBox ftSF = (CheckBox)findViewById(R.id.file_transfer_sf);
        CheckBox gcSF = (CheckBox)findViewById(R.id.group_chat_sf);
        TextView extensions = (TextView)findViewById(R.id.extensions);

    	if (capabilities != null) {
    		// Set timestamp
    		lastCapabilitiesModified.setText(Utils.formatDateToString(capabilities.getTimestamp()));

    		// Set capabilities
    		imageCSh.setVisibility(View.VISIBLE);
    		videoCSh.setVisibility(View.VISIBLE);
    		ft.setVisibility(View.VISIBLE);
    		ftHttp.setVisibility(View.VISIBLE);
    		im.setVisibility(View.VISIBLE);
    		csVideo.setVisibility(View.VISIBLE);
    		presenceDiscovery.setVisibility(View.VISIBLE);
    		socialPresence.setVisibility(View.VISIBLE);
            geolocationPush.setVisibility(View.VISIBLE);
            ftThumbnail.setVisibility(View.VISIBLE);
    		ftSF.setVisibility(View.VISIBLE);
    		gcSF.setVisibility(View.VISIBLE);
    		imageCSh.setChecked(capabilities.isImageSharingSupported());
    		videoCSh.setChecked(capabilities.isVideoSharingSupported());
    		ft.setChecked(capabilities.isFileTransferSupported());
    		ftHttp.setChecked(capabilities.isFileTransferHttpSupported());
    		im.setChecked(capabilities.isImSessionSupported());
    		csVideo.setChecked(capabilities.isCsVideoSupported());
    		presenceDiscovery.setChecked(capabilities.isPresenceDiscoverySupported());
    		socialPresence.setChecked(capabilities.isSocialPresenceSupported());
            geolocationPush.setChecked(capabilities.isGeolocationPushSupported());
            ftThumbnail.setChecked(capabilities.isFileTransferThumbnailSupported());
            ftSF.setChecked(capabilities.isFileTransferStoreForwardSupported());
            gcSF.setChecked(capabilities.isGroupChatStoreForwardSupported());

            // Set extensions
    		extensions.setVisibility(View.VISIBLE);
            String result = "";
            List<String> extensionList = capabilities.getSupportedExtensions();
            for(int i=0; i<extensionList.size(); i++) {
            	String value = extensionList.get(i);
            	result += value.substring(Utils.FEATURE_RCSE_EXTENSION.length()+1) + "\n";
            }
            extensions.setText(result);    		
    	} else {
    		lastCapabilitiesModified.setText("");
    		imageCSh.setVisibility(View.GONE);
    		videoCSh.setVisibility(View.GONE);
    		ft.setVisibility(View.GONE);
    		ftHttp.setVisibility(View.GONE);
    		im.setVisibility(View.GONE);
    		csVideo.setVisibility(View.GONE);
    		presenceDiscovery.setVisibility(View.GONE);
    		socialPresence.setVisibility(View.GONE);
    		geolocationPush.setVisibility(View.GONE);
    		ftThumbnail.setVisibility(View.GONE);
    		ftSF.setVisibility(View.GONE);
    		gcSF.setVisibility(View.GONE);
    		extensions.setVisibility(View.GONE);
    	}
    }
    
    /**
     * Update the quick contact badge
     * 
     * @param contact
     */
    private void updateQuickContactBadge(String contact) {
		QuickContactBadge quickContactBadge = (QuickContactBadge)findViewById(R.id.contact_badge);
		quickContactBadge.assignContactFromPhone(contact, true);
		
		// Get the contact id
		int id = -1;
		
		// Query the Phone API
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
        		new String[]{ContactsContract.CommonDataKinds.Phone.CONTACT_ID,  ContactsContract.CommonDataKinds.Phone.NUMBER},
        		null, 
     		    null, 
     		    null);
        while(cursor.moveToNext()) {
    		String databaseNumber = PhoneUtils.extractNumberFromUri(cursor.getString(1));
    		if (PhoneUtils.compareNumbers(databaseNumber, contact)) {
    			id = cursor.getInt(0);
    			break;
    		}
        }
        cursor.close();
        
        // Set the photo
        if (id != -1) {
        	Uri contactUri = ContentUris.withAppendedId(Contacts.CONTENT_URI, id);
        	InputStream input = Contacts.openContactPhotoInputStream(getContentResolver(), contactUri);     
        	if (input != null) {         
        		Uri photoUri = Uri.withAppendedPath(contactUri, Contacts.Photo.CONTENT_DIRECTORY);
        		quickContactBadge.setImageURI(photoUri);
        	} else {
        		quickContactBadge.setImageResource(R.drawable.ri_default_portrait_icon);
        	}
        } else { 
        	// Could not find the contact, set default portrait
        	quickContactBadge.setImageResource(R.drawable.ri_default_portrait_icon);
        }		
    }
    
    /**
     * Intent receiver for capabilities result
     */
    private BroadcastReceiver capabilitiesIntentReceiver = new BroadcastReceiver() {
		public void onReceive(Context context, final Intent intent) {
			handler.post(new Runnable(){
				public void run(){
					// Read capabilities from intent
					Capabilities capabilities = intent.getParcelableExtra("capabilities");
					
					// Check if this intent concerns the current selected contact					
					if (PhoneUtils.compareNumbers(getSelectedContact(), intent.getStringExtra("contact"))){
						// Update UI
						displayCapabilities(capabilities);
					}
				}
			});
		}
    };    
}
