README - RCS Android client
Release 2.5.5

The RCS stack is under Apache 2 license (see LICENSE-2.0.txt) and uses the following open source librairies:
 - NIST SIP: see LICENSE-NIST.txt.
 - DNS Java: see LICENSE-DNS.txt.


News:
-----

- Add Neusoft contributions.
- FT Store & Forward.
- Remove H263 codec and prerecorded feature.
- Invites missing participants on incoming group chat request.


Bugs fixed:
-----------

See Mantis details at http://container.rd.francetelecom.com
See Opensource issue details at http://code.google.com/p/android-rcs-ims-stack/issues/list


v2.5.5
- Mantis #0017281	Stack: "To" URI should be "anonymous" in group chat messages
- Mantis #0017280	Stack: On chat restart don't reinvite participants that were departed
- Trello #45		Stack: Fix DE-REGISTER issue if core is stopped - https://trello.com/c/dPVYlvPz

v2.5.4
- Mantis #0016927	Stack: CSeq value not well incremented on REFER after a 407
- Mantis #0016935	Stack: Request capabilities fail in session
- Open source #107	Stack: Memory leak on video decoder
- Mantis #0017155	Stack: Bug Autoprovisioning (GSMA ID_RCSE_1_2_5)
- Deutsche Telekom	Stack: Video JavaDepacktizer and codec issue

v2.5.3
- Mantis #0016925	Stack: Parameter client_version not well formated in HTTP provisioning request
- ID_2_4 Clarification on HTTP configurations parameters and white listing
- Mantis #0016915	Stack: Video sharing must not be available in EDGE
- Mantis #0016917	Stack: Camera size CIF not supported on LG Optimus. Codec list must be dependant of supported camera sizes in originating side.

v2.5.2
- Mantis #0016862	Stack: No displayed IMDN report for geoloc message
- Mantis #0016866	Stack: Service timer must be 120s (not 60s)
- Mantis #0016859	Stack: Parameter TimerIdle should be only used for 1-1 chat
- Deutsche Telekom	Stack: Management of packetization-mode 0

v2.5.1
- Mantis #0016859	Stack: Parameter TimerIdle should be only used for 1-1 chat
- Mantis #0016852	Stack: Max length of a group chat message not taken into account
- Mantis #0016788	Stack: Autoprovisioning of IMS domain not well extracted
- Mantis #0016833	Stack: re-REGISTER should use the default expire value (600000s) instead the received one in 200 OK
- Mantis #0010397	Stack: Check size of FT and IS with max in RCS settings

v2.5.0
- Mantis #0016782	Stack: Difference in progress for file transfer between originating and terminating
- Mantis #0016779	Stack: When a session invitation has no response from the end user a 486 error should be sent instead of a 603
- Mantis #0016616	Stack: QCIF video size from camera not always supported
- Mantis #0016775	Stack: Codec chain processing error
- Mantis #0016761	Stack: NullPointerException when transfering an unkown file MIME type
- Mantis #0016717	Stack: Reason code "Call completed" should be sent only if the session is terminated by the end user itself
- Mantis #0016716	Stack: Stack doesn't restart in some cases
- Mantis #0016715	Stack: Application exception not well catched by AIDL server part in listener methods

v2.4.12
- Mantis #0016653	Stack: SDP small h is used instead of H for H.264 format
- GSMA Guidelines 	Stack: ID_4_36: Idle group chat sessions should be cleared from the controlling function
- GSMA Guidelines 	Stack: ID_4_35: Include a Reason Code in the BYE requests
- Mantis #0016645	Stack: Bad nonce count value after 401 response during registration
- Deutsche Telekom	Stack: Improve MSRP failure reports handling
- Mantis #0016636	Stack: Contribution-ID is null when extending a 1-1 chat session to a group chat session
- Mantis #0016615	Stack: Potential issue with list of group chat participants
- Open source #63	Stack: Request rejected because IP doesn't match (NAT)
- Deutsche Telekom	Stack: Save FT in files to avoid Out-of-Memory problem
- Mantis #0016512	Stack: Separate "realm" and "home domain" in the IMS parameters
- Deutsche Telekom	Stack: Support of MSRP chunk Byte-Range with "*" (e.g. 1-*/22123)
- Mantis #0015046	Stack: Adressbook issue on Samsung 2.3.6


v2.4.11
- Mantis #0016549	Stack: Can't test APN under OS 4.2
- Mantis #0016402	Stack: No transport in Contact header so IMS switch to UDP instead of TCP for terminating usecases
- Mantis #0016401	Stack: Bad entry in the address book after the initial capability check for contacts without first or last name
- Mantis #0016370	Stack: Bad file transfer API event after a session cancel

v2.4.10
- Open source #85	Stack: PUBLISHed presence document contains illegal XML
- Mantis #0016362	Stack: Bad local tag on 487 response after a CANCEL request


v2.4.9
- GSMA Guidelines 	Stack: ID_4_33_1: Multidevice support
- GSMA Guidelines 	Stack: ID_4_11: Redo registration if 403 and no warning header
- Mantis #0016114	Stack: If a user is booted he's not removed from the list of connected participants
- Mantis #0016112	Stack: No subject added in the invitation when adding a participant to a group chat
- Mantis #0016111	Stack: Duplicate entries in chat history if a participant restart a session
- Mantis #0016098	Stack: Restart failed for a participant: bad URI list of participants
- Mantis #0016108	Stack: Restart group chat don't invite participants who have been invited after the initial invitation
- Mantis #0016125	Stack: After a FT session timeout a 603 error is sent by the stack even if the session has been cancelled by the server

v2.4.8
- GSMA Guidelines 	Stack: ID_4_21_1: Clarification on a group chat initiation
- GSMA Guidelines 	Stack: ID_4_21_2: Clarification on a group chat automatic re-join
- GSMA Guidelines 	Stack: ID_4_21_3: Clarification on a group chat re-start
- GSMA Guidelines 	Stack: ID_4_21_4: Clarification on a abandoning a group chat
- GSMA Guidelines 	Stack: ID_4_21_5: Re-joining or re-starting a chat that the user has previously abandoned
- GSMA Guidelines 	Stack: ID_4_21_6: Clarifications on adding participants to a Group Chat
- GSMA Guidelines 	Stack: ID_4_21_7: Clarifications on Contribution-ID value
- GSMA Guidelines 	Stack: ID_4_21_8: List of participants
- GSMA Guidelines 	Stack: ID_4_21_9: Chat autoaccept setting
- GSMA Guidelines 	Stack: ID_4_21_10: Clarifications on Closing Group Chat
- Mantis #0016012	Stack: This header "Require: recipient-list-invite" is missing from INVITE when extending a chat
- Mantis #0016062	Stack: Max size should not be used in SDP if it's value is 0

v2.4.7
- Mantis #0014730	Stack: Race problem : Receiving SIP BYE when session is being stopped by other thread
- GSMA Guidelines 	Stack: ID_4_20: Optimization on the options exchange during a call
- Mantis #0015013 	Stack: Send an OPTIONS if bearer has changed during a call
- Open source #78	Stack: How to compile the RCS code
- GSMA Guidelines 	Stack: ID_4_32: File transfer auto accept
- GSMA Guidelines 	Stack: ID_3_1: RCS client detection
- Mantis #0015496	Stack: DNS problems after network switch
- GSMA Guidelines 	Stack: ID_4_32 Auto accept FT
- Mantis #0015832	Stack: Bad SMS fallback parameter parsing from provisioning
- Open source #77	Stack: Potential cursor leak
- Mantis #0015815	Stack: API method addParticipants don't support only one participant in its list
- Mantis #0015691	Stack: Some updates on session may have an expire set to 0
- Mantis #0015693	Stack: When using rejoinChatGroupSession() function, the chat session object has a different chat session id than the one given in parameter

v2.4.6
- Mantis #0015656	Stack: P-Preferred-URI should contain a Tel-URI if both SIP-URI and Tel-URI are provisioned
- Mantis #0015651	Stack: No anonymous URI in CPIM From/To of the SIP MESSAGE report
- Mantis #0015636	Stack: No accept-wrapped-type for chat

v2.4.5
- Mantis #0015507	Stack: Multipart parser exception if no Content-Length header
- Mantis #0015457	Stack: Bad nonce count value in REGISTER after having change of bearer
- GSMA Guidelines 	Stack: ID_5_4 FT chunk size
- Open source #70	Stack: Failure to send response to End User Confirmation request

v2.4.4
- Mantis #0015350	Stack: Hot SIM swap don't reset the user account
- Open source #66	Stack: EndUserConfirmationResponse document - unexpected element NewDataSet
- GSMA Guidelines 	Stack: ID_4_5 Network time for chat
- Mantis #0015303	Stack: Phone number not well formated into international format if the area code is null
- DEV #12	 Provisioning: Config Deletion - (Version 0)
- DEV #16	 Provisioning: SIM2 uses the XML configuration of SIM1
- DEV #17	 Provisioning: SIM2 uses the XML configuration of SIM1
- DEV #21	 Provisioning: Missing parameters in HTTP request (403 error)
- DEV #22	 Provisioning: Missing parameters in HTTPS request (403 error)


v2.4.3
- Mantis #0015155	Stack: If airplane mode the RCS provisioning doesn't start after activating the network
- Mantis #0015030	Stack: Block the RCS service until terms & conditions are accepted
- Mantis #0015208	Stack: Media codec offer propose only the preferred codec instead of all the supported codecs
- Open source #57	Stack: MESSAGE (IMDN) response failed with 404 on S&F session message
- Mantis #0015173	Stack: Deprecated image sharing feature tag	
- Mantis #0015165	Stack: Media codec negociation analyzes only the first proposition on terminating side
- GSMA Guidelines 	Stack: ID_4_16 Video interoperability: H264 profile 1b encoding

v2.4.2
- Open source #62	Stack: API inconsistency - ImsApi does not follow the same usage pattern as other *Api classes
- Mantis #0015081	Stack: Content type "message/CPIM" should be not case sensitive for incoming SIP MESSAGE
- Mantis #0015080	Stack: Message ID header should be after From/To headers in MSRP REPORT request
- Mantis #0015079	Stack: Partial MSRP REPORT not well aggregated
- Open source #60	Stack: Partial SRV implementation
- Mantis #0015068	Stack: There is no response to wait on MSRP REPORT request
- Mantis #0015067	Stack: Min session expire not saved after 422 error
- Mantis #0015066	Stack: Bad Cseq after 422 Session Interval Too Small
- Mantis #0015027	Stack: Profile-level-id=42900B not supported by packet video software codec
- GSMA Guidelines 	Stack: ID_4_X Negative IMDN notifications
- GSMA Guidelines 	Stack: ID_4_4 Hiding identities in CPIM/IMDN
- GSMA Guidelines 	Stack: ID_2_1 FQDN resolution

v2.4.1
- Mantis #0015011	Stack: Bad Cseq number for subsequent request sent by terminating side
- Mantis #0015019	Stack: Bad naming for parameter Private_User_Identity
- Mantis #0015023	Stack: Send a SIP OPTIONS only when CS call is connected
- Mantis #0015012	Stack: If remote MSISDN is hidden don't try to send an OPTIONS
- Mantis #0015010	Stack: GSMA guidelines: for richcall send only one OPTIONS from the terminating side
- Mantis #0015009	Stack: Blocked contact don't send "delivered" notification in 1-1 chat
- Mantis #0015008	Stack: Date time reference not the same between first message in SIP and next messages via MSRP
- Mantis #0015007	Stack: Several contact not supported in 200 OK of REGISTER
- Mantis #0015006	Stack: Anonymous "From" not taken into account by 1-1 chat
- Mantis #0015005	Stack: Bad XML grammar for IMDN displayed notification
- Mantis #0015004	Stack: Bad XML grammar for is-composing document
- Mantis #0015003	Stack: Mime type should be "video/mp4" instead of "video/mpeg4"
- GSMA Guidelines 	Stack: ID_1_6 Units employed for the File Transfer and Image Share configuration parameters


v2.4.0
- Mantis #0014674	Stack: IOT with Nokia needs to manage partial MSRP reports
- Mantis #0014671	Stack: Send chunk not well synchronized
- Mantis #0014491	Stack: Blocked file transfer is not added in the event log
- Mantis #0010522	Stack: Bad SIP retransmission timer for 3G
- Open source #55	Stack: End User Confirmation Request without "pin" attribute fails
- Open source #54	Stack: 423 Interval Too Brief retry CSeq issue
- Open source #52	Stack: Group chat issue
- Open source #50	Stack: Unsupported Media Type in case of lack of 'type' or 'size' selector in file-selector attribute
- Open source #34	Stack: Trouble processing "javax/sip/address/Address.class"

v2.3.9
- Mantis #0014401	Stack: 500 invalid SDP receive from IMS with special characters in Subject header
- Mantis #0014016	Chat app: The "display" notifications are not all send by the terminal at screen activation


v2.3.8
- Mantis #0014493	Stack: Bad URL encoding for HTTPS provisioning if space in the vendor name
- Open source #51	Stack: Bad interpretation of ChatAuth configuration parameter
- Open source #46	Stack: Content-Leght calculation for non latin characters
- Mantis #0014438	Stack: Reject requests having a different IP address than the registered IP address


v2.3.7
- Mantis #0014419	Stack: MNC code not well fomatted for HTTPS provisioning
- Mantis #0014365	Stack: Refresh all capabilities crash
- Mantis #0014342	Stack: Stack ANR on unregistration
- Mantis #0014341	Stack: Same route path twice in a Route header
- Open source #49	Stack: Image Share: no empty MSRP message when receiver initiates MSRP connection
- Open source #47	Stack: Conference URI configuration

v2.3.6
- Mantis #0014241	Stack: Infinite loop on SIP OPTIONS after SIM card exchange
- Mantis #0014328	Stack: Bad content length in multipart SDP
- Mantis #0014327	Stack: No "rport" sent in SIP request
- Open source #44	Stack: Reregistration bad interval in case registry expiration is over 1200

v2.3.5
- Mantis #0014193	Stack: No register sent with LG P920
- Mantis #0014175	Stack: Never sends response to MSRP REPORT request
- Mantis #0014167	Stack: With empty MSRP packet no 200 OK is sent when requested
- Mantis #0013359	Stack: MSRP parser don't support no byte-range header for non chunked data transfer
- Open source #39	Stack: Content-Type capital T in cpim message not supported
- Open source #36	Stack: GRUU support

v2.3.4
- Mantis #0014026	Stack: Parameter +sip.instance not set on INVITE request
- Mantis #0014032	Stack: FQDN not supported ofr outbound proxy
- Mantis #0013985	Stack: From and To header not well formated in CPIM/MSRP message for chat group
- Mantis #0013840	Stack: Can't send ACK (no ListeningPoint for transport) with TCP
- Mantis #0013825	Stack: Bad CSeq header after sending a REFER
- Mantis #0013767	Stack: Stack overflow if first REGISTER always fails
- Mantis #0013846	Stack: Capability are reset after a 480 response on SIP OPTIONS
- Mantis #0013826	Stack: Exception when MSRP traces are activated
- Open source #33	Stack: Unexpected internal error FIXME!! Cannot create ACK

v2.3.3
- Mantis #0013733	Stack: Referred-By header not well decoded when not in contracted form
- Mantis #0013724	Stack: Timestamp of chat message should be taken from CPIM DateTime header
- Mantis #0013715	Stack: Char "_" is fobidden is message ID for MSRP
- Mantis #0013701	Stack: Bad CPIM From header if a displayname exists
- Mantis #0013651	Stack: Bad message ID sent by terminating on the first message delivery report
- Mantis #0013587	Stack: Chat session subject not well decoded
- Mantis #0013583	Stack: Contact identity not well extracted from the S&F session

v2.3.2
- Mantis #0013584	Stack: 00xx phone numbers not taken into account
- Mantis #0013582	Stack: The SIP header "imdn.messageID" is not necessary
- Mantis #0013580	Stack: Bad UTF-8 encoding for MSRP messages and subject
- Mantis #0013564	Stack: Bad Message ID on SIP MESSAGE
- Mantis #0013531	Stack: No SIP OPTIONS exchanged after call hold
- Mantis #0013530	Stack: No local tag on SIP 200 OK response of SIP MESSAGE
- Mantis #0013501	Stack: can't add a participant who has declined the group chat invitation
- Mantis #0013489	Stack: Exception on RCS service force close
- Mantis #0013243	Stack: If no SIM card or in flight mode the RCS service is not started
- Mantis #0013157	Stack: H264 encoder sometimes fails to encode


v2.3.1
- Mantis #0013441	Stack: Add participant to chat group failed
- Mantis #0013379	Stack: Event log crash if phone number starts with 0033
- Mantis #0013392	EAB app: "Call %s" is displayed in contact's contextual menu
- Mantis #0013408	Stack: not necessary SIP OPTIONS send on MO side after call released

v2.3.0

- Mantis #0013384	Stack: Two headers "Contribution-id" in SIP part
- Mantis #0013381	Rich call app: Can't initiate Live video sharing on HTC Sensation
- Mantis #0013367	Stack: Content sharing error 5 when a live video session is cancelled
- Mantis #0013355	Stack: Exception when stopping live video sharing
- Mantis #0013338	Rich call app: ISH_FUNC14 step 1 : Csh does not stop on call dropped
- Mantis #0013337	Rich call app: ISH_FUNC6 step 2 : No capability OPTION sent when call on hold
- Mantis #0013336	Rich call app: ISH_FUNC5 step 2 : media sharing is not deactivated during multiparty call
- Mantis #0013335	Chat app: FT_FUNC19 step1 : can't send more than one file simultaneously
- Mantis #0013332	Chat app: FT_FUNC15 step1 : Max file transfer size not checked before initiation
- Mantis #0013331	Chat app: TF_FUNC5 step3 : No duration approximation during file transfer
- Mantis #0013330	Chat app: FT_FUNC2 step 1 : No access to the service from Call log
- Mantis #0013328	Chat app: IM_FUNC6 step1 : Redirect to SMS if contact is not connected
- Mantis #0013317	Chat app: Contact list is not refreshed when forcing a refresh from the menu
- Mantis #0013315	Stack: Contacts with number starting with 0033 are not correctly handled
- Mantis #0013314	Stack: Content-ID should be in the body
- Mantis #0013151	RI: The received media codec is not taken into account
- Mantis #0013133	Stack: Capability exchange exception
- Mantis #0013123	Stack: Internal minor exception on chat activity expiration
- Mantis #0013105	Stack: Messages are not displayed
- Mantis #0012611	Stack: CSeq value not well incremented after a session refresh timer
- Mantis #0012438	Rich call app: V226_RI_Option to "336" send by terminal but "336" not un adress book
- Mantis #0012299	Stack: V226-Request capability: 3 options are send by the terminal in a verry short time
- Mantis #0010774	Stack: Double call - No Csh menu
- Mantis #0010395	Stack: Video share: problem on 603 Decline call flow

v2.2.9
- Mantis #0013161	Stack: Crash after SIM Change
- Mantis #0013060	Stack: Widget exception
- Mantis #0013056	Chat app: Chat exception
- Mantis #0013052	Chat app: Don't display IMDN icon for received message

v2.2.8
- Mantis #0013059	Rich call app: Pre-recorded video sharing always get media player error
- Mantis #0013054	Chat app: Transfer file dialog not well displayed
- Mantis #0013053	Chat app: File transfer notification not well formatted
- Mantis #0013051	Chat app: Latency when chat view is opened
- Mantis #0013037	Stack: Bad Accept-types in chat session
- Mantis #0013033	Chat app: Is composing feature not taken into account when chat view is in background
- Mantis #0013029	RI: Chat invitation notification is never removed if not clicked
- Mantis #0012986	Stack: Bad IMDN SIP MESSAGE content-type
- Mantis #0012995	Stack: Menu event log should not be displayed for normal contacts
- Mantis #0012994	Stack: Menu event log does not appear in native contact card
- Mantis #0012862	Stack: ANR in rcs settings activity while syncing a lot of contacts (>100)
- Mantis #0012797	Stack: Start RCS service failed in IPv6
- Mantis #0012639	Stack: Create new contact does not send a SIP OPTIONS on Nexus S
- Mantis #0012638	Stack: Event log: chat entry not well displayed
- Mantis #0012703	EAB app: Exception when adding presence widget
- Mantis #0012594	Stack: Switch between 2 wi-fi access
- Mantis #0012544	Stack: Chat session no "ack" send by MO side on 200OK received
- Mantis #0012252	RI: Problem with video output while video sharing with galaxy Tab
- Mantis #0012077	Chat app: Chat 1-1 crash application during chat
- Mantis #0011249	RI: Display only capable contacts for FT, chat and rich call

v2.2.7
- Mantis #0012604	Stack: RCS icon not grayed in air plane mode
- Mantis #0012600	RI: chat view exception
- Mantis #0012599	Stack: Bad boudary tag in multipart content of the chat INVITE after a 407 response
- Mantis #0012559	Stack: Exception when changing a contact's telephone number
- Mantis #0012495	Stack: No empty packet sent for file transfer on terminating side
- Mantis #0012494	Stack: User-agent or Server header shall start with "IM-client" text
- Mantis #0012186	Chat app: Add participant generates 3 times the same participant instead of one
- Mantis #0012180	Chat app: Exception if capture photo is used to initiate a file transfer
- Mantis #0011921	Stack: Notification not removed when 3G lost during a rich call
- Mantis #0011254	RI: No text entry to edit the first message before to initiate a chat session
- Mantis #0011248	Stack: Request capabilities on a lot of contacts is too long
- Mantis #0010429	Stack: When a number is present more than once, only the first entry encountered is enriched with a rcs raw contact


v2.2.5
- Mantis #0012119	Stack: RTCP transmiter not well stopped at the end of the session
- Mantis #0012103	Chat application crash after initiation has failed
- Mantis #0012069	Chat app: Displayed report not displayed in 1-1 chat
- Mantis #0012068	Chat app: No limit on text length
- Mantis #0012065	Stack: Force content sharing capabilities reset at the end of the call
- Mantis #0012006	Stack: Set unused MSRP port to 9 as defined in RFC4975 chapitre 5.4
- Mantis #0012003	RI: Event log exception
- Mantis #0011955	Stack: BYE failed on terminating chat group
- Mantis #0011658	Chat app: Add contact button never set to enabled when initiating chat session
- Mantis #0011253	RI: chat session list not updated
- Mantis #0010627	Stack: Exception on 407 response for RCS-e OPTIONS
- Mantis #0010623	Stack: ContactsManager exception on GalaxyS
- Opensource #17	Stack: Encoding of RCS-e capabilities in Contact Header
- Opensource #18	Stack: Can't connect if no P-Associated-URI header in REGISTER response

v2.2.4
- Mantis #0009919	File transfer failed with file upper than 1Mb
- Mantis #0009642	No "from" tag on REGISTER
- Mantis #0009567	Activate roaming don't start the RCS service if authorized
- Mantis #0009596	Contact formatting issue on chat initiation
- Mantis #0010895	Bad Service-Route header
- Mantis #0010474	Exception on terminating file transfer
- Mantis #0010158	Invitation notification not removed in notification bar
- Mantis #0011252	Display an "in progress" screen during network operations
- Mantis #0011396	Bad expire period on un-register after a 407
- Mantis #0011246	Received file transfer's name truncated
- Mantis #0010580	RCS service startup failed if GIBA and Airplane mode activated
- Mantis #0011249	Display only capable contacts for FT, chat and rich call

v2.2.3
- Mantis #0011436	Service is not started when air plane mode is disabled- Mantis #0010849	Chat not well displayed in event log
- Mantis #0010368	Video preview error on LG Optimus

v2.2.2
- UI enhancements.

v2.2.1
- Mantis #0010895	Bad Service-Route header
- Mantis #0010827	Capability discovery mechanism creates a new phone number even if it already exists in the address book
- Mantis #0010634	SIP provider exception due to stopAddressBookmonitoring error on stack termina
- Mantis #0010580	RCS service startup failed if GIBA and Airplane mode activated
- Mantis #0010410	Crash on the "Is composing" feature (with RI)
- Opensource #3		Register Authorization qop parameter not compliant with RFC2617
- Opensource #4		REGISTER challenge: wrong calculation of the response in case of several tokens for qop option
- Opensource #10	Client uses unquoted etags in XCAP operations
- Opensource #11	Client does not handle SUBSCRIBE/202 responses properly
- Opensource #12	X-3GPP-Intended-Identity header is unquoted


v2.2.0
- Mantis #0010430	First account launch procedure failed if not connected to IMS
- Mantis #0010428	Expire value from settings database not taken into account
- Mantis #0010401	Message-ID is different between file transfer chunks
- Mantis #0010391	No 200 OK sent on NOTIFY (terminated)
- Mantis #0010388	Initial anonymous fecth procedure not threaded
- Mantis #0010394	Too many ACK after 200 OK
- Mantis #0009964	Too many ACK retransmission
- Mantis #0009961	Exception during a file transfer on HTC Hero

v2.1.5
- Mantis #0010387	Change presence status icon

v2.1.4
- Anonymous fetches for multiple contacts at terminal startup are all done in the same thread (no longer one thread per request)
- Anonymous fetches requests may be disabled by setting the "anonymous refresh timeout" setting to -1
- No more anonymous fetches when coming to the contact View if the profile is shared with this number
- Timestamps for profile invitations were not correctly set


v2.1.3
- Mantis #0010389	When a RCS contact is deleted it is automatically recreated when a NOTIFY is received	
- Mantis #0010393	Automatic linking failing when contact has two numbers	
- Mantis #0010388	Initial anonymous fecth procedure not threaded	
- Mantis #0010389	When a RCS contact is deleted it is automatically recreated when a NOTIFY is received	
- Mantis #0009963	Phone contact number keeps coming back in EAB
- Mantis #0009728	NullPointer exception on terminating video sharing invitation
- Missing "Privacy: id" header on anonymous fetch

v2.1.2
- Defect #188 Bad Is composing timeout
- Defect #177 Menu file transfer from contact card don't load
- Defect #170 Rich call popup no more displayed
- Defect #168 File transfer crash

v2.1.1
- Defect #139 Must subscribe to his own presence
- Defect #130 File transfer not cancelled on network error


Known bugs:
-----------

- Mantis #10522	Bad SIP retransmission timer
- Defect #194	Each time the terminal boot, pending presence sharing invitations are again displayed in notification bar
- Defect #131	No message sent at the stop of the device (same as Defect E2E#108)
- Defect #121	Black screen during call
- Defect #99	Double call - No Csh menu


Notes:
-------
- This release runs from Android 2.x release.
- TLS runs from Android 2.3 release.


Contact:
--------
Orange Labs
jeanmarc.auffret@orange.com
