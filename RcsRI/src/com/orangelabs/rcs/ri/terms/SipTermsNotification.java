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
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.orangelabs.rcs.ri.R;
import com.orangelabs.rcs.ri.utils.Utils;

/**
 * SIP terms notification
 *
 * @author hlxn7157
 */
public class SipTermsNotification extends Activity {
    /**
     * UI handler
     */
    private final Handler handler = new Handler();

    /**
     * Request id
     */
    private String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set layout
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.terms_notification);

        // Get request info
        setTitle(getIntent().getStringExtra("subject"));
        TextView msg = (TextView) findViewById(R.id.text);
        msg.setText(getIntent().getStringExtra("text"));
        id = getIntent().getStringExtra("id");

        // Set button callback
        Button okBtn = (Button) findViewById(R.id.ok_btn);
        okBtn.setText(getIntent().getStringExtra("okButtonLabel"));
        okBtn.setOnClickListener(okBtnListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * API disabled
     */
    public void handleApiDisabled() {
        handler.post(new Runnable() {
            public void run() {
                Utils.showMessageAndExit(SipTermsNotification.this,
                        getString(R.string.label_api_disabled));
            }
        });
    }

    /**
     * Accept button listener
     */
    private OnClickListener okBtnListener = new OnClickListener() {
        public void onClick(View v) {
            try {
                // Remove notification
                SipTermsNotification.removeNotification(
                        SipTermsNotification.this, id);

                // Exit activity
                finish();
            } catch (Exception e) {
                Utils.showMessageAndExit(SipTermsNotification.this,
                        getString(R.string.label_accept_terms_failed));
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
        case KeyEvent.KEYCODE_BACK:
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    /**
     * Add notification
     *
     * @param context Context
     * @param incoming Incoming intent
     */
    public static void addNotification(Context context, Intent incoming) {
        String subject = incoming.getStringExtra("subject");
        String requestId = incoming.getStringExtra("id");

        // Create notification
        Intent intent = new Intent(incoming);
        intent.setClass(context, SipTermsNotification.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(requestId);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notif = new Notification(R.drawable.ri_terms_icon,
                subject, System.currentTimeMillis());
        notif.flags = Notification.FLAG_NO_CLEAR;
        notif.setLatestEventInfo(context, context.getString(R.string.app_name),
                subject, contentIntent);

        // Send notification
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(requestId, Utils.NOTIF_ID_TERMS, notif);
    }

    /**
     * Remove file transfer notification
     *
     * @param context Context
     * @param requestId Request ID
     */
    public static void removeNotification(Context context, String requestId) {
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(requestId, Utils.NOTIF_ID_TERMS);
    }
}
