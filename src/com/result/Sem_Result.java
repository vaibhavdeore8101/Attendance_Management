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

public class Sem_Result extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
		protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			float status=0;
			int nonattendlec,attendsize,totsize;
			

		String year, smonth, emonth, branch,division, sem, subject, rollno;
		year = request.getParameter("year");
		smonth = request.getParameter("smonth");
		emonth = request.getParameter("endmonth");
		branch = request.getParameter("branch");
		division=request.getParameter("division");
		sem = request.getParameter("sem");
		subject = request.getParameter("subject");
		rollno = request.getParameter("rollno");

		PrintWriter out = response.getWriter();
		Connection con = null;
		con = Conn.getCon();
		PreparedStatement ps = null;

		ResultSet rs;

		response.setContentType("text/html");

		List<String> attend = new ArrayList<String>();
		List<String> total = new ArrayList<String>();

		if (year.equals("") || branch.equals("") || sem.equals("") || rollno.equals("") || subject.equals("")
				|| smonth.equals("") || emonth.equals("")||division.equals("")) {
			request.setAttribute("msg", "Plz Fill All Field");
			RequestDispatcher rd2 = request.getRequestDispatcher("sem.jsp");
			if (rd2 != null) {
				rd2.forward(request, response);
			}
		}
		try {
			
			String sql1 = "select DISTINCT date from attendance where branch=? and division=? and sem=? and subject=? and rollno=? and month between ? and ? and year=?";
			ps = con.prepareStatement(sql1);
			ps.setString(1, branch);
			ps.setString(2, division);
			ps.setString(3, sem);
			ps.setString(4, subject);
			ps.setString(5, rollno);
			ps.setString(6, smonth);
			ps.setString(7, emonth);
			ps.setString(8, year);

			rs = ps.executeQuery();

			while (rs.next()) {
				// Add records into data list
				attend.add(rs.getString("date"));

			}

			
			// total lecture
			String sql2 = "select DISTINCT date from attendance where branch=? and division=? and sem=? and subject=? and  month between ? and ? and year=?";
			ps = con.prepareStatement(sql2);
			ps.setString(1, branch);
			ps.setString(2, division);
			ps.setString(3, sem);
			ps.setString(4, subject);
			ps.setString(5, smonth);
			ps.setString(6, emonth);
			ps.setString(7, year);

			rs = ps.executeQuery();

			while (rs.next()) {
				// Add records into data list
				total.add(rs.getString("date"));

			}

		} catch (Exception e) {
			out.println(e);
		}
attendsize=attend.size();
totsize=total.size();
System.out.println(attendsize);
System.out.println(totsize);
		if (attendsize >0 ) {
			status =attendsize*100/totsize;
			System.out.println(status);
		}
		nonattendlec = totsize - attendsize;

		request.setAttribute("nonattendlec", nonattendlec);
		
		request.setAttribute("totaldatesem", total);
		request.setAttribute("attenddatesem", attend);
		request.setAttribute("totlec", totsize);
		request.setAttribute("attlec", attendsize);
		request.setAttribute("status", status);
		// Disptching request
		RequestDispatcher rd2 = request.getRequestDispatcher("sem.jsp");
		if (rd2 != null) {
			rd2.forward(request, response);
		}

	}

}
