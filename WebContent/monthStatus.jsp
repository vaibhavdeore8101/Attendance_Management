
 <%@page language="java" import="java.util.*" %>
<html>
<head>

 <link rel="stylesheet" href="viewAllStudent.css">
 <style type="text/css">
 #div1{
 align-items: center;
 width: 100%;
 height: 25px;
 background-color:99FFFF;
 }
 </style>
 
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
<form action="printmonthstatus" method="post">
<input type="hidden" name="branch" value="<%=request.getAttribute("branch")%>">
<input type="hidden" name="sem" value="<%=request.getAttribute("sem")%>">
<input type="hidden" name="month" value="<%=request.getAttribute("month")%>">
<input type="hidden" name="branch" value="<%=request.getAttribute("year")%>">
<input type="hidden" name="division" value="<%=request.getAttribute("division")%>">
<input type="hidden" name="subject" value="<%=request.getAttribute("subject")%>">

<br>
<b style="padding-left: 20px">Year :-</b> ${year} 
<b style="padding-left: 50px">Month :-</b> ${month} 

<b style="padding-left: 80px">Sem :-</b> ${sem} 
<b style="padding-left: 110px">Branch :-</b> ${branch} 
<b style="padding-left: 140px">Division :-</b> ${division} 
<b style="padding-left: 170px">Subject :-</b> ${subject} 
<br>
<br>
<table border="1" >
<tr>
<th width="100"><b>Roll NO</b></th>
<th width="500"><b>Student Name</b></th>
<th width="500"><b>Status(%)</b></th>


</tr>
<%Iterator itr;
 List data= (List)request.getAttribute("data");
for (itr=data.iterator(); itr.hasNext(); )
{
%>
<tr>
<td ><div><%=itr.next()%></div></td>
<td ><div><%=itr.next()%></div></td>
<td ><div><%=itr.next()%>%</div></td>
</tr>


<%
}
%>
</table><br> 



<center><input type="submit" value="Print" name="action"><br><br>
<input type="submit" value="Create Sheet" name="action"></center>
</form>
</body>
</html>