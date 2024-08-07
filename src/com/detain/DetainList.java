package com.detain;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbcon.Conn;

public class DetainList extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Connection con;
	Statement stmt;
	int result = 0;
	ResultSet rs;
@SuppressWarnings("rawtypes")
ArrayList<Comparable> al;
	@SuppressWarnings("rawtypes")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String year, smonth, emonth, branch, sem, subject;

		year = request.getParameter("year");
		smonth = request.getParameter("smonth");
		emonth = request.getParameter("emonth");
		branch = request.getParameter("branch");
		sem = request.getParameter("sem");
		subject = request.getParameter("subject");

		if (year.equals("") || smonth.equals("") || emonth.equals("") || branch.equals("") || sem.equals("")
				|| subject.equals("")) 
		{
			
request.setAttribute("msg", "Select All Fields");
RequestDispatcher rd=request.getRequestDispatcher("detainListOpt.jsp");
rd.forward(request,response);
		} 
		else {
			try {
				al=new ArrayList<Comparable>();
				con = Conn.getCon();
				stmt=con.createStatement();
			String sql="select rollno,studentname,semstatus from status where status <=75";
			
			rs=stmt.executeQuery(sql);
				while (rs.next()) {
					al.add(rs.getInt("rollno"));
					al.add(rs.getString("studentname"));
					al.add(rs.getString("status"));
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(al);
			request.setAttribute("data", al);
			request.setAttribute("year", year);
			request.setAttribute("smonth", smonth);
			request.setAttribute("endmonth", emonth);
			request.setAttribute("sem", sem);
			request.setAttribute("branch", branch);
			request.setAttribute("subject", subject);



			RequestDispatcher rd=request.getRequestDispatcher("detainList.jsp");
			rd.forward(request, response);

		}

	}

}
