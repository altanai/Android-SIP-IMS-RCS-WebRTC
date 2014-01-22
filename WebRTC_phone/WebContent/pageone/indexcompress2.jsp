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

<body>

<!-- header-wrap -->
<div id="header-wrap">
    <header>

<div><img src="images/tcs_logo.png"</div>

        <nav>
            <ul>
            <li><a href="#main">Home</a></li>
            <li><a href="#services">Services</a></li>
            <li><a href="#portfolio">Phonebook</a></li>
            <li><a href="#about-us">Message Book</a></li>
            <li><a href="#contact">Send a Message</a></li>
            </ul>
        </nav>

    </header>
</div>

<!-- content-wrap -->
<div class="content-wrap">


    <!----------------------------------------------------- main ---------------------------->
    <section id="main">

        
         <iframe src="../loginservlet?username=<%=request.getParameter("username")%>&password=<%=request.getParameter("password")%>" id="myIframe" frameborder="1" width="1000" height="700"></iframe>         
             
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
                    <h2><a href="contactbook.jsp">Phonebook</a></h2>
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
                    <h2><a href="../googleContacts1Servlet"> Contacts from Google </a></h2>
                    <p><img class="align-left" alt="" src="images/services/logo-design-and-branding.png" /> Imported Contacts from Google</p>
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
        
      <iframe src="../phonebookLineServlet" id="myIframe" frameborder="1" width="1000" height="700"></iframe> 

      </section>


<!-- messagebook -->








      <!--------------------------------------------------------- /messahe Book ------------------------------------------->
      <section id="about-us" class="clearfix">
      
            <h2>Message </h2>


      <iframe src="messagebook.jsp" id="myIframe" frameborder="1" width="700" height="500"></iframe>

      <a class="back-to-top" href="#main">Back to Top</a>



      </section>
      
      
      
      
      
      
      

      <!------------------------------------------------------- Message Send ---------------------------------------------->
      <section id="contact" class="clearfix">

        

            <div class="primary">
               <jsp:include page="messagesend.jsp"/>
              


            </div>

            <aside>
            
                    <h2> Send via </h2>

                    <ul class="link-list social">
                        <li class="dribble"><a href="#"> SMS </a></li>
                        <li class="dribble"><a href="#"> Email </a></li>
                        <li class="dribble"><a href="#"> SIP Instant Message </a></li>
                        <li class="dribble"><a href="#"> HTTP </a></li>
                        <li class="facebook"><a href="../FacebookServlet">Facebook</a></li>
                    </ul>
            
                   <!--  <h2> share on Social Media</h2>

                    <ul class="link-list social">
                        <li class="facebook"><a href="#">Facebook</a></li>
                        <li class="googleplus"><a href="#">Google+</a></li>
                        <li class="twitter"><a href="#">Twitter</a></li>
                        <li class="linkedin"><a href="#">Linkedin</a></li>
                        <li class="delicious"><a href="#">Delicious</a></li>
                        <li class="flickr"><a href="#">Flickr</a></li>
                    </ul>
					-->
            </aside>

            <a class="back-to-top" href="#main">Back to Top</a>

     </section>

     

</div>

<!-- footer -->
<footer>
    <div class="footer-content">
        <ul class="footer-menu">
            <li><a href="#main">Home</a></li>
            <li><a href="#services">Services</a></li>
            <li><a href="#portfolio">Phonebook</a></li>
            <li><a href="#about-us">Message Book</a></li>
            <li><a href="#contact">Send a Message</a></li>
            <!-- <li class="rss-feed"><a href="#">RSS Feed</a></li> -->
        </ul>

        <p class="footer-text">Tata Copnsultancy Services &nbsp;&nbsp;&nbsp; Designed by CNS Lab</a></p>
    </div>

</footer>

</body>
</html>
</html>