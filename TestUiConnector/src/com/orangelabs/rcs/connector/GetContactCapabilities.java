package com.orangelabs.rcs.connector;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.orangelabs.rcs.connector.utils.Utils;
import com.orangelabs.rcs.service.api.client.gsma.GsmaUiConnector;

/**
 * Get contact capabilities
 * 
 * @author jexa7410
 */
public class GetContactCapabilities extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Set layout
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.get_contact_capabilities);
        
        // Set title
        setTitle(R.string.menu_contact_capabilities);

        // Set the contact selector
        Spinner spinner = (Spinner)findViewById(R.id.contact);
        spinner.setAdapter(Utils.createContactListAdapter(this));
        spinner.setOnItemSelectedListener(listenerContact);
                
		// Set buttons callback
        Button btn = (Button)findViewById(R.id.refresh_btn);
        btn.setOnClickListener(btnRefreshListener);  
        
        // Display capabilities
		getCapabilities();
    }

    /**
     * Request button listener
     */
    private OnClickListener btnRefreshListener = new OnClickListener() {
        public void onClick(View v) {
        	getCapabilities();
        }
    };
    
    /**
     * Spinner contact listener
     */
    private OnItemSelectedListener listenerContact = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			// Get capabilities
			getCapabilities();
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
	};
	
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
     * get capabilities of a contact
     */
    private void getCapabilities() {
    	String contact = getSelectedContact();
    	Intent intent = new Intent(GsmaUiConnector.ACTION_GET_CONTACT_CAPABILITIES);
    	intent.putExtra(GsmaUiConnector.EXTRA_CONTACT, contact); 
    	sendOrderedBroadcast(intent, null, new ResultReceiver(),
                null, Activity.RESULT_OK, null, null);
    }
    
	/**
	 * Result receiver
	 */
    public class ResultReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
        	// Get result
            Bundle result = getResultExtras(true);
            boolean capabilityIm = result.getBoolean(GsmaUiConnector.EXTRA_CAPABILITY_CHAT, false);
            boolean capabilityFt = result.getBoolean(GsmaUiConnector.EXTRA_CAPABILITY_FT, false);
            boolean capabilityIs = result.getBoolean(GsmaUiConnector.EXTRA_CAPABILITY_IMAGE_SHARE, false);
            boolean capabilityVs = result.getBoolean(GsmaUiConnector.EXTRA_CAPABILITY_VIDEO_SHARE, false);
            boolean capabilityPd = result.getBoolean(GsmaUiConnector.EXTRA_CAPABILITY_PRESENCE_DISCOVERY, false);
            boolean capabilitySp = result.getBoolean(GsmaUiConnector.EXTRA_CAPABILITY_SOCIAL_PRESENCE, false);
            boolean capabilityCsv = result.getBoolean(GsmaUiConnector.EXTRA_CAPABILITY_CS_VIDEO, false);
            boolean capabilityGeoloc = result.getBoolean(GsmaUiConnector.EXTRA_CAPABILITY_GEOLOCATION_PUSH, false);
            String[] capabilityExts = result.getStringArray(GsmaUiConnector.EXTRA_CAPABILITY_EXTENSIONS);
            
        	// Show result
            CheckBox imageCSh = (CheckBox)findViewById(R.id.image_sharing);
            imageCSh.setChecked(capabilityIs);
            
            CheckBox videoCSh = (CheckBox)findViewById(R.id.video_sharing);
            videoCSh.setChecked(capabilityVs);
            
            CheckBox ft = (CheckBox)findViewById(R.id.file_transfer);
            ft.setChecked(capabilityFt);
            
            CheckBox im = (CheckBox)findViewById(R.id.im);
            im.setChecked(capabilityIm);
            
            CheckBox csVideo = (CheckBox)findViewById(R.id.cs_video);
            csVideo.setChecked(capabilityCsv);
            
            CheckBox presenceDiscovery = (CheckBox)findViewById(R.id.presence_discovery);
            presenceDiscovery.setChecked(capabilityPd);
            
            CheckBox socialPresence = (CheckBox)findViewById(R.id.social_presence);
            socialPresence.setChecked(capabilitySp);

            CheckBox geoloc = (CheckBox)findViewById(R.id.geoloc_push);
            geoloc.setChecked(capabilityGeoloc);

            if (capabilityExts != null) {
	            TextView ext = (TextView)findViewById(R.id.extensions);
	            String txt = "";
	            for(int i=0; i<capabilityExts.length; i++) {
	            	String value = capabilityExts[i];
	            	txt += value.substring(Utils.FEATURE_RCSE_EXTENSION.length()+1) + "\n";
	            }
	            ext.setText(txt);
            }
        }
    }
}
