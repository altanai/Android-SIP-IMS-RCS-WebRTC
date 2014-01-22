package com.orangelabs.rcs.tts;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.provider.Contacts;
import android.provider.Contacts.People;

import com.orangelabs.rcs.utils.PhoneUtils;

/**
 * Contact utility functions
 * 
 * @author jexa7410
 */
public class ContactUtils {
	
	/**
	 * Returns the contact display name
	 *  
	 * @param context Context
	 * @param uro Contact URI (SIP or Tel)
	 * @return Display name in the address book or the username part of the URI
	 */
	public static String getContactDisplayName(Context context, String uri) {
		String number = PhoneUtils.extractNumberFromUri(uri);
		String displayName = number;
		try {
			int personId = -1;
	    	Cursor cursor = context.getContentResolver().query(
	    			Contacts.Phones.CONTENT_URI,
	    			new String[]{Contacts.Phones.PERSON_ID,
	    			Contacts.Phones.NUMBER}, null, null, null);
	    	while(cursor.moveToNext()) {
	    		String databaseNumber = PhoneUtils.extractNumberFromUri(cursor.getString(1));
	    		if (databaseNumber.equals(number)) {
	    			personId = cursor.getInt(0);
	    			break;
	    		}
	       	}
	    	cursor.close();
			
	       	if (personId != -1) {
		    	cursor = context.getContentResolver().query(ContentUris.withAppendedId(
		    			People.CONTENT_URI, personId),
		    			new String[]{People.NAME}, null, null, null);
		    	if (cursor.moveToFirst()){
		    		String name = cursor.getString(0); 
		    		if ((name!=null) && (name.length()>0)) {
		    			displayName = name;
		    		}
		    	}
		    	cursor.close();
	       	}
		} catch(Exception e) {
			// Use default value
		}
		return displayName;
	}
}
