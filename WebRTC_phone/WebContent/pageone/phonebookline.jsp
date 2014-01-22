<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.tcs.controller.registration"%>
<%@page import="com.tcs.controller.phonebookLineServlet"%>
<%
	ArrayList<registration> list = new ArrayList<registration>();
	list = (ArrayList) request.getAttribute("registrationDetails");
%>


<!DOCTYPE html>
<!--[if IE 7 ]>    <html class="ie7 oldie"> <![endif]-->
<!--[if IE 8 ]>    <html class="ie8 oldie"> <![endif]-->
<!--[if IE 9 ]>    <html class="ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html>
<!--<![endif]-->

<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="description" content="">
<meta name="author" content="">

<title>WebRTC client</title>

<link rel="stylesheet" href="css/style.css" type="text/css"
	media="screen" />
<link rel="stylesheet" href="css/nivo-slider.css" type="text/css" />
<link rel="stylesheet" href="css/jquery.fancybox-1.3.4.css"
	type="text/css" />

<!--[if lt IE 9]>
	    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.6/jquery.min.js"></script>
<script>
	window.jQuery
			|| document
					.write('<script src="js/jquery-1.6.1.min.js"><\/script>')
</script>

<script src="js/jquery.smoothscroll.js"></script>
<script src="js/jquery.nivo.slider.pack.js"></script>
<script src="js/jquery.easing-1.3.pack.js"></script>
<script src="js/jquery.fancybox-1.3.4.pack.js"></script>
<script src="js/init.js"></script>

</head>

<body>


<table>

<tr>

<form name="f2" method ="post" action="../WebRTC_v9/phonebookServlet">

<td colspan="3"> 
<table>
             <h3 align="center">Add Contacts</h3>
            
            <tr>
            <th align="left">Display Name:</th><td><input type="text" name="displayName"></td>
            </tr>
            
            <tr>
            <th align="left">Public Identity:</th><td><input type="text" name="publicIdentity"></td>
            </tr>
            <tr>
            <td colspan="4" align="center"><input type="submit" name="submit" value="Add"></td>
            </tr>
                      
</table></td>
</form>       
    
 <td>          
	<div class="content-wrap">

		<section id="about-us" class="clearfix">



			<div class="primary">

				<p class="intro">
				<h3 align="center">Phone Book Enteries</h3>
				</p>

				<ul class="the-team">

					<%
						for (int i = 0; i < list.size(); i++) {
					%>
					<li class="odd">
						<div class="thumbnail">
						<table>
						<tr>
						
							<td>
							 
							 <% if (list.get(i).getDisplayName().toString().equalsIgnoreCase("alice")) { %>
								<a href="../sipml5/usercall.jsp"><img alt="thumbnail" src="pageone/images/alice.png"
								width="83" height="78"></a> <%} %>
								
							 <% if (list.get(i).getDisplayName().toString().equalsIgnoreCase("cred")) { %>
								<a href="../sipml5/usercall.jsp"><img alt="thumbnail" src="pageone/images/bob.png"
								width="83" height="78"></a> <%} %>
							
							<% if (list.get(i).getDisplayName().toString().equalsIgnoreCase("stun")) { %>
								<a href="../sipml5/usercall.jsp"><img alt="thumbnail" src="pageone/images/stun.png"
								width="83" height="78"></a> <%} %>	
							
							<% if (list.get(i).getDisplayName().toString().equalsIgnoreCase("hunt")) { %>
								<a href="../sipml5/usercall.jsp"><img alt="thumbnail" src="pageone/images/hunt.png"
								width="83" height="78"></a> <%} %>
								
							</td>
								
							<td>
								<%=list.get(i).getDisplayName().toString()%>&nbsp;	&nbsp;	&nbsp;	&nbsp;
							</td>
				
							<td>
								<%=list.get(i).getPrivateIdentity().toString()%>&nbsp;	&nbsp;	&nbsp;	&nbsp;
							</td>
                    
                           <td> <a href="pageone/contact.jsp?name=<%=list.get(i).getDisplayName().toString()%>&privateIdentity=<%=list.get(i).getPrivateIdentity().toString()%>&wsuri=<%=list.get(i).getWSUri().toString()%>&realm=<%=list.get(i).getRealm().toString()%>" target="_blank">go</a></td>
                    
								</tr>
					
								
								
						</table>
							
						</div>
						</li>
						
					<%
			}
%>
				</ul>

			</div>

		</section>
	</div></td>
	</tr>
            </table>
         
   </body>
</html>
