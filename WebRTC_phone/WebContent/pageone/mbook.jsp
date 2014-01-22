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
            <li><a href="indexcompress.jsp#main">Home</a></li>
            <li><a href="indexcompress.jsp#services">Services</a></li>
            <li><a href="indexcompress.jsp#portfolio">Phone Book</a></li>
            <li><a href="mbook.jsp">Message Book</a></li>
            </ul>
        </nav>

    </header>
</div>

<!-- content-wrap -->
<div class="content-wrap">


    <!----------------------------------------------------- main ---------------------------->
    <section id="main">


    <iframe src="../sipml5/message.jsp?name=<%=request.getParameter("name")%>&privateIdentity=sip:<%=request.getParameter("name")%>@tcs.com&realm=tcs.com" id="myIframe" frameborder="1" width="500" height="300"></iframe>           

          <aside>
            
                    <h2> Send via </h2>

                    <ul class="link-list social">
                        <li class="dribble"><a href="#"> SMS </a></li>
                        <li class="dribble"><a href="#"> Email </a></li>
                        <li class="dribble"><a href="#"> SIP Instant Message </a></li>
                        <li class="dribble"><a href="#"> HTTP </a></li>
                        <li class="facebook"><a href="../FacebookServlet">Facebook</a></li>
                    </ul>
                    
       </aside>
      </section>

<h2>Message List</h2>
    <iframe src="msglist.jsp" id="myIframe" frameborder="1" width="1000" height="300"></iframe>           
    

<!-- footer -->
<footer>
    <div class="footer-content">
        <ul class="footer-menu">
            <li><a href="indexcompress.jsp#main">Home</a></li>
            <li><a href="indexcompress.jsp#services">Services</a></li>
            <li><a href="indexcompress.jsp#portfolio">Phone book</a></li>
            <li><a href="mbook.jsp">Message Book</a></li>
        </ul>

        <p class="footer-text">Tata Copnsultancy Services &nbsp;&nbsp;&nbsp; Designed By CNS Lab</a></p>
    </div>

</footer>

</body>
</html>
</html>