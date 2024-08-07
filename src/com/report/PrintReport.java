package com.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.dbcon.Conn;


import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.jasperreports.engine.JasperFillManager;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;

public class PrintReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String EXCEL_FILE_LOCATION = "D:\\Student_Detail.xls";
	static int i = 1;
	Connection con;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String branch, sem, action;
		branch = request.getParameter("branch");
		sem = request.getParameter("sem");
		action = request.getParameter("action");

		if (action.equals("Print")) {
			try {
				con = Conn.getCon();
				HashMap<String, Object> param = new HashMap<String, Object>();

				param.put("branch", branch);
				param.put("sem", sem);

				/*
				 * InputStream is=ClassLoader.getSystemResourceAsStream("ListStudent.jasper");
				 * JasperPrint jp=JasperFillManager.fillReport(is, param,con); JasperViewer
				 * jw=new JasperViewer(jp); jw.setVisible(true);
				 */
			

				@SuppressWarnings("deprecation")
				String jrxmlfile = request.getRealPath("/Reports/ListStudent.jrxml");
				InputStream input = new FileInputStream(new File(jrxmlfile));

				JasperReport jr = JasperCompileManager.compileReport(input);
				JasperPrint jp = JasperFillManager.fillReport(jr, param, con);
				JasperExportManager.exportReportToPdfStream(jp, response.getOutputStream());

				JasperViewer jw = new JasperViewer(jp, false); // if we dont give second parameter false then it stop
																// the tomcat
																// when u closing jasper report
				jw.setVisible(true);

				response.getOutputStream().flush();
				response.getOutputStream().close();
				/*
				 * RequestDispatcher rd=request.getRequestDispatcher("/view_all_student.jsp");
				 * rd.forward(request, response);
				 */

			} catch (Exception exc) {
				System.out.println(exc);
				// out.println(exc);
				// out.println("print page");
			}
		} else if(action.equals("Create Sheet")) {
			WritableWorkbook myFirstWbook = null;
			try {
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;

				con = Conn.getCon();
				// 1. Create an Excel file

				myFirstWbook = Workbook.createWorkbook(new File(EXCEL_FILE_LOCATION));
				
				// create an Excel sheet
				WritableSheet excelSheet = myFirstWbook.createSheet("First Sheet", 0);

				// Create Cell font,colour and format

				WritableFont cellfont = new WritableFont(WritableFont.TIMES, 12);
				cellfont.setColour(Colour.BLUE);

				WritableCellFormat cellformat = new WritableCellFormat(cellfont);
				cellformat.setBackground(Colour.ORANGE);
				cellformat.setBorder(Border.ALL, BorderLineStyle.THIN);

				excelSheet.setColumnView(0,20);
				excelSheet.setColumnView(5,20);
				for (int i = 2; i <= 3; i++) {
					excelSheet.setColumnView(i, 25);
				}
				for (int i = 9; i <= 14; i++) {
					excelSheet.setColumnView(i, 25);
				}
				excelSheet.setColumnView(15, 30);
				// add something into the Excel sheet
				Label label = new Label(0, 0, "Admision Date", cellformat);// first 0=coloumn number second zero=row
																			// number "Test Count"=value
				excelSheet.addCell(label);

				label = new Label(1, 0, "Roll No.", cellformat);
				excelSheet.addCell(label);

				label = new Label(2, 0, "Enrollnment No.", cellformat);
				excelSheet.addCell(label);

				label = new Label(3, 0, "Student Name", cellformat);
				excelSheet.addCell(label);

				label = new Label(4, 0, "Branch", cellformat);
				excelSheet.addCell(label);

				label = new Label(5, 0, "Dob", cellformat);
				excelSheet.addCell(label);

				label = new Label(6, 0, "Year", cellformat);
				excelSheet.addCell(label);

				label = new Label(7, 0, "Gender", cellformat);
				excelSheet.addCell(label);

				label = new Label(8, 0, "Sem", cellformat);
				excelSheet.addCell(label);

				label = new Label(9, 0, "Father Name", cellformat);
				excelSheet.addCell(label);

				label = new Label(10, 0, "Father Mobile", cellformat);
				excelSheet.addCell(label);

				label = new Label(11, 0, "Mother Name", cellformat);
				excelSheet.addCell(label);

				label = new Label(12, 0, "Mother Mobile", cellformat);
				excelSheet.addCell(label);

				label = new Label(13, 0, "Student Mobile", cellformat);
				excelSheet.addCell(label);

				label = new Label(14, 0, "Address", cellformat);
				excelSheet.addCell(label);

				label = new Label(15, 0, "Email", cellformat);
				excelSheet.addCell(label);

				String query = "select * from studentregistration where branch=? and sem=?";
				ps = con.prepareStatement(query);
				ps.setString(1, branch);
				ps.setString(2, sem);
				rs = ps.executeQuery();
				while (rs.next()) {

					Label lb0 = new Label(0, i, rs.getString("admisiondate"));
					excelSheet.addCell(lb0);

					Label lb1 = new Label(1, i, rs.getString("rollno"));
					excelSheet.addCell(lb1);

					Label lb2 = new Label(2, i, rs.getString("enrollmentno"));
					excelSheet.addCell(lb2);

					Label lb3 = new Label(3, i, rs.getString("studentname"));
					excelSheet.addCell(lb3);

					Label lb4 = new Label(4, i, rs.getString("branch"));
					excelSheet.addCell(lb4);

					Label lb5 = new Label(5, i, rs.getString("dob"));
					excelSheet.addCell(lb5);

					Label lb6 = new Label(6, i, rs.getString("year"));
					excelSheet.addCell(lb6);

					Label lb7 = new Label(7, i, rs.getString("gender"));
					excelSheet.addCell(lb7);

					Label lb8 = new Label(8, i, rs.getString("sem"));
					excelSheet.addCell(lb8);

					Label lb9 = new Label(9, i, rs.getString("fathername"));
					excelSheet.addCell(lb9);

					Label lb10 = new Label(10, i, rs.getString("fathermono"));
					excelSheet.addCell(lb10);

					Label lb11 = new Label(11, i, rs.getString("mothername"));
					excelSheet.addCell(lb11);

					Label lb12 = new Label(12, i, rs.getString("mothermono"));
					excelSheet.addCell(lb12);

					Label lb13 = new Label(13, i, rs.getString("studentmono"));
					excelSheet.addCell(lb13);

					Label lb14 = new Label(14, i, rs.getString("address"));
					excelSheet.addCell(lb14);

					Label lb15 = new Label(15, i, rs.getString("email"));
					excelSheet.addCell(lb15);

					// System.out.println("ok");
					i++;

				}

				myFirstWbook.write();
				System.out.println("ok");
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (myFirstWbook != null) {
					try {
						myFirstWbook.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				
				
			}
			request.setAttribute("msg","Excell Sheet Generated At  "+EXCEL_FILE_LOCATION);
			RequestDispatcher rd=request.getRequestDispatcher("/home.jsp");
			 rd.forward(request, response);
		}
		
	}
	

}
