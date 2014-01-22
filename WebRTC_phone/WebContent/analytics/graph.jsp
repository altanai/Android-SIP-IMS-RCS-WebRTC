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

        <nav>
            <ul>
            <li><a href="/pageone/index.jsp#main">Home</a></li>
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

        <h2>Call Details ( Outgoing / Incoming )</h2>
        
         <iframe src="line.html" id="myIframe" frameborder="1" width="600" height="500"></iframe> 
         
         <br/><br/><br/>   
      <a class="back-to-top" href="#main">Back to Top</a>

      </section>





      <!------------------------------------------ services -------------------------------------------->
      <section id="services" >

         <h2>Usage Analytics</h2>
        
         <iframe src="pie.html" id="myIframe" frameborder="1" width="1000" height="500"></iframe>             

            <a class="back-to-top" href="#main">Back to Top</a>

      </section>


  <!--------------------------------------------------------------------  Phone book -------------------------->
  
     <section id="portfolio" class="clearfix">
      
          <h2>Plar Area Graph </h2>
        
         <iframe src="polarArea.html" id="myIframe" frameborder="1" width="500" height="500"></iframe>             

            <a class="back-to-top" href="#main">Back to Top</a>
            
      </section>







      <!--------------------------------------------------------- /messahe Book ------------------------------------------->
      <section id="about-us" class="clearfix">
      
          <h2>Doughnut Area Graph </h2>
        
         <iframe src="doughnut.html" id="myIframe" frameborder="1" width="500" height="500"></iframe>
         
      <a class="back-to-top" href="#main">Back to Top</a>

      </section>
         
      
      
      
      

      <!------------------------------------------------------- Message Send ---------------------------------------------->
      <section id="contact" class="clearfix">

          <h2>Radar Area Graph </h2>
        
         <iframe src="radar.html" id="myIframe" frameborder="1" width="500" height="500"></iframe>
       
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