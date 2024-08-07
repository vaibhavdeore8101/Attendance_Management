
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
 
<title>Over All Status</title>
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
<form action="overalltatusprint" method="post">
<input type="hidden" name="branch" value="<%=request.getAttribute("branch")%>">
<input type="hidden" name="sem" value="<%=request.getAttribute("sem")%>">
<input type="hidden" name="year" value="<%=request.getAttribute("year")%>">


<br>
<b style="padding-left: 40px">Year :-</b> ${year} 
<b style="padding-left: 100px">Sem :-</b> ${sem} 
<b style="padding-left: 160px">Branch :-</b> ${branch} 
<input type="submit" value="Print" name="action" style="margin-right: 50px">
<input type="submit" value="Create Sheet" name="action" style="margin-right	: 10px">

<br>

<br>
<center>
<table border="1" style="float: left;">
<tr>
<th width="80"><b>Roll NO</b></th>
</tr>
 <%Iterator itr1;
 
 List data1= (List)request.getAttribute("rolllist");

for (itr1=data1.iterator(); itr1.hasNext(); )
{
%>
<tr>
<td ><div><%=itr1.next()%></div></td>

<% 
}
%>
</tr>

</table>

<table border="1" style="float: left;">
<tr>
<th width="350"><b>Student Name</b></th>
</tr>
 <%Iterator itr2;
 
 List data2= (List)request.getAttribute("name");

for (itr2=data2.iterator(); itr2.hasNext(); )
{
%>
<tr>
<td ><div><%=itr2.next()%></div></td>

<% 
}
%>
</tr>

</table>

<table border="1" style="float: left;">
<tr>
<%
Iterator itr3;
List data3= (List)request.getAttribute("subjects");
for (itr3=data3.iterator(); itr3.hasNext(); )
{
%>

<th><b><%=itr3.next()%></b></td>
<%
}
%>
</tr>
 <%Iterator itr4;
 
 List data4= (List)request.getAttribute("statuslist");

 int size=(Integer)request.getAttribute("subjectsize");
 
for (itr4=data4.iterator(); itr4.hasNext(); )
{
%>
<tr>
<%
for(int i=1;i<=size;i++)
{
%>
<td ><div><%=itr4.next()%>%</div></td>
<%
}
}
%>
</tr>

</table>
</center>
<br> 


</form>
</body>
</html>