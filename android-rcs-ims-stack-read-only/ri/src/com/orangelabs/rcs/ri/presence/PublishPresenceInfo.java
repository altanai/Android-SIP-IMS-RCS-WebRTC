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

import java.io.ByteArrayOutputStream;
import java.util.Vector;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.orangelabs.rcs.service.api.client.presence.PhotoIcon;
import com.orangelabs.rcs.service.api.client.presence.PresenceInfo;
import com.orangelabs.rcs.provider.settings.RcsSettings;
import com.orangelabs.rcs.ri.R;
import com.orangelabs.rcs.ri.utils.Utils;
import com.orangelabs.rcs.ri.utils.bookmark.BrowserBookmarksPage;
import com.orangelabs.rcs.service.api.client.presence.PresenceApi;

/**
 * Publish my presence info
 * 
 * @author jexa7410
 */
public class PublishPresenceInfo extends Activity {
	/**
	 * Activity result constants
	 */
	private final static int SELECT_PHOTO = 0;
	private final static int SELECT_LINK = 1;

	/**
	 * Presence info
	 */
	private PresenceInfo presenceInfo;

	/**
	 * Presence API
	 */
    private PresenceApi presenceApi;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Set layout
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.presence_edit_info);
        
        // Set title
        setTitle(R.string.menu_presence_info);
        
        // Instanciate presence API
        presenceApi = new PresenceApi(getApplicationContext());
        presenceApi.connectApi();
        
		// Display the current presence info from the RCS contacts API
		presenceInfo = presenceApi.getMyPresenceInfo();
		
    	Spinner statusList = (Spinner)findViewById(R.id.availability);
    	if (presenceInfo.isOnline()) { 
    		statusList.setSelection(0);
    	} else {
    		statusList.setSelection(1);
    	}   	
    	
    	EditText freetextEdit = (EditText)findViewById(R.id.freetext);
    	freetextEdit.setText(presenceInfo.getFreetext());
    	freetextEdit.setOnLongClickListener(new FreetextLongClickListener());

    	EditText favoritelinkEdit = (EditText)findViewById(R.id.favoritelink);
		favoritelinkEdit.setText(presenceInfo.getFavoriteLinkUrl());
		favoritelinkEdit.setOnLongClickListener(new BookmarkLongClickListener());
		
        ImageView photoView = (ImageView)findViewById(R.id.photo);
        if (presenceInfo.getPhotoIcon() != null) {
            byte[] data = presenceInfo.getPhotoIcon().getContent();
            Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
        	photoView.setImageBitmap(bmp);
        } else {
        	photoView.setImageResource(R.drawable.ri_default_portrait_icon);
        }

		// Set buttons callback
        Button btn = (Button)findViewById(R.id.publish_btn);
        btn.setOnClickListener(btnPublishPresenceListener);
        btn = (Button)findViewById(R.id.select_btn);
        btn.setOnClickListener(btnSelectPhotoListener);
        btn = (Button)findViewById(R.id.delete_btn);
        btn.setOnClickListener(btnDeletePhotoListener);
        
    }

    @Override
    public void onDestroy() {
    	super.onDestroy();
    	
        // Disconnect presence API
    	presenceApi.disconnectApi();
    }
    
    /**
     * Publish button listener
     */
    private OnClickListener btnPublishPresenceListener = new OnClickListener() {
        public void onClick(View v) {
        	try {
        		// Get the new presence info to be published
            	Spinner statusList = (Spinner)findViewById(R.id.availability);
            	if (statusList.getSelectedItemId() == 0) {
            		presenceInfo.setPresenceStatus(PresenceInfo.ONLINE);
            	} else {
            		presenceInfo.setPresenceStatus(PresenceInfo.OFFLINE);
            	}
                EditText freetextEdit = (EditText)findViewById(R.id.freetext);
                presenceInfo.setFreetext(freetextEdit.getText().toString());
                EditText favoritelinkEdit = (EditText)findViewById(R.id.favoritelink);
                presenceInfo.setFavoriteLinkUrl(favoritelinkEdit.getText().toString());

        		// Publish the new presence info
                if (presenceApi.setMyPresenceInfo(presenceInfo)) {
                	Utils.displayToast(PublishPresenceInfo.this, getString(R.string.label_publish_ok));
        		} else {
        			Utils.showMessageAndExit(PublishPresenceInfo.this, getString(R.string.label_publish_ko));
        		}
        	} catch(Exception e) {
    			Utils.showMessageAndExit(PublishPresenceInfo.this, getString(R.string.label_publish_ko));
        	}
        }
    };

    /**
     * Delete photo button listener
     */
    private OnClickListener btnDeletePhotoListener = new OnClickListener() {
        public void onClick(View v) {
            ImageView photoView = (ImageView)findViewById(R.id.photo);
            photoView.setImageResource(R.drawable.ri_default_portrait_icon);
            presenceInfo.setPhotoIcon(null);
        }
    };

    /**
     * Select photo button listener
     */
    private OnClickListener btnSelectPhotoListener = new OnClickListener() {
        public void onClick(View v) {
        	// Select a photo from the gallery
        	Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 96);
            intent.putExtra("outputY", 96);
            intent.putExtra("return-data", true);
            startActivityForResult(intent, SELECT_PHOTO);            
        }
    };

    /**
     * On activity result
     * 
     * @param requestCode Request code
     * @param resultCode Result code
     * @param data Data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (resultCode != RESULT_OK) {
    		return;
    	}
    	
        switch(requestCode) {
            case SELECT_PHOTO: {
            	if (data != null) {
            		// Display the selected photo
            		Bundle extras = data.getExtras();            		
            		Bitmap bmp = extras.getParcelable("data");
                    ImageView photoView = (ImageView)findViewById(R.id.photo);
                    photoView.setImageBitmap(bmp);
					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					bmp.compress(Bitmap.CompressFormat.JPEG, 75, stream);
					byte[] content = stream.toByteArray();
					PhotoIcon photoIcon = new PhotoIcon(content, bmp.getWidth(), bmp.getHeight());
                    presenceInfo.setPhotoIcon(photoIcon);
            	}
	    	}             
            break;
            case SELECT_LINK: {
            	String link = data.getStringExtra("bookmarkWebLink");
            	EditText favoritelinkEdit = (EditText)findViewById(R.id.favoritelink);
        		favoritelinkEdit.setText(link);
	    	}             
            break;
        }
    }

    /**
     * Select a predefined freetext
     */
	private class FreetextLongClickListener implements OnLongClickListener{
		public boolean onLongClick(View view) {
			Vector<String> freeTexts = new Vector<String>();

	    	// Read predefined free texts
	   		String freeText = RcsSettings.getInstance().getPredefinedFreetext1();
	    	if ((freeText != null) && (freeText.trim().length()>0)) {
    			freeTexts.addElement(freeText);
	    	}
	    	freeText = RcsSettings.getInstance().getPredefinedFreetext2();
	    	if ((freeText != null) && (freeText.trim().length()>0)) {
    			freeTexts.addElement(freeText);
	    	}    	   
	    	freeText = RcsSettings.getInstance().getPredefinedFreetext3();
	    	if ((freeText != null) && (freeText.trim().length()>0)) {
    			freeTexts.addElement(freeText);
    		}
	    	freeText = RcsSettings.getInstance().getPredefinedFreetext4();
	    	if ((freeText != null) && (freeText.trim().length()>0)) {
    			freeTexts.addElement(freeText);
	  	    }
	    	final CharSequence[] items = new CharSequence[freeTexts.size()];
	    	for (int i=0;i<freeTexts.size();i++){
	    		items[i] = freeTexts.elementAt(i);
	    	}
	    	AlertDialog.Builder builder = new AlertDialog.Builder(PublishPresenceInfo.this);
	    	builder.setTitle(getString(R.string.label_predefined_freetext));
	    	builder.setItems(items, new DialogInterface.OnClickListener(){
	    		public void onClick(DialogInterface dialog, int item){
	    	        EditText freetextEdit = (EditText)findViewById(R.id.freetext);
	    	        freetextEdit.setText(items[item].toString());
	    		}
	    	});
	    	AlertDialog alert = builder.create();
	    	alert.show();
			return true;
		}		
	}

    /**
     * Select a web link from the browser bookmark
     */
	private class BookmarkLongClickListener implements OnLongClickListener{
		public boolean onLongClick(View view) {
			Intent intent = new Intent(PublishPresenceInfo.this, BrowserBookmarksPage.class);
			startActivityForResult(intent, SELECT_LINK);
			return true;
		}
	}
}
