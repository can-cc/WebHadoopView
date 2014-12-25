<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'redactortest.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<link rel="stylesheet" type="text/css" href="src/Redactor/css/kube.min.css">
	<link rel="stylesheet" type="text/css" href="src/Redactor/css/screen.css">
	<link rel="stylesheet" type="text/css" href="src/Redactor/css/redactor.css">
	<script src="src/Redactor/js/jquery-1.8.2.min.js"></script>
	<script src="src/Redactor/js/global.js"></script>
	<script src="src/Redactor/js/redactor.js"></script>
	<script src="src/Redactor/js/prettify.js"></script>
	<script src="src/Redactor/js/zh_cn.js"></script>
	
	<script type="text/javascript">
    $(function () {
        $('#redactor_content').redactor({
               focus: true
        });
    });
</script>

  </head>
  
  <body>
    This is my JSP page. <br>
    <div id="box-inside-white">
    <div class="wrapper">      
        <div id="main" class="row" style="height:1000px;">
            <article class="threequarter">         
            <h2>
                Fixed toolbar <a href="http://www.jq22.com">帮助</a>
            </h2>
                        <textarea id="redactor_content" name="content"style="height:580px;"> <h3>这里是标题</h3><p>这里面放内容</p></textarea> </article>    
        </div>
    </div>
</div>
  </body>
</html>
