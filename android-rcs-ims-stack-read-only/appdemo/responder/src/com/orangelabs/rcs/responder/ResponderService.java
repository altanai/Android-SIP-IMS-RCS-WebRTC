package com.orangelabs.rcs.responder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.orangelabs.rcs.service.api.client.ClientApiListener;
import com.orangelabs.rcs.service.api.client.messaging.GeolocMessage;
import com.orangelabs.rcs.service.api.client.messaging.IChatEventListener;
import com.orangelabs.rcs.service.api.client.messaging.IChatSession;
import com.orangelabs.rcs.service.api.client.messaging.InstantMessage;
import com.orangelabs.rcs.service.api.client.messaging.MessagingApi;

/**
 * Responder service
 * 
 * @author jexa7410
 */
public class ResponderService extends Service implements ClientApiListener {
	private static final String TAG = "ResponderService";
	
	/**
	 * Messaging API
	 */
	private MessagingApi messagingApi = null;

	/**
	 * Session ID
	 */
	private String sessionId = null;
	
	/**
	 * Auto accept procedure
	 */
	private boolean autoAccept = false;

	/**
	 * Responder message
	 */
	private String message = null;

	/**
     * Chat session 
     */
	private IChatSession chatSession = null;

	@Override
    public void onCreate() {
		// Instanciate messaging API
		messagingApi = new MessagingApi(getApplicationContext());
        messagingApi.addApiEventListener(this);
        messagingApi.connectApi();
		Log.v(TAG, "Connect to RCS API");
    }

    @Override
    public void onDestroy() {
		// Disconnect messaging API
		Log.v(TAG, "Disconnect from RCS API");
    	if (messagingApi != null) {
    		messagingApi.removeApiEventListener(this);
    		messagingApi.disconnectApi();
    		messagingApi = null;
    	}
    }	
    
    @Override
    public IBinder onBind(Intent intent) {
    	return null;
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Get intent info
        sessionId = intent.getStringExtra("sessionId");
        autoAccept = intent.getBooleanExtra("autoAccept", false);
        message = intent.getStringExtra("message");
		Log.v(TAG, "Receive new session " + sessionId);
        
        return START_NOT_STICKY;
    }    

    /**
     * API disabled
     */
    public void handleApiDisabled() {
		Log.v(TAG, "Disconnected from RCS API");
    }
    
    /**
     * API connected
     */
    public void handleApiConnected() {
		Log.v(TAG, "Connected to RCS API");

		try {
			if (sessionId == null) {
				Log.v(TAG, "Session ID not found: exit service");
				stopSelf();
				return;
			}

			// Get session
			chatSession = messagingApi.getChatSession(sessionId);
			if (chatSession == null) {
				Log.v(TAG, "Chat session not found: exit service");
				stopSelf();
				return;
			}

			// Add session listener event
			chatSession.addSessionListener(chatSessionListener);

			// Auto accept session
	        if (!autoAccept) {
				Log.v(TAG, "Auto accept the session");
	        	chatSession.acceptSession();
	        }
		} catch(Exception e) {
			Log.e(TAG, "Unexpected exception");
			e.printStackTrace();
		}
    }

    /**
     * API disconnected
     */
    public void handleApiDisconnected() {
		Log.v(TAG, "Disconnected from RCS API");

		// Stop the service
		Log.v(TAG, "Stop service");    	
    	stopSelf();
    }  
    
    /**
     * Chat session event listener
     */
    private IChatEventListener chatSessionListener = new IChatEventListener.Stub() {
		// Session is started
		public void handleSessionStarted() {
			Log.v(TAG, "Session started");

			try {
				// Send responder message
				Log.v(TAG, "Send responder message: " + message);
	        	chatSession.sendMessage(getString(R.string.label_message_prefix) + message);

	        	// End of session
	        	new QuitTask().start();
			} catch(Exception e){
				Log.e(TAG, "Can't send message");
				e.printStackTrace();
			}
		}
	
		// Session has been aborted
		public void handleSessionAborted(int reason) {
			// Nothing to do
		}
	    
		// Session has been terminated by remote
		public void handleSessionTerminatedByRemote() {
			// Nothing to do
		}
		
		// New text message received
		public void handleReceiveMessage(final InstantMessage msg) {
			// Nothing to do
		}		
				
		// Chat error
		public void handleImError(final int error) {
			// Nothing to do
		}	
		
		// Is composing event
		public void handleIsComposingEvent(String contact, final boolean isComposing) {
			// Nothing to do
		}

		// Conference event
	    public void handleConferenceEvent(final String contact, final String contactDisplayname, final String state) {
			// Nothing to do
		}
	    
		// Message delivery status
		public void handleMessageDeliveryStatus(final String msgId, final String status) {
			// Nothing to do
		}
		
		// Request to add participant is successful
		public void handleAddParticipantSuccessful() {
			// Nothing to do
		}
	    
		// Request to add participant has failed
		public void handleAddParticipantFailed(String reason) {
			// Nothing to do
		}
		
		// New geoloc message received
		public void handleReceiveGeoloc(final GeolocMessage geoloc) {
			// Nothing to do
		}
    };

    /**
     * Quit session task
     */
    private class QuitTask extends Thread {
		public void run() {
        	try {
	    		// Stop the session
	    		Log.v(TAG, "Quit the session");
	        	chatSession.cancelSession();

	        	// Exit service
	        	stopSelf();
        	} catch(Exception e) {
				Log.e(TAG, "Can't quit the session");
				e.printStackTrace();
        	}
		}
    };
}
