package com.user;

import java.io.*;

import java.util.*;

import java.sql.*;

import javax.servlet.*;

import javax.servlet.http.*;

import com.dbcon.Conn;

public class ShowAllStudent extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {

		Connection con = null;
		con = Conn.getCon();
		String branch,division, sem;
		branch = request.getParameter("branch");
		sem = request.getParameter("sem");
		division=request.getParameter("division");
		PrintWriter out = response.getWriter();
		ResultSet rs;

		response.setContentType("text/html");

		List<String> dataList = new ArrayList<String>();

		try { 
			String sql = "select * from studentregistration where branch=? and division=? and sem=?";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, branch);
			ps.setString(2, division);
			ps.setString(3, sem);

			rs = ps.executeQuery();

			while (rs.next()) {

				// Add records into data list

				dataList.add(rs.getString("rollno"));
				dataList.add(rs.getString("studentname"));
				dataList.add(rs.getString("enrollmentno"));
				dataList.add(rs.getString("image"));

			}

		} catch (Exception e) {

			out.println(e);

		}

		request.setAttribute("data", dataList);
		request.setAttribute("branch", branch);
		request.setAttribute("sem", sem);
		// Disptching request

		RequestDispatcher rd = request.getRequestDispatcher("view_all_student.jsp");

		if (rd != null) {

			rd.forward(request, response);

		}

	}

}