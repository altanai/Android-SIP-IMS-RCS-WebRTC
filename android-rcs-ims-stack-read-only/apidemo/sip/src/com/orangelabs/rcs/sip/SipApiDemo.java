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
package com.orangelabs.rcs.sip;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.orangelabs.rcs.platform.AndroidFactory;
import com.orangelabs.rcs.platform.logger.AndroidAppender;
import com.orangelabs.rcs.utils.PhoneUtils;
import com.orangelabs.rcs.utils.logger.Appender;
import com.orangelabs.rcs.utils.logger.Logger;

/**
 * SIP API demo
 * 
 * @author jexa7410
 */
public class SipApiDemo extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		// Set application context
		AndroidFactory.setApplicationContext(getApplicationContext());

        // Set logger appenders
		Appender[] appenders = new Appender[] { 
			new AndroidAppender()
		};
		Logger.setAppenders(appenders);
		
		// Set title
        setTitle(getString(R.string.app_name));

        // Set layout
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
       
        // Initialize the country code
		PhoneUtils.initialize(getApplicationContext());
		
		// Set items
        String[] items = {
        		getString(R.string.menu_initiate_session),
        		getString(R.string.menu_list_session),	
        		getString(R.string.menu_session_settings),
        		getString(R.string.menu_send_im)
		};
    	setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	switch(position) {
	        case 0:
	        	startActivity(new Intent(this, OriginatingSide.class));
	            break;
	        case 1:
	        	startActivity(new Intent(this, SessionsList.class));
	            break;
	        case 2:
	        	startActivity(new Intent(this, SessionSettings.class));
	            break;
	        case 3:
	        	startActivity(new Intent(this, SendInstantMessage.class));
	            break;
    	}
    }
}
