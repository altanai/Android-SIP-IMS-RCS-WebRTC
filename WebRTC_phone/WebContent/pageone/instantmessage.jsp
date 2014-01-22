<!DOCTYPE html>
<!--[if IE 7 ]>    <html class="ie7 oldie"> <![endif]-->
<!--[if IE 8 ]>    <html class="ie8 oldie"> <![endif]-->
<!--[if IE 9 ]>    <html class="ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html> <!--<![endif]-->

<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="description" content="">
    <meta name="author" content="">

    <title>WebRTC client </title>

    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="css/nivo-slider.css" type="text/css" />
    <link rel="stylesheet" href="css/jquery.fancybox-1.3.4.css" type="text/css" />



   <script src="../instantmessage/jquery-1.6.1.min.js" type="text/javascript"></script>
   <script src="../instantmessage/init.js" type="text/javascript"></script>
   <script src="../instantmessage/gui.js" type="text/javascript"></script>
   <script src="../instantmessage/jssip-devel.js" type="text/javascript"></script>
   <script src="../instantmessage/piwik.js" type="text/javascript"></script>
   
   
    <!--[if lt IE 9]>
	    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="js/jquery-1.6.1.min.js"><\/script>')</script>

    <script src="js/jquery.smoothscroll.js"></script>
    <script src="js/jquery.nivo.slider.pack.js"></script>
    <script src="js/jquery.easing-1.3.pack.js"></script>
    <script src="js/jquery.fancybox-1.3.4.pack.js"></script>
    <script src="js/init.js"></script>

</head>

<body>

<div class="content-wrap">

      <section id="about-us" class="clearfix">

            <h1>Instant Message </h1>

            
 <div id="login-page">
    <div id="login-box">
      <div class="logo"></div>
      
      <form id="login-form" action="/">
        <label>SIP URI <span class="description"><span class="ie">i.e. </span>sip:alice@example.com</span></label>
        <input id="sip_uri" class="unset" type="text"/>
        <label>SIP password</label>
        <input id="sip_password" class="unset" type="password"/>
        <label>WS URI <span class="description"><span class="ie">i.e. </span>ws://example.com:10080</span></label>
        <input id="ws_servers" class="unset" type="text"/>
        <input type="submit" style="position: absolute; left: -9999px; width: 1px; height: 1px;"/>
      </form>
      
      <div id="advanced-settings">
       <div class="close"></div>
       
       <form id="advanced-settings-form" action="/">
          <table>
            <tr>
              <td ><label>display_name</label></td>
              <td class="value"><input type="text" name="display_name"></td>
            </tr>
            <tr>
              <td ><label>authorization_user</label></td>
              <td class="value"><input type="text" name="authorization_user"></td>
            </tr>
            <tr>
              <td ><label>register</label></td>
              <td class="value"><input type="checkbox" name="register" checked></td>
            </tr>
            <tr>
              <td ><label>register_expires</label></td>
              <td class="value"><input type="text" name="register_expires" value="600"></td>
            </tr>
            <tr>
              <td ><label>no_answer_timeout</label></td>
              <td class="value"><input type="text" name="no_answer_timeout" value="60"></td>
            </tr>
            <tr>
              <td ><label>trace_sip</label></td>
              <td class="value"><input type="checkbox" name="trace_sip" checked></td>
            </tr>
            <tr>
              <td ><label>stun_servers</label></td>
              <td class="value"><input type="text" name="stun_servers" value="stun:stun.l.google.com:19302"></td>
            </tr>
            <tr>
              <td ><label>turn_servers</label></td>
              <td class="value"><input type="text" name="turn_servers" value=""></td>
            </tr>
            <tr>
              <td ><label>use_preloaded_route</label></td>
              <td class="value"><input type="checkbox" name="use_preloaded_route"></td>
            </tr>
            <tr>
              <td ><label>connection_recovery_min_interval</label></td>
              <td class="value"><input type="text" name="connection_recovery_min_interval" value="2"></td>
            </tr>
            <tr>
              <td ><label>connection_recovery_max_interval</label></td>
              <td class="value"><input type="text" name="connection_recovery_max_interval" value="30"></td>
            </tr>
            <tr>
              <td ><label>hack_via_tcp</label></td>
              <td class="value"><input type="checkbox" name="hack_via_tcp"></td>
            </tr>
            <tr>
              <td ><label>hack_ip_in_contact</label></td>
              <td class="value"><input type="checkbox" name="hack_ip_in_contact"></td>
            </tr>
          </table>
          <input type="submit" style="position: absolute; left: -9999px; width: 1px; height: 1px;"/>
        </form>
      </div>
    </div>

    <div id="Y_U_NO"><p></p></div>
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
          <span class="field">status: </span>
          <span class="value"></span>
        </div>
        <div>
          <span class="field">register: </span>
          <input type="checkbox" id="register"/>
        </div>
        <div>
          <span class="field">enable video: </span>
          <input type="checkbox" id="video" checked/>
        </div>
        <div>
          <span class="field">user: </span>
          <span class="value user"></span>
        </div>
      </div>

      <div class="controls">

        <div class="ws-disconnected"></div>

        <div class="dialbox">
          
          <div class="to">To:</div><input type="text" class="destination" value=""/>
          <div class="dial-buttons">
            <div class="button call">call</div>
            <div class="line-separator"></div>
            <div class="button chat">chat</div>
          </div>
        </div>

       

      </div><!-- .controls -->
    </div><!-- #phone -->


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
  </div><!-- phone-page -->
        <footer>
            <object id="fakeVideoDisplay" classid="clsid:5C2C407B-09D9-449B-BB83-C39B7802A684" style="visibility:hidden;"> </object>
            <object id="fakeLooper" classid="clsid:7082C446-54A8-4280-A18D-54143846211A" style="visibility:hidden;"> </object>
            <object id="fakeSessionDescription" classid="clsid:DBA9F8E2-F9FB-47CF-8797-986A69A1CA9C" style="visibility:hidden;"> </object>
            <object id="fakeNetTransport" classid="clsid:5A7D84EC-382C-4844-AB3A-9825DBE30DAE" style="visibility:hidden;"> </object>
            <object id="fakePeerConnection" classid="clsid:56D10AD3-8F52-4AA4-854B-41F4D6F9CEA3" style="visibility:hidden;"> </object>
            <!-- 
                NPAPI  browsers: Safari, Opera and Firefox
            -->
            <!--embed id="WebRtc4npapi" type="application/w4a" width='1' height='1' style='visibility:hidden;' /-->
        </footer>
    </div>
    <!-- /container -->

    <!-- Glass Panel -->
    <div id='divGlassPanel' class='glass-panel' style='visibility:hidden'></div>
    <!-- KeyPad Div -->
    <div id='divKeyPad' class='span2 well div-keypad' style="left:0px; top:0px; width:250; height:240; visibility:hidden">
        <table style="width: 100%; height: 100%">
            <tr><td><input type="button" style="width: 33%" class="btn" value="1" onclick="sipSendDTMF('1');"/><input type="button" style="width: 33%" class="btn" value="2" onclick="sipSendDTMF('2');"/><input type="button" style="width: 33%" class="btn" value="3" onclick="sipSendDTMF('3');"/></td></tr>
            <tr><td><input type="button" style="width: 33%" class="btn" value="4" onclick="sipSendDTMF('4');"/><input type="button" style="width: 33%" class="btn" value="5" onclick="sipSendDTMF('5');"/><input type="button" style="width: 33%" class="btn" value="6" onclick="sipSendDTMF('6');"/></td></tr>
            <tr><td><input type="button" style="width: 33%" class="btn" value="7" onclick="sipSendDTMF('7');"/><input type="button" style="width: 33%" class="btn" value="8" onclick="sipSendDTMF('8');"/><input type="button" style="width: 33%" class="btn" value="9" onclick="sipSendDTMF('9');"/></td></tr>
            <tr><td><input type="button" style="width: 33%" class="btn" value="*" onclick="sipSendDTMF('*');"/><input type="button" style="width: 33%" class="btn" value="0" onclick="sipSendDTMF('0');"/><input type="button" style="width: 33%" class="btn" value="#" onclick="sipSendDTMF('#');"/></td></tr>
            <tr><td colspan=3><input type="button" style="width: 100%" class="btn btn-medium btn-danger" value="close" onclick="closeKeyPad();" /></td></tr>
        </table>
    </div>

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/javascript" src="./assets/js/jquery.js"></script>
    <script type="text/javascript" src="./assets/js/bootstrap-transition.js"></script>
    <script type="text/javascript" src="./assets/js/bootstrap-alert.js"></script>
    <script type="text/javascript" src="./assets/js/bootstrap-modal.js"></script>
    <script type="text/javascript" src="./assets/js/bootstrap-dropdown.js"></script>
    <script type="text/javascript" src="./assets/js/bootstrap-scrollspy.js"></script>
    <script type="text/javascript" src="./assets/js/bootstrap-tab.js"></script>
    <script type="text/javascript" src="./assets/js/bootstrap-tooltip.js"></script>
    <script type="text/javascript" src="./assets/js/bootstrap-popover.js"></script>
    <script type="text/javascript" src="./assets/js/bootstrap-button.js"></script>
    <script type="text/javascript" src="./assets/js/bootstrap-collapse.js"></script>
    <script type="text/javascript" src="./assets/js/bootstrap-carousel.js"></script>
    <script type="text/javascript" src="./assets/js/bootstrap-typeahead.js"></script>
	
    <!-- Audios -->
    <audio id="audio_remote" autoplay="autoplay" />
    <audio id="ringtone" loop src="sounds/ringtone.wav" />
    <audio id="ringbacktone" loop src="sounds/ringbacktone.wav" />
    <audio id="dtmfTone" src="sounds/dtmf.wav" />

 
    <script type="text/javascript">
        var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
        document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
    </script>

    <script type="text/javascript">
        try {
            var pageTracker = _gat._getTracker("UA-6868621-19");
            pageTracker._trackPageview();
        } catch (err) { }
    </script>
<!-- jssip-->

	</section>
</div>



</body>
</html>







