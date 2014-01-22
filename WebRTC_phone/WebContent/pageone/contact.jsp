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
         
         
         					<% if (request.getParameter("name").equalsIgnoreCase("alice")) { %>
								<img alt="thumbnail" src="images/alice.png" width="83" height="78"></a> <%} %>
								
							 <% if (request.getParameter("name").equalsIgnoreCase("cred")) { %>
								<img alt="thumbnail" src="images/bob.png" width="83" height="78"></a> <%} %>
							
							<%  if (request.getParameter("name").equalsIgnoreCase("stun")) { %>
								<img alt="thumbnail" src="images/stun.png" width="83" height="78"></a> <%} %>	
							
							<% if (request.getParameter("name").equalsIgnoreCase("hunt")) { %>
								<img alt="thumbnail" src="images/hunt.png" width="83" height="78"></a> <%} %>  
								
                            User name : <%=request.getParameter("name")%>
           
 <iframe src="../sipml5/usercall.jsp?name=<%=request.getParameter("name")%>&privateIdentity=<%=request.getParameter("privateIdentity")%>&wsuri=<%=request.getParameter("wsuri")%>&realm=<%=request.getParameter("realm")%>" id="myIframe" frameborder="1" width="1000" height="510"></iframe>           
       
        </p>
        </div>



        <div class="row no-bottom-margin">

            <section class="col">
                <h2></h2>

                <p> Tata Consultancy Services</p>
            </section>
            <section class="col mid">
                <h2></h2>

                <p>Proof of concept Demostration </p>
            </section>

            <section class="col">
                <h2></h2>

                <p>WebRTC platform </p>
            </section>
        </div>

        <a class="back-to-top" href="#main">Back to Top</a>

      </section>

</div>

<!-- footer -->
<footer>
    <div class="footer-content">
        <ul class="footer-menu">
            <li><a href="#main">Home</a></li>
            <li><a href="#services">Services</a></li>
            <li><a href="#portfolio">Portfolio</a></li>
            <li><a href="#about-us">About</a></li>
            <li><a href="#contact">Contact</a></li>
            <!-- <li class="rss-feed"><a href="#">RSS Feed</a></li> -->
        </ul>

        <p class="footer-text">Tata Copnsultancy Services &nbsp;&nbsp;&nbsp; Designed by CNS Lab</a></p>
    </div>

</footer>

</body>
</html>
