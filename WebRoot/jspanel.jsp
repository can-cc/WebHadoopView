<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'jspanel.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="src/jquery/jquery-2.1.0.min.js" type="text/javascript"></script>  
	<script src="src/jspanel/jquery.jspanel-1.6.1.js" type="text/javascript"></script>  
	<link href="src/jspanel/jsPanel.css" rel="stylesheet" type="text/css"/>
	<script src="src/jquery-ui-1.9.2.custom/js/jquery-ui-1.9.2.custom.js" type="text/javascript"></script>  
	<link href="src/jquery-ui-1.9.2.custom/css/ui-lightness/jquery-ui-1.9.2.custom.css" rel="stylesheet" type="text/css"/>

	
	<script type="text/javascript">
	$(document).ready(function() {
	$("#fuck").click(function(){

	 $("body").jsPanel({
            size: { width:440, height:'auto' },
            theme: 'info',
            overflow: 'hidden',
            contentBG: {'padding':'20px'},
            ajax: {
                url: './sss.html'
              
            }
        });
	});
});
	</script>

  </head>
  
  <body>
    <input  id="fuck" type="button"/>
  </body>
</html>
