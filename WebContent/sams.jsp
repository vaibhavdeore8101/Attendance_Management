<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SAMS</title>
<link rel="stylesheet" href="css/sams_styles.css">
</head>
<body>
<div class="sams_allpage">

<iframe name="action" src="home.jsp" align="right" height=100% width=88% scrolling="yes" frameborder="1" marginheight="2" marginwidth="2"></iframe>

<div id='cssmenu'>
<ul>
    <li><a href="home.jsp" target="action"><span>Home</span></a></li>	
    <li><a href="branchdetail.jsp" target="action"><span>Branch Detail</span></a></li>
    <li><a href="getbranch?link=studentdetail" name ="link" target="action"><span>Student Detail</span></a></li>
    <li><a href="getbranch?link=viewallstdopt" name ="link" target="action"><span>View All Student</span></a></li>
    <li><a href="getbranch?link=takeattendance" name ="link" target="action"><span>Take Attendance</span></a></li>
    <li class='active has-sub'><a href='#'><span>Result </span></a>
        <ul>
            <li class='has-sub'><a href="getbranch?link=day" name ="link" target="action"><span>Daily </span></a>

            </li>
            <li class='has-sub'><a href="getbranch?link=month" name ="link" target="action"><span>Monthly</span></a>

            </li>
            <li class='has-sub'><a href="getbranch?link=sem" name ="link" target="action"><span>Sem Wise</span></a>

            </li>
        </ul>
        </li>
        
        <li><a href="getbranch?link=generatestatus" name ="link" target="action"><span>Generate / Update Status </span></a>
       
        </li>
        
        <li><a href="getbranch?link=showstatusopt" name ="link" target="action"><span>Show Attendance Status </span></a>
        
        </li>
        
              <li><a href="getbranch?link=overallstatus" name ="link" target="action"><span>OverAll Status </span></a>
       
        
       <li><a href="getbranch?link=detainlist" name ="link" target="action"><span>Detain List</span></a></li>
        
   
    <li><a href="chk" target="action"><span>User Detail</span></a></li>
    <li class='last'><a href="index.jsp"><span>Logout</span></a></li>
</ul>
</div>

</div>
</body>
</html>