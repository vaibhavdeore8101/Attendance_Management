<!DOCTYPE html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Add User</title>
  <link rel="stylesheet" href="css/regstyles.css">
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
  <div class="container">
    <section class="register">
      <h1>Add User</h1>
      <form method="post" action="register">
<%--       <h1 style="color:red">${msg}</h1>  
 --%>      <div class="reg_section personal_info">
    	  <h3>Username And Password</h3>
    	  
    	  <input type="text" name="username" value="" placeholder="Your  Username">
      	<input type="text" name="pass" value="" placeholder="User Password">
      	<select name="type">
      	    <option value="" name="type">User Type</option>
      	
      	   <option value="Admin" name="type">Admin</option>
     	   <option value="Teacher" name="type">Teacher</option>
     	   </select>
      </div>
      
      <div class="reg_section password">
     	   <h3>Question And Answer</h3>
     	   <select name="question">
      	   <option value="" name="question">Security Question</option>
      	  
      	   <option value="WHAT IS YOUR NICKNAME ?" name="question">WHAT IS YOUR NICKNAME ?</option>
     	   <option value=""WHAT IS YOUR BIRTH YEAR ?" name="question">WHAT IS YOUR BIRTH YEAR ?</option>
     	   </select>
     	   <input type="text" name="answer" value="" placeholder="Answer">
   	 </div>
    
           <p class="submit"><input type="submit" name="action" value="Create">
           					 <input type="submit" name="action" value="Delete">
           					 <input type="submit" name="action" value="Update">
           
           </p>
      </form>
   	 </section>
  </div>

 

</body>
</html>