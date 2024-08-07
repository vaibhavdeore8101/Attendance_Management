<!DOCTYPE html>

<html lang="en">

<head>

<meta charset="UTF-8">
<title>Login</title>

<link rel="stylesheet" href="css/style.css">


</head>

<body>

<%String msg=(String)request.getAttribute("msg");

if(msg!=null)
{%>
<script type="text/javascript">
    
	alert("${msg}");
  </script>
<% 
}
%>
  
	<form action="login" method="post">
		<div class='brand'></div>
		
		<div class='login'>
<%-- 			<h5 style="color: red;">${msg}</h5>
 --%>			<div class='login_title'>
				<span>Login to your account</span>
			</div>
			<div class='login_fields'>
				<div class='login_fields__user'>
					<div class='icon'>
						<img
							src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/user_icon_copy.png'>
					</div>
					<input placeholder="Username" type="text" name="uname">


				</div>
				<div class='login_fields__password'>
					<div class='icon'>
						<img
							src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/lock_icon_copy.png'>
					</div>
					<input placeholder='Password' type="password" name="psw">

				</div>

				<div class="select-style" >
					<select name="type">
						<option value="">Type</option>
						<option value="Admin">Admin</option>
						<option value="Teacher">Teacher</option>
						
					</select>
				</div>

			</div>



			<div class='login_fields__submit'>
				<input type='submit' value='Log In'">
				<div class='forgot'>
					<a href="forgot.jsp">Forgotten password?</a>
				</div>
			</div>
		</div>

	</form>
</body>

</html>
