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

package com.orangelabs.rcs.ri.terms;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.orangelabs.rcs.ri.R;
import com.orangelabs.rcs.ri.utils.Utils;
import com.orangelabs.rcs.service.api.client.ClientApiListener;
import com.orangelabs.rcs.service.api.client.terms.TermsApi;

/**
 * SIP terms request
 * 
 * @author jexa7410
 */
public class SipTermsRequest extends Activity implements ClientApiListener {
    /**
     * UI handler
     */
    private final Handler handler = new Handler();
    
    /**
     * Request id
     */
    private String id;

    /**
	 * Terms API
	 */
    private TermsApi termsApi;    
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Set layout
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.terms_request);

        // Get request info
        TextView msg = (TextView)findViewById(R.id.text);
        msg.setText(getIntent().getStringExtra("text"));
		setTitle(getIntent().getStringExtra("title"));
		id = getIntent().getStringExtra("id");


		// Set button callback
        Button acceptBtn = (Button)findViewById(R.id.accept_btn);
        acceptBtn.setEnabled(false);
        acceptBtn.setText(getIntent().getStringExtra("acceptButtonLabel"));
        acceptBtn.setOnClickListener(acceptBtnListener);
        Button declineBtn = (Button)findViewById(R.id.decline_btn);
        declineBtn.setText(getIntent().getStringExtra("rejectButtonLabel"));
        declineBtn.setOnClickListener(declineBtnListener);
        declineBtn.setEnabled(false);
        
        // Instanciate terms API
        termsApi = new TermsApi(getApplicationContext());
        termsApi.addApiEventListener(this);
        termsApi.connectApi();        
	}

    @Override
    public void onDestroy() {
    	super.onDestroy();
    	
		// Disconnect terms API
    	termsApi.disconnectApi();
    }
    	
	
	/**
     * API disabled
     */
    public void handleApiDisabled() {
		handler.post(new Runnable() { 
			public void run() {
				Utils.showMessageAndExit(SipTermsRequest.this, getString(R.string.label_api_disabled));
			}
		});
    }
        
    /**
     * API connected
     */
    public void handleApiConnected() {
		handler.post(new Runnable() { 
			public void run() {
		        Button acceptBtn = (Button)findViewById(R.id.accept_btn);
		        acceptBtn.setEnabled(true);
		        Button declineBtn = (Button)findViewById(R.id.decline_btn);
		        declineBtn.setEnabled(true);
			}
		});
    }

    /**
     * API disconnected
     */
    public void handleApiDisconnected() {
    	// Service has been disconnected
		handler.post(new Runnable(){
			public void run(){
				Utils.showMessageAndExit(SipTermsRequest.this, getString(R.string.label_api_disconnected));
			}
		});
	}

    /**
     * Client is connected to the IMS
     */
	public void handleImsConnected() {
	}

    /**
     * Client is disconnected from the IMS
     * 
     * @param reason Disconnection reason
     */
	public void handleImsDisconnected(int reason) {
    	// IMS has been disconnected
		handler.post(new Runnable(){
			public void run(){
				Utils.showMessageAndExit(SipTermsRequest.this, getString(R.string.label_ims_disconnected));
			}
		});
	}     
    
    /**
     * Accept button listener
     */
    private OnClickListener acceptBtnListener = new OnClickListener() {
        public void onClick(View v) {
        	try {
		    	// Accept terms (no PIN)
		    	if (termsApi.acceptTerms(id, null)) {
			        // Remove notification
		    		SipTermsRequest.removeNotification(SipTermsRequest.this, id);
			        
			        // Exit activity
			        finish();
		    	} else {
		    		// Retry later
					Utils.showMessageAndExit(SipTermsRequest.this, getString(R.string.label_accept_terms_failed));
		    	}
        	} catch(Exception e) {
				Utils.showMessageAndExit(SipTermsRequest.this, getString(R.string.label_accept_terms_failed));
        	}
        }
    };

    /**
     * Reject button listener
     */    
    private OnClickListener declineBtnListener = new OnClickListener() {
        public void onClick(View v) {
        	try {
		    	// Reject terms (no PIN)
		    	if (termsApi.rejectTerms(id, null)) {
			        // Remove notification
		    		SipTermsRequest.removeNotification(SipTermsRequest.this, id);
			        
			        // Exit activity
			        finish();
		    	} else {
		    		// Retry later
					Utils.showMessageAndExit(SipTermsRequest.this, getString(R.string.label_accept_terms_failed));
		    	}
        	} catch(Exception e) {
				Utils.showMessageAndExit(SipTermsRequest.this, getString(R.string.label_reject_terms_failed));
        	}
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
            	finish();
                return true;
        }

        return super.onKeyDown(keyCode, event);
    }
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater=new MenuInflater(getApplicationContext());
		inflater.inflate(R.menu.menu_ft, menu);
		return true;
	}
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_close_session:
				finish();
				break;
		}
		return true;
	}            

    /**
     * Add notification
     * 
     * @param context Context
     * @param incoming Incoming intent
     */
    public static void addNotification(Context context, Intent incoming) {
		String subject = incoming.getStringExtra("subject");
		String requestId = incoming.getStringExtra("id");
		
		// Create notification
		Intent intent = new Intent(incoming);
		intent.setClass(context, SipTermsRequest.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(requestId);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notif = new Notification(R.drawable.ri_terms_icon,
        		subject,
        		System.currentTimeMillis());
        notif.flags = Notification.FLAG_NO_CLEAR;
        notif.setLatestEventInfo(context,
        		context.getString(R.string.app_name),
        		subject,
        		contentIntent);
        
        // Send notification
		NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(requestId, Utils.NOTIF_ID_TERMS, notif);
    }
    
	/**
     * Remove file transfer notification
     * 
     * @param context Context
     * @param requestId Request ID
     */
    public static void removeNotification(Context context, String requestId) {
		NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.cancel(requestId, Utils.NOTIF_ID_TERMS);
    }
}
