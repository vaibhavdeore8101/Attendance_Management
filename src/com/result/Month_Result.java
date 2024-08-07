package com.result;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbcon.Conn;

public class Month_Result extends HttpServlet {
	private static final long serialVersionUID = 1L;
	float status;
	int nonattendlec;
	int totalsize;
	int attendsize;
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String year, month, branch,division, sem, subject, rollno;
		year = request.getParameter("year");
		month = request.getParameter("month");

		branch = request.getParameter("branch");
		division=request.getParameter("division");
		sem = request.getParameter("sem");

		subject = request.getParameter("subject");
		rollno = request.getParameter("rollno");

		PrintWriter out = response.getWriter();
		Connection con = null;
		con = Conn.getCon();
		ResultSet rs;
		PreparedStatement ps = null;

		response.setContentType("text/html");

		List<String> attend = new ArrayList<String>();
		List<String> total = new ArrayList<String>();

		if (year.equals("") || branch.equals("") || sem.equals("") || rollno.equals("") || subject.equals("")|| month.equals("")||division.equals("")) {
			request.setAttribute("msg", "Plz Fill All Field");
			RequestDispatcher rd2 = request.getRequestDispatcher("monthly.jsp");
			if (rd2 != null) {
				rd2.forward(request, response);
			}
		}
		try {
			String sql1 = "select distinct date from attendance where year=? and month=? and branch=? and division=? and sem=? and subject=? and rollno=?";
			ps = con.prepareStatement(sql1);
			ps.setString(1, year);
			ps.setString(2, month);
			ps.setString(3, branch);
			ps.setString(4, division);
			ps.setString(5, sem);
			ps.setString(6, subject);
			ps.setString(7, rollno);

			rs = ps.executeQuery();

			while (rs.next()) {
				// Add records into data list
				attend.add(rs.getString("date"));
			
			}

			String sql2 = "select distinct date from attendance where year=? and month=? and branch=? and division=? and sem=? and subject=?";
			ps = con.prepareStatement(sql2);
			ps.setString(1, year);
			ps.setString(2, month);
			ps.setString(3, branch);
			ps.setString(4, division);
			ps.setString(5, sem);
			ps.setString(6, subject);

			rs = ps.executeQuery();

			while (rs.next()) {
				// Add records into data list
				total.add(rs.getString("date"));
				
			}

		} catch (Exception e) {
			out.println(e);
		}
		totalsize=total.size();
		attendsize=attend.size();
		if(attendsize>0) {
			status =attendsize*100/totalsize;
			}
		nonattendlec=totalsize-attendsize;

		
		
		
		/*request.setAttribute("sem", sem);
		request.setAttribute("year", year);
		request.setAttribute("branch", branch);
		request.setAttribute("subject", subject);
		request.setAttribute("month", month);*/

		
		
		
		request.setAttribute("nonattendlec", nonattendlec);
		request.setAttribute("totlec",totalsize);
		request.setAttribute("attlec", attendsize);
		request.setAttribute("status", status);
	
		request.setAttribute("attenddate", attend);
		request.setAttribute("totaldate", total);
		// Disptching request
		RequestDispatcher rd2 = request.getRequestDispatcher("monthly.jsp");
		if (rd2 != null) {
			rd2.forward(request, response);
		}

	}

}
