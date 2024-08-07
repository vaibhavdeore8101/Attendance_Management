 package com.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
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


public class Monthly_Report extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	Connection con;
    public Monthly_Report() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

   // PrintWriter out=response.getWriter();
		String year,month,branch,sem,subject,rollno;
		year=request.getParameter("year");
		month=request.getParameter("month");
		branch=request.getParameter("branch");
		sem=request.getParameter("sem");
		subject=request.getParameter("subject");
		rollno=request.getParameter("rollno");
	
		if(year.equals("")||branch.equals("")||sem.equals("")||rollno.equals("")||subject.equals("")||month.equals(""))
		{
		request.setAttribute("msg", "Plz Fill All Field");
		 RequestDispatcher rd2 = request.getRequestDispatcher("monthly.jsp");
		   if (rd2 != null)
		  {
			rd2.forward(request, response);
		  } 
		}
		try {
			con=Conn.getCon();
		HashMap<String, Object> param =new HashMap<String, Object>();
		
		param.put("month", month);
		param.put("branch", branch);
		param.put("rollno", rollno);
		param.put("sem", sem);
		param.put("subject", subject);
		param.put("year", year);
		/*InputStream is=ClassLoader.getSystemResourceAsStream("ListStudent.jasper");
		JasperPrint jp=JasperFillManager.fillReport(is, param,con);
		JasperViewer jw=new JasperViewer(jp);
		jw.setVisible(true);*/
		
		
		@SuppressWarnings("deprecation")
		String jrxmlfile=request.getRealPath("/Reports/monthlyReport.jrxml");
		InputStream input=new FileInputStream(new File(jrxmlfile));
		
		
		JasperReport jr=JasperCompileManager.compileReport(input);
		JasperPrint jp=JasperFillManager.fillReport(jr, param,con);
		JasperExportManager.exportReportToPdfStream(jp,response.getOutputStream());

		JasperViewer jw=new JasperViewer(jp,false);
		jw.setVisible(true);
		
		response.getOutputStream().flush();
		response.getOutputStream().close();
	RequestDispatcher rd=request.getRequestDispatcher("/monthly.jsp");
		rd.forward(request, response);  
		
		}catch(Exception exc)
		{
			System.out.println(exc);
					//out.println(exc);
						//out.println("print page");
		}
			
	}

}
