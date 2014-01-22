<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<%@ page language="java"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JSP Multiple Checkbox</title>
</head>
<body><form name="form1" onsubmit="checkBoxValidation()">
<table align="center">
<tr>
<td><input type="checkbox" name="widget" value="<iframe id='datetime' src ='../googleContacts1Servlet'  width='700' height='700' ></iframe>"/> Google Contacts </td>
<td><input type="checkbox" name="widget" value="<iframe id='googlecalendar' src ='../googleCalendar.jsp'  width='700' height='700' ></iframe>"/> Facebook Contacts</td>
<td><input type="checkbox" name="widget" value="<iframe id='googlemap' src ='phonebook.jsp'  width='700' height='700'></iframe>"/> WebRTc phonebook</td>
<td><input type="checkbox" name="widget" value="<iframe id='twitter' src ='../registrationServlet' width='700' height='700'></iframe>"/> Registration values</td>
</tr>
<tr >
<td colspan="8" align="center" ><input type="submit" value="submit"/></td>
</tr>
</table>
</form>

<%-- <%String widgets[]= request.getParameterValues("widget");
if(widgets != null){%>
<ul>
<%for(int i=0; i<widgets.length; i++){%>
<li><%=widgets[i]%></li>
<%}%>
</ul>
<%}%> --%>

<%String widgets[]= request.getParameterValues("widget");
if(widgets != null){%>
<table>
<tr>
<%for(int i=0; i<widgets.length; i++){%>
<td>
<%=widgets[i]%>
</td>
<%}%>
</tr>
<%}%>
</table>
</body>
</html>