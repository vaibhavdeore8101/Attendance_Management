package com.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbcon.Conn;



public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection con=null;
    PreparedStatement ps=null;
   
    String username,password,question,answer,type;
    int result=0;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	PrintWriter out=response.getWriter();
	
	username=request.getParameter("username");
	password=request.getParameter("pass");
	question=request.getParameter("question");
	answer=request.getParameter("answer");
	type=request.getParameter("type");
	
	String button = request.getParameter("action");
	if (button.equals("Create")) {
		if(username.equals("")||password.equals("")||question.equals("")||answer.equals("")||type.equals(""))
		{
			request.setAttribute("msg", "Plz Fill All Fields");
			RequestDispatcher rd=request.getRequestDispatcher("/user_detail.jsp");
			rd.forward(request, response);
		
		}
			try
	{
		con=Conn.getCon();
		ps=con.prepareStatement("insert into logintable values(?,?,?,?,?)");
		ps.setString(1, username);
		ps.setString(2, password);
		ps.setString(3, type);
		ps.setString(4, question);
		ps.setString(5, answer);
		
		
		result=ps.executeUpdate();
		if(result>0)
		{
			request.setAttribute("msg", "User Created Successfully");
			RequestDispatcher rd=request.getRequestDispatcher("/user_detail.jsp");
			rd.forward(request, response);
		}
		else
		{
			request.setAttribute("msg", "Something Wrong");
			RequestDispatcher rd=request.getRequestDispatcher("/user_detail.jsp");
			rd.forward(request, response);
		}
	}
	catch(Exception e)
	  {
		
		out.print(e);
	   }
	}
	else if(button.equals("Delete"))
	{
		if(username.equals("")||password.equals(""))
		{
			request.setAttribute("msg", "Username And Password Required For Delete User");
			RequestDispatcher rd=request.getRequestDispatcher("/user_detail.jsp");
			rd.forward(request, response);
		
		}
		try
		{
			con=Conn.getCon();
			ps=con.prepareStatement("delete from logintable where username=? and password=?");
			ps.setString(1, username);
			ps.setString(2, password);

			result=ps.executeUpdate();
			if(result>0)
			{
				request.setAttribute("msg", "User Deleted Successfully");
				RequestDispatcher rd=request.getRequestDispatcher("/user_detail.jsp");
				rd.forward(request, response);
			}
			else
			{
				request.setAttribute("msg", "Something Wrong");
				RequestDispatcher rd=request.getRequestDispatcher("/user_detail.jsp");
				rd.forward(request, response);
			}
		}
		catch(Exception e1)
		{
			out.print(e1);
		}
	}
	
	else 
	{
		if(username.equals("")||password.equals("")||question.equals("")||answer.equals(""))
		{
			request.setAttribute("msg", "Username,Password,Question And Answer Required For Update");
			RequestDispatcher rd=request.getRequestDispatcher("/user_detail.jsp");
			rd.forward(request, response);
		
		}
		try
		{
			con=Conn.getCon();
			ps=con.prepareStatement("update logintable set password=?,usertype=?,question=?,answer=? where username=?");
			
			ps.setString(1, password);
			ps.setString(2, type);
			ps.setString(3, question);
			ps.setString(4, answer);
			ps.setString(5, username);
			result=ps.executeUpdate();
			if(result>0)
			{
				request.setAttribute("msg", "User Updated Successfully");
				RequestDispatcher rd=request.getRequestDispatcher("/user_detail.jsp");
				rd.forward(request, response);
			}
			else
			{
				request.setAttribute("msg", "Something Wrong");
				RequestDispatcher rd=request.getRequestDispatcher("/user_detail.jsp");
				rd.forward(request, response);
			}
		}
		catch(Exception e1)
		{
			out.print(e1);
		}
	}
}
	
	
}
