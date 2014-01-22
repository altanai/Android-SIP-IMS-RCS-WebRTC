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

package com.orangelabs.rcs.core.ims.service.im.filetransfer.http;

import com.orangelabs.rcs.provider.settings.RcsSettings;

/**
 * Abstract HTTP transfer manager
 *
 * @author vfml3370
 */
public abstract class HttpTransferManager {
	/**
	 * Max chunk size
	 */
	public static final int CHUNK_MAX_SIZE = 10 * 1024;

	/**
     * HTTP server address
     */
    private String serverAddr = RcsSettings.getInstance().getFtHttpServer();

	/**
     * HTTP server login
     */
    private String serverLogin = RcsSettings.getInstance().getFtHttpLogin();

    /**
     * HTTP server password
     */
    private String serverPwd = RcsSettings.getInstance().getFtHttpPassword();

    /**
     * HTTP transfer event listener
     */
    private HttpTransferEventListener listener;

    /**
     * Constructor
     * 
     * @param listener HTTP event listener
     */
    public HttpTransferManager(HttpTransferEventListener listener) {
        this.listener = listener;
    }
    
    /**
     * Returns the transfer event listener
     * 
     * @return Listener
     */
    public HttpTransferEventListener getListener() {
		return listener;
	}

    /**
     * Returns server address
     * 
     * @return Address
     */
	public String getHttpServerAddr() {
		return serverAddr;
	}

    /**
     * Returns server login
     * 
     * @return Login
     */
	public String getHttpServerLogin() {
		return serverLogin;
	}

    /**
     * Returns server password
     * 
     * @return Password
     */
	public String getHttpServerPwd() {
		return serverPwd;
	}
}
