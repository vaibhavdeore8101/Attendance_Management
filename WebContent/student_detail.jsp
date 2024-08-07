<!DOCTYPE html>
<%@ page language="java" import="java.util.*"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Student Registration</title>
<link rel="stylesheet" href="css/regstyles.css">
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
	<form action="studentreg" method="post">
		
		<div class="container"
			style="float: left; margin-left: 30px; margin-top: 10px">
			<section class="register">
				<h3>Section 1</h3>
				<br>
				<%-- 	<h1 style="color:red">${msg}</h1> 
 --%>
				<h3 style="color: red">${resultMessage}</h3>
				<div class="reg_section password">
					<h3>Student Detail:-</h3>
					<%--  <input type="text" name="addate" required="required" value="${admisiondate}"/> --%>
					<h3>Admision Date</h3>
					<input type="date" id="attDate" name="attDate"
						value="${admisiondate}"> <input type="text"
						name="enrollmentno" placeholder="Enrollment No" value="${enrno}">

					<select name="branch"  id="selc">
					<%
					String branch=(String)request.getAttribute("branch");
					if(branch!=null)
					{
				     %>
				     <option value="<%=branch %>" name="branch"><%=branch %></option>
					<% 
					}
					else
					{
						%>
						 <option value="" name="branch">Select Branch</option>
					<% 
					}
					%>
						

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
					
					<select name="year">
					<%
					String year=(String)request.getAttribute("year");
					if(year!=null)
					{
						%>
						<option value="<%=year%>" name="year"><%=year%></option>
					<%
					}
					else
					{
						%>
						<option value="" name="year">Select Year</option>
				   <% 
					}
					%>
						<option value="1" name="year">1</option>
						<option value="2" name="year">2</option>
						<option value="3" name="year">3</option>
						<option value="4" name="year">4</option>
					</select> <select name="sem">

					<%
					String sem=(String)request.getAttribute("sem");
					if(sem!=null)
					{
						%>
					<option value="<%=sem %>" name="sem"><%=sem %></option>
					<%
					}
					else
					{
						%>
						<option value="" name="sem">Select Sem</option>
					<%}
					
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
					
					<input type="text" name="rollno" placeholder="Roll No"
						value="${rollno}"> 
						
						<input type="text" name="studentname"
						placeholder="Student Name" value="${sname}"> 
						
						<select name="gender">
						<%
						String gender=(String)request.getAttribute("gender");
						if(gender!=null)
						{
							%>
							<option value="<%=gender%>" name="gender"><%=gender%></option>
						<%
						}
						else
						{
							%>
						<option value="" name="gender">Select Gender</option>
						<%
						}
						%>
						

						

						<option value="Male" name="gender">Male</option>
						<option value="Female" name="gender">Female</option>
						<option value="Other" name="gender">Other</option>
					</select>
					<!--  dob  -->
					<h3>DOB</h3>
					<input type="date" id="dobDate" placeholder="Date Of Birth"
						name="dob" value="${dob}">
					<%--  <input type="text" name="dob" value="" placeholder="Date Of Birth" value="${dob}"> --%>


				</div>

			</section>
		</div>
		<!-- -------------------------------------------------------------------------------------------------------- -->
		<div class="container"
			style="float: left; margin-left: 20px; margin-top: 10px">
			<section class="register"
				style="position: absolute; height: 425px; width: 310px">
				<h3>Section 2</h3>
				<div class="reg_section password">
					<h3>Parent Detail:-</h3>

					<input type="text" name="fname" placeholder="Father Name"
						value="${fname}"> <input type="text" name="fmono"
						placeholder="Father Mo." value="${fmono }"> <input
						type="text" name="mname" placeholder="Mother Name"
						value="${mname }"> <input type="text" name="mmono"
						placeholder="Mother Mo." value="${mmono }">

				</div>
			</section>
		</div>
		<!-- -------------------------------------------------------------------------------------------------------- -->
		<div class="container" style="float: left; margin-left: 400px;">
			<section class="register"
				style="position: absolute; height: 425px; width: 350px">

				<h3>Section 3</h3>
				<div class="reg_section password">
					<h3>Contact Detail:-</h3>

					<input type="text" name="smono" placeholder="Student Mo."
						value="${smono }">
					<textarea rows="8" cols="50" name="address" placeholder="Address">${address }</textarea>
					<input type="text" name="email" placeholder="Email"
						value="${email}">
					<h3>Image:-</h3>
					<INPUT NAME="file1" TYPE="file">


<br><br>

					<p class="submit" style="padding-right: 100px">
						<input type="submit" name="action" value="Save"> <input
							type="submit" name="action" value="Delete"> <input
							type="submit" name="action" value="Update"> <input
							type="submit" name="action" value="Search">
					</p>
					<br><br>
					<a href="choosefile.jsp">Upload Student Detail Excel Sheet</a>

				</div>
			</section>
		</div>
	</form>


	<!--   --------------------------------------------------------------------------------------------- -->
</body>
</html>