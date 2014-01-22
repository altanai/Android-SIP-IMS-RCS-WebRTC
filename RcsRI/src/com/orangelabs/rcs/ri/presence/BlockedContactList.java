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

package com.orangelabs.rcs.ri.presence;

import java.util.List;
import android.os.Bundle;

import com.orangelabs.rcs.ri.R;
import com.orangelabs.rcs.service.api.client.ClientApiException;

/**
 * Show blocked contacts
 * 
 * @author jexa7410
 */
public class BlockedContactList extends RcsContactList {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                       
        // Set UI title
        setTitle(R.string.menu_blocked_contacts);
    }

    /**
     * Get the RCS list
     * 
     * @return List
     * @throws ClientApiException
     */
    public List<String> getRcsContactList() throws ClientApiException {
    	return getPresenceApi().getBlockedContacts();
    }
}

