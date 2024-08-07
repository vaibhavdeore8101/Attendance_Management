package com.result;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbcon.Conn;

public class ByRollNO extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	Connection con=null;
    PreparedStatement ps=null;
  ResultSet rs=null;
    int result=0;
	String attdate,atttime,branch,division,sem,subject,rollno;

  
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		
		attdate=request.getParameter("attdate");
		atttime=request.getParameter("atttime");
		branch=request.getParameter("branch");
		division=request.getParameter("division");
		sem=request.getParameter("sem");
		subject=request.getParameter("subject");
		rollno=request.getParameter("rollno");
		
		con=Conn.getCon();
		System.out.println("date"+attdate);
		System.out.println("time"+atttime);


		try {
			String time_12= LocalTime.parse(atttime, DateTimeFormatter.ofPattern("HH:mm")).format(DateTimeFormatter.ofPattern("hh:mm a"));
			attdate=attdate+" "+time_12;
			System.out.println(attdate);

			String qur="select rollno from attendance where date=? and branch=? and division=? and sem=? and subject=? and rollno=?";
			 ps=(PreparedStatement) con.prepareStatement(qur);
			 ps.setString(1, attdate);
			 ps.setString(2, branch);
			 ps.setString(3, division);
			 ps.setString(4,sem);
			 ps.setString(5, subject);
			 ps.setString(6, rollno);
			 
			rs=ps.executeQuery();
				
				
				if(rs.next())
				{
					
					request.setAttribute("name",rollno);
					request.setAttribute("status", "Present");
					RequestDispatcher rd=request.getRequestDispatcher("/daily.jsp");
					rd.forward(request, response);
				}
				else
				{
					
					request.setAttribute("name","Not Available");
					request.setAttribute("status", "Absent");
					RequestDispatcher rd=request.getRequestDispatcher("/daily.jsp");
					rd.forward(request, response);
				}
			
		}
		catch(DateTimeParseException dtpe) {
			request.setAttribute("msg", "Select Correct Date And Time");
			RequestDispatcher rd=request.getRequestDispatcher("/daily.jsp");
			rd.forward(request, response);
				}
		
		
		catch(Exception e)
		{
			out.println(e);
			e.printStackTrace();
		}
	}

}
