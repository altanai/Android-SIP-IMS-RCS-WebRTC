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

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orangelabs.rcs.ri.R;

/**
 *  Custom layout for an item representing a bookmark in the browser.
 */
class BookmarkItem extends LinearLayout {

    protected TextView    mTextView;
    protected TextView    mUrlText;
    protected String      mUrl;

    /**
     *  Instantiate a bookmark item, including a default favicon.
     *
     *  @param context  The application context for the item.
     */
    BookmarkItem(Context context) {
        super(context);

        LayoutInflater factory = LayoutInflater.from(context);
        factory.inflate(R.layout.utils_browser_bookmark_item, this);
        mTextView = (TextView) findViewById(R.id.title);
        mUrlText = (TextView) findViewById(R.id.url);
    }

    /**
     *  Copy this BookmarkItem to item.
     *  @param item BookmarkItem to receive the info from this BookmarkItem.
     */
    /* package */ void copyTo(BookmarkItem item) {
        item.mTextView.setText(mTextView.getText());
        item.mUrlText.setText(mUrlText.getText());
    }

    /**
     * Return the name assigned to this bookmark item.
     */
    String getName() {
        return mTextView.getText().toString();
    }

    /**
     * Return the TextView which holds the name of this bookmark item.
     */
    TextView getNameTextView() {
        return mTextView;
    }

    String getUrl() {
        return mUrl;
    }

    /**
     *  Set the new name for the bookmark item.
     *
     *  @param name The new name for the bookmark item.
     */
    void setName(String name) {
        mTextView.setText(name);
    }
    
    /**
     *  Set the new url for the bookmark item.
     *  @param url  The new url for the bookmark item.
     */
    void setUrl(String url) {
        mUrlText.setText(url);
        mUrl = url;
    }
}
