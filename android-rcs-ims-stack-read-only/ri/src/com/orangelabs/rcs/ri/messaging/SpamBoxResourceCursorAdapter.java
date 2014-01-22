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

package com.orangelabs.rcs.ri.messaging;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import com.orangelabs.rcs.ri.R;
import com.orangelabs.rcs.service.api.client.eventslog.EventsLogApi;
import com.orangelabs.rcs.utils.PhoneUtils;

/**
 * EventLog Adapter based on a ResourceCursorAdapter which gets its data from 
 * the EventLogProvider.
 * 
 * @author mhsm6403
 */
public class SpamBoxResourceCursorAdapter extends ResourceCursorAdapter{
	
	private Drawable mDrawableIncoming;
	private Drawable mDrawableChat;
	
	public SpamBoxResourceCursorAdapter(Context context) {
		super(context, R.layout.eventlog_list_item, null);
		
		/**
		 * Load the Drawables to use in the bindView method.
		 */
		mDrawableIncoming = context.getResources().getDrawable(R.drawable.ri_eventlog_list_incoming_call);
		mDrawableChat = context.getResources().getDrawable(R.drawable.ri_eventlog_chat);
	}
	
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		TextView line1View = (TextView) view.findViewById(R.id.line1); 
		TextView numberView = (TextView) view.findViewById(R.id.number);
		TextView dateView = (TextView) view.findViewById(R.id.date);
		
		ImageView eventDirectionIconView = (ImageView) view.findViewById(R.id.call_type_icon);
		ImageView eventIconView = (ImageView) view.findViewById(R.id.call_icon);
		
		/* Set the number */
		String number = PhoneUtils.extractNumberFromUri(cursor.getString(EventsLogApi.CONTACT_COLUMN));
		numberView.setText(number);
		numberView.setVisibility(View.VISIBLE);
		
		/* Set the date/time field by mixing relative and absolute times. */
		long date = cursor.getLong(EventsLogApi.DATE_COLUMN);		
		dateView.setText(DateUtils.getRelativeTimeSpanString(date,
				System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS,
				DateUtils.FORMAT_ABBREV_RELATIVE));
		
		/* Set the destination icon */
		eventDirectionIconView.setImageDrawable(mDrawableIncoming);
		
		/* Set icon and data*/
		/* Set the line text */
		line1View.setText(R.string.label_eventlog_chat);
		eventIconView.setImageDrawable(mDrawableChat);
		String data = cursor.getString(EventsLogApi.DATA_COLUMN);
		if(data!=null && data.trim().length()>0){
			line1View.setText(data);
		}
	}		
}
