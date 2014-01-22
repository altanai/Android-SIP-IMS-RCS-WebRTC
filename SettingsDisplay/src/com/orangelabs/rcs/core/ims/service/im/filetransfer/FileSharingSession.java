package com.orangelabs.rcs.core.ims.service.im.filetransfer;

import com.orangelabs.rcs.core.content.MmContent;
import com.orangelabs.rcs.core.ims.service.ImsService;
import com.orangelabs.rcs.core.ims.service.ImsServiceSession;
import com.orangelabs.rcs.provider.settings.RcsSettings;

/**
 * Abstract file sharing session 
 * 
 * @author jexa7410
 */
public abstract class FileSharingSession extends ImsServiceSession {
	/**
	 * Content to be shared
	 */
	private MmContent content;
	
	/**
	 * File transfered
	 */
	private boolean fileTransfered = false;

	/**
	 * Thumbnail
	 */
	private byte[] thumbnail = null;

    /**
	 * Constructor
	 * 
	 * @param parent IMS service
	 * @param content Content to be shared
	 * @param contact Remote contact
	 * @param thumbnail Thumbnail
	 */
	public FileSharingSession(ImsService parent, MmContent content, String contact, byte[] thumbnail) {
		super(parent, contact);
		
		this.content = content;
		this.thumbnail = thumbnail;
	}
	
	/**
	 * Returns the content
	 * 
	 * @return Content 
	 */
	public MmContent getContent() {
		return content;
	}
	
	/**
	 * Set the content
	 * 
	 * @param content Content  
	 */
	public void setContent(MmContent content) {
		this.content = content;
	}	
	
	/**
	 * Returns the "file-transfer-id" attribute
	 * 
	 * @return String
	 */
	public String getFileTransferId() {
		return "" + System.currentTimeMillis();
	}	
	
	/**
	 * File has been transfered
	 */
	public void fileTransfered() {
		this.fileTransfered = true;
	}
	
	/**
	 * Is file transfered
	 * 
	 * @return Boolean
	 */
	public boolean isFileTransfered() {
		return fileTransfered; 
	}
		
	/**
	 * Returns max file sharing size
	 * 
	 * @return Size in bytes
	 */
	public static int getMaxFileSharingSize() {
		return RcsSettings.getInstance().getMaxFileTransferSize()*1024;
	}

    /**
     * Returns the thumbnail
     * 
     * @return Thumbnail
     */
    public byte[] getThumbnail() {
    	return thumbnail;
    }

    /**
     * Set the thumbnail
     *
     * @param Thumbnail
     */
    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }
}
