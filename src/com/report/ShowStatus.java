package com.report;

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

public class ShowStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String branch,division, sem,subject,month,year,action;
	int from,to;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Connection con = null;
		con = Conn.getCon();
		month=request.getParameter("month");
		branch = request.getParameter("branch");
		division=request.getParameter("division");
		sem = request.getParameter("sem");
		year=request.getParameter("year");
		subject=request.getParameter("subject");
		action=request.getParameter("action");
		PrintWriter out = response.getWriter();
		ResultSet rs;
		List<String> dataList = new ArrayList<String>();

		if(action.equals("Show Month Status"))   //month status
		{
			System.out.println("month status");
		
		try { 
			String sql = "select rollno,studentname,status from monthstatus where year=? and month=? and branch=? and division=? and sem=? and subject=?";
			System.out.println(sql);

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, year);
			ps.setString(2, month);
			ps.setString(3, branch);
			ps.setString(4, division);
			ps.setString(5, sem);
			ps.setString(6, subject);
			rs = ps.executeQuery();

			while (rs.next()) {
System.out.println("111");
				// Add records into data list

				dataList.add(rs.getString("rollno"));
				dataList.add(rs.getString("studentname"));
				dataList.add(rs.getString("status"));

			}
			
			request.setAttribute("data", dataList);
			request.setAttribute("branch", branch);
			request.setAttribute("division", division);
			request.setAttribute("sem", sem);
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.setAttribute("subject", subject);
			// Disptching request

			RequestDispatcher rd = request.getRequestDispatcher("monthStatus.jsp");

			if (rd != null) {

				rd.forward(request, response);

			}
			System.out.println("data"+dataList);

		} catch (Exception e) {

			out.println(e);

		}

		

		} // if closing
		
		else
		{
			
			try { 
				String sql = "select rollno,studentname,status from semstatus where year=? and branch=? and division=? and sem=? and subject=?";

				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, year);
				ps.setString(2, branch);
				ps.setString(3, division);
				ps.setString(4, sem);
				ps.setString(5, subject);
				rs = ps.executeQuery();

				while (rs.next()) {

					// Add records into data list

					dataList.add(rs.getString("rollno"));
					dataList.add(rs.getString("studentname"));
					dataList.add(rs.getString("status"));

				}
				
				request.setAttribute("data", dataList);
				request.setAttribute("branch", branch);
				request.setAttribute("division", division);
				request.setAttribute("sem", sem);
				request.setAttribute("year", year);
				request.setAttribute("month", month);
				request.setAttribute("subject", subject);
				// Disptching request

				RequestDispatcher rd = request.getRequestDispatcher("monthStatus.jsp");

				if (rd != null) {

					rd.forward(request, response);

				}

			} catch (Exception e) {

				out.println(e);

			}

			request.setAttribute("data", dataList);
			request.setAttribute("branch", branch);
			request.setAttribute("sem", sem);
			request.setAttribute("year", year);
			request.setAttribute("subject", subject);
			// Disptching request

			RequestDispatcher rd = request.getRequestDispatcher("semStatus.jsp");

			if (rd != null) {

				rd.forward(request, response);

			}

		}
	}

}
