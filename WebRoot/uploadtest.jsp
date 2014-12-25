<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Insert title here</title>
</head>
<body>
 <div align="center">
<p>请选择需要上传的文件</p>
<form action="./servlet/UpLoadServer" method="post" enctype="multipart/form-data" name="form1" id="form1">
<table width="320" border="1">
    <tr>
      <td width="69" height="81">上传人</td>
      <td width="235"><label><input type="text" name="name" id="name" /></label></td>
    </tr>
    <tr>
      <td>上传文件</td>
      <td><label>
        <input type="file" name="uploadfile" id="uploadfile" />
      </label></td>
    </tr>
    <tr>
      <td><label>
        <input type="reset" name="button" id="button" value="重置" />
      </label></td>
      <td><label>
        <input type="submit" name="button2" id="button2" value="提交" />
      </label></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
</table>
</form>
</div>
</body>
</html>