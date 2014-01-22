package com.orangelabs.rcs.tts;

import java.util.ArrayList;

import com.orangelabs.rcs.service.api.client.messaging.InstantMessage;

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
        boolean flag = Registry.readBoolean(preferences, Registry.ACTIVATE_TTS, false);
        if (flag) {
        	boolean isGroupChat = intent.getBooleanExtra("isGroupChat", false);
        	ArrayList<String> messages = new ArrayList<String>();
        	String contact = intent.getStringExtra("contact");
        	String displayName = intent.getStringExtra("contactDisplayname");
        	String remote;
        	if ((displayName != null) && (!displayName.equals(contact))) {
        		remote = displayName;
        	} else {
        		remote = ContactUtils.getContactDisplayName(context, contact);
        	}
        	
        	if (isGroupChat) {
        		messages.add(context.getString(R.string.label_groupchat, remote));
        		String subject = intent.getStringExtra("subject");
        		if (subject != null) {
        			messages.add(subject);
        		}
        	} else {
        		InstantMessage firstMessage = intent.getParcelableExtra("firstMessage");
        		messages.add(context.getString(R.string.label_play, remote));
    			messages.add(firstMessage.getTextMessage());        		
        	}
			
			// Play TTS
			Intent serviceIntent = new Intent(context, PlayTextToSpeech.class);
			serviceIntent.putStringArrayListExtra("messages", messages);
			context.startService(serviceIntent);        	    	
        }
    }
}
