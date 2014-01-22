package com.orangelabs.rcs.responder;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Chat invitation event receiver
 * 
 * @author jexa7410
 */
public class ChatInvitationEvent extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
        SharedPreferences preferences = context.getSharedPreferences(Registry.REGISTRY, Activity.MODE_PRIVATE);
        boolean flag = Registry.readBoolean(preferences, Registry.ACTIVATE_RESPONDER, false);
        if (flag) {
            String msg = Registry.readString(preferences, Registry.DEFAULT_MSG, null);
        	if (msg == null) {
        		// Set a default message
        		msg = context.getResources().getStringArray(R.array.message_list)[0];
        	}
        	
            Intent responderIntent = new Intent(context, ResponderService.class);
            responderIntent.putExtra("sessionId", intent.getStringExtra("sessionId"));
            responderIntent.putExtra("autoAccept", intent.getBooleanExtra("autoAccept", false));
            responderIntent.putExtra("message", msg);
            context.startService(responderIntent);
        }
    }
}
