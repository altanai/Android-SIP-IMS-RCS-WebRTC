package com.orangelabs.rcs.core.ims.service;

import com.orangelabs.rcs.core.ims.network.sip.SipMessageFactory;
import com.orangelabs.rcs.core.ims.protocol.sip.SipRequest;
import com.orangelabs.rcs.core.ims.protocol.sip.SipResponse;
import com.orangelabs.rcs.core.ims.protocol.sip.SipTransactionContext;
import com.orangelabs.rcs.utils.logger.Logger;

/**
 * Update session manager
 * 
 * @author jexa7410
 */
public class UpdateSessionManager {
	/**
	 * Session to be renegociated
	 */
	private ImsServiceSession session;

	/**
	 * The logger
	 */
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	/**
	 * Constructor
	 * 
	 * @param session Session to be refreshed
	 */
	public UpdateSessionManager(ImsServiceSession session) {
		this.session = session;
	}

    /**
     * Receive RE-INVITE request 
     * 
     * @param reInvite RE-INVITE request
     */
    public void receiveReInvite(SipRequest reInvite) {
    	// TODO: handle update session events
        try {
            if (logger.isActivated()) {
                logger.debug("Session update request received");
            }

            // Send 200 OK response
            if (logger.isActivated()) {
                logger.debug("Send 200 OK");
            }
            SipResponse resp = SipMessageFactory.create200OkReInviteResponse(session.getDialogPath(), reInvite);

            // The signalisation is established
            session.getDialogPath().sigEstablished();

            // Send response
            SipTransactionContext ctx = session.getImsService().getImsModule().getSipManager().sendSipMessageAndWait(resp);

            // Analyze the received response 
            if (ctx.isSipAck()) {
                // ACK received
                if (logger.isActivated()) {
                    logger.info("ACK request received");
                }
                // The session is established
                session.getDialogPath().sessionEstablished();
            } else {
                if (logger.isActivated()) {
                    logger.debug("No ACK received for INVITE");
                }
                // TODO ?
            }
        } catch(Exception e) {
            if (logger.isActivated()) {
                logger.error("Session update refresh has failed", e);
            }
        }
   }
}