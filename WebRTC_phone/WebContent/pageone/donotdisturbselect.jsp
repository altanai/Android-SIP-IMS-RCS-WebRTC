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






<body link=#000066>

<!-- header-wrap -->
<div id="header-wrap">
    <header>

<div><img src="images/tcs_logo.png"</div>

        <nav>
            <ul>
            <li><a href="indexcompress.jsp#main">Home</a></li>
            <li><a href="indexcompress.jsp#services">Services</a></li>
            <li><a href="indexcompress.jsp#portfolio">Phone book</a></li>
            <li><a href="mbook.jsp">Message Book</a></li>
            </ul>
        </nav>

    </header>
</div>

<!-- content-wrap -->
<div class="content-wrap">

    <!-- main -->
    <section id="main">

        <div class="intro-box">
           <p class="intro"> 



<style type="text/css">
<!--
body {
      background-color: #FFFFFF;
      font-family: Verdana, Arial, Helvetica, sans-serif;
      font-style: normal;
      font-size:8pt;
      margin: 0 15px;
}
td {font-size: 8pt;}

label, input, button, select, textarea {
    font-size:8pt;
}
.hidden {
  display: none;
  font-family: Verdana, Arial, Helvetica, sans-serif;
  font-size: 7pt;
  color: black;
}

.visible {
  display: block;
  font-family: Verdana, Arial, Helvetica, sans-serif;
  font-size: 7pt;
  color: black;
}

.checked_groups { color: #FF1493;font-size: 7.5pt; border: 1px solid #cccccc;}

.contacts_table td {
	vertical-align: middle;
}

.control-group:nth-child(2n+1) {
    background-color: #f9f9f9;
}
.evenc           { background-color: #f9f9f9;}

input[type="checkbox"] {
        margin-top: 1px;
    }

.checkbox.inline {
        padding-top: 0px;
        margin-right: 5px;
    }
-->
</style>

        <SCRIPT>
        var _action = '';
        function checkForm(form) {
            if (_action=='send' && form.mailto.value=='') {
                window.alert('Please fill in the Email address');
                form.mailto.focus();
                return false;
            } else {
                return true;
            }
        }
        function saveHandler(elem) {
            _action = 'save';
        }
        function sendHandler(elem) {
            _action = 'send';
        }
        </SCRIPT>
        
            <div class=row-fluid>
                <div class='span12'>
                </div>
            </div>
            
           <div class='row-fluid'>
          
		</div>
	    <div class='pull-left'></div>
        <div class='pull-right'>
        
        </div>
        <div class='row-fluid'>
        
        </div>
        
        
           <div class=row-fluid><div class=span12>
           <h2>Do Not Disturb</h2>
           </div></div>
        
        <form method=post class=form-horizontal name=sipsettings onSubmit="return checkForm(this)">
  
        
           <div class=row-fluid><div class=span12><h4>Rules
          </h4></div></div>
        
        <div class=row-fluid>
        <table class='table table-condensed table-striped middle' border=0 width=100%><thead><tr>
            <tr>
            <th colspan=6>
            Temporary</th></tr></thead><tr><td style='vertical-align: middle'><span>Duration</span></td><td colspan='2' style='vertical-align: middle'><select id=testselect rel='popover' class=input-medium name=duration data-original-title='Temporary Rules' data-content='This will override the permanent rules for the chosen duration.'> <option><option value=1  >  1<option value=5  >  5<option value=10 > 10<option value=20 > 20<option value=30 > 30<option value=45 > 45<option value=60 > 60<option value=90 > 90<option value=120>120<option value=150>150<option value=180>180<option value=240>240<option value=480>480</select><span> Minute(s)</span></td><td style='vertical-align:middle' class='note'><input type=radio name=radio_temporary value=0 > Everybody</td> <td style='vertical-align:middle' class='note'><input type=radio name=radio_temporary value=1 > Nobody </td><td style='vertical-align:middle' class='checked_groups'></td></tr><thead>
            <th colspan=6>
            Permanent</th></tr><tr>
            <th>Days</th>
        <th colspan=2>Time Interval</th>
        <th colspan=3>Groups</th>
        </tr></thead>
        
            <tr>
            <td style='vertical-align: middle;'><span class=''>Every day</span></td><td><select class=span10 name=start_127><option><option >00:00<option >00:30<option >01:00<option >01:30<option >02:00<option >02:30<option >03:00<option >03:30<option >04:00<option >04:30<option >05:00<option >05:30<option >06:00<option >06:30<option >07:00<option >07:30<option >08:00<option >08:30<option >09:00<option >09:30<option >10:00<option >10:30<option >11:00<option >11:30<option >12:00<option >12:30<option >13:00<option >13:30<option >14:00<option >14:30<option >15:00<option >15:30<option >16:00<option >16:30<option >17:00<option >17:30<option >18:00<option >18:30<option >19:00<option >19:30<option >20:00<option >20:30<option >21:00<option >21:30<option >22:00<option >22:30<option >23:00<option >23:30<option >23:59</select><td><select class=span10 name=stop_127><option><option > 00:00<option > 00:30<option > 01:00<option > 01:30<option > 02:00<option > 02:30<option > 03:00<option > 03:30<option > 04:00<option > 04:30<option > 05:00<option > 05:30<option > 06:00<option > 06:30<option > 07:00<option > 07:30<option > 08:00<option > 08:30<option > 09:00<option > 09:30<option > 10:00<option > 10:30<option > 11:00<option > 11:30<option > 12:00<option > 12:30<option > 13:00<option > 13:30<option > 14:00<option > 14:30<option > 15:00<option > 15:30<option > 16:00<option > 16:30<option > 17:00<option > 17:30<option > 18:00<option > 18:30<option > 19:00<option > 19:30<option > 20:00<option > 20:30<option > 21:00<option > 21:30<option > 22:00<option > 22:30<option > 23:00<option > 23:30<option >23:59</select><td style='vertical-align: middle;' class='note'><input style='vertical-align: top;' type=radio name=radio_persistent_127 value=0 checked> Everybody</td><td style='vertical-align: middle;' class='note'><input style='vertical-align: top;' type=radio name=radio_persistent_127 value=1 > Nobody</td><td style='vertical-align: middle' class='controls note'></td>
            </tr>
            
            <tr>
            <td style='vertical-align: middle;'><span class=''>Weekday</span></td><td><select class=span10 name=start_31><option><option >00:00<option >00:30<option >01:00<option >01:30<option >02:00<option >02:30<option >03:00<option >03:30<option >04:00<option >04:30<option >05:00<option >05:30<option >06:00<option >06:30<option >07:00<option >07:30<option >08:00<option >08:30<option >09:00<option >09:30<option >10:00<option >10:30<option >11:00<option >11:30<option >12:00<option >12:30<option >13:00<option >13:30<option >14:00<option >14:30<option >15:00<option >15:30<option >16:00<option >16:30<option >17:00<option >17:30<option >18:00<option >18:30<option >19:00<option >19:30<option >20:00<option >20:30<option >21:00<option >21:30<option >22:00<option >22:30<option >23:00<option >23:30<option >23:59</select><td><select class=span10 name=stop_31><option><option > 00:00<option > 00:30<option > 01:00<option > 01:30<option > 02:00<option > 02:30<option > 03:00<option > 03:30<option > 04:00<option > 04:30<option > 05:00<option > 05:30<option > 06:00<option > 06:30<option > 07:00<option > 07:30<option > 08:00<option > 08:30<option > 09:00<option > 09:30<option > 10:00<option > 10:30<option > 11:00<option > 11:30<option > 12:00<option > 12:30<option > 13:00<option > 13:30<option > 14:00<option > 14:30<option > 15:00<option > 15:30<option > 16:00<option > 16:30<option > 17:00<option > 17:30<option > 18:00<option > 18:30<option > 19:00<option > 19:30<option > 20:00<option > 20:30<option > 21:00<option > 21:30<option > 22:00<option > 22:30<option > 23:00<option > 23:30<option >23:59</select><td style='vertical-align: middle;' class='note'><input style='vertical-align: top;' type=radio name=radio_persistent_31 value=0 checked> Everybody</td><td style='vertical-align: middle;' class='note'><input style='vertical-align: top;' type=radio name=radio_persistent_31 value=1 > Nobody</td><td style='vertical-align: middle' class='controls note'></td>
            </tr>
            
            <tr>
            <td style='vertical-align: middle;'><span class=''>Weekend</span></td><td><select class=span10 name=start_96><option><option >00:00<option >00:30<option >01:00<option >01:30<option >02:00<option >02:30<option >03:00<option >03:30<option >04:00<option >04:30<option >05:00<option >05:30<option >06:00<option >06:30<option >07:00<option >07:30<option >08:00<option >08:30<option >09:00<option >09:30<option >10:00<option >10:30<option >11:00<option >11:30<option >12:00<option >12:30<option >13:00<option >13:30<option >14:00<option >14:30<option >15:00<option >15:30<option >16:00<option >16:30<option >17:00<option >17:30<option >18:00<option >18:30<option >19:00<option >19:30<option >20:00<option >20:30<option >21:00<option >21:30<option >22:00<option >22:30<option >23:00<option >23:30<option >23:59</select><td><select class=span10 name=stop_96><option><option > 00:00<option > 00:30<option > 01:00<option > 01:30<option > 02:00<option > 02:30<option > 03:00<option > 03:30<option > 04:00<option > 04:30<option > 05:00<option > 05:30<option > 06:00<option > 06:30<option > 07:00<option > 07:30<option > 08:00<option > 08:30<option > 09:00<option > 09:30<option > 10:00<option > 10:30<option > 11:00<option > 11:30<option > 12:00<option > 12:30<option > 13:00<option > 13:30<option > 14:00<option > 14:30<option > 15:00<option > 15:30<option > 16:00<option > 16:30<option > 17:00<option > 17:30<option > 18:00<option > 18:30<option > 19:00<option > 19:30<option > 20:00<option > 20:30<option > 21:00<option > 21:30<option > 22:00<option > 22:30<option > 23:00<option > 23:30<option >23:59</select><td style='vertical-align: middle;' class='note'><input style='vertical-align: top;' type=radio name=radio_persistent_96 value=0 checked> Everybody</td><td style='vertical-align: middle;' class='note'><input style='vertical-align: top;' type=radio name=radio_persistent_96 value=1 > Nobody</td><td style='vertical-align: middle' class='controls note'></td>
            </tr>
            <tr><td colspan=6 style='height:3px; padding:0px' bgcolor=lightgrey></td></tr>
            <tr>
            <td style='vertical-align: middle;'><span class=''>Monday</span></td><td><select class=span10 name=start_1><option><option >00:00<option >00:30<option >01:00<option >01:30<option >02:00<option >02:30<option >03:00<option >03:30<option >04:00<option >04:30<option >05:00<option >05:30<option >06:00<option >06:30<option >07:00<option >07:30<option >08:00<option >08:30<option >09:00<option >09:30<option >10:00<option >10:30<option >11:00<option >11:30<option >12:00<option >12:30<option >13:00<option >13:30<option >14:00<option >14:30<option >15:00<option >15:30<option >16:00<option >16:30<option >17:00<option >17:30<option >18:00<option >18:30<option >19:00<option >19:30<option >20:00<option >20:30<option >21:00<option >21:30<option >22:00<option >22:30<option >23:00<option >23:30<option >23:59</select><td><select class=span10 name=stop_1><option><option > 00:00<option > 00:30<option > 01:00<option > 01:30<option > 02:00<option > 02:30<option > 03:00<option > 03:30<option > 04:00<option > 04:30<option > 05:00<option > 05:30<option > 06:00<option > 06:30<option > 07:00<option > 07:30<option > 08:00<option > 08:30<option > 09:00<option > 09:30<option > 10:00<option > 10:30<option > 11:00<option > 11:30<option > 12:00<option > 12:30<option > 13:00<option > 13:30<option > 14:00<option > 14:30<option > 15:00<option > 15:30<option > 16:00<option > 16:30<option > 17:00<option > 17:30<option > 18:00<option > 18:30<option > 19:00<option > 19:30<option > 20:00<option > 20:30<option > 21:00<option > 21:30<option > 22:00<option > 22:30<option > 23:00<option > 23:30<option >23:59</select><td style='vertical-align: middle;' class='note'><input style='vertical-align: top;' type=radio name=radio_persistent_1 value=0 checked> Everybody</td><td style='vertical-align: middle;' class='note'><input style='vertical-align: top;' type=radio name=radio_persistent_1 value=1 > Nobody</td><td style='vertical-align: middle' class='controls note'></td>
            </tr>
            
            <tr>
            <td style='vertical-align: middle;'><span class=''>Tuesday</span></td><td><select class=span10 name=start_2><option><option >00:00<option >00:30<option >01:00<option >01:30<option >02:00<option >02:30<option >03:00<option >03:30<option >04:00<option >04:30<option >05:00<option >05:30<option >06:00<option >06:30<option >07:00<option >07:30<option >08:00<option >08:30<option >09:00<option >09:30<option >10:00<option >10:30<option >11:00<option >11:30<option >12:00<option >12:30<option >13:00<option >13:30<option >14:00<option >14:30<option >15:00<option >15:30<option >16:00<option >16:30<option >17:00<option >17:30<option >18:00<option >18:30<option >19:00<option >19:30<option >20:00<option >20:30<option >21:00<option >21:30<option >22:00<option >22:30<option >23:00<option >23:30<option >23:59</select><td><select class=span10 name=stop_2><option><option > 00:00<option > 00:30<option > 01:00<option > 01:30<option > 02:00<option > 02:30<option > 03:00<option > 03:30<option > 04:00<option > 04:30<option > 05:00<option > 05:30<option > 06:00<option > 06:30<option > 07:00<option > 07:30<option > 08:00<option > 08:30<option > 09:00<option > 09:30<option > 10:00<option > 10:30<option > 11:00<option > 11:30<option > 12:00<option > 12:30<option > 13:00<option > 13:30<option > 14:00<option > 14:30<option > 15:00<option > 15:30<option > 16:00<option > 16:30<option > 17:00<option > 17:30<option > 18:00<option > 18:30<option > 19:00<option > 19:30<option > 20:00<option > 20:30<option > 21:00<option > 21:30<option > 22:00<option > 22:30<option > 23:00<option > 23:30<option >23:59</select><td style='vertical-align: middle;' class='note'><input style='vertical-align: top;' type=radio name=radio_persistent_2 value=0 checked> Everybody</td><td style='vertical-align: middle;' class='note'><input style='vertical-align: top;' type=radio name=radio_persistent_2 value=1 > Nobody</td><td style='vertical-align: middle' class='controls note'></td>
            </tr>
            
            <tr>
            <td style='vertical-align: middle;'><span class=''>Wednesday</span></td><td><select class=span10 name=start_4><option><option >00:00<option >00:30<option >01:00<option >01:30<option >02:00<option >02:30<option >03:00<option >03:30<option >04:00<option >04:30<option >05:00<option >05:30<option >06:00<option >06:30<option >07:00<option >07:30<option >08:00<option >08:30<option >09:00<option >09:30<option >10:00<option >10:30<option >11:00<option >11:30<option >12:00<option >12:30<option >13:00<option >13:30<option >14:00<option >14:30<option >15:00<option >15:30<option >16:00<option >16:30<option >17:00<option >17:30<option >18:00<option >18:30<option >19:00<option >19:30<option >20:00<option >20:30<option >21:00<option >21:30<option >22:00<option >22:30<option >23:00<option >23:30<option >23:59</select><td><select class=span10 name=stop_4><option><option > 00:00<option > 00:30<option > 01:00<option > 01:30<option > 02:00<option > 02:30<option > 03:00<option > 03:30<option > 04:00<option > 04:30<option > 05:00<option > 05:30<option > 06:00<option > 06:30<option > 07:00<option > 07:30<option > 08:00<option > 08:30<option > 09:00<option > 09:30<option > 10:00<option > 10:30<option > 11:00<option > 11:30<option > 12:00<option > 12:30<option > 13:00<option > 13:30<option > 14:00<option > 14:30<option > 15:00<option > 15:30<option > 16:00<option > 16:30<option > 17:00<option > 17:30<option > 18:00<option > 18:30<option > 19:00<option > 19:30<option > 20:00<option > 20:30<option > 21:00<option > 21:30<option > 22:00<option > 22:30<option > 23:00<option > 23:30<option >23:59</select><td style='vertical-align: middle;' class='note'><input style='vertical-align: top;' type=radio name=radio_persistent_4 value=0 checked> Everybody</td><td style='vertical-align: middle;' class='note'><input style='vertical-align: top;' type=radio name=radio_persistent_4 value=1 > Nobody</td><td style='vertical-align: middle' class='controls note'></td>
            </tr>
            
            <tr>
            <td style='vertical-align: middle;'><span class=''>Thursday</span></td><td><select class=span10 name=start_8><option><option >00:00<option >00:30<option >01:00<option >01:30<option >02:00<option >02:30<option >03:00<option >03:30<option >04:00<option >04:30<option >05:00<option >05:30<option >06:00<option >06:30<option >07:00<option >07:30<option >08:00<option >08:30<option >09:00<option >09:30<option >10:00<option >10:30<option >11:00<option >11:30<option >12:00<option >12:30<option >13:00<option >13:30<option >14:00<option >14:30<option >15:00<option >15:30<option >16:00<option >16:30<option >17:00<option >17:30<option >18:00<option >18:30<option >19:00<option >19:30<option >20:00<option >20:30<option >21:00<option >21:30<option >22:00<option >22:30<option >23:00<option >23:30<option >23:59</select><td><select class=span10 name=stop_8><option><option > 00:00<option > 00:30<option > 01:00<option > 01:30<option > 02:00<option > 02:30<option > 03:00<option > 03:30<option > 04:00<option > 04:30<option > 05:00<option > 05:30<option > 06:00<option > 06:30<option > 07:00<option > 07:30<option > 08:00<option > 08:30<option > 09:00<option > 09:30<option > 10:00<option > 10:30<option > 11:00<option > 11:30<option > 12:00<option > 12:30<option > 13:00<option > 13:30<option > 14:00<option > 14:30<option > 15:00<option > 15:30<option > 16:00<option > 16:30<option > 17:00<option > 17:30<option > 18:00<option > 18:30<option > 19:00<option > 19:30<option > 20:00<option > 20:30<option > 21:00<option > 21:30<option > 22:00<option > 22:30<option > 23:00<option > 23:30<option >23:59</select><td style='vertical-align: middle;' class='note'><input style='vertical-align: top;' type=radio name=radio_persistent_8 value=0 checked> Everybody</td><td style='vertical-align: middle;' class='note'><input style='vertical-align: top;' type=radio name=radio_persistent_8 value=1 > Nobody</td><td style='vertical-align: middle' class='controls note'></td>
            </tr>
            
            <tr>
            <td style='vertical-align: middle;'><span class=''>Friday</span></td><td><select class=span10 name=start_16><option><option >00:00<option >00:30<option >01:00<option >01:30<option >02:00<option >02:30<option >03:00<option >03:30<option >04:00<option >04:30<option >05:00<option >05:30<option >06:00<option >06:30<option >07:00<option >07:30<option >08:00<option >08:30<option >09:00<option >09:30<option >10:00<option >10:30<option >11:00<option >11:30<option >12:00<option >12:30<option >13:00<option >13:30<option >14:00<option >14:30<option >15:00<option >15:30<option >16:00<option >16:30<option >17:00<option >17:30<option >18:00<option >18:30<option >19:00<option >19:30<option >20:00<option >20:30<option >21:00<option >21:30<option >22:00<option >22:30<option >23:00<option >23:30<option >23:59</select><td><select class=span10 name=stop_16><option><option > 00:00<option > 00:30<option > 01:00<option > 01:30<option > 02:00<option > 02:30<option > 03:00<option > 03:30<option > 04:00<option > 04:30<option > 05:00<option > 05:30<option > 06:00<option > 06:30<option > 07:00<option > 07:30<option > 08:00<option > 08:30<option > 09:00<option > 09:30<option > 10:00<option > 10:30<option > 11:00<option > 11:30<option > 12:00<option > 12:30<option > 13:00<option > 13:30<option > 14:00<option > 14:30<option > 15:00<option > 15:30<option > 16:00<option > 16:30<option > 17:00<option > 17:30<option > 18:00<option > 18:30<option > 19:00<option > 19:30<option > 20:00<option > 20:30<option > 21:00<option > 21:30<option > 22:00<option > 22:30<option > 23:00<option > 23:30<option >23:59</select><td style='vertical-align: middle;' class='note'><input style='vertical-align: top;' type=radio name=radio_persistent_16 value=0 checked> Everybody</td><td style='vertical-align: middle;' class='note'><input style='vertical-align: top;' type=radio name=radio_persistent_16 value=1 > Nobody</td><td style='vertical-align: middle' class='controls note'></td>
            </tr>
            
            <tr>
            <td style='vertical-align: middle;'><span class=''>Saturday</span></td><td><select class=span10 name=start_32><option><option >00:00<option >00:30<option >01:00<option >01:30<option >02:00<option >02:30<option >03:00<option >03:30<option >04:00<option >04:30<option >05:00<option >05:30<option >06:00<option >06:30<option >07:00<option >07:30<option >08:00<option >08:30<option >09:00<option >09:30<option >10:00<option >10:30<option >11:00<option >11:30<option >12:00<option >12:30<option >13:00<option >13:30<option >14:00<option >14:30<option >15:00<option >15:30<option >16:00<option >16:30<option >17:00<option >17:30<option >18:00<option >18:30<option >19:00<option >19:30<option >20:00<option >20:30<option >21:00<option >21:30<option >22:00<option >22:30<option >23:00<option >23:30<option >23:59</select><td><select class=span10 name=stop_32><option><option > 00:00<option > 00:30<option > 01:00<option > 01:30<option > 02:00<option > 02:30<option > 03:00<option > 03:30<option > 04:00<option > 04:30<option > 05:00<option > 05:30<option > 06:00<option > 06:30<option > 07:00<option > 07:30<option > 08:00<option > 08:30<option > 09:00<option > 09:30<option > 10:00<option > 10:30<option > 11:00<option > 11:30<option > 12:00<option > 12:30<option > 13:00<option > 13:30<option > 14:00<option > 14:30<option > 15:00<option > 15:30<option > 16:00<option > 16:30<option > 17:00<option > 17:30<option > 18:00<option > 18:30<option > 19:00<option > 19:30<option > 20:00<option > 20:30<option > 21:00<option > 21:30<option > 22:00<option > 22:30<option > 23:00<option > 23:30<option >23:59</select><td style='vertical-align: middle;' class='note'><input style='vertical-align: top;' type=radio name=radio_persistent_32 value=0 checked> Everybody</td><td style='vertical-align: middle;' class='note'><input style='vertical-align: top;' type=radio name=radio_persistent_32 value=1 > Nobody</td><td style='vertical-align: middle' class='controls note'></td>
            </tr>
            
            <tr>
            <td style='vertical-align: middle;'><span class=''>Sunday</span></td><td><select class=span10 name=start_64><option><option >00:00<option >00:30<option >01:00<option >01:30<option >02:00<option >02:30<option >03:00<option >03:30<option >04:00<option >04:30<option >05:00<option >05:30<option >06:00<option >06:30<option >07:00<option >07:30<option >08:00<option >08:30<option >09:00<option >09:30<option >10:00<option >10:30<option >11:00<option >11:30<option >12:00<option >12:30<option >13:00<option >13:30<option >14:00<option >14:30<option >15:00<option >15:30<option >16:00<option >16:30<option >17:00<option >17:30<option >18:00<option >18:30<option >19:00<option >19:30<option >20:00<option >20:30<option >21:00<option >21:30<option >22:00<option >22:30<option >23:00<option >23:30<option >23:59</select><td><select class=span10 name=stop_64><option><option > 00:00<option > 00:30<option > 01:00<option > 01:30<option > 02:00<option > 02:30<option > 03:00<option > 03:30<option > 04:00<option > 04:30<option > 05:00<option > 05:30<option > 06:00<option > 06:30<option > 07:00<option > 07:30<option > 08:00<option > 08:30<option > 09:00<option > 09:30<option > 10:00<option > 10:30<option > 11:00<option > 11:30<option > 12:00<option > 12:30<option > 13:00<option > 13:30<option > 14:00<option > 14:30<option > 15:00<option > 15:30<option > 16:00<option > 16:30<option > 17:00<option > 17:30<option > 18:00<option > 18:30<option > 19:00<option > 19:30<option > 20:00<option > 20:30<option > 21:00<option > 21:30<option > 22:00<option > 22:30<option > 23:00<option > 23:30<option >23:59</select><td style='vertical-align: middle;' class='note'><input style='vertical-align: top;' type=radio name=radio_persistent_64 value=0 checked> Everybody</td><td style='vertical-align: middle;' class='note'><input style='vertical-align: top;' type=radio name=radio_persistent_64 value=1 > Nobody</td><td style='vertical-align: middle' class='controls note'></td>
            </tr>
            </table></div>
        <div class='form-actions'>
            <input type=hidden name=action value="set accept rules">
        
        <input class='btn' type=submit value="Save"
               onClick=saveHandler(this)>
        
          </div>
        
            <input type=hidden name=account value='altanaibisht@sip2sip.info'>
            <input type=hidden name=sip_engine value='sipthor'>
            <input type=hidden name=user_agent value=''>
            <input type=hidden name=realm value=''>
            
        </form>
        
           <div class=row-fluid><div class=span12><h4>Rejected Callers
          </h4></div></div>
        
        <form class=form-horizontal method=post name=reject_form onSubmit="return checkForm(this)">
        
        <div class=row-fluid>
        
        </div>
        <div class=control-group><label class=control-label>SIP Address</label><div class=controls>
            <input type=text size=35 name=rejectMembers[]></div></div><div class='form-actions'><input class=btn type=submit value="Save"
               onClick=saveHandler(this)>
            </div></div>
            <input type=hidden name=action value="set reject">
        
            <input type=hidden name=account value='altanaibisht@sip2sip.info'>
            <input type=hidden name=sip_engine value='sipthor'>
            <input type=hidden name=user_agent value=''>
            <input type=hidden name=realm value=''>
            
        </form>







            </p>
        </div>


      </section>

</div>








</body>
</html>
