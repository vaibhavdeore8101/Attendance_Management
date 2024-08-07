 package com.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.dbcon.Conn;

import net.sf.jasperreports.engine.JasperFillManager;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;


public class Daily_Result extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	Connection con;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

   // PrintWriter out=response.getWriter();
		String date ,time,branch,sem,subject;
		branch = request.getParameter("branch");
		sem = request.getParameter("sem");
		date = request.getParameter("date");
		time=request.getParameter("atttime");
		subject = request.getParameter("subject");
		System.out.println(branch);
		System.out.println(date);
		System.out.println(sem);
		System.out.println(subject);
		con=Conn.getCon();
		System.out.println("time:"+time);
		try {
	String time_12= LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm")).format(DateTimeFormatter.ofPattern("hh:mm a"));
date=date+" "+time_12;
System.out.println(date);
		HashMap<String, Object> param =new HashMap<String, Object>();
		
		param.put("date", date);
		param.put("branch", branch);
		param.put("sem", sem);
		param.put("subject", subject);
	
		/*InputStream is=ClassLoader.getSystemResourceAsStream("ListStudent.jasper");
		JasperPrint jp=JasperFillManager.fillReport(is, param,con);
		JasperViewer jw=new JasperViewer(jp);
		jw.setVisible(true);*/
		
		@SuppressWarnings("deprecation")
		String jrxmlfile=request.getRealPath("/Reports/DailyReport.jrxml");
		InputStream input=new FileInputStream(new File(jrxmlfile));
		
		
		JasperReport jr=JasperCompileManager.compileReport(input);
		JasperPrint jp=JasperFillManager.fillReport(jr, param,con);
		JasperExportManager.exportReportToPdfStream(jp,response.getOutputStream());

		JasperViewer jw=new JasperViewer(jp,false);
		jw.setVisible(true);
		
		RequestDispatcher rd=request.getRequestDispatcher("/daily.jsp");
		rd.forward(request, response);  
		
		}catch(Exception exc)
		{
			System.out.println(exc);
					//out.println(exc);
						//out.println("print page");
		}
			
	}

}
