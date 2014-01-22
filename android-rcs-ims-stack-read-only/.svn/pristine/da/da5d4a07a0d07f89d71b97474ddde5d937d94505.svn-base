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
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;

import com.orangelabs.rcs.ri.R;

/**
 * SIP terms ack
 * 
 * @author jexa7410
 */
public class SipTermsAck extends Activity {
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Set layout
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.terms_welcome);
        
        // Display welcome message
        String msg = getIntent().getStringExtra("subject") +
        	"\r\n" +
        	"\r\n" +
        	getIntent().getStringExtra("text");        
    	TextView txt = (TextView)findViewById(R.id.text);
    	txt.setText(msg);
	}

    @Override
    public void onDestroy() {
    	super.onDestroy();
    }
}
