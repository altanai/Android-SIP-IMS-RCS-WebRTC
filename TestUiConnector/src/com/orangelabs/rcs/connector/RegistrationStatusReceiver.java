package com.orangelabs.rcs.connector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.orangelabs.rcs.connector.R;
import com.orangelabs.rcs.connector.utils.Utils;
import com.orangelabs.rcs.service.api.client.gsma.GsmaUiConnector;

/**
 * Registration status receiver 
 * 
 * @author jexa7410
 */
public class RegistrationStatusReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
    	// Display a toast
		String status;
		if (intent.getBooleanExtra(GsmaUiConnector.EXTRA_REGISTRATION_STATUS, false)) {
			status = context.getString(R.string.label_registered);
		} else {
			status = context.getString(R.string.label_unregistered);
		}
		Utils.displayToast(context, context.getString(R.string.label_registration_event, status));
    }
}
