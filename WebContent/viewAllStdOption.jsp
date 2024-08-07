
<!DOCTYPE html>
<%@ page language="java" import="java.util.*"%>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  
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
  <div class="container" style="margin-top:200px;">
    <section class="register">
      <h1>Select Branch And Sem To View Student</h1>
      <form method="post" action="showall">
<%--       <h1 style="color:red">${msg}</h1>  
 --%>      <div class="reg_section personal_info">
    	  <h3>Select Branch</h3>
    	  
    	<select name="branch" onChange="change()" id="selc" >
		<%Iterator itr;%>
		<% List data= (List)request.getAttribute("data");
		for (itr=data.iterator(); itr.hasNext(); )
		{
			String value=(String)itr.next();
	%>

 <option value=<%=value%>><%=value%></option>
 	<%
 	}
 	%>	
</select>

<h3>Select Division</h3>
					<select name="division">
					
					<%
					String div=(String)request.getAttribute("division");
					if(div!=null)
					{
						%>
						<option value="<%=div%>" name="division"><%=div%></option>
					<%
					}
					else
					{
						%>
						<option value="" name="division">Select Division</option>
				   <% 
					}
					%>
						<option value="A" name="year">A</option>
						<option value="B" name="year">B</option>
						
					
					</select>
     	 
      </div>
      
      <div class="reg_section password">
     	   <h3>Select Sem</h3>
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
    
           <p class="submit"><input type="submit" name="action"  value="Show Student">
           
           					 
           
           </p>
      </form>
   	 </section>
  </div>

 

</body>
</html>