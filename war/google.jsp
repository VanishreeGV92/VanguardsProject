<!DOCTYPE html>
<%@page import="com.OAuth.firstsample.OAuthCons"%>
<%@page import="com.OAuth.firstsample.OAuthPojo"%>
<html>
<head>
<title>DEMO - Login With Google using Java</title>
<link href='/bootstrap.min.css' rel='stylesheet' type='text/css'/>
</head>
<body>
 
    
 <% 
 String id = request.getParameter("id");
 String name = (String)session.getAttribute("name");
 String email = (String)session.getAttribute("email");
 

 %>

 <div style="width:400px;margin:auto;padding-top:30px;">
  <table class="table table-bordered">
    <tr>
      <td>User ID</td>
     <td> <%=id%></td>
    </tr>
    <tr>
      <td>Name</td>
      <td><%=name%></td>
  </tr>
    <tr>
      <td>Email</td>
   <td><%=email%></td>
    </tr>
    
    
  </table> 
</div>

</body>
</html>