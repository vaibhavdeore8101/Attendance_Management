package com.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckUserType extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String type = (String) session.getAttribute("usertype");

		 if (type.equals("Admin")) {
		
			response.sendRedirect("user_detail.jsp");
		} 
		
		else 
		{
			request.setAttribute("msg", "YOU ARE NOT AUTHORIZED USER LOGIN AS A ADMIN");
			RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);

		}
	}

}
