<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import ="java.sql.*"%>
   <%@ page import ="java.util.*"%>
    <%@ page import ="java.io.PrintWriter"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View All Student</title>
</head>
<body>
<input type="hidden" name="branch" value="<%=request.getAttribute("branch")%>">
<input type="hidden" name="sem" value="<%=request.getAttribute("sem")%>">

<table border="1" >
<tr>
<th width="100"><b>Roll NO</b></th>
<th width="500"><b>Student Name</b></th>
<th width="500"><b>Enrollment No.</b></th>
<th width="500"><b>Image</b></th>

</tr>

<%
Connection con = null;
Class.forName("com.mysql.jdbc.Driver");  
//con=DriverManager.getConnection("jdbc:mysql://node38208-digitalstud.cloud.cms500.com/samsdatabase","root","18GV1vCZV1");  
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/samsdatabase","root","admin");  

String branch, sem;
branch = request.getParameter("branch");
sem = request.getParameter("sem");

ResultSet rs;

response.setContentType("text/html");

List dataList = new ArrayList();

try {
	String sql = "select * from studentregistration where branch=? and sem=?";

	PreparedStatement ps = con.prepareStatement(sql);
	ps.setString(1, branch);
	ps.setString(2, sem);

	rs = ps.executeQuery();

	while (rs.next()) {
%>
		<tr>
		<td ><div><%=rs.getString("rollno")%></div></td>
		<td ><div><%=rs.getString("studentname")%></div></td>
		<td ><div><%=rs.getString("enrollmentno")%></div></td>
		<%-- <td ><div><%=rs.getBlob("image")%></div></td> --%>
		
		
		  <td ><div><img src="<%=rs.getString("image")%>"></div></td> 
		</tr>
		<% 
	}

} catch (Exception e) {

	out.println(e);

}
%>


</table>
</body>
</html>