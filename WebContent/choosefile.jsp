<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Choose File</title>
</head>
<body>
<form action="uploadsheet" method = "post" enctype="multipart/form-data">
  Select a file: 
  <input name="myfile" type="file" value="Browse">

  <input type="submit" value="Upload File">
</form>
</body>
</html>