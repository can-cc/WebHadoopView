<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'HbaseTable.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="src/jquery/jquery-2.1.0.min.js" type="text/javascript"></script>
	<script src="src/hbase/hbase.js" type="text/javascript"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    This is my JSP page. <br>
    <input type="text" id="fucktest" value="SinaWeiboData"></input>
    <input  id="fuck" value="gethbasetable" type="button"/>
    <input  id="next" value="next" type="button"/>
    <input  id="fuck" value="f5" type="button"/>
    <input  id="last" value="last" type="button"/>
    <div id="createtable"></div>
  </body>
</html>
