<!DOCTYPE html>
<%@page import="javax.swing.text.Style"%>
<%@ page language="java" import="java.util.*"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Attendance</title>
<link rel="stylesheet" href="css/attendance&result.css">

<script type="text/javascript">
			function selectAll(){
				var items=document.getElementsByName('rollno');
				for(var i=0; i<items.length; i++){
					if(items[i].type=='checkbox')
						items[i].checked=true;
				}
			}
			
			function UnSelectAll(){
				var items=document.getElementsByName('rollno');
				for(var i=0; i<items.length; i++){
					if(items[i].type=='checkbox')
						items[i].checked=false;
				}
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
	<form action="attendance" name="attendance">
		
		<div class="container"
			style="float: left; margin-left: 130px; margin-top: 50px">
			<section class="register">

				<h3>Section 1</h3>
				<br>
				<%-- 	<h1 style="color:red">${msg}</h1>  
 --%>
				<div class="reg_section password">


					<h3>Date</h3>
					<h4> MM/DD/YYYY</h4>
					<input id="File1" type="date" name="attdates" value="${attdate}" />
					<h3>Time</h3>
					<input type="time" name="atttime" value="${atttime}">
					

					<h3>Branch</h3>
					<select name="branch">
						<%
							Iterator itr;
						%>
						<%
							List branches = (List) request.getAttribute("data");
							if (branches != null) {
								for (itr = branches.iterator(); itr.hasNext();) {
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
					<select name="sem">
						<%
							String sem = (String) request.getAttribute("sem");

							if (sem != null) {
						%>
						<option value="${sem}" name="sem">${sem}</option>
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
					<script type="text/javascript">
						function getsubrollno() {
							var attdates=document.forms['attendance']['attdates'].value;
							var atttime=document.forms['attendance']['atttime'].value;

							var branch = document.forms['attendance']['branch'].value;
							var division = document.forms['attendance']['division'].value;

							var sem = document.forms['attendance']['sem'].value;
							var action = document.forms['attendance']['action'].value;
							
							document.location.href = "getsub?branch=" + branch+
									"&division="+division+
									 "&sem=" + sem + 
									"&action=" + action+
									"&attdates="+attdates+
									"&atttime="+atttime;

						}
					</script>

					<input type="button" id="action" name="action" value="Show Student"
						onclick="getsubrollno()" />
				</div>

			</section>
		</div>
		<!-- -------------------------------------------------------------------------------------------------------- -->
		<div class="container"
			style="float: left; margin-left: 200px; margin-top: 50px;">
			<section class="register"
				style="position: absolute; height: 500px; width: 350px; max-height: 500px; overflow-y: scroll;">
				<%--     <h3 style="color: red">${msg}</h3>
 --%>
				<!--  <select name="rollno" multiple="multiple" style="height: 460px;"> -->
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;
				<input type="button" onclick='selectAll()' value="Select All"/>
				<input type="button" onclick='UnSelectAll()' value="Unselect All"/><br>
				<%
					Iterator itr2;
					List rollno = (List) request.getAttribute("attrollno");
					if (rollno != null) {
						for (itr2 = rollno.iterator(); itr2.hasNext();) {
							String roll = (String) itr2.next();
				%>

				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox"  name="rollno" value=<%=roll%> style="margin-left: 5px"><%=roll%><br>
				<%
					}
					}
				%>
			

	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;
				<input type="submit" value="Submit Attendance"
					name="submitattendance">

			</section>

		</div>

	</form>


	<!--   --------------------------------------------------------------------------------------------- -->
</body>
</html>