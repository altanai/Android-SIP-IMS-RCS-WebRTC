package com.orangelabs.rcs.core.ims.service.im.filetransfer.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.orangelabs.rcs.core.CoreException;
import com.orangelabs.rcs.core.TerminalInfo;
import com.orangelabs.rcs.core.content.MmContent;
import com.orangelabs.rcs.core.ims.protocol.http.HttpAuthenticationAgent;
import com.orangelabs.rcs.core.ims.protocol.http.HttpGetRequest;
import com.orangelabs.rcs.core.ims.protocol.http.HttpRequest;
import com.orangelabs.rcs.core.ims.protocol.http.HttpResponse;
import com.orangelabs.rcs.platform.network.NetworkFactory;
import com.orangelabs.rcs.platform.network.SocketConnection;
import com.orangelabs.rcs.utils.HttpUtils;
import com.orangelabs.rcs.utils.logger.Logger;

/**
 * HTTP upload manager
 * 
 * @author jexa7410
 */
public class HttpDownloadManager extends HttpTransferManager {
    /**
     * File content to upload
     */
    private MmContent content;
	
    /**
     * The logger
     */
    private Logger logger = Logger.getLogger(this.getClass().getName());
    
    /**
     * Constructor
     * 
     * @param content File content to download
     * @param listener HTTP transfer event listener
     */
    public HttpDownloadManager(MmContent content, HttpTransferEventListener listener) {
        super(listener);
        
        this.content = content;
    }
    
    /**
     * Download file
     * 
     * @return Boolean result. Data are saved during the transfer in the content object.  
     */
    public boolean downloadFile() {
        try {
        	if (logger.isActivated()) {
        		logger.debug("Download file " + content.getUrl());
        	}

        	// Send a first GET request
        	if (logger.isActivated()) {
        		logger.debug("Send first GET request");
        	}
            HttpGetRequest request = new HttpGetRequest(content.getUrl());
            HttpResponse response = sendRequest(request, false);

            // Analyze the response
            int code = response.getResponseCode();
            if (code == 401) {
                // 401 AUTHENTICATION REQUIRED
            	if (logger.isActivated()) {
            		logger.debug("Receive 401 response");
            	}

                request = new HttpGetRequest(content.getUrl());

                // Update the authentication agent and set the cookie from the received response
                request.getAuthenticationAgent().readWwwAuthenticateHeader(response.getHeader("www-authenticate"));
                String cookie = response.getHeader("set-cookie");
                request.setCookie(cookie);
                request.setAuthenticationAgent(new HttpAuthenticationAgent(getHttpServerLogin(), getHttpServerPwd()));

                // Send second request with authentication header and file content
                response = sendRequest(request, true);
                if (response.getResponseCode() == 200) {
                    // 200 OK
                	if (logger.isActivated()) {
                		logger.debug("Receive 200 response");
                	}
                	return true;
                } else {
                	if (logger.isActivated()) {
                		logger.debug("Receive " + code + " error");
                	}
                	return false;
                }
            } else
            if (code == 200) {
                // 200 OK
            	if (logger.isActivated()) {
            		logger.debug("Receive 200 response");
            	}
                return true;
            } else {
            	// Error
            	if (logger.isActivated()) {
            		logger.debug("Receive " + code + " error");
            	}
            	return false;
            }

            // TODO: retry procedure not implemented in the stack
        
        } catch(Exception e) {
            return false;
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
        String[] parts = request.getUrl().substring(7).split(":|/");
        String host = parts[0];
        int port = Integer.parseInt(parts[1]);
        String serviceRoot = "";
        if (parts.length > 2) {
            serviceRoot = "/" + parts[2];
        }

        // Open connection with the FileTransfer HTTP server
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
                    .generateAuthorizationHeader(request.getMethod(), requestUri,
                            request.getContent());
            httpRequest += authorizationHeader + HttpUtils.CRLF;
        }

        String cookie = request.getCookie();
        if (cookie != null) {
            // Set the cookie header
            httpRequest += "Cookie: " + cookie + HttpUtils.CRLF;
        }

        httpRequest += "Content-Length: 0" + HttpUtils.CRLF + HttpUtils.CRLF;

        // Write HTTP request headers
        os.write(httpRequest.getBytes());
        os.flush();

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
			int nb = -1;
			int pos = 0;
			byte[] buffer = new byte[1024];
			while((nb = is.read(buffer)) != -1) {
				pos += nb;

	        	// Update content with received data
	            content.writeData2File(buffer);
				
                // Notify listener
            	getListener().httpTransferProgress(pos, length);

            	if (pos >= length) {
					// End of content
					break;
				}
			}
		}
        
        // Close the connection
        is.close();
        os.close();
        conn.close();

        return response;
    }
}
