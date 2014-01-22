package com.orangelabs.rcs.connector;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import com.orangelabs.rcs.connector.utils.Utils;
import com.orangelabs.rcs.service.api.client.gsma.GsmaUiConnector;

/**
 * Get my capabilities
 *  
 * @author jexa7410
 */
public class GetMyCapabilities extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Set layout
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.get_my_capabilities);
        
        // Set title
        setTitle(R.string.menu_my_capabilities);
        
        // Display capabilities
        getCapabilities();
    }

    /**
     * Get my capabilities
     */
    private void getCapabilities() {
    	Intent intent = new Intent(GsmaUiConnector.ACTION_GET_MY_CAPABILITIES);
    	sendOrderedBroadcast(intent, null, new ResultReceiver(),
                null, Activity.RESULT_OK, null, null);
    };
    
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
            boolean capabilitySf = result.getBoolean(GsmaUiConnector.EXTRA_CAPABILITY_SF, false);
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

            CheckBox sf = (CheckBox)findViewById(R.id.sf);
            sf.setChecked(capabilitySf);
            
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
