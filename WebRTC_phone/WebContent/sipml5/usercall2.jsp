<!DOCTYPE html>

<html>
<!-- head -->
<head>
<meta charset="utf-8" />
<title>sipML5 live demo</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="Keywords"
	content="doubango, sipML5, VoIP, HTML5, WebRTC, RTCWeb, SIP, IMS, Video chat, VP8, live demo " />
<meta name="Description"
	content="HTML5 SIP client using WebRTC framework" />
<meta name="author" content="Doubango Telecom" />

<!-- SIPML5 API:
    DEBUG VERSION: 'SIPml-api.js'
    RELEASE VERSION: 'release/SIPml-api.js'
    -->
<!-- Styles -->
<script src="SIPml-api.js?svn=179" type="text/javascript">
	
</script>

<script src="jquery-1.6.1.min.js" type="text/javascript"></script>
<script src="init.js" type="text/javascript"></script>
<script src="gui.js" type="text/javascript"></script>
<script src="jssip-devel.js" type="text/javascript"></script>
<script src="piwik.js" type="text/javascript"></script>
<link href="./assets/css/bootstrap.css" rel="stylesheet" />


<style type="text/css">
body {
	padding-top: 80px;
	padding-bottom: 40px;
}

.navbar-inner-red {
	background-color: #600000;
	background-image: none;
	background-repeat: no-repeat;
	filter: none;
}

.full-screen {
	position: absolute;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: hidden;
}

.normal-screen {
	position: relative;
}

.call-options {
	padding: 5px;
	background-color: #f0f0f0;
	border: 1px solid #eee;
	border: 1px solid rgba(0, 0, 0, 0.08);
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.05);
	-moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.05);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.05);
	-webkit-transition-property: opacity;
	-moz-transition-property: opacity;
	-o-transition-property: opacity;
	-webkit-transition-duration: 2s;
	-moz-transition-duration: 2s;
	-o-transition-duration: 2s;
}

.tab-video,.div-video {
	width: 100%;
	height: 0px;
	-webkit-transition-property: height;
	-moz-transition-property: height;
	-o-transition-property: height;
	-webkit-transition-duration: 2s;
	-moz-transition-duration: 2s;
	-o-transition-duration: 2s;
}

.label-align {
	display: block;
	padding-left: 15px;
	text-indent: -15px;
}

.input-align {
	width: 13px;
	height: 13px;
	padding: 0;
	margin: 0;
	vertical-align: bottom;
	position: relative;
	top: -1px;
	*overflow: hidden;
}

.glass-panel {
	z-index: 99;
	position: fixed;
	width: 100%;
	height: 100%;
	margin: 0;
	padding: 0;
	top: 0;
	left: 0;
	opacity: 0.8;
	background-color: Gray;
}

.div-keypad {
	z-index: 100;
	position: fixed;
	-moz-transition-property: left top;
	-o-transition-property: left top;
	-webkit-transition-duration: 2s;
	-moz-transition-duration: 2s;
	-o-transition-duration: 2s;
}
</style>
<link href="./assets/css/bootstrap-responsive.css" rel="stylesheet" />
<!-- Le fav and touch icons -->
<link rel="shortcut icon" href="./assets/ico/favicon.ico" />
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="./assets/ico/apple-touch-icon-114-precomposed.png" />
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="./assets/ico/apple-touch-icon-72-precomposed.png" />
<link rel="apple-touch-icon-precomposed"
	href="./assets/ico/apple-touch-icon-57-precomposed.png" />
</head>
<!-- Javascript code -->
<script type="text/javascript">
	// to avoid caching
	//if (window.location.href.indexOf("svn=") == -1) {
	//    window.location.href += (window.location.href.indexOf("?") == -1 ? "?svn=13" : "&svn=13");
	//}

	var sTransferNumber;
	var oRingTone, oRingbackTone;
	var oSipStack, oSipSessionRegister, oSipSessionCall, oSipSessionTransferCall;
	var videoRemote, videoLocal, audioRemote;
	var bFullScreen = false;
	var oNotifICall;
	var oReadyStateTimer;
	var bDisableVideo = false;
	var viewVideoLocal, viewVideoRemote; // <video> (webrtc) or <div> (webrtc4all)

	C = {
		divKeyPadWidth : 220
	};

	window.onload = function() {
		videoLocal = document.getElementById("video_local");
		videoRemote = document.getElementById("video_remote");
		audioRemote = document.getElementById("audio_remote");

		document.onkeyup = onKeyUp;
		document.body.onkeyup = onKeyUp;
		divCallCtrl.onmousemove = onDivCallCtrlMouseMove;

		loadCredentials();
		loadCallOptions();

		oReadyStateTimer = setInterval(function() {
			if (document.readyState === "complete") {
				clearInterval(oReadyStateTimer);
				// initialize SIPML5
				SIPml.init(postInit);
			}
		}, 500);
	};

	function postInit() {
		// check webrtc4all version
		if (SIPml.isWebRtc4AllSupported() && SIPml.isWebRtc4AllPluginOutdated()) {
			if (confirm("Your WebRtc4all extension is outdated. A new version("
					+ SIPml.getWebRtc4AllVersion()
					+ ") with critical bug fix is available. Do you want to install it?\nIMPORTANT: You must restart your browser after the installation.")) {
				window.location = 'http://code.google.com/p/webrtc4all/downloads/list';
				return;
			}
		}

		// check for WebRTC support
		if (!SIPml.isWebRtcSupported()) {
			// is it chrome?
			if (SIPml.getNavigatorFriendlyName() == 'chrome') {
				if (confirm("You're using an old Chrome version or WebRTC is not enabled.\nDo you want to see how to enable WebRTC?")) {
					window.location = 'http://www.webrtc.org/running-the-demos';
				} else {
					window.location = "index.html";
				}
				return;
			}

			// for now the plugins (WebRTC4all only works on Windows)
			if (SIPml.getSystemFriendlyName() == 'windows') {
				// Internet explorer
				if (SIPml.getNavigatorFriendlyName() == 'ie') {
					// Check for IE version 
					if (parseFloat(SIPml.getNavigatorVersion()) < 9.0) {
						if (confirm("You are using an old IE version. You need at least version 9. Would you like to update IE?")) {
							window.location = 'http://windows.microsoft.com/en-us/internet-explorer/products/ie/home';
						} else {
							window.location = "index.html";
						}
					}

					// check for WebRTC4all extension
					if (!SIPml.isWebRtc4AllSupported()) {
						if (confirm("webrtc4all extension is not installed. Do you want to install it?\nIMPORTANT: You must restart your browser after the installation.")) {
							window.location = 'http://code.google.com/p/webrtc4all/downloads/list';
						} else {
							// Must do nothing: give the user the chance to accept the extension
							// window.location = "index.html";
						}
					}
					// break page loading ('window.location' won't stop JS execution)
					if (!SIPml.isWebRtc4AllSupported()) {
						return;
					}
				} else if (SIPml.getNavigatorFriendlyName() == "safari"
						|| SIPml.getNavigatorFriendlyName() == "firefox"
						|| SIPml.getNavigatorFriendlyName() == "opera") {
					if (confirm("Your browser don't support WebRTC.\nDo you want to install WebRTC4all extension to enjoy audio/video calls?\nIMPORTANT: You must restart your browser after the installation.")) {
						window.location = 'http://code.google.com/p/webrtc4all/downloads/list';
					} else {
						window.location = "index.html";
					}
					return;
				}
			}
			// OSX, Unix, Android, iOS...
			else {
				if (confirm('WebRTC not supported on your browser.\nDo you want to download a WebRTC-capable browser?')) {
					window.location = 'https://www.google.com/intl/en/chrome/browser/';
				} else {
					window.location = "index.html";
				}
				return;
			}
		}

		// checks for WebSocket support
		if (!SIPml.isWebSocketSupported() && !SIPml.isWebRtc4AllSupported()) {
			if (confirm('Your browser don\'t support WebSockets.\nDo you want to download a WebSocket-capable browser?')) {
				window.location = 'https://www.google.com/intl/en/chrome/browser/';
			} else {
				window.location = "index.html";
			}
			return;
		}

		// FIXME: displays must be per session

		// attachs video displays
		if (SIPml.isWebRtc4AllSupported()) {
			viewVideoLocal = document.getElementById("divVideoLocal");
			viewVideoRemote = document.getElementById("divVideoRemote");
			WebRtc4all_SetDisplays(viewVideoLocal, viewVideoRemote); // FIXME: move to SIPml.* API
		} else {
			viewVideoLocal = videoLocal;
			viewVideoRemote = videoRemote;
		}

		if (!SIPml.isWebRtc4AllSupported() && !SIPml.isWebRtcSupported()) {
			if (confirm('Your browser don\'t support WebRTC.\naudio/video calls will be disabled.\nDo you want to download a WebRTC-capable browser?')) {
				window.location = 'https://www.google.com/intl/en/chrome/browser/';
			}
		}

		btnRegister.disabled = false;
		document.body.style.cursor = 'default';
	}

	function loadCallOptions() {
		if (window.localStorage) {
			var s_value;
			if ((s_value = window.localStorage
					.getItem('org.doubango.call.phone_number')))
				txtPhoneNumber.value = s_value;
			bDisableVideo = (window.localStorage
					.getItem('org.doubango.expert.disable_video') == "true");

			txtCallStatus.innerHTML = '<i>Video '
					+ (bDisableVideo ? 'disabled' : 'enabled') + '</i>';
		}
	}

	function saveCallOptions() {
		if (window.localStorage) {
			window.localStorage.setItem('org.doubango.call.phone_number',
					txtPhoneNumber.value);
			window.localStorage.setItem('org.doubango.expert.disable_video',
					bDisableVideo ? "true" : "false");
		}
	}

	function loadCredentials() {
		if (window.localStorage) {
			// IE retuns 'null' if not defined
			var s_value;
			if ((s_value = window.localStorage
					.getItem('org.doubango.identity.display_name')))
				txtDisplayName.value = s_value;
			if ((s_value = window.localStorage
					.getItem('org.doubango.identity.impi')))
				txtPrivateIdentity.value = s_value;
			if ((s_value = window.localStorage
					.getItem('org.doubango.identity.impu')))
				txtPublicIdentity.value = s_value;
			if ((s_value = window.localStorage
					.getItem('org.doubango.identity.password')))
				txtPassword.value = s_value;
			if ((s_value = window.localStorage
					.getItem('org.doubango.identity.realm')))
				txtRealm.value = s_value;
		} else {
			/*txtDisplayName.value = "005";
			txtPrivateIdentity.value = "005";
			txtPublicIdentity.value = "sip:005@192.168.0.42";
			txtPassword.value = "005";
			txtRealm.value = "192.168.0.42";
			txtPhoneNumber.value = "005";*/
		}
	};

	function saveCredentials() {
		if (window.localStorage) {
			window.localStorage.setItem('org.doubango.identity.display_name',
					txtDisplayName.value);
			window.localStorage.setItem('org.doubango.identity.impi',
					txtPrivateIdentity.value);
			window.localStorage.setItem('org.doubango.identity.impu',
					txtPublicIdentity.value);
			window.localStorage.setItem('org.doubango.identity.password',
					txtPassword.value);
			window.localStorage.setItem('org.doubango.identity.realm',
					txtRealm.value);
		}
	};

	// sends SIP REGISTER request to login
	function sipRegister() {
		// catch exception for IE (DOM not ready)
		try {
			btnRegister.disabled = true;
			if (!txtRealm.value || !txtPrivateIdentity.value
					|| !txtPublicIdentity.value) {
				txtRegStatus.innerHTML = '<b>Please fill madatory fields (*)</b>';
				btnRegister.disabled = false;
				return;
			}
			var o_impu = tsip_uri.prototype.Parse(txtPublicIdentity.value);
			if (!o_impu || !o_impu.s_user_name || !o_impu.s_host) {
				txtRegStatus.innerHTML = "<b>[" + txtPublicIdentity.value
						+ "] is not a valid Public identity</b>";
				btnRegister.disabled = false;
				return;
			}

			// enable notifications if not already done
			if (window.webkitNotifications
					&& window.webkitNotifications.checkPermission() != 0) {
				window.webkitNotifications.requestPermission();
			}

			// save credentials
			saveCredentials();

			// create SIP stack
			oSipStack = new SIPml.Stack(
					{
						realm : txtRealm.value,
						impi : txtPrivateIdentity.value,
						impu : txtPublicIdentity.value,
						password : txtPassword.value,
						display_name : txtDisplayName.value,
						websocket_proxy_url : (window.localStorage ? window.localStorage
								.getItem('org.doubango.expert.websocket_server_url')
								: null),
						outbound_proxy_url : (window.localStorage ? window.localStorage
								.getItem('org.doubango.expert.sip_outboundproxy_url')
								: null),
						ice_servers : (window.localStorage ? window.localStorage
								.getItem('org.doubango.expert.ice_servers')
								: null),
						enable_rtcweb_breaker : (window.localStorage ? window.localStorage
								.getItem('org.doubango.expert.enable_rtcweb_breaker') == "true"
								: false),
						events_listener : {
							events : '*',
							listener : onSipEventStack
						},
						sip_headers : [ {
							name : 'User-Agent',
							value : 'IM-client/OMA1.0 sipML5-v1.2013.05.24'
						}, {
							name : 'Organization',
							value : 'Doubango Telecom'
						} ]
					});
			if (oSipStack.start() != 0) {
				txtRegStatus.innerHTML = '<b>Failed to start the SIP stack</b>';
			} else
				return;
		} catch (e) {
			txtRegStatus.innerHTML = "<b>2:" + e + "</b>";
		}
		messageValue();
		btnRegister.disabled = false;
		//messageValue();
	}

	// sends SIP REGISTER (expires=0) to logout
	function sipUnRegister() {
		if (oSipStack) {
			oSipStack.stop(); // shutdown all sessions
		}
	}

	// makes a call (SIP INVITE)
	function sipCall() {
		// call configuration
		var oConf = {
			audio_remote : audioRemote,
			video_local : viewVideoLocal,
			video_remote : viewVideoRemote,
			events_listener : {
				events : '*',
				listener : onSipEventSession
			},
			sip_caps : [ {
				name : '+g.oma.sip-im'
			}, {
				name : '+sip.ice'
			}, {
				name : 'language',
				value : '\"en,fr\"'
			} ]
		};
		if (oSipStack && !oSipSessionCall
				&& !tsk_string_is_null_or_empty(txtPhoneNumber.value)) {
			btnCall.disabled = true;
			btnHangUp.disabled = false;

			// check whether video is disabled or not
			bDisableVideo = (window.localStorage && window.localStorage
					.getItem('org.doubango.expert.disable_video') == "true");
			// create call session
			oSipSessionCall = oSipStack.newSession(bDisableVideo ? 'call-audio'
					: 'call-audiovideo', oConf);
			// make call
			if (oSipSessionCall.call(txtPhoneNumber.value) != 0) {
				oSipSessionCall = null;
				txtCallStatus.value = 'Failed to make call';
				btnCall.disabled = false;
				btnHangUp.disabled = true;
				return;
			}
			saveCallOptions();
		} else if (oSipSessionCall) {
			txtCallStatus.innerHTML = '<i>Connecting...</i>';
			oSipSessionCall.accept(oConf);
		}

	}

	// transfers the call
	function sipTransfer() {
		if (oSipSessionCall) {
			var s_destination = prompt('Enter destination number', '');
			if (!tsk_string_is_null_or_empty(s_destination)) {
				btnTransfer.disabled = true;
				if (oSipSessionCall.transfer(s_destination) != 0) {
					txtCallStatus.innerHTML = '<i>Call transfer failed</i>';
					btnTransfer.disabled = false;
					return;
				}
				txtCallStatus.innerHTML = '<i>Transfering the call...</i>';
			}
		}
	}

	// holds or resumes the call
	function sipToggleHoldResume() {
		if (oSipSessionCall) {
			var i_ret;
			btnHoldResume.disabled = true;
			txtCallStatus.innerHTML = oSipSessionCall.bHeld ? '<i>Resuming the call...</i>'
					: '<i>Holding the call...</i>';
			i_ret = oSipSessionCall.bHeld ? oSipSessionCall.resume()
					: oSipSessionCall.hold();
			if (i_ret != 0) {
				txtCallStatus.innerHTML = '<i>Hold / Resume failed</i>';
				btnHoldResume.disabled = false;
				return;
			}
		}
	}

	// terminates the call (SIP BYE or CANCEL)
	function sipHangUp() {
		if (oSipSessionCall) {
			txtCallStatus.innerHTML = '<i>Terminating the call...</i>';
			oSipSessionCall.hangup({
				events_listener : {
					events : '*',
					listener : onSipEventSession
				}
			});
		}
	}

	function sipSendDTMF(c) {
		if (oSipSessionCall && c) {
			if (oSipSessionCall.dtmf(c) == 0) {
				try {
					dtmfTone.play();
				} catch (e) {
				}
			}
		}
	}

	function startRingTone() {
		try {
			ringtone.play();
		} catch (e) {
		}
	}

	function stopRingTone() {
		try {
			ringtone.pause();
		} catch (e) {
		}
	}

	function startRingbackTone() {
		try {
			ringbacktone.play();
		} catch (e) {
		}
	}

	function stopRingbackTone() {
		try {
			ringbacktone.pause();
		} catch (e) {
		}
	}

	function toggleFullScreen() {
		if (videoRemote.webkitSupportsFullscreen) {
			fullScreen(!videoRemote.webkitDisplayingFullscreen);
		} else {
			fullScreen(!bFullScreen);
		}
	}

	function openKeyPad() {
		divKeyPad.style.visibility = 'visible';
		divKeyPad.style.left = ((document.body.clientWidth - C.divKeyPadWidth) >> 1)
				+ 'px';
		divKeyPad.style.top = '70px';
		divGlassPanel.style.visibility = 'visible';
	}

	function closeKeyPad() {
		divKeyPad.style.left = '0px';
		divKeyPad.style.top = '0px';
		divKeyPad.style.visibility = 'hidden';
		divGlassPanel.style.visibility = 'hidden';
	}

	function fullScreen(b_fs) {
		bFullScreen = b_fs;
		if (tsk_utils_have_webrtc4native() && bFullScreen
				&& videoRemote.webkitSupportsFullscreen) {
			if (bFullScreen) {
				videoRemote.webkitEnterFullScreen();
			} else {
				videoRemote.webkitExitFullscreen();
			}
		} else {
			if (tsk_utils_have_webrtc4npapi()) {
				try {
					if (window.__o_display_remote)
						window.__o_display_remote.setFullScreen(b_fs);
				} catch (e) {
					divVideo.setAttribute("class", b_fs ? "full-screen"
							: "normal-screen");
				}
			} else {
				divVideo.setAttribute("class", b_fs ? "full-screen"
						: "normal-screen");
			}
		}
	}

	function showNotifICall(s_number) {
		// permission already asked when we registered
		if (window.webkitNotifications
				&& window.webkitNotifications.checkPermission() == 0) {
			if (oNotifICall) {
				oNotifICall.cancel();
			}
			oNotifICall = window.webkitNotifications.createNotification(
					'images/sipml-34x39.png', 'Incaming call',
					'Incoming call from ' + s_number);
			oNotifICall.onclose = function() {
				oNotifICall = null;
			};
			oNotifICall.show();
		}
	}

	function onKeyUp(evt) {
		evt = (evt || window.event);
		if (evt.keyCode == 27) {
			fullScreen(false);
		} else if (evt.ctrlKey && evt.shiftKey) { // CTRL + SHIFT
			if (evt.keyCode == 65 || evt.keyCode == 86) { // A (65) or V (86)
				bDisableVideo = (evt.keyCode == 65);
				txtCallStatus.innerHTML = '<i>Video '
						+ (bDisableVideo ? 'disabled' : 'enabled') + '</i>';
				window.localStorage.setItem(
						'org.doubango.expert.disable_video', bDisableVideo);
			}
		}
	}

	function onDivCallCtrlMouseMove(evt) {
		try { // IE: DOM not ready
			if (tsk_utils_have_stream()) {
				btnCall.disabled = (!tsk_utils_have_stream()
						|| !oSipSessionRegister || !oSipSessionRegister
						.is_connected());
				document.getElementById("divCallCtrl").onmousemove = null; // unsubscribe
			}
		} catch (e) {
		}
	}

	function uiOnConnectionEvent(b_connected, b_connecting) { // should be enum: connecting, connected, terminating, terminated
		btnRegister.disabled = b_connected || b_connecting;
		btnUnRegister.disabled = !b_connected && !b_connecting;
		btnCall.disabled = !(b_connected && tsk_utils_have_webrtc() && tsk_utils_have_stream());
		btnHangUp.disabled = !oSipSessionCall;
	}

	function uiVideoDisplayEvent(b_local, b_added) {
		//if (!bDisableVideo) {
		var o_elt_video = b_local ? videoLocal : videoRemote;

		if (b_added) {
			if (SIPml.isWebRtc4AllSupported()) {
				if (b_local) {
					if (window.__o_display_local)
						window.__o_display_local.style.visibility = "visible";
				} else {
					if (window.__o_display_remote)
						window.__o_display_remote.style.visibility = "visible";
				}

			} else {
				o_elt_video.style.opacity = 1;
			}
			uiVideoDisplayShowHide(true);
		} else {
			if (SIPml.isWebRtc4AllSupported()) {
				if (b_local) {
					if (window.__o_display_local)
						window.__o_display_local.style.visibility = "hidden";
				} else {
					if (window.__o_display_remote)
						window.__o_display_remote.style.visibility = "hidden";
				}
			} else {
				o_elt_video.style.opacity = 0;
			}
			fullScreen(false);
		}
		//}
	}

	function uiVideoDisplayShowHide(b_show) {
		if (b_show) {
			tdVideo.style.height = '340px';
			divVideo.style.height = navigator.appName == 'Microsoft Internet Explorer' ? '100%'
					: '340px';
		} else {
			tdVideo.style.height = '0px';
			divVideo.style.height = '0px';
		}
		btnFullScreen.disabled = !b_show;
	}

	function uiCallTerminated(s_description) {
		btnCall.value = 'Call';
		btnHangUp.value = 'HangUp';
		btnHoldResume.value = 'hold';
		btnCall.disabled = false;
		btnHangUp.disabled = true;

		oSipSessionCall = null;

		stopRingbackTone();
		stopRingTone();

		txtCallStatus.innerHTML = "<i>" + s_description + "</i>";
		uiVideoDisplayShowHide(false);
		divCallOptions.style.opacity = 0;

		if (oNotifICall) {
			oNotifICall.cancel();
			oNotifICall = null;
		}

		uiVideoDisplayEvent(true, false);
		uiVideoDisplayEvent(false, false);

		setTimeout(function() {
			if (!oSipSessionCall)
				txtCallStatus.innerHTML = '';
		}, 2500);
	}

	// Callback function for SIP Stacks
	function onSipEventStack(e /*SIPml.Stack.Event*/) {
		tsk_utils_log_info('==stack event = ' + e.type);
		switch (e.type) {
		case 'started': {
			// catch exception for IE (DOM not ready)
			try {
				// LogIn (REGISTER) as soon as the stack finish starting
				oSipSessionRegister = this.newSession('register', {
					expires : 200,
					events_listener : {
						events : '*',
						listener : onSipEventSession
					},
					sip_caps : [ {
						name : '+g.oma.sip-im',
						value : null
					}, {
						name : '+audio',
						value : null
					}, {
						name : 'language',
						value : '\"en,fr\"'
					} ]
				});
				oSipSessionRegister.register();
			} catch (e) {
				txtRegStatus.value = txtRegStatus.innerHTML = "<b>1:" + e
						+ "</b>";
				btnRegister.disabled = false;
			}
			break;
		}
		case 'stopping':
		case 'stopped':
		case 'failed_to_start':
		case 'failed_to_stop': {
			var bFailure = (e.type == 'failed_to_start')
					|| (e.type == 'failed_to_stop');
			oSipStack = null;
			oSipSessionRegister = null;
			oSipSessionCall = null;

			uiOnConnectionEvent(false, false);

			stopRingbackTone();
			stopRingTone();

			uiVideoDisplayShowHide(false);
			divCallOptions.style.opacity = 0;

			txtCallStatus.innerHTML = '';
			txtRegStatus.innerHTML = bFailure ? "<i>Disconnected: <b>"
					+ e.description + "</b></i>" : "<i>Disconnected</i>";
			break;
		}

		case 'i_new_call': {
			if (oSipSessionCall) {
				// do not accept the incoming call if we're already 'in call'
				e.newSession.hangup(); // comment this line for multi-line support
			} else {
				oSipSessionCall = e.newSession;

				btnCall.value = 'Answer';
				btnHangUp.value = 'Reject';
				btnCall.disabled = false;
				btnHangUp.disabled = false;

				startRingTone();

				var sRemoteNumber = (oSipSessionCall.getRemoteFriendlyName() || 'unknown');
				txtCallStatus.innerHTML = "<i>Incoming call from [<b>"
						+ sRemoteNumber + "</b>]</i>";
				showNotifICall(sRemoteNumber);
			}
			break;
		}

		case 'm_permission_requested': {
			divGlassPanel.style.visibility = 'visible';
			break;
		}
		case 'm_permission_accepted':
		case 'm_permission_refused': {
			divGlassPanel.style.visibility = 'hidden';
			if (e.type == 'm_permission_refused') {
				uiCallTerminated('Media stream permission denied');
			}
			break;
		}

		case 'starting':
		default:
			break;
		}
	};

	// Callback function for SIP sessions (INVITE, REGISTER, MESSAGE...)
	function onSipEventSession(e /* SIPml.Session.Event */) {
		tsk_utils_log_info('==session event = ' + e.type);

		switch (e.type) {
		case 'connecting':
		case 'connected': {
			var bConnected = (e.type == 'connected');
			if (e.session == oSipSessionRegister) {
				uiOnConnectionEvent(bConnected, !bConnected);
				txtRegStatus.innerHTML = "<i>" + e.description + "</i>";
			} else if (e.session == oSipSessionCall) {
				btnHangUp.value = 'HangUp';
				btnCall.disabled = true;
				btnHangUp.disabled = false;
				btnTransfer.disabled = false;

				if (bConnected) {
					stopRingbackTone();
					stopRingTone();

					if (oNotifICall) {
						oNotifICall.cancel();
						oNotifICall = null;
					}
				}

				txtCallStatus.innerHTML = "<i>" + e.description + "</i>";
				divCallOptions.style.opacity = bConnected ? 1 : 0;

				if (SIPml.isWebRtc4AllSupported()) { // IE don't provide stream callback
					uiVideoDisplayEvent(true, true);
					uiVideoDisplayEvent(false, true);
				}
			}
			break;
		} // 'connecting' | 'connected'
		case 'terminating':
		case 'terminated': {
			if (e.session == oSipSessionRegister) {
				uiOnConnectionEvent(false, false);

				oSipSessionCall = null;
				oSipSessionRegister = null;

				txtRegStatus.innerHTML = "<i>" + e.description + "</i>";
			} else if (e.session == oSipSessionCall) {
				uiCallTerminated(e.description);
			}
			break;
		} // 'terminating' | 'terminated'

		case 'm_stream_video_local_added': {
			if (e.session == oSipSessionCall) {
				uiVideoDisplayEvent(true, true);
			}
			break;
		}
		case 'm_stream_video_local_removed': {
			if (e.session == oSipSessionCall) {
				uiVideoDisplayEvent(true, false);
			}
			break;
		}
		case 'm_stream_video_remote_added': {
			if (e.session == oSipSessionCall) {
				uiVideoDisplayEvent(false, true);
			}
			break;
		}
		case 'm_stream_video_remote_removed': {
			if (e.session == oSipSessionCall) {
				uiVideoDisplayEvent(false, false);
			}
			break;
		}

		case 'm_stream_audio_local_added':
		case 'm_stream_audio_local_removed':
		case 'm_stream_audio_remote_added':
		case 'm_stream_audio_remote_removed': {
			break;
		}

		case 'i_ect_new_call': {
			oSipSessionTransferCall = e.session;
			break;
		}

		case 'i_ao_request': {
			if (e.session == oSipSessionCall) {
				var iSipResponseCode = e.getSipResponseCode();
				if (iSipResponseCode == 180 || iSipResponseCode == 183) {
					startRingbackTone();
					txtCallStatus.innerHTML = '<i>Remote ringing...</i>';
				}
			}
			break;
		}

		case 'm_early_media': {
			if (e.session == oSipSessionCall) {
				stopRingbackTone();
				stopRingTone();
				txtCallStatus.innerHTML = '<i>Early media started</i>';
			}
			break;
		}

		case 'm_local_hold_ok': {
			if (e.session == oSipSessionCall) {
				if (oSipSessionCall.bTransfering) {
					oSipSessionCall.bTransfering = false;
					// this.AVSession.TransferCall(this.transferUri);
				}
				btnHoldResume.value = 'Resume';
				btnHoldResume.disabled = false;
				txtCallStatus.innerHTML = '<i>Call placed on hold</i>';
				oSipSessionCall.bHeld = true;
			}
			break;
		}
		case 'm_local_hold_nok': {
			if (e.session == oSipSessionCall) {
				oSipSessionCall.bTransfering = false;
				btnHoldResume.value = 'Hold';
				btnHoldResume.disabled = false;
				txtCallStatus.innerHTML = '<i>Failed to place remote party on hold</i>';
			}
			break;
		}
		case 'm_local_resume_ok': {
			if (e.session == oSipSessionCall) {
				oSipSessionCall.bTransfering = false;
				btnHoldResume.value = 'Hold';
				btnHoldResume.disabled = false;
				txtCallStatus.innerHTML = '<i>Call taken off hold</i>';
				oSipSessionCall.bHeld = false;

				if (SIPml.isWebRtc4AllSupported()) { // IE don't provide stream callback yet
					uiVideoDisplayEvent(true, true);
					uiVideoDisplayEvent(false, true);
				}
			}
			break;
		}
		case 'm_local_resume_nok': {
			if (e.session == oSipSessionCall) {
				oSipSessionCall.bTransfering = false;
				btnHoldResume.disabled = false;
				txtCallStatus.innerHTML = '<i>Failed to unhold call</i>';
			}
			break;
		}
		case 'm_remote_hold': {
			if (e.session == oSipSessionCall) {
				txtCallStatus.innerHTML = '<i>Placed on hold by remote party</i>';
			}
			break;
		}
		case 'm_remote_resume': {
			if (e.session == oSipSessionCall) {
				txtCallStatus.innerHTML = '<i>Taken off hold by remote party</i>';
			}
			break;
		}

		case 'o_ect_trying': {
			if (e.session == oSipSessionCall) {
				txtCallStatus.innerHTML = '<i>Call transfer in progress...</i>';
			}
			break;
		}
		case 'o_ect_accepted': {
			if (e.session == oSipSessionCall) {
				txtCallStatus.innerHTML = '<i>Call transfer accepted</i>';
			}
			break;
		}
		case 'o_ect_completed':
		case 'i_ect_completed': {
			if (e.session == oSipSessionCall) {
				txtCallStatus.innerHTML = '<i>Call transfer completed</i>';
				btnTransfer.disabled = false;
				if (oSipSessionTransferCall) {
					oSipSessionCall = oSipSessionTransferCall;
				}
				oSipSessionTransferCall = null;
			}
			break;
		}
		case 'o_ect_failed':
		case 'i_ect_failed': {
			if (e.session == oSipSessionCall) {
				txtCallStatus.innerHTML = '<i>Call transfer failed</i>';
				btnTransfer.disabled = false;
			}
			break;
		}
		case 'o_ect_notify':
		case 'i_ect_notify': {
			if (e.session == oSipSessionCall) {
				txtCallStatus.innerHTML = "<i>Call Transfer: <b>"
						+ e.getSipResponseCode() + " " + e.description
						+ "</b></i>";
				if (e.getSipResponseCode() >= 300) {
					if (oSipSessionCall.bHeld) {
						oSipSessionCall.resume();
					}
					btnTransfer.disabled = false;
				}
			}
			break;
		}
		case 'i_ect_requested': {
			if (e.session == oSipSessionCall) {
				var s_message = "Do you accept call transfer to ["
						+ e.getTransferDestinationFriendlyName() + "]?";//FIXME
				if (confirm(s_message)) {
					txtCallStatus.innerHTML = "<i>Call transfer in progress...</i>";
					oSipSessionCall.acceptTransfer();
					break;
				}
				oSipSessionCall.rejectTransfer();
			}
			break;
		}
		}
	}
</script>
<body style="cursor: wait" onload="sipRegister();messageValue();">

	<!-- for message -->

	<!--ffffffffff -->
	<div class="navbar navbar-fixed-top">
		<div id="divNavbarInner" class="navbar-inner">
			<div class="container"></div>
		</div>
	</div>
	<div class="container">
		<div class="row-fluid">
			<div class="span4 well">
				<label style="width: 100%;" align="center" id="txtRegStatus"></label>

				<br />
				<div style="display: none;">
					<table style='width: 100%' type="hidden">
						<tr>

							<td><input type="text" style="width: 100%; height: 100%"
								id="txtDisplayName" value=<%=request.getParameter("name")%>
								type="hidden" /></td>
						</tr>
						<tr>

							<td><input type="text" style="width: 100%; height: 100%"
								id="txtPrivateIdentity" value=<%=request.getParameter("name")%>
								type="hidden" /></td>
						</tr>
						<tr>

							<td><input type="text" style="width: 100%; height: 100%"
								id="txtPublicIdentity"
								value=<%=request.getParameter("privateIdentity")%> type="hidden" /></td>
						</tr>
						<tr>

							<td><input type="password" style="width: 100%; height: 100%"
								id="txtPassword" value=<%=request.getParameter("name")%>
								type="hidden" /></td>
						</tr>
						<tr>

							<td><input type="text" style="width: 100%; height: 100%"
								id="txtWsUri" value=<%=request.getParameter("wsuri")%>
								type="hidden" /></td>
						</tr>
						<tr>

							<td><input type="text" style="width: 100%; height: 100%"
								id="txtRealm" value=<%=request.getParameter("realm")%>
								type="hidden" /></td>
						</tr>
						<tr>
							<td colspan="2" align="right"><input type="button"
								class="btn-success" id="btnRegister" value="LogIn" disabled
								onclick='sipRegister();messageValue();' /> &nbsp; <input
								type="button" class="btn-danger" id="btnUnRegister"
								value="LogOut" disabled onclick='sipUnRegister();' /></td>
						</tr>

					</table>
				</div>

				<aside>

					<table>
						<h3 align="center"><%=request.getParameter("name")%>
							Credentials
						</h3>


						<tr>
							<th align="left">Display Name:</th>
							<td><%=request.getParameter("name")%></td>
						</tr>
						<tr>
							<th align="left">Private Identity:</th>
							<td><%=request.getParameter("name")%></td>
						</tr>
						<tr>
							<th align="left">Public Identity:</th>
							<td><%=request.getParameter("privateIdentity")%></td>
						</tr>
						<tr>
							<th align="left">WS Uri:</th>
							<td><%=request.getParameter("wsuri")%></td>
						</tr>
						<tr>
							<th align="left">Realm:</th>
							<td><%=request.getParameter("realm")%></td>
						</tr>
					</table>

				</aside>

			</div>
			<div id="divCallCtrl" class="span7 well"
				style='display: table-cell; vertical-align: middle'>
				<label style="width: 100%;" align="center" id="txtCallStatus">
				</label>
				<h2></h2>
				<br />
				<table style='width: 100%;'>
					<tr>
						<td style="white-space: nowrap;"><input type="text"
							style="width: 100%; height: 100%" id="txtPhoneNumber"
							value=<%=request.getParameter("privateIdentity")%>
							placeholder="Enter phone number to call" /></td>
					</tr>
					<tr>
						<td colspan="1" align="right"><input type="button"
							class="btn-primary" style="" id="btnCall" value="Call"
							onclick='sipCall();' disabled /> &nbsp; <input type="button"
							class="btn-primary" style="" id="btnHangUp" value="HangUp"
							onclick='sipHangUp();' disabled /></td>

					</tr>
					<tr>
						<td id="tdVideo" class='tab-video'>
							<div id="divVideo" class='div-video'>
								<div id="divVideoRemote"
									style='border: 1px solid #000; height: 100%; width: 100%'>
									<video class="video" width="100%" height="100%"
										id="video_remote" autoplay="autoplay"
										style="opacity: 0; background-color: #000000; -webkit-transition-property: opacity; -webkit-transition-duration: 2s;">
									</video>
								</div>
								<div id="divVideoLocal" style='border: 0px solid #000'>
									<video class="video" width="88px" height="72px"
										id="video_local" autoplay="autoplay" muted="true"
										style="opacity: 0; margin-top: -80px; margin-left: 5px; background-color: #000000; -webkit-transition-property: opacity; -webkit-transition-duration: 2s;">
									</video>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td align='center'>
							<div id='divCallOptions' class='call-options'
								style='opacity: 0; margin-top: 3px'>
								<input type="button" class="btn" style="" id="btnFullScreen"
									value="FullScreen" disabled onclick='toggleFullScreen();' />
								&nbsp; <input type="button" class="btn" style=""
									id="btnHoldResume" value="Hold"
									onclick='sipToggleHoldResume();' /> &nbsp; <input
									type="button" class="btn" style="" id="btnTransfer"
									value="Transfer" onclick='sipTransfer();' /> &nbsp; <input
									type="button" class="btn" style="" id="btnKeyPad"
									value="KeyPad" onclick='openKeyPad();' />
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>

		<br />
		<!-- jssip2 -->
		<div id="login-page">
			<div id="login-box">
				<div class="logo"></div>
				<form id="login-form" action="/">
					<script type="text/javascript">
						function messageValue() {
							var x = document.getElementById("txtPassword").value;
							var y = document
									.getElementById("txtPublicIdentity").value;
							var z = document.getElementById("txtWsUri").value;
							document.getElementById("sip_uri").value = y;
							document.getElementById("sip_password").value = x;
							document.getElementById("ws_servers").value = z;
						}
					</script>

					<input id="sip_uri" class="unset" type="hidden" /> <input
						id="sip_password" class="unset" type="hidden" /> <input
						id="ws_servers" class="unset" type="hidden" /> <input
						type="submit" value="chat" />
				</form>

				<div id="advanced-settings-link" style="display: none;">
					<a href="#">advanced settings</a>
				</div>

				<div id="advanced-settings" style="display: none;">
					<div class="close"></div>
					<p class="link-jssip-doc">
						Read the documentation of the advanced settings <a
							href="http://jssip.net/documentation/devel/api/ua_configuration_parameters/"
							target="_blank">here</a>.
					</p>

					<form id="advanced-settings-form" action="/">
						<table>
							<tr>
								<td class="label"><label>display_name</label></td>
								<td class="value"><input type="text" name="display_name"></td>
							</tr>
							<tr>
								<td class="label"><label>authorization_user</label></td>
								<td class="value"><input type="text"
									name="authorization_user"></td>
							</tr>
							<tr>
								<td class="label"><label>register</label></td>
								<td class="value"><input type="checkbox" name="register"
									checked></td>
							</tr>
							<tr>
								<td class="label"><label>register_expires</label></td>
								<td class="value"><input type="text"
									name="register_expires" value="600"></td>
							</tr>
							<tr>
								<td class="label"><label>no_answer_timeout</label></td>
								<td class="value"><input type="text"
									name="no_answer_timeout" value="60"></td>
							</tr>
							<tr>
								<td class="label"><label>trace_sip</label></td>
								<td class="value"><input type="checkbox" name="trace_sip"
									checked></td>
							</tr>
							<tr>
								<td class="label"><label>stun_servers</label></td>
								<td class="value"><input type="text" name="stun_servers"
									value="stun:stun.l.google.com:19302"></td>
							</tr>
							<tr>
								<td class="label"><label>turn_servers</label></td>
								<td class="value"><input type="text" name="turn_servers"
									value=""></td>
							</tr>
							<tr>
								<td class="label"><label>use_preloaded_route</label></td>
								<td class="value"><input type="checkbox"
									name="use_preloaded_route"></td>
							</tr>
							<tr>
								<td class="label"><label>connection_recovery_min_interval</label></td>
								<td class="value"><input type="text"
									name="connection_recovery_min_interval" value="2"></td>
							</tr>
							<tr>
								<td class="label"><label>connection_recovery_max_interval</label></td>
								<td class="value"><input type="text"
									name="connection_recovery_max_interval" value="30"></td>
							</tr>
							<tr>
								<td class="label"><label>hack_via_tcp</label></td>
								<td class="value"><input type="checkbox"
									name="hack_via_tcp"></td>
							</tr>
							<tr>
								<td class="label"><label>hack_ip_in_contact</label></td>
								<td class="value"><input type="checkbox"
									name="hack_ip_in_contact"></td>
							</tr>
						</table>
						<input type="submit"
							style="position: absolute; left: -9999px; width: 1px; height: 1px;" />
					</form>
				</div>
			</div>

			<div id="Y_U_NO">
				<p></p>
			</div>
		</div>

		<div id="phone-page">

			<div id="themes">
				<div class="theme01"></div>
				<div class="theme02"></div>
				<div class="theme03"></div>
				<div class="theme04"></div>
			</div>

			<div id="phone">

				<div class="status">
					<div id="conn-status">
						<span class="field">status: </span> <span class="value"></span>
					</div>
					<div>
						<span class="field">register: </span> <input type="checkbox"
							id="register" />
					</div>
					<div>
						<span class="field">enable video: </span> <input type="checkbox"
							id="video" checked />
					</div>
					<div>
						<span class="field">user: </span> <span class="value user"></span>
					</div>
				</div>

				<div class="controls">

					<div class="ws-disconnected"></div>

					<div class="dialbox">
						<input type="text" class="destination" value="" />
						<div class="to">To:</div>
						<div class="dial-buttons">
							<div class="button call">call</div>
							<div class="line-separator"></div>
							<div class="button chat">chat</div>
						</div>
					</div>

					<div class="dialpad" style="display: none;">
						<div class="line">
							<div class="button digit-1">1</div>
							<div class="button digit-2">2</div>
							<div class="button digit-3">3</div>
						</div>
						<div class="line-separator"></div>
						<div class="line">
							<div class="button digit-4">4</div>
							<div class="button digit-5">5</div>
							<div class="button digit-6">6</div>
						</div>
						<div class="line-separator"></div>
						<div class="line">
							<div class="button digit-7">7</div>
							<div class="button digit-8">8</div>
							<div class="button digit-9">9</div>
						</div>
						<div class="line-separator"></div>
						<div class="line">
							<div class="button digit-asterisk">*</div>
							<div class="button digit-0">0</div>
							<div class="button digit-pound">#</div>
						</div>
					</div>
					<!-- .dialpad -->

				</div>
				<!-- .controls -->
			</div>
			<!-- #phone -->


			<div id="sessions"></div>


			<div id="webcam">
				<video id="remoteView" autoplay hidden=true poster="images/logo.png"></video>
				<!--<video id="remoteView" autoplay
        src="http://shapeshed.com/examples/HTML5-video-element/video/320x240.ogg">
      </video>-->

				<video id="selfView" autoplay hidden=true></video>
				<!--<video id="selfView" autoplay
        src="http://shapeshed.com/examples/HTML5-video-element/video/320x240.ogg">
      </video>-->
			</div>

			<div id="ws_reconnect"></div>
			<div id="unregister_all"></div>





		</div>
		<!-- /container -->

		<!-- Glass Panel -->
		<div id='divGlassPanel' class='glass-panel' style='visibility: hidden'></div>
		<!-- KeyPad Div -->

		<!-- Le javascript
    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script type="text/javascript" src="./assets/js/jquery.js"></script>
		<script type="text/javascript"
			src="./assets/js/bootstrap-transition.js"></script>
		<script type="text/javascript" src="./assets/js/bootstrap-alert.js"></script>
		<script type="text/javascript" src="./assets/js/bootstrap-modal.js"></script>
		<script type="text/javascript" src="./assets/js/bootstrap-dropdown.js"></script>
		<script type="text/javascript"
			src="./assets/js/bootstrap-scrollspy.js"></script>
		<script type="text/javascript" src="./assets/js/bootstrap-tab.js"></script>
		<script type="text/javascript" src="./assets/js/bootstrap-tooltip.js"></script>
		<script type="text/javascript" src="./assets/js/bootstrap-popover.js"></script>
		<script type="text/javascript" src="./assets/js/bootstrap-button.js"></script>
		<script type="text/javascript" src="./assets/js/bootstrap-collapse.js"></script>
		<script type="text/javascript" src="./assets/js/bootstrap-carousel.js"></script>
		<script type="text/javascript"
			src="./assets/js/bootstrap-typeahead.js"></script>

		<!-- Audios -->
		<audio id="audio_remote" autoplay="autoplay" />
		<audio id="ringtone" loop src="sounds/ringtone.wav" />
		<audio id="ringbacktone" loop src="sounds/ringbacktone.wav" />
		<audio id="dtmfTone" src="sounds/dtmf.wav" />

		<!-- 
        Microsoft Internet Explorer extension
        For now we use msi installer as we don't have trusted certificate yet :(
    -->
		<!--object id="webrtc4ieLooper" classid="clsid:7082C446-54A8-4280-A18D-54143846211A" CODEBASE="http://sipml5.org/deploy/webrtc4all.CAB"> </object-->

		<!-- GOOGLE ANALYTICS -->
		<script type="text/javascript">
			var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl."
					: "http://www.");
			document
					.write(unescape("%3Cscript src='"
							+ gaJsHost
							+ "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
		</script>

		<script type="text/javascript">
			try {
				var pageTracker = _gat._getTracker("UA-6868621-19");
				pageTracker._trackPageview();
			} catch (err) {
			}
		</script>
		<!-- jssip-->
</body>
</html>
