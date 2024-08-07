package com.result;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbcon.Conn;

public class ByDate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con = null;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String branch,division,sem, date,time, subject;
		branch = request.getParameter("branch");
		division=request.getParameter("division");
		sem = request.getParameter("sem");
		date = request.getParameter("attdate");
		
		time=request.getParameter("atttime");
		subject = request.getParameter("subject");

		//System.out.println(branch+" "+division+" "+sem+" "+date+" "+time+" "+subject );
		
		String time_12= LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm")).format(DateTimeFormatter.ofPattern("hh:mm a"));

		PrintWriter out = response.getWriter();

		con = Conn.getCon();
		ResultSet rs;
		PreparedStatement ps = null;

		response.setContentType("text/html");

		List<String> rollno = new ArrayList<String>();

		if (date.equals("") || branch.equals("")||division.equals("") || sem.equals("") || sem.equals("")) {
			request.setAttribute("msg", "Plz Fill All Field");
			RequestDispatcher rd2 = request.getRequestDispatcher("daily.jsp");
			if (rd2 != null) {
				rd2.forward(request, response);
			}
		}
		try {
			date=date+" "+time_12;
			/*System.out.println("date.."+date);
			System.out.println("time.."+time_12);*/
			String sql1 = "select distinct rollno from attendance where date=? and branch=? and division=? and sem=? and subject=?";
			ps = con.prepareStatement(sql1);
			ps.setString(1, date);
			ps.setString(2, branch);
			ps.setString(3, division);
			ps.setString(4, sem);
			ps.setString(5, subject);

			rs = ps.executeQuery();

			while (rs.next()) {
				// Add records into data list
				rollno.add(rs.getString("rollno"));
			}
			
			request.setAttribute("rollbydate", rollno);

			RequestDispatcher rd2 = request.getRequestDispatcher("daily.jsp");
			if (rd2 != null) {
				rd2.forward(request, response);
			}
		}
		
		catch(DateTimeParseException dtpe)
		{
			request.setAttribute("msg", "Select Proper Date And Time With AM/PM");
			RequestDispatcher rd2 = request.getRequestDispatcher("daily.jsp");
			if (rd2 != null) {
				rd2.forward(request, response);
			}
		}
		
		catch (Exception e) {
			out.println(e);
		}

		

	}

}
