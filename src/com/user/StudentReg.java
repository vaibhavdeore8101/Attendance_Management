package com.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.dbcon.Conn;

public class StudentReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 Connection con=null;
	    PreparedStatement ps=null; 
	  ResultSet rs=null;
	    int result=0;
	String addate,branch,year,sem;
	static public String rollno;
	static public String studentname;
	static public String enrno;
	static public String email;
	static public String division;
	
	String gender;
	String dob;
	String fname;
	String fmono;
	String mname;
	String mmono;
	String smono;
	String address;
	RequestDispatcher rd;
	//Part part;
    public StudentReg() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		con=Conn.getCon();
		PrintWriter out=response.getWriter();
		addate=request.getParameter("attDate");
		enrno=request.getParameter("enrollmentno");
		branch=request.getParameter("branch");
		division=request.getParameter("division");
		year=request.getParameter("year");
		sem=request.getParameter("sem");
		rollno=request.getParameter("rollno");
		studentname=request.getParameter("studentname");
		gender=request.getParameter("gender");
		dob=request.getParameter("dob");
		fname=request.getParameter("fname");
		fmono=request.getParameter("fmono");
		mname=request.getParameter("mname");
		mmono=request.getParameter("mmono");
		smono=request.getParameter("smono");
		address=request.getParameter("address");
		email=request.getParameter("email");
		String filename = request.getParameter("file1");  //image is a image textfield name
		String button = request.getParameter("action");
		
		if (button.equals("Save")) {
			if(addate.equals("")||enrno.equals("")||rollno.equals("")||studentname.equals("")||email.equals("")||division.equals(""))
			{
				request.setAttribute("msg", "Required Detail Not Filled Correctly");
				rd=request.getRequestDispatcher("/student_detail.jsp");
				rd.forward(request, response);
			}
		try
		{
			String sqll="select rollno from studentregistration where year=? and sem=? and branch=? and division=? and rollno=? ";
			  
			PreparedStatement pst=con.prepareStatement(sqll);
			pst.setString(1, year);
			pst.setString(2, sem);
			pst.setString(3, branch);
			pst.setString(4, division);
			pst.setString(5, rollno);
			
			rs=pst.executeQuery();
			if(rs.next())
			{
				request.setAttribute("msg", "Already Exit");
				rd=request.getRequestDispatcher("/student_detail.jsp");
				rd.forward(request, response);
			
			}
			else {
		    ps=con.prepareStatement("insert into studentregistration values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		    /*InputStream is=part.getInputStream();*/
		    ps.setString(1, addate);
			ps.setString(2, rollno);
			ps.setString(3, enrno);
			ps.setString(4, studentname);
			ps.setString(5, branch);
			ps.setString(6, division);
			ps.setString(7, dob);
			ps.setString(8, year);
			ps.setString(9, gender );
			ps.setString(10, sem);
			ps.setString(11, fname);
		
			ps.setString(12, mname);
			ps.setString(13, fmono);
			ps.setString(14, mmono);
			ps.setString(15, smono);
			ps.setString(16, address);
			ps.setString(17, email);
			ps.setString(18, filename);
			
			
			result=ps.executeUpdate();
			if(result>0)
			{
				
				 rd=request.getRequestDispatcher("/studentmail");
				rd.forward(request, response);
				
			}
			else
			{
				request.setAttribute("msg", "Something Wrong");
				rd=request.getRequestDispatcher("/student_detail.jsp");
				rd.forward(request, response);
			}
		}
			 
		}
		
		catch(Exception e)
		  {
			
			out.print(e);
			e.printStackTrace();
		   }
		}
		else if(button.equals("Delete"))
		{
			try
			{
				  if(rollno.equals("")||branch.equals("")||sem.equals("")||branch.equals("")||division.equals(""))
				  {
					  request.setAttribute("msg", "Check Roll NO,Branch,Sem,Division");
						 rd=request.getRequestDispatcher("/student_detail.jsp");
						rd.forward(request, response);
				  }
				ps=con.prepareStatement("delete from studentregistration where rollno=? and branch=? and sem=? and division=?");
				ps.setString(1, rollno);
				ps.setString(2, branch);
				ps.setString(3, sem);
				ps.setString(4, division);
				result=ps.executeUpdate();
				if(result>0)
				{
					request.setAttribute("msg", "Student Record Deleted Successfully");
					 rd=request.getRequestDispatcher("/student_detail.jsp");
					rd.forward(request, response);
				}
				else
				{
					request.setAttribute("msg", "Something Wrong");
					 rd=request.getRequestDispatcher("/student_detail.jsp");
					rd.forward(request, response);
				}
			}
			catch(Exception e1)
			{
				out.print(e1);
			}
		}
		
		else if(button.equals("Update"))
		{
			try
			{
				 if(rollno.equals(""))
				  {
					  request.setAttribute("msg", "Check Roll NO");
						 rd=request.getRequestDispatcher("/student_detail.jsp");
						rd.forward(request, response);
				  }
					ps=con.prepareStatement(("update studentregistration set admisiondate=?,enrollmentno=?,studentname=?,dob=?,"
							+ "gender=?,fathername=?,mothername=?,fathermono=?,mothermono=?,studentmono=?,address=?,email=?,image=? "
							+ "where rollno=? and year=? and sem=? and branch=? and division=?"));
					ps.setString(1,addate);
					ps.setString(2,enrno);
					ps.setString(3,studentname);
					ps.setString(4,dob);
					ps.setString(5,gender);
					ps.setString(6,fname);
					ps.setString(7,mname);
					ps.setString(8,fmono);
					ps.setString(9,mmono);
					ps.setString(10,smono);
					ps.setString(11,address);
					ps.setString(12,email);
					ps.setString(13,filename);
					ps.setString(14,rollno);
					ps.setString(15,year);
					ps.setString(16,sem);
					ps.setString(17,branch);
					ps.setString(18,division);
					
					int i=ps.executeUpdate();
					
					if(i>0) 
						{
						request.setAttribute("msg", "Student Update Successfully");
							 rd=request.getRequestDispatcher("/student_detail.jsp");
					          rd.forward(request, response);
					    }
					
				
				
				else
				{
					request.setAttribute("msg", "Something Wrong");
					 rd=request.getRequestDispatcher("/student_detail.jsp");
			          rd.forward(request, response);
				}
				  
			}
			catch(Exception e)
			{
				out.println(e);
				
			}
			
		}
		else
		{
			try {
				 if(rollno.equals(""))
				  {
					  request.setAttribute("msg", "Check Roll NO");
						 rd=request.getRequestDispatcher("/student_detail.jsp");
						rd.forward(request, response);
				  }
				ps=con.prepareStatement("select * from studentregistration where rollno=? and branch=? and sem=? and division=?");
				ps.setString(1, rollno);
				ps.setString(2, branch);
				ps.setString(3, sem);
				ps.setString(4, division);
				rs=ps.executeQuery();
				if(rs.next())
				{
					
				//	System.out.println(adate);
					request.setAttribute("admisiondate",rs.getString("admisiondate"));
					request.setAttribute("enrno",rs.getString("enrollmentno"));
					request.setAttribute("rollno",rs.getString("rollno"));
					request.setAttribute("sname",rs.getString("studentname"));
					request.setAttribute("branch",rs.getString("branch"));
					request.setAttribute("division", rs.getString("division"));
					request.setAttribute("dob",rs.getString("dob"));
					request.setAttribute("year",rs.getString("year"));
					request.setAttribute("gender",rs.getString("gender"));
					request.setAttribute("sem",rs.getString("sem"));
					request.setAttribute("fname",rs.getString("fathername"));
					request.setAttribute("mname",rs.getString("mothername"));
					request.setAttribute("fmono",rs.getString("fathermono"));
					request.setAttribute("mmono",rs.getString("mothermono"));
					request.setAttribute("smono",rs.getString("studentmono"));
					request.setAttribute("address",rs.getString("address"));
					request.setAttribute("email",rs.getString("email"));
					//request.setAttribute("image",rs.getString("image"));
					 rd=request.getRequestDispatcher("/student_detail.jsp");
			          rd.forward(request, response);
				}
				else
				{
					request.setAttribute("msg", rollno+ "  "+"  Student Not Registered Check All Fields");
					 rd=request.getRequestDispatcher("/student_detail.jsp");
			          rd.forward(request, response);
				}
				
				
			}
			
			catch(Exception e)
			
			{
			out.println(e);
			}
		}
	}

}
