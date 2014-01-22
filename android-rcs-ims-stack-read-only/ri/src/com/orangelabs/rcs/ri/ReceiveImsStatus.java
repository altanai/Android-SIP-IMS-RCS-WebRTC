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

package com.orangelabs.rcs.ri;

import com.orangelabs.rcs.provider.settings.RcsSettings;
import com.orangelabs.rcs.service.api.client.ImsDisconnectionReason;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Receive IMS status
 *
 * @author hlxn7157
 */
public class ReceiveImsStatus extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean status = intent.getBooleanExtra("status", false);
        if (!status) {
            int reason = intent.getIntExtra("reason", ImsDisconnectionReason.UNKNOWN);
            if (reason == ImsDisconnectionReason.BATTERY_LOW) {
                // Show disconnected reason due to battery low
                int batteryLimit = RcsSettings.getInstance().getMinBatteryLevel();
                String msg = context.getString(R.string.label_battery_running_low, batteryLimit);
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            }
        }
    }
}
