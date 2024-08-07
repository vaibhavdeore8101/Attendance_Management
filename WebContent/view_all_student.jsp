
 <%@page language="java" import="java.util.*" %>
<html>
<head>

 <link rel="stylesheet" href="viewAllStudent.css">
<title>List Of All Student</title>
</head>
<body > 
<%String msg=(String)request.getAttribute("msg");

if(msg!=null)
{%>
<script type="text/javascript">
    
	alert("${msg}");
  </script>
<% 
}

%>
<form action="printstudent" method="post">
<input type="hidden" name="branch" value="<%=request.getAttribute("branch")%>">
<input type="hidden" name="sem" value="<%=request.getAttribute("sem")%>">

<table border="1" >
<tr>
<th width="100"><b>Roll NO</b></th>
<th width="500"><b>Student Name</b></th>
<th width="500"><b>Enrollment No.</b></th>
<th width="500"><b>Image</b></th>
<th width="100"><b>Take Action</b></th>


</tr>
<%Iterator itr;
 List data= (List)request.getAttribute("data");
for (itr=data.iterator(); itr.hasNext(); )
{
%>
<tr>
<td ><div><%=itr.next()%></div></td>
<td ><div><%=itr.next()%></div></td>
<td ><div><%=itr.next()%></div></td>
<td ><div><img src="images/<%=itr.next()%>"></div></td> 
<td><div> <a href="delete.html?rollno=">Delete</a></div>
		  <a href="edit.html?rollno=">Update</a><br><br></td>
</tr>


<%
}
%>
</table><br> 
<%-- 
<c:if test="${!empty data}">

<table border="1" >
<tr>
<th width="100"><b>Roll NO</b></th>
<th width="500"><b>Student Name</b></th>
<th width="500"><b>Enrollment No.</b></th>
<th width="500"><b>Image</b></th>
<th width="100"><b>Take Action</b></th>
</tr>

<c:forEach items="${data}" var="studentdata">
				<tr>
				<td><c:out value="${studentdata.rollno}" /></td>
				<td><c:out value="${studentdata.studentname}" /></td>
				<td><c:out value="${studentdata.enrollmentno}" /></td>
			
				
				<td align="center">
						  <a href="edit.html?id=${employee.rollno}">Edit</a>
						| <a href="delete.html?id=${employee.rollno}">Delete</a>
						
					</td>
				</tr>
			</c:forEach>
</table>

</c:if> --%>

<center><input type="submit" value="Print" name="action"><br><br>
<input type="submit" value="Create Sheet" name="action"></center>
</form>
</body>
</html>