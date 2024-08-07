package com.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dbcon.Conn;


public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
  Connection con=null;
   Statement st=null;
   ResultSet rs=null;
   PreparedStatement ps=null;
   
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		con=Conn.getCon();
		 PrintWriter out=response.getWriter();
		String un=request.getParameter("uname");
		String pass=request.getParameter("psw");
		String type=request.getParameter("type");
		
		 HttpSession session = request.getSession();
		 session.setAttribute("usertype", type);
		try
		{
			
		
		String qur="select * from logintable where username=? and password=? and usertype=?";
		 ps=(PreparedStatement) con.prepareStatement(qur);
		 ps.setString(1, un);
		 ps.setString(2,pass);
		 ps.setString(3, type);
		 
		rs=ps.executeQuery();
			
			
			if(rs.next())
			{
				RequestDispatcher rd=request.getRequestDispatcher("/sams.jsp");
				rd.forward(request, response);
			}
			else
			{
				request.setAttribute("msg", "Invalid username And password");
				RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
			}
		}catch(Exception e)
			{
				out.println(e);
				e.printStackTrace();
			}
	}
	
}
