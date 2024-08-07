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


<table border="1" >
<tr>
<th width="100"><b>Sem</b></th>
<th width="500"><b>Branch</b></th>
<th width="500"><b>Sub Code</b></th>


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

</tr>
<%
}
%>
</table><br>

</form>
</body>
</html>