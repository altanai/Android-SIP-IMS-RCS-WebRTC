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

import java.util.Vector;

import com.orangelabs.rcs.core.Core;
import com.orangelabs.rcs.core.content.MmContent;
import com.orangelabs.rcs.core.ims.service.ImsService;
import com.orangelabs.rcs.core.ims.service.im.chat.ChatSession;
import com.orangelabs.rcs.core.ims.service.im.chat.ChatUtils;
import com.orangelabs.rcs.core.ims.service.im.chat.cpim.CpimMessage;
import com.orangelabs.rcs.core.ims.service.im.filetransfer.FileSharingError;
import com.orangelabs.rcs.core.ims.service.im.filetransfer.FileSharingSessionListener;
import com.orangelabs.rcs.utils.logger.Logger;

/**
 * Originating file transfer HTTP session
 *
 * @author vfml3370
 */
public class OriginatingHttpFileSharingSession extends HttpFileTransferSession implements HttpTransferEventListener {

    /**
     * HTTP upload manager
     */
    private HttpUploadManager uploadManager;

    /**
     * The logger
     */
    private Logger logger = Logger.getLogger(this.getClass().getName());

	/**
	 * Constructor
	 *
	 * @param parent IMS service
	 * @param content Content to be shared
	 * @param contact Remote contact
	 * @param thumbnail Thumbnail
	 */
	public OriginatingHttpFileSharingSession(ImsService parent, MmContent content, String contact, byte[] thumbnail) {
		super(parent, content, contact, thumbnail);
		
		// Instanciate the upload manager
		uploadManager = new HttpUploadManager(getContent(), getThumbnail(), this);
	}

	/**
	 * Background processing
	 */
	public void run() {
		try {
	    	if (logger.isActivated()) {
	    		logger.info("Initiate a new HTTP file transfer session as originating");
	    	}

	    	// Upload the file to the HTTP server 
            byte[] result = uploadManager.uploadFile();
            if (result != null) {
            	String fileInfo = new String(result);
                
                if (logger.isActivated()) {
                    logger.debug("Upload done with success: " + fileInfo);
                }
                
				// Send the file transfer info via a chat message
				Vector<ChatSession> chatSessions = Core.getInstance().getImService().getImSessionsWith(getRemoteContact());
				if (chatSessions.size() > 0) {
					// A chat session exists
	                if (logger.isActivated()) {
	                    logger.debug("Send file transfer info via an existing chat session");
	                }

	                // Get the last chat session in progress to send file transfer info
					ChatSession chatSession = chatSessions.lastElement();
					String mime = CpimMessage.MIME_TYPE;
					String from = ChatUtils.ANOMYNOUS_URI;
					String to = ChatUtils.ANOMYNOUS_URI;
					String msgId = ChatUtils.generateMessageId();

					// Send file info in CPIM message
					String content = ChatUtils.buildCpimMessage(from, to, fileInfo, FileTransferHttpInfoDocument.MIME_TYPE);

					// Send content
					chatSession.sendDataChunks(msgId, content, mime);
				} else {
					// A chat session should be initiated
	                if (logger.isActivated()) {
	                    logger.debug("Send file transfer info via a new chat session");
	                }

	                // Initiate a new chat session to send file transfer info in the first message
	            	Core.getInstance().getImService().initiateOne2OneChatSession(getRemoteContact(), fileInfo);
				}
				
		        // Notify listeners
		        for(int j=0; j < getListeners().size(); j++) {
		            ((FileSharingSessionListener)getListeners().get(j)).handleFileTransfered(getContent().getUrl());
		        }

		        // TODO: monitor the result on the chat message?
				
			} else {
                if (logger.isActivated()) {
                    logger.debug("Upload has failed");
                }
                
                // Upload error
    			handleError(new FileSharingError(FileSharingError.MEDIA_UPLOAD_FAILED));
			}
		} catch(Exception e) {
        	if (logger.isActivated()) {
        		logger.error("File transfer has failed", e);
        	}

        	// Unexpected error
			handleError(new FileSharingError(FileSharingError.UNEXPECTED_EXCEPTION, e.getMessage()));
		}
	}

    /**
     * HTTP transfer progress
     *
     * @param currentSize Current transfered size in bytes
     * @param totalSize Total size in bytes
     */
	public void httpTransferProgress(long currentSize, long totalSize) {
        // Notify listeners
        for(int j=0; j < getListeners().size(); j++) {
            ((FileSharingSessionListener)getListeners().get(j)).handleTransferProgress(currentSize, totalSize);
        }
    }
}
