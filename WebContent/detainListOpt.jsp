
<!DOCTYPE html>
<%@ page language="java" import="java.util.*"%>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  
  <link rel="stylesheet" href="css/regstyles.css">
  
  				<script type="text/javascript">
					function getsubjects() {
							var action="DetainOptSub";
							var branch = document.forms['status']['branch'].value;
							var smonth = document.forms['status']['smonth'].value;
							var endmonth = document.forms['status']['emonth'].value;
							var sem = document.forms['status']['sem'].value;
							var year = document.forms['status']['year'].value;

						document.location.href = "getsub?branch=" + branch
									+"&smonth=" + smonth
									+"&endmonth=" + endmonth
									+"&sem=" + sem
									+"&year=" + year
									+"&action=" + action;

						}
					</script>
					
					
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
  <div class="container" style="margin-top:100px;">
    <section class="register">
      <h1>Detain List Option</h1>
      
      <form method="post" action="detainlist" name="status">
<%--       <h1 style="color:red">${msg}</h1>  
 --%>      <div class="reg_section personal_info">
 
 <h3>Year</h3>
					<input type="text"  name="year" value="${year}">
 
  <h3>Start Month</h3>
 
 <select name="smonth">
						<%
							String smonth = (String) request.getAttribute("smonth");

							if (smonth != null) {
						%>
						<option value="<%=smonth%>" name="smonth"><%=smonth%></option>
						<%
							}
							else
							{
								%>
								<option>Select Start Month</option>
								<%
							}
						%>
						<option value="01" name="smonth">1</option>
						<option value="02" name="smonth">2</option>
						<option value="03" name="smonth">3</option>
						<option value="04" name="smonth">4</option>
						<option value="05" name="smonth">5</option>
						<option value="06" name="smonth">6</option>
						<option value="07" name="smonth">7</option>
						<option value="08" name="smonth">8</option>
						<option value="09" name="smonth">9</option>
						<option value="10" name="smonth">10</option>
						<option value="11" name="smonth">11</option>
						<option value="12" name="smonth">12</option>
					</select>
					
  <h3>End Month</h3>
 
 <select name="emonth">
						<%
							String endmonth = (String) request.getAttribute("endmonth");

							if (endmonth != null) {
						%>
						<option value="<%=endmonth%>" name="emonth"><%=endmonth%></option>
						<%
							}
							else
							{
								%>
								<option>Select End Month</option>
								<%
							}
						%>
						<option value="01" name="emonth">1</option>
						<option value="02" name="emonth">2</option>
						<option value="03" name="emonth">3</option>
						<option value="04" name="emonth">4</option>
						<option value="05" name="emonth">5</option>
						<option value="06" name="emonth">6</option>
						<option value="07" name="emonth">7</option>
						<option value="08" name="emonth">8</option>
						<option value="09" name="emonth">9</option>
						<option value="10" name="emonth">10</option>
						<option value="11" name="emonth">11</option>
						<option value="12" name="emonth">12</option>
					</select>
 
  <h3>Branch</h3>
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
     	 
      
       <h3>Sem</h3>
      
     	   <select name="sem" onchange="getsubjects();">
					
					<%
							String sem = (String) request.getAttribute("sem");

							if (sem != null) {
						%>
						<option value="<%=sem %>" name="sem"><%=sem %></option>
						<%
							}
							else
							{
								%>
								 <option>Select Sem</option>
								<%
							}
						%>
						

						<option value="1" name="sem">1</option>
						<option value="2" name="sem">2</option>
						<option value="3" name="sem">3</option>
						<option value="4" name="sem">4</option>
						<option value="5" name="sem">5</option>
						<option value="6" name="sem">6</option>
						<option value="7" name="sem">7</option>
						<option value="8" name="sem">8</option>
					</select>
 				<h3>Subject</h3>
						<select name="subject">
						<%
							Iterator itr1;
							List subject = (List) request.getAttribute("subject");
							if (subject != null) {
								for (itr1 = subject.iterator(); itr1.hasNext();) {
									String value1 = (String) itr1.next();
						%>

						<option value=<%=value1%>><%=value1%></option>
						<%
							}
							}
						%>
					</select> 
     	  
   	 
    			<p class="submit"><input type="submit" name="action"  value="Show Detain List ">
           					 
           
           </p>
      </form>
   	 </section>
  </div>

 

</body>
</html>