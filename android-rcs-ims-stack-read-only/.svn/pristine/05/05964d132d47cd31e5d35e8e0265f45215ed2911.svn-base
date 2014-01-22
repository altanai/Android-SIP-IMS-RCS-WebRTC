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

package com.orangelabs.rcs.ri.capabilities;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Data;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.orangelabs.rcs.ri.R;
import com.orangelabs.rcs.service.api.client.capability.CapabilityApiIntents;
import com.orangelabs.rcs.service.api.client.contacts.ContactInfo;
import com.orangelabs.rcs.service.api.client.contacts.ContactsApi;
import com.orangelabs.rcs.utils.StringUtils;

/**
 * Extensions capabilities
 */
public class ExtensionsCapabilities extends ListActivity implements OnItemClickListener{

	/**
	 * Contact
	 */
	private String contact = null;

    /**
     * Layout inflater
     */
    private LayoutInflater inflater;
    
    /**
     * List adapter
     */
    private ActivityListAdapter adapter;
    
    /**
     * List of activity info
     */
    private List<ResolveInfo> resolveInfos = new ArrayList<ResolveInfo>();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Set layout
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		inflater=getLayoutInflater();
		adapter=new ActivityListAdapter();
		setListAdapter(adapter);
		getListView().setOnItemClickListener(this);
		getListView().setOnCreateContextMenuListener(this);
		
		// Get the corresponding contact from the intent
        Uri contactUri = getIntent().getData();
    	if (contactUri != null) {
	        Cursor cursor = managedQuery(contactUri, null, null, null, null);
	        if (cursor.moveToNext()) {
	        	contact = cursor.getString(cursor.getColumnIndex(Data.DATA1));
	        }
	        cursor.close();
        }
    	
		// Get the contact supported extensions
		ContactsApi contactsApi = new ContactsApi(this);
		ContactInfo contactInfo = contactsApi.getContactInfo(contact);
		List<String> extensions = contactInfo.getCapabilities().getSupportedExtensions();
		PackageManager packageManager = getApplicationContext().getPackageManager();
		for(int i=0; i < extensions.size(); i++) {
			try {
				String extension = extensions.get(i);

				// Intent query on current installed extension
				Intent intent = new Intent(CapabilityApiIntents.RCS_EXTENSIONS);
				String mime = formatIntentMimeType(extension);
				intent.setType(mime);
				List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.GET_RESOLVED_FILTER);
				for(int j=0; j < list.size(); j++) {
					resolveInfos.add(list.get(j));
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
    }
    
	public class ActivityListAdapter extends BaseAdapter{
		
		@Override
		public int getCount() {
			return resolveInfos.size();
		}
		
		@Override
		public Object getItem(int position) {
			return resolveInfos.get(position);
		}
	
		@Override
		public long getItemId(int position) {
			return position;
		}
	
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null){
				convertView=inflater.inflate(R.layout.utils_list_item, null);
			}
			ResolveInfoViewHolder resolveInfoHolder = (ResolveInfoViewHolder) convertView.getTag();
			if (resolveInfoHolder == null){
				resolveInfoHolder = new ResolveInfoViewHolder();
				resolveInfoHolder.initViews(convertView);
			} 
			ResolveInfo resolveInfo = resolveInfos.get((int) getItemId(position));
			resolveInfoHolder.update(resolveInfo);
			convertView.setTag(resolveInfoHolder);
			return convertView;
		}
	
		private class ResolveInfoViewHolder{
			public ImageView icon;
			public TextView name;
		
			public void initViews(View root){
				icon = (ImageView)root.findViewById(R.id.icon);
				name = (TextView)root.findViewById(R.id.name);
			}
			
			public void update(ResolveInfo resolveInfo){
				name.setText(resolveInfo.loadLabel(getPackageManager()));
				
				icon.setImageDrawable(resolveInfo.loadIcon(getPackageManager()));
			}
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		ResolveInfo resolveInfo = ((ResolveInfo)adapter.getItem(position));
		Intent intent = new Intent();
		intent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
		intent.putExtra("contact", contact);
		startActivity(intent);
		finish();
	}

	/**
	 * Format intent MIME type
	 * 
	 * @param tag Feature tag
	 * @return Intent MIME type
	 */
	private static String formatIntentMimeType(String tag) { 
		String mime;
		if (tag.contains("=")) {
			String[] submime = tag.split("=");
			mime = submime[0] + "/" + StringUtils.removeQuotes(submime[1]);
		} else
		if (tag.startsWith("+")) {
			mime = tag + "/*";
		} else {
			mime = "+g.3gpp.iari-ref/" + tag;
		}
		return mime;
	}
}
