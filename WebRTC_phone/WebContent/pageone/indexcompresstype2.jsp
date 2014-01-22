<%@include file="header.jsp" %>

<!-- content-wrap -->
<div class="content-wrap">


    <!----------------------------------------------------- main ---------------------------->
    <section id="main">

        
         <iframe src="http://10.1.5.13:8080/WebRTC_logic/loginservlet?userName=<%=request.getParameter("userName")%>&password=<%=request.getParameter("password")%>" id="myIframe" frameborder="1" width="600" height="700"></iframe>         
             
    <aside>
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
    </aside>

      </section>



  <!--------------------------------------------------------------------  Phone book -------------------------->
  
     <section id="portfolio" class="clearfix">
        
      <iframe src="../phonebookLineServlet" id="myIframe" frameborder="1" width="1000" height="700"></iframe> 

      </section>


      
    

<!-- footer -->
<%@include file="footer.jsp" %>