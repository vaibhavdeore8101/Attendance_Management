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

import com.dbcon.Conn;

public class Forgot extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Connection con =null;
	Statement 	st =null;
	ResultSet	rs =null;
	PreparedStatement stmt=null;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		con=Conn.getCon();
		PrintWriter out=response.getWriter();
		String user=request.getParameter("user");
		String ques=request.getParameter("question");
		String ans=request.getParameter("ans");
		String npass=request.getParameter("newpass");
		String cpass=request.getParameter("confpass");
		
		try
		{
			String sql="select * from logintable where username='"+user+"'  AND question='" +ques+"' AND answer='"+ans+"'";	
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			
			if(rs.next())
			{

				if(npass.equals(cpass))
				{
					
					stmt=(PreparedStatement) con.prepareStatement(("update logintable set password=? where username=?"));
					stmt.setString(1,cpass);
					stmt.setString(2,user);
					
					int i=stmt.executeUpdate();
					//plz login next time ....
					if(i>0) 
						{
						request.setAttribute("msg", "username.."+user+" ..ANd Password is.."+cpass);
							RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
					          rd.forward(request, response);
					    }
					}else
					{
						request.setAttribute("msg", "New Password And Confirm Password Must Be Same");
						RequestDispatcher rd=request.getRequestDispatcher("/forgot.jsp");
				          rd.forward(request, response);
						
					}
					
				}
			else
			{
				request.setAttribute("msg", "Wrong Question And Answer Of That User");
				RequestDispatcher rd=request.getRequestDispatcher("/forgot.jsp");
		          rd.forward(request, response);
			}
			 
			
		}
		catch(Exception ex)
		{
			out.println(ex);
		}
		
	

	}

}
