<!DOCTYPE html>

<head>
<script type="text/javascript" src="validate.js"></script>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Branch Detail</title>
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
      <h1>Branch And Subject Detail</h1>
      <form method="post" action="branchdtl" name="branch" onsubmit="branch_detail()">
<%--       <h1 style="color:red">${msg}</h1>  
 --%>      
      <div class="reg_section personal_info">
    	  <h3>Branch Name</h3>
    	  
    	  <input type="text" name="bn" value="" placeholder="Short Name">
      	<input type="text" name="branchname" value="" placeholder="Full Name" >
      	
      </div>
      
      <div class="reg_section password">
     	   <h3>Subject</h3>
     	    	<input type="text" name="subname" value="" placeholder="Subject Name" >
     	   		<input type="text" name="subcode" value="" placeholder="Subject Code">
     	   		<h3>Sem</h3>
     	   <select name="sem">
     	    <option value="" name="sem">Select Sem</option>
     	   
      	    <option value="1" name="sem">1</option>
     	    <option value="2" name="sem">2</option>
     	    <option value="3" name="sem">3</option>
     	    <option value="4" name="sem">4</option>
     	    <option value="5" name="sem">5</option>
     	    <option value="6" name="sem">6</option>
     	    <option value="7" name="sem">7</option>
     	    <option value="8" name="sem">8</option>
     	   </select>
     	 
   	 </div>
    
           <p class="submit"><input type="submit" name="action" value="Add" onclick="branch_detail()">
           					 <input type="submit" name="action" value="Delete">
           					 
           
           </p>
      </form>
   	 </section>
  </div>

 

</body>
</html>