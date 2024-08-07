package com.report;

import java.io.IOException;
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

public class OverAllStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con;
	ResultSet rs;
	String year, branch,division, sem, action;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		year = request.getParameter("year");
		branch = request.getParameter("branch");
		division=request.getParameter("division");
		sem = request.getParameter("sem");
		action = request.getParameter("action");
		
		List<String> rollnoList = new ArrayList<String>();
		List<String> subjectList = new ArrayList<String>();
		List<String> statusList=new ArrayList<String>();
		List <String> nameList=new ArrayList<String>();

 
			try
			{
				con=Conn.getCon();
				String qry="select DISTINCT sub from branchdetail where sem=? and branch=?";
				PreparedStatement ps = con.prepareStatement(qry);
				
				ps.setString(1, sem);
				ps.setString(2, branch);
				
				rs=ps.executeQuery();
				while (rs.next()) {

					subjectList.add(rs.getString("sub"));
				}
				System.out.println(subjectList);
				
		String qry1="select DISTINCT rollno from studentregistration where  sem=? and branch=?";
		PreparedStatement ps1 = con.prepareStatement(qry1);
		ps1.setString(1, sem);
		ps1.setString(2, branch);
		
		rs=ps1.executeQuery();
		while(rs.next())
		{
			rollnoList.add(rs.getString("rollno"));
		}

		System.out.println(rollnoList);
		
		for(int i=0;i<rollnoList.size();i++)
		{
		String qr="select DISTINCT studentname from studentregistration where rollno=? and sem=? and branch=? and division=? ";
		PreparedStatement p = con.prepareStatement(qr);
		p.setString(1, rollnoList.get(i));
		p.setString(2, sem);
		p.setString(3, branch);
		p.setString(4, division);
		
		rs=p.executeQuery();
		while(rs.next())
		{
			nameList.add(rs.getString("studentname"));
		}
		}
		System.out.println(nameList);
		
		
		
		for(int i=0;i<rollnoList.size();i++)
		{
			for(int j=0;j<subjectList.size();j++)
			{
				String qry2="select DISTINCT status from semstatus where year=? and sem=? and branch=? and division=? and subject=? and rollno=?";
				PreparedStatement ps2 = con.prepareStatement(qry2);
				ps2.setString(1, year);
				ps2.setString(2, sem);
				ps2.setString(3, branch);
				ps2.setString(4, division);
				ps2.setString(5, subjectList.get(j));
				ps2.setString(6, rollnoList.get(i));
				
				rs=ps2.executeQuery();
				while(rs.next())
				{
					statusList.add(rs.getString("status"));
				}

				
			}
		}
		System.out.println(statusList);
		request.setAttribute("rolllist", rollnoList);
		request.setAttribute("subjects", subjectList);
		request.setAttribute("statuslist", statusList);
		request.setAttribute("name", nameList);
		request.setAttribute("subjectsize", subjectList.size());
		request.setAttribute("year", year);
		request.setAttribute("sem", sem);
		request.setAttribute("branch", branch);
		RequestDispatcher rd=request.getRequestDispatcher("overallList.jsp");
		rd.forward(request, response);

			}catch(Exception e)
			{
				e.printStackTrace();
			}
		

	}

}
