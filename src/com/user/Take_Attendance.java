package com.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbcon.Conn;

public class Take_Attendance extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs;
	String date, atttime,year, month, branch,division, sem, subject;
	//int i = 0;
	int result = 0;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		date = request.getParameter("attdates");
		atttime=request.getParameter("atttime");
		
		/*year = request.getParameter("year");
		month = request.getParameter("month");*/
		sem = request.getParameter("sem");
		branch = request.getParameter("branch");
		division=request.getParameter("division");
		subject = request.getParameter("subject");
		String[]rollno =request.getParameterValues("rollno");
		
		try {
			
			String time_12= LocalTime.parse(atttime, DateTimeFormatter.ofPattern("HH:mm")).format(DateTimeFormatter.ofPattern("hh:mm a"));

		String[] words=date.split("-");
		for(int i=1;i<words.length-1;i++)
		{
		month=words[i];
		}

		for(int i=0;i<words.length-2;i++)
		{
		year=words[i];
		}

		
		
		
		
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        Date givendate = sdf.parse(date);
	    
	        Date d =new Date();
	        Date currentdate = sdf.parse(sdf.format(d));
	       /* System.out.println("current date " +sdf.format(d));
	        System.out.println("G : " + sdf.format(givendate));
	        System.out.println("C : " + sdf.format(currentdate));
	        */
		if (sem.equals("")||branch.equals("")||division.equals("")||year.equals("")||month.equals("")||subject.equals("")||date.equals("")||atttime.equals("") || rollno.length == 0)
		{
			System.out.println(11);
			request.setAttribute("msg", "Plz Fill Or Check All Field");
			RequestDispatcher rd = request.getRequestDispatcher("/attendance.jsp");
			rd.forward(request, response);

		}
		else if (givendate.after(currentdate)) {
           
            request.setAttribute("msg", "Select Correct Date Selected Date Is Greter Than Current Date");
			RequestDispatcher rd = request.getRequestDispatcher("/attendance.jsp");
			rd.forward(request, response);
        }
		
		else {
			date=date+" "+time_12;
		con = Conn.getCon();
		ArrayList<String> name=new ArrayList<String>();
		for (int i = 0; i < rollno.length; i++) {
			String sql1 = "select studentname from studentregistration where rollno=? and division=? and sem=? and branch=?";

			PreparedStatement ps1 = con.prepareStatement(sql1);
			ps1.setString(1, String.valueOf(rollno[i]));
			ps1.setString(2, division);
			ps1.setString(3, sem);
			ps1.setString(4, branch);
			rs = ps1.executeQuery();

			while (rs.next()) {

				// Add name into namelist
				name.add(rs.getString("studentname"));
			}
		}
		System.out.println(name);
			for(int i=0;i<rollno.length;i++)
			/*while (i < roll_len)*/ {
				ps = con.prepareStatement("insert into attendance(date,branch,division,sem,subject,rollno,studentname,month,year) values(?,?,?,?,?,?,?,?,?)");
				ps.setString(1, date);
				ps.setString(2, branch);
				ps.setString(3, division);
				ps.setString(4, sem);
				ps.setString(5, subject);
				ps.setString(6, rollno[i]);
				ps.setString(7, name.get(i));
				ps.setString(8, month);
				ps.setString(9, year);

				result = ps.executeUpdate();

			}
		}
			if (result > 0) {
				request.setAttribute("msg", "Successfully Submited");
				RequestDispatcher rd = request.getRequestDispatcher("/attendance.jsp");
				rd.forward(request, response);
			}

			else {
				request.setAttribute("msg", "Something Wrong");
				RequestDispatcher rd = request.getRequestDispatcher("/attendance.jsp");
				rd.forward(request, response);
			}

		} 
		catch(NullPointerException n)
		{
			request.setAttribute("msg", "Plz Fill Or Check All Field");
			RequestDispatcher rd = request.getRequestDispatcher("/attendance.jsp");
			rd.forward(request, response);
		}
		
		catch(ParseException p) 
		{
			request.setAttribute("msg", "Check Date And Time Properly"+p.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/attendance.jsp");
			rd.forward(request, response);
		}
		catch (Exception e) {

						out.println(e);
							}
		
	}
}
