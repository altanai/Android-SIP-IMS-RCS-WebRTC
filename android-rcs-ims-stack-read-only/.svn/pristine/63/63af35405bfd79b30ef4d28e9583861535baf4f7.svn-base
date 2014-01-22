package com.orangelabs.rcs.connector;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.orangelabs.rcs.service.api.client.gsma.GsmaUiConnector;

/**
 * Get the RCS status
 * 
 * @author jexa7410
 */
public class GetRcsStatus extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Set layout
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.get_rcs_status);
        
        // Set title
        setTitle(R.string.menu_rcs_status);
        
		// Set buttons callback
        Button btn = (Button)findViewById(R.id.refresh_btn);
        btn.setOnClickListener(btnRefreshListener);
        
        // Get status
        getStatus();
    }

    /**
     * Refresh button listener
     */
    private OnClickListener btnRefreshListener = new OnClickListener() {
        public void onClick(View v) {
        	// Refresh status
        	getStatus();
        }
    };

    /**
     * Get status
     */
    private void getStatus() {
    	Intent intent = new Intent(GsmaUiConnector.ACTION_GET_RCS_STATUS);
    	sendOrderedBroadcast(intent, null, new ResultReceiver(),
                null, Activity.RESULT_OK, null, null);
    }
    
	/**
	 * Result receiver
	 */
    public class ResultReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            Bundle result = getResultExtras(true);
            boolean rcs = result.getBoolean(GsmaUiConnector.EXTRA_RCS_STATUS, false);
            boolean registered = result.getBoolean(GsmaUiConnector.EXTRA_REGISTRATION_STATUS, false);

            TextView txt = (TextView)findViewById(R.id.rcs_status);
            if (rcs) {
            	txt.setText(getString(R.string.label_rcs));
            } else {
            	txt.setText(getString(R.string.label_not_rcs));
            }

            txt = (TextView)findViewById(R.id.registration_status);
            if (registered) {
            	txt.setText(getString(R.string.label_registered));
            } else {
            	txt.setText(getString(R.string.label_unregistered));
            }
        }
    }
}
