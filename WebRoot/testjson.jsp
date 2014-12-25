<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'testjson.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <script src="src/jquery/jquery-2.1.0.min.js" type="text/javascript"></script>  
  <script>
  $(document).ready(function() {
		$("#home").click(function() {
			$.ajax({
				type : "POST",
				url : "./servlet/MSG",
				success : function(data) {
					alert(data);
				},
				dataType : "text",
			});
		});
	
});
  </script>
  
  <body>
    This is my JSP page. <br>
    <div id="home" class="icondiv">  
    <img src="src/Desk/images/icon/dos.ico" class="icon" />
    </div>  
  </body>
</html>
