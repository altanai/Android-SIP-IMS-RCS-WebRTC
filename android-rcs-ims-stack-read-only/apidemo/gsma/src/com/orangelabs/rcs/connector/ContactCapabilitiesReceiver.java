package com.orangelabs.rcs.connector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.orangelabs.rcs.connector.utils.Utils;
import com.orangelabs.rcs.service.api.client.gsma.GsmaUiConnector;

/**
 * Contact capabilities receiver
 * 
 * @author jexa7410
 */
public class ContactCapabilitiesReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
    	// Display a toast
		String contact = intent.getStringExtra(GsmaUiConnector.EXTRA_CONTACT);
		Utils.displayToast(context, context.getString(R.string.label_capabilities_changed_event, contact));
    }
}
