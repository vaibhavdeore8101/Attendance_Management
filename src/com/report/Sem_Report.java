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


public class Sem_Report extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	Connection con;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

   // PrintWriter out=response.getWriter();
		String year,smonth,emonth,branch,sem,subject,rollno;
		year=request.getParameter("year");
		smonth=request.getParameter("startmonth");
		emonth=request.getParameter("endmonth");
		branch=request.getParameter("branch");
		sem=request.getParameter("sem");
		subject=request.getParameter("subject");
		rollno=request.getParameter("rollno");
		
		System.out.println(477);
		if(year.equals("")||branch.equals("")||sem.equals("")||rollno.equals("")||subject.equals("")||smonth.equals("")||emonth.equals(""))
		{
		request.setAttribute("msg", "Plz Fill All Field");
		 RequestDispatcher rd2 = request.getRequestDispatcher("sem.jsp");
		   if (rd2 != null)
		  {
			rd2.forward(request, response);
		  } 
		}
		try {
			con=Conn.getCon();
		HashMap<String, Object> param =new HashMap<String, Object>();
		 param.put("year", year);
         param.put("Start month", smonth);
         param.put("End month", emonth);
         param.put("branch", branch);
         param.put("sem", sem);
         param.put("rollno", rollno);
         param.put("subject", subject);
		/*InputStream is=ClassLoader.getSystemResourceAsStream("ListStudent.jasper");
		JasperPrint jp=JasperFillManager.fillReport(is, param,con);
		JasperViewer jw=new JasperViewer(jp);
		jw.setVisible(true);*/
		
		
		@SuppressWarnings("deprecation")
		String jrxmlfile=request.getRealPath("/Reports/SemwiseReport.jrxml");
		InputStream input=new FileInputStream(new File(jrxmlfile));
		
		
		JasperReport jr=JasperCompileManager.compileReport(input);
		JasperPrint jp=JasperFillManager.fillReport(jr, param,con);
		JasperExportManager.exportReportToPdfStream(jp,response.getOutputStream());

		JasperViewer jw=new JasperViewer(jp,false);
		jw.setVisible(true);
		
		response.getOutputStream().flush();
		response.getOutputStream().close();
	RequestDispatcher rd=request.getRequestDispatcher("/sem.jsp");
		rd.forward(request, response);  
		
		}catch(Exception exc)
		{
			System.out.println(exc);
					//out.println(exc);
						//out.println("print page");
		}
			
	}

}
