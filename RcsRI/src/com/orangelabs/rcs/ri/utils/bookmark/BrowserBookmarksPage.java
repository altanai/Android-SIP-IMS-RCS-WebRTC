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

package com.orangelabs.rcs.ri.utils.bookmark;

import com.orangelabs.rcs.ri.R;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * View showing the user's bookmarks of the browser
 */
public class BrowserBookmarksPage extends Activity implements 
        View.OnCreateContextMenuListener {

    private BrowserBookmarksAdapter mBookmarksAdapter;
    private boolean                 mCanceled = false;
    
    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        // Set layout
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.utils_browser_bookmarks_page);
        
        // Set title
        setTitle(R.string.label_browser_bookmarks);

        mBookmarksAdapter = new BrowserBookmarksAdapter(this, 
                getIntent().getStringExtra("url"), false);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(mBookmarksAdapter);
        listView.setDrawSelectorOnTop(false);
        listView.setVerticalScrollBarEnabled(true);
        listView.setOnItemClickListener(mListener);

        listView.setOnCreateContextMenuListener(this);
    }

    private AdapterView.OnItemClickListener mListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            // It is possible that the view has been canceled when we get to
            // this point as back has a higher priority 
            if (mCanceled) {
                return;
            }
            // We come from edit web link activity, we give the result
            Intent intent = new Intent();
            intent.putExtra("bookmarkWebLink", getUrl(position));
            setResult(RESULT_OK, intent);
            finish();
        }
    };

    /**
     *  Refresh the shown list after the database has changed.
     */
    public void refreshList() {
        mBookmarksAdapter.refreshList();
    }
    
    /**
     *  Return a hashmap representing the currently highlighted row.
     */
    public Bundle getRow(int position) {
        return mBookmarksAdapter.getRow(position);
    }

    /**
     *  Return the url of the currently highlighted row.
     */
    public String getUrl(int position) {
        return mBookmarksAdapter.getUrl(position);
    }

    /**
     * Return the favicon of the currently highlighted row.
     */
    public Bitmap getFavicon(int position) {
        return mBookmarksAdapter.getFavicon(position);
    }

    public String getBookmarkTitle(int position) {
        return mBookmarksAdapter.getTitle(position);
    }

    /**
     *  Delete the currently highlighted row.
     */
    public void deleteBookmark(int position) {
        mBookmarksAdapter.deleteRow(position);
    }
}
