<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/home_style.css">
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body style="background-color: #ff00ff;">
<%String msg=(String)request.getAttribute("msg");

if(msg!=null)
{%>
<script type="text/javascript">
    
	alert("${msg}");
  </script>
<% 
}
%>
<img id="backgroundimage" src="images/attendance.jpg" border="1"  align="top">
<div class="home_text">
<h2 style="color: yellow;"><b>Note:</b></h2>
&nbsp;&nbsp;<h3>1)Give Valid Mail Id To Inform About Registration Success</h3>
&nbsp;&nbsp;<h3>2)Only Admin Can Perform User Detail Operation (Add,Delete,Update.)</h3>
</div>
</body>
</html>