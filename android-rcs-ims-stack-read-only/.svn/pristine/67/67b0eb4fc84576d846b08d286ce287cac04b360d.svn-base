package com.orangelabs.rcs.core.ims.service.im.filetransfer.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.orangelabs.rcs.core.CoreException;
import com.orangelabs.rcs.core.TerminalInfo;
import com.orangelabs.rcs.core.content.MmContent;
import com.orangelabs.rcs.core.ims.network.sip.Multipart;
import com.orangelabs.rcs.core.ims.network.sip.SipUtils;
import com.orangelabs.rcs.core.ims.protocol.http.HttpAuthenticationAgent;
import com.orangelabs.rcs.core.ims.protocol.http.HttpPostRequest;
import com.orangelabs.rcs.core.ims.protocol.http.HttpRequest;
import com.orangelabs.rcs.core.ims.protocol.http.HttpResponse;
import com.orangelabs.rcs.platform.file.FileFactory;
import com.orangelabs.rcs.platform.network.NetworkFactory;
import com.orangelabs.rcs.platform.network.SocketConnection;
import com.orangelabs.rcs.utils.Base64;
import com.orangelabs.rcs.utils.HttpUtils;
import com.orangelabs.rcs.utils.logger.Logger;

/**
 * HTTP upload manager
 *  
 * @author jexa7410
 */
public class HttpUploadManager extends HttpTransferManager {
    /**
     * Boundary tag
     */
    private final static String BOUNDARY_TAG = "boundary1";
	
    /**
     * File content to upload
     */
    private MmContent content;
    
    /**
     * Thumbnail to upload
     */
    private byte[] thumbnail;
    
    /**
     * The logger
     */
    private Logger logger = Logger.getLogger(this.getClass().getName());
    
    /**
     * Constructor
     * 
     * @param content File content to upload
     * @param thumbnail Thumbnail of the file
     * @param listener HTTP transfer event listener
     */
    public HttpUploadManager(MmContent content, byte[] thumbnail, HttpTransferEventListener listener) {
        super(listener);
        
        this.content = content;
        this.thumbnail = thumbnail;
    }

    /**
     * Upload a file
     *
     * @return XML result or null if fails
     */
    public byte[] uploadFile() {
    	try {
        	if (logger.isActivated()) {
        		logger.debug("Upload file " + content.getUrl());
        	}

        	// Send a first POST request
	        return sendFirstPost();
    	} catch(Exception e) {
        	if (logger.isActivated()) {
        		logger.error("Upload file has failed", e);
        	}
    		return null;
    	}
    }
    
    /**
     * Send the first empty POST
     *
     * @return XML result or null if fails
     * @throws IOException 
     * @throws CoreException
     */
    private byte[] sendFirstPost() throws IOException, CoreException {
        // Send empty request
    	if (logger.isActivated()) {
    		logger.debug("Send first empty POST request");
    	}
        HttpPostRequest request = new HttpPostRequest(getHttpServerAddr(), null, null); 
        HttpResponse response = sendRequest(request, false);

        // Analyze the response
        int code = response.getResponseCode();
        if (code == 401) {
            // 401 AUTHENTICATION REQUIRED
        	if (logger.isActivated()) {
        		logger.debug("Receive 401 response");
        	}

            // Create the request
        	HttpPostRequest postRequest = new HttpPostRequest(getHttpServerAddr(), null, "multipart/form-data");

            // Update the authentication agent and set the cookie from the received response
            postRequest.getAuthenticationAgent().readWwwAuthenticateHeader(response.getHeader("www-authenticate"));
            String cookie = response.getHeader("set-cookie");
            postRequest.setCookie(cookie);
            postRequest.setAuthenticationAgent(new HttpAuthenticationAgent(getHttpServerLogin(), getHttpServerPwd()));

            // Send second request with authentication header and file content
        	if (logger.isActivated()) {
        		logger.debug("Send second POST request with content");
        	}
            return sendSecondPost(postRequest, true);
        } else
        if (code == 204) {
            // 204 NO CONTENT - authentication not required
        	if (logger.isActivated()) {
        		logger.debug("Receive 204 response");
        	}
        	
            // Create the request
        	HttpPostRequest postRequest = new HttpPostRequest(getHttpServerAddr(), null, "multipart/form-data");

        	// Send second request with file content
        	if (logger.isActivated()) {
        		logger.debug("Send second POST request with content");
        	}
            return sendSecondPost(postRequest, false);
        } else {
        	// Error
        	if (logger.isActivated()) {
        		logger.debug("Receive " + code + " error");
        	}
        	return null;
        }

        // TODO: retry procedure not implemented in the stack
    }
    
    /**
     * Send the second POST with content
     *
     * @param postRequest POST request
     * @param authenticate Authentication needed
     * @return XML result or null if fails
     * @throws IOException 
     * @throws CoreException
     */
    private byte[] sendSecondPost(HttpPostRequest postRequest, boolean authenticate) throws IOException, CoreException {
    	HttpResponse response = sendRequest(postRequest, authenticate);
    	if ((response != null) && (response.getResponseCode() == 200)) {
    		return response.getContent();
    	} else {
    		return null;
    	}
    }
    
    /**
     * Send HTTP request
     *
     * @param request HTTP request
     * @param authenticate Authentication needed
     * @return HTTP response
     * @throws IOException
     * @throws CoreException
     */
    private HttpResponse sendRequest(HttpRequest request, boolean authenticate) throws IOException, CoreException {
        // Extract host & port
        String[] parts = request.getUrl().split(":");
        String host = parts[0];
        int port = Integer.parseInt(parts[1]);
        String serviceRoot = "";
        if (parts.length > 2) {
            serviceRoot = "/" + parts[2];
        }

        // Open connection with the HTTP server
        SocketConnection conn = NetworkFactory.getFactory().createSocketClientConnection();
        conn.open(host, port);
        InputStream is = conn.getInputStream();
        OutputStream os = conn.getOutputStream();

        // Create the HTTP request
        String requestUri = serviceRoot + request.getUrl();
        String httpRequest = request.getMethod() + " " + requestUri + " HTTP/1.1" + HttpUtils.CRLF
                + "Host: " + host + ":" + port + HttpUtils.CRLF
                + "User-Agent: " + TerminalInfo.getProductName() + " " + TerminalInfo.getProductVersion()
                + HttpUtils.CRLF;
        if (authenticate) {
            // Set the Authorization header
            String authorizationHeader = request.getAuthenticationAgent()
                    .generateAuthorizationHeader(request.getMethod(), requestUri, request.getContent());
            httpRequest += authorizationHeader + HttpUtils.CRLF;
        }

        String cookie = request.getCookie();
        if (cookie != null) {
            // Set the cookie header
            httpRequest += "Cookie: " + cookie + HttpUtils.CRLF;
        }

        if (request.getContent() != null) {
            // Set the content type
            httpRequest += "Content-type: " + request.getContentType() + HttpUtils.CRLF;
            httpRequest += "Content-Length:" + request.getContentLength() + HttpUtils.CRLF + HttpUtils.CRLF;
        } else {
            httpRequest += "Content-Length: 0" + HttpUtils.CRLF + HttpUtils.CRLF;
        }

        // Write HTTP request headers
        os.write(httpRequest.getBytes());
        os.flush();

        // Write HTTP content
        if (request.getContentType() != null) {
            // Write thumbnail part
            String multipart = Multipart.BOUNDARY_DELIMITER + BOUNDARY_TAG + SipUtils.CRLF;
            os.write(multipart.getBytes());
            os.flush();

            if (thumbnail != null) {
                // Encode the thumbnail file
                String imageEncoded = Base64.encodeBase64ToString(thumbnail);
                multipart = "Content-Disposition: form-data;name=\"Thumbnail\";filename=\" " + content.getName() + "\"" + SipUtils.CRLF +
                        "Content-Type: " + content.getEncoding() + SipUtils.CRLF + SipUtils.CRLF +
                        imageEncoded + SipUtils.CRLF;
	            os.write(multipart.getBytes());
	            os.flush();
            }

            // Write file part
            multipart = Multipart.BOUNDARY_DELIMITER + BOUNDARY_TAG + SipUtils.CRLF +
                    "Content-Disposition: form-data;name=\"File\"; filename=\" " + content.getName() + "\"" + SipUtils.CRLF +
                    "Content-Type: " + content.getEncoding() + SipUtils.CRLF + SipUtils.CRLF;
            os.write(multipart.getBytes());
            os.flush();
                    
	        InputStream inputStream = FileFactory.getFactory().openFileInputStream(content.getUrl());
			long totalSize = content.getSize();
			long transferedSize = 0;
			boolean cancelTransfer = false;
	        byte data[] = new byte[HttpTransferManager.CHUNK_MAX_SIZE];
			for (int i = inputStream.read(data); (!cancelTransfer) & (i>-1); i=inputStream.read(data)) {
				// Send a chunk
	            os.write(data, (int)transferedSize, i);
	            os.flush();
	
				// Update upper byte range
	            transferedSize += i;
	            
	    		// Notify transfer event listener
	            getListener().httpTransferProgress(transferedSize, totalSize);
			}

			// Write end of multipart
            multipart = SipUtils.CRLF + Multipart.BOUNDARY_DELIMITER +
            		BOUNDARY_TAG + Multipart.BOUNDARY_DELIMITER;
            os.write(multipart.getBytes());
            os.flush();
        }
        
        // Read HTTP headers response
        HttpResponse response = new HttpResponse();
        int ch = -1;
        String line = "";
        while ((ch = is.read()) != -1) {
            line += (char) ch;
            if (line.endsWith(HttpUtils.CRLF)) {
                if (line.equals(HttpUtils.CRLF)) {
                    // All headers has been read
                    break;
                }
                // Remove CRLF
                line = line.substring(0, line.length() - 2);
                if (line.startsWith("HTTP/")) {
                    // Status line
                    response.setStatusLine(line);
                } else {
                    // Header
                    int index = line.indexOf(":");
                    String name = line.substring(0, index).trim().toLowerCase();
                    String value = line.substring(index + 1).trim();
                    response.addHeader(name, value);
                }
                line = "";
            }
        }

        // Extract content length
        int length = -1;
        try {
            String value = response.getHeader("content-length");
            length = Integer.parseInt(value);
        } catch (Exception e) {
            length = -1;
        }

		// Read HTTP content response
		if (length > 0) {
			byte[] content = new byte[length];
			int nb = -1;
			int pos = 0;
			byte[] buffer = new byte[1024];
			while((nb = is.read(buffer)) != -1) {
				System.arraycopy(buffer, 0, content, pos, nb);
				pos += nb;
				
				if (pos >= length) {
					// End of content
					break;
				}
			}
			response.setContent(content);
		}

        // Close the connection
        is.close();
        os.close();
        conn.close();

        return response;
    }    
}
