<!DOCTYPE html>

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

<body>

<!-- header-wrap -->
<div id="header-wrap">
    <header>

<div><img src="images/tcs_logo.png"</div>

        <nav>
            <ul>
            <li><a href="#main">Home</a></li>
            <li><a href="#services">Services</a></li>
            <li><a href="#portfolio">Phone Book</a></li>
            <li><a href="mbook.jsp?name=<%=request.getParameter("username")%>&privateIdentity=sip:<%=request.getParameter("username")%>@tcs.com&realm=tcs.com">Message Book</a></li>

            </ul>
        </nav>

    </header>
</div>

<!-- content-wrap -->
<div class="content-wrap">


    <!----------------------------------------------------- main ---------------------------->
    <section id="main">

        
         <iframe src="http://10.1.5.13:8080/WebRTC_logic/loginservletphone?username=<%=request.getParameter("username")%>&password=<%=request.getParameter("password")%>" id="myIframe" frameborder="1" width="1000" height="700"></iframe>         
             
      </section>

      <!------------------------------------------ services -------------------------------------------->
      <section id="services" >

             <div class="row no-bottom-margin">
                <section class="col">
                    <h2><a href="conferencing1.jsp"> Conferencing </a></h2>
                    <p><img class="align-left" alt="" src="images/services/webdesign.png" /> Video Conferencing </p>
                </section>
                <section class="col mid">
                  <h2><a href="../sipml5/expert.htm"> Settings </a></h2>
                    <p><img class="align-left" alt="" src="images/services/webdevelopment.png" />Expert Settings</p>
                </section>

                <section class="col">
                  <h2><a href="widgets1.jsp">Widgets </a></h2>
                    <p><img class="align-left" alt="" src="images/services/seo-services.png" />Google Widgets Settings</p>

                </section>
            </div>

            <div class="row">
                <section class="col">
                    <h2><a href="chatroom1.jsp">Chatroom </a></h2>
                    <p><img class="align-left" alt="" src="images/services/print-design.png" /> Group Chat Room</p>
                </section>
                
                <section class="col mid">
                    <h2><a href="voicemail.jsp">Voice Mail</a></h2>
                    <p><img class="align-left" alt="" src="images/services/logo-design-and-branding.png" />Cloud sync phone book </p>
                </section>

                <section class="col">
                
                    <h2><a href="filesharing1.jsp">File Sharing </a></h2>
                    <p><img class="align-left" alt="" src="images/services/newsletter.png" />Share Files </p>

                </section>
                
            <div class="row">
                <section class="col">
                    <h2><a href="donotdisturbselect.jsp"> Do Not Disturb</a></h2>
                    <p><img class="align-left" alt="" src="images/services/webdevelopment.png" /> Reject calls depending on the time of day and Caller-ID.</p>
                </section>
                
                <section class="col mid">
                    <h2><a href="subscribepublish.jsp?name=<%=request.getParameter("username")%>&privateIdentity=sip:<%=request.getParameter("username")%>@tcs.com&realm=tcs.com"> Subscribe to status </a></h2>
                    <p><img class="align-left" alt="" src="images/services/logo-design-and-branding.png" /> Publish presence information and subscribe to others updates</p>
                </section>

                <section class="col">
                
                    <h2><a href="../analytics/graph.jsp"> Analytics</a></h2>
                    <p><img class="align-left" alt="" src="images/services/seo-services.png" />History Trends and Graphical Represenatation </p>

                </section>
                
            </div>
<!--             
              <div class="row no-bottom-margin">
                <section class="col">
                    <h2><a href="face.jsp"> Face Detection</a></h2>
                    <p><img class="align-left" alt="" src="images/services/webdesign.png" /> Human face detection </p>
                </section>
                <section class="col mid">
                  <h2><a href="tunning.jsp"> Tunning </a></h2>
                    <p><img class="align-left" alt="" src="images/services/webdevelopment.png" />Transmaission setiings </p>
                </section>

                <section class="col">
                  <h2><a href="filter.jsp">Filter and snapshot</a></h2>
                    <p><img class="align-left" alt="" src="images/services/seo-services.png" />Applies filter and snapshot of stream </p>

                </section>
            </div>
-->
            <a class="back-to-top" href="#main">Back to Top</a>
            <br/><br/><br/>

      </section>



  <!--------------------------------------------------------------------  Phone book -------------------------->
  
     <section id="portfolio" class="clearfix">
        
      <iframe src="http://10.1.5.13:8080/WebRTC_logic/phonebookLineServletphone" id="myIframe" frameborder="1" width="1000" height="700"></iframe> 

      </section>


      
    

<!-- footer -->
<footer>
    <div class="footer-content">
        <ul class="footer-menu">
            <li><a href="#main">Home</a></li>
            <li><a href="#services">Services</a></li>
            <li><a href="#portfolio">Phone book</a></li>
            <li><a href="mbook.jsp">Message Book</a></li>
        </ul>

        <p class="footer-text">Tata Consultancy Services &nbsp;&nbsp;&nbsp; Designed by CNS Lab</a></p>
    </div>

</footer>

</body>
</html>
</html>