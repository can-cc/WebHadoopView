<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>jquery+div 弹出层(窗口) div 可拖拽移动位置</title>
    <link href="src/tyanX/CSS/tyanX.css" rel="stylesheet" type="text/css"/>
  <script src="src/jquery/jquery-2.1.0.min.js" type="text/javascript"></script>
    <script src="src/tyanX/js/tyanX.js" type="text/javascript"></script>
    <script src="src/tyanX/js/MsgEngine.js" type="text/javascript"></script>
<script type="text/javascript">

	$(document).ready(function() {
		$("#fuck2").click(function() {
			$.ajax({
				type : "POST",
				url : "./MSG",
				success : function(data) {
					MsgEngine(data);
				},
				dataType : "text",
			});
		});
	});
</script>

</head>
<body>

     fucsk;
     <input  id="fuck" type="button"/>
     <input type="button" id="fuck2" name="Submit2" value="POST" /> 
     <div id="span"></div>
     
</body>
</html>