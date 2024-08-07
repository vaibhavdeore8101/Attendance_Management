

<!DOCTYPE html>
<%@ page language="java" import="java.util.*"%>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<link rel="stylesheet" href="css/regstyles.css">

<script type="text/javascript">
	function getsubjects() {
		var action = "genStatusSub";
		var year = document.forms['status']['year'].value;
		var month = document.forms['status']['month'].value;
		var branch = document.forms['status']['branch'].value;
		var division = document.forms['status']['division'].value;
		var sem = document.forms['status']['sem'].value;
		document.location.href = "getsub?branch=" + branch + 
				"&division="+division+
				"&sem=" + sem+
				"&year=" + year + 
				"&month=" + month + 
				"&action=" + action;

	}
</script>


</head>

<body>
	<%
		String msg = (String) request.getAttribute("msg");

		if (msg != null) {
	%>
	<script type="text/javascript">
		alert("${msg}");
	</script>
	<%
		}
	%>
	<div class="container" style="margin-top: 100px;">
		<section class="register">
			<h1>Generate Attendance Status</h1>

			<form method="post" action="genstatus" name="status">

				<%--       <h1 style="color:red">${msg}</h1>  
 --%>
				<div class="reg_section personal_info">

					<h3>Year</h3>
					<input type="text" name="year" value="${year}">

					<h3>Month (only for month)</h3>
					<select name="month">
						<%
							String month = (String) request.getAttribute("month");

							if (month != null) {
						%>
						<option value="<%=month%>" name="month"><%=month%></option>
						<%
							} else {
						%>
						<option>Select Month</option>
						<%
							}
						%>
						<option value="01" name="month">1</option>
						<option value="02" name="month">2</option>
						<option value="03" name="month">3</option>
						<option value="04" name="month">4</option>
						<option value="05" name="month">5</option>
						<option value="06" name="month">6</option>
						<option value="07" name="month">7</option>
						<option value="08" name="month">8</option>
						<option value="09" name="month">9</option>
						<option value="10" name="month">10</option>
						<option value="11" name="month">11</option>
						<option value="12" name="month">12</option>
					</select>


					<h3>Select Branch</h3>

					<select name="branch" id="selc">
						<%
							Iterator itr;
						%>
						<%
							List data = (List) request.getAttribute("data");
							for (itr = data.iterator(); itr.hasNext();) {
								String value = (String) itr.next();
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


					<h3>Select Sem</h3>
					<select name="sem" onchange="getsubjects();">

						<%
							String sem = (String) request.getAttribute("sem");

							if (sem != null) {
						%>
						<option value="<%=sem%>" name="sem"><%=sem%></option>
						<%
							} else {
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
					
				</div>

				<p class="submit">
					<input type="submit" name="action" value="Generate For Monthly">
					<input type="submit" name="action" value="Generate Sor Sem">



				</p>
			</form>
		</section>
	</div>



</body>
</html>