package com.branch.subject;

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

public class GetBranch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String  link;
		link = request.getParameter("link");

		PrintWriter out = response.getWriter();
		Connection con = null;
		ResultSet rs;

		response.setContentType("text/html");
		List<String> branchList = new ArrayList<String>();
		con = Conn.getCon();
		try {

			String sql = "select  distinct branch from branchdetail";
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				// Add records into data list
				branchList.add(rs.getString("branch"));
			}

		} catch (Exception e) {
			out.println(e);
		}
		request.setAttribute("data",branchList);

		// Disptching request

		if (link.equals("takeattendance")) {
			RequestDispatcher rd1 = request.getRequestDispatcher("attendance.jsp");
			if (rd1 != null) {
				rd1.forward(request, response);
			}
		} else if (link.equals("viewallstdopt")) {
			RequestDispatcher rd2 = request.getRequestDispatcher("viewAllStdOption.jsp");
			if (rd2 != null) {
				rd2.forward(request, response);
			}
		}

		else if (link.equals("studentdetail")) {
			RequestDispatcher rd2 = request.getRequestDispatcher("student_detail.jsp");
			if (rd2 != null) {
				rd2.forward(request, response);
			}
		}

		else if (link.equals("day")) {
			RequestDispatcher rd2 = request.getRequestDispatcher("daily.jsp");
			if (rd2 != null) {
				rd2.forward(request, response);
			}
		} else if (link.equals("month")) {
			RequestDispatcher rd2 = request.getRequestDispatcher("monthly.jsp");
			if (rd2 != null) {
				rd2.forward(request, response);
			}
		} else if (link.equals("sem")) {
			RequestDispatcher rd2 = request.getRequestDispatcher("sem.jsp");
			if (rd2 != null) {
				rd2.forward(request, response);
			}
		}
		
		else if(link.equals("showstatusopt"))
		{
			RequestDispatcher rd2 = request.getRequestDispatcher("showStatusOpt.jsp");
			if (rd2 != null) {
				rd2.forward(request, response);
			}
		}
		
		else if(link.equals("generatestatus"))
		{
			RequestDispatcher rd2 = request.getRequestDispatcher("generateStatus.jsp");
			if (rd2 != null) {
				rd2.forward(request, response);
			}
		}
		
		
		else if(link.equals("detainlist"))
		{
			RequestDispatcher rd2 = request.getRequestDispatcher("detainListOpt.jsp");
			if (rd2 != null) {
				rd2.forward(request, response);
			}
		}
		
		else if(link.equals("overallstatus"))
		{
			RequestDispatcher rd2 = request.getRequestDispatcher("overall.jsp");
			if (rd2 != null) {
				rd2.forward(request, response);
			}
		}

	}

}
