package com.user;

import com.dbcon.*;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Branch_Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;

	PreparedStatement ps = null;
	String bn, branchname, subname, subcode, sem;
	int result = 0;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;
		con = Conn.getCon();
		PrintWriter out = response.getWriter();
		bn = request.getParameter("bn");
		branchname = request.getParameter("branchname");
		subname = request.getParameter("subname");
		subcode = request.getParameter("subcode");
		sem = request.getParameter("sem");

		String button = request.getParameter("action");
		if (button.equals("Add")) {
			if (bn.equals(""))
			{
				request.setAttribute("msg", "Branch Short Name can't be blank");
				  rd=request.getRequestDispatcher("/branchsdetail.jsp");
				rd.forward(request, response);
			}
			else if(subname.equals(""))
			{
				request.setAttribute("msg", "Subject Name can't be blank");
				  rd=request.getRequestDispatcher("/branchdetail.jsp");
				rd.forward(request, response);
			}
			else if(subcode.equals(""))
			{
				request.setAttribute("msg", "Subject Code can't be blank");
				  rd=request.getRequestDispatcher("/branchdetail.jsp");
				rd.forward(request, response);
			}
			else if(sem.equals(""))
			{
				request.setAttribute("msg", "Sem can't be blank");
				  rd=request.getRequestDispatcher("/branchdetail.jsp");
				rd.forward(request, response);
			}
				try {

					ps = con.prepareStatement("insert into branchdetail values(?,?,?,?,?)");
					ps.setString(1, bn);
					ps.setString(2, branchname);
					ps.setString(3, subname);
					ps.setString(4, sem);
					ps.setString(5, subcode);

					result = ps.executeUpdate();
					if (result > 0) {

						request.setAttribute("msg", "Branch & Subject Added Successfully");

						 rd = request.getRequestDispatcher("/branchdetail.jsp");
						rd.forward(request, response);
					} else {

						request.setAttribute("msg", "Something Wrong To Add Subject");
						rd = request.getRequestDispatcher("/branchdetail.jsp");
						rd.forward(request, response);
					}

				} 
				catch (Exception e) {

					out.print(e);
				}
		} else if (button.equals("Delete")) {
			if(bn.equals("") || sem.equals(""))
				
			{
				request.setAttribute("msg", "Branch Short Name & Sem can't be blank");
				  rd=request.getRequestDispatcher("/branchdetail.jsp");
				rd.forward(request, response);
			}
			try {

				ps = con.prepareStatement("delete from branchdetail where branch=? and sub=? and sem=?");
				ps.setString(1, bn);
				ps.setString(2, subname);
				ps.setString(3, sem);

				result = ps.executeUpdate();
				if (result > 0) {
					request.setAttribute("msg", "Subject Deleted Successfully");
					  rd=request.getRequestDispatcher("/branchdetail.jsp");
					rd.forward(request, response);
				} else {
					request.setAttribute("msg", "Something Wrong For Deleting");
					 rd = request.getRequestDispatcher("/branchdetail.jsp");
					rd.forward(request, response);
				}
			} catch (Exception e1) {
				out.print(e1);
			}
		}

	}

}
