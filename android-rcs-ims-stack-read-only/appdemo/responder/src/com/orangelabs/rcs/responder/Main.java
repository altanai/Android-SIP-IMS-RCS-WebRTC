package com.orangelabs.rcs.responder;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;

/**
 * Auto respond a chat invitation with a predefined text
 * 
 * @author jexa7410
 */
public class Main extends PreferenceActivity implements Preference.OnPreferenceChangeListener {
	
    private CheckBoxPreference activateCheck;

    private ListPreference msgList;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set title
        setTitle(R.string.app_title);
        
        // Set preferences
        addPreferencesFromResource(R.xml.responder_preferences);
        activateCheck = (CheckBoxPreference)getPreferenceScreen().findPreference("activate");
        activateCheck.setOnPreferenceChangeListener(this);
        msgList = (ListPreference)getPreferenceScreen().findPreference("message");
        msgList.setPersistent(false);
        msgList.setOnPreferenceChangeListener(this);
    }
    
    @Override
    protected void onResume() {
    	super.onResume();

        // Load preferences
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(Registry.REGISTRY, Activity.MODE_PRIVATE);
        boolean flag = Registry.readBoolean(preferences, Registry.ACTIVATE_RESPONDER, false);
		activateCheck.setChecked(flag);
		String msg = Registry.readString(preferences, Registry.DEFAULT_MSG, null);
		if (msg == null) {
	        msgList.setValueIndex(0);
		} else {
			msgList.setValue(msg);
		}
    }
    
    public boolean onPreferenceChange(Preference preference, Object objValue) {
    	// Update preferences
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(Registry.REGISTRY, Activity.MODE_PRIVATE);
        if (preference.getKey().equals("activate")) {
	        boolean flag = !activateCheck.isChecked();
	        Registry.writeBoolean(preferences, Registry.ACTIVATE_RESPONDER, flag);
        } else
        if (preference.getKey().equals("message")) {
        	String msg = (String)objValue;
            Registry.writeString(preferences, Registry.DEFAULT_MSG, msg);
        }
    	return true;
    }    
 }