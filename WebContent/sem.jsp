<!DOCTYPE html>
<%@ page language="java" import="java.util.*"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Semister Wise Attendance Result</title>
<link rel="stylesheet" href="css/attendance&result.css">
</head>

<body>
	<%
		String msg = (String) request.getAttribute("msg");

		if (msg != null) {
	%>
	<script type="text/javasc"WebContent/sem.jsp"ript">
		alert("${msg}");
	</script>
	<%
		}
	%>
	<form action="semresult" name="semresult" method="post">


		<div class="container"
			style="float: left; margin-left: 130px; margin-top: 10px">
			<section class="register">
				<div class="reg_section password">

					<h3>Year</h3>
					<input type="text" placeholder="Year" name="year" value="${year}">

					<h3>From</h3>
					<select name="smonth">
						<%
							String smonth = (String) request.getAttribute("smonth");

							if (smonth != null) {
						%>
						<option value="<%=smonth %>" name="smonth"><%=smonth %></option>
						<%
							}
							else
							{
								%>
								<option>Select Starting Month</option>
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


					<h3>TO</h3>
					<select name="endmonth">
						<%
							String emonth = (String) request.getAttribute("endmonth");

							if (emonth != null) {
						%>
						<option value="<%=emonth %>" name="endmonth"><%=emonth %></option>
						<%
							}
							else
							{
								%>
								<option>Select End Month</option>
								<%							}
						%>


						<option value="01" name="endmonth">1</option>
						<option value="02" name="endmonth">2</option>
						<option value="03" name="endmonth">3</option>
						<option value="04" name="endmonth">4</option>
						<option value="05" name="endmonth">5</option>
						<option value="06" name="endmonth">6</option>
						<option value="07" name="endmonth">7</option>
						<option value="08" name="endmonth">8</option>
						<option value="09" name="endmonth">9</option>
						<option value="10" name="endmonth">10</option>
						<option value="11" name="endmonth">11</option>
						<option value="12" name="endmonth">12</option>
					</select>


					<h3>Branch</h3>
					<select name="branch">
						<%
							Iterator itr;
						%>
						<%
							List data = (List) request.getAttribute("data");
							if (data != null) {
								for (itr = data.iterator(); itr.hasNext();) {
									String value = (String) itr.next();
						%>

						<option value=<%=value%>><%=value%></option>
						<%
							}
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
					</select> <input type="text" name="rollno" value="" placeholder="Roll No">
					
					
					
						<script type="text/javascript">
						function getsubjects() {
							var action = "Get_Subjects";
							var branch = document.forms['semresult']['branch'].value;
							var division = document.forms['semresult']['division'].value;
							var year = document.forms['semresult']['year'].value;
							var smonth = document.forms['semresult']['smonth'].value;
							var endmonth = document.forms['semresult']['endmonth'].value;
							var sem = document.forms['semresult']['sem'].value;
							/* 							var action = document.forms['semresult']['action'].value;
							 */document.location.href = "getsub?branch="+ branch + 
									"&division="+division+
									"&smonth=" + smonth+
									"&endmonth=" + endmonth + 
									"&sem=" + sem+
									"&action=" + action + 
									"&year=" + year;

						}
					</script>

	
				</div>

			</section>
		</div>
		<!-- -------------------------------------------------------------------------------------------------------- -->
		<div class="container"
			style="float: left; margin-left: 200px; margin-top: 10px">
			<section class="register"
				style="position: absolute; height: 550px; width: 350px">

				<h3 style="float: left; margin-top: 20px">&nbsp;&nbsp;Attend
					Lecture
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total
					Lecture</h3>



				<select name="total" multiple="multiple"
					style="height: 400px; width: 150px; margin-left: 30px;">
					<%
						Iterator itr2;
						List rollno = (List) request.getAttribute("totaldatesem");
						if (rollno != null) {
							for (itr2 = rollno.iterator(); itr2.hasNext();) {
								String roll = (String) itr2.next();
					%>

					<option value=<%=roll%>><%=roll%></option>
					<%
						}
						}
					%>
				</select> <select name="attend" multiple="multiple"
					style="float: left; width: 150px; height: 400px;">
					<%
						Iterator itr3;
						List attenddate = (List) request.getAttribute("attenddatesem");
						if (attenddate != null) {
							for (itr3 = attenddate.iterator(); itr3.hasNext();) {
								String attlec = (String) itr3.next();
					%>

					<option value=<%=attlec%>><%=attlec%></option>
					<%
						}
						}
					%>
				</select>

				<h3>Total Lecture=<b style="color: red">${totlec}</b></h3>
				<h3>Attend Lecture=<b style="color: red">${attlec}</b></h3>
				<h3>Non Attend Lecture=<b style="color: red">${nonattendlec}</b></h3>
				<h3>Status=<b style="color: red">${status } %</b></h3>

				<input type="submit" value="Show Result">

				<script type="text/javascript">
					function printreport() {

						var branch = document.forms['semresult']['branch'].value;
						var division = document.forms['semresult']['division'].value;
						var year = document.forms['semresult']['year'].value;
						var smonth = document.forms['semresult']['smonth'].value;
						var endmonth = document.forms['semresult']['endmonth'].value;
						var sem = document.forms['semresult']['sem'].value;
						var subject = document.forms['semresult']['subject'].value;
						var rollno = document.forms['semresult']['rollno'].value;

						document.location.href = "semreport?year=" + year
								+ "&startmonth=" + smonth + "&endmonth="
								+ endmonth + "&branch=" + branch + "&sem="
								+ sem + "&subject=" + subject + "&rollno="
								+ rollno;

					}
				</script>
				<input type="button" value="Print Report" onclick="printreport()">
			</section>

		</div>

		<!-- -------------------------------------------------------------------------------------------------------- -->

	</form>


	<!--   --------------------------------------------------------------------------------------------- -->
</body>
</html>