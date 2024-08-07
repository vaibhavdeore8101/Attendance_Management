package com.user;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbcon.Conn;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String file= "D:\\studentReg.xls";
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		/*
		ArrayList al = new ArrayList();
		ArrayList al2=new ArrayList();*/
		Workbook workbook = null;
		try {

			workbook = Workbook.getWorkbook(new File(file));

			Sheet sheet = workbook.getSheet(0);

				con=Conn.getCon();
				//for(int i=0;i<al.size();i++)
				for (int i = 1; i < sheet.getRows(); i++)
				{
					Cell admdate = sheet.getCell(0, i);
					Cell rollno = sheet.getCell(0,i);
					Cell enrno = sheet.getCell(0,i);
					Cell sname = sheet.getCell(0,i);
					
					Cell branch = sheet.getCell(0, i);
					Cell dob = sheet.getCell(0,i);
					Cell year = sheet.getCell(0,i);
					Cell gender = sheet.getCell(0,i);
					
					Cell sem = sheet.getCell(0, i);
					Cell fname = sheet.getCell(0,i);
					Cell mname = sheet.getCell(0,i);
					Cell fmono = sheet.getCell(0,i);
					
					Cell mmono = sheet.getCell(0, i);
					Cell smono = sheet.getCell(0,i);
					Cell address = sheet.getCell(0,i);
					Cell image = sheet.getCell(0,i);
					
					Cell email = sheet.getCell(0,i);
				ps=con.prepareStatement("insert into studentregistration(admisiondate,rollno,enrollmentno,studentname,branch,division,dob,year,gender,sem,fathername,mothername,fathermono,mothermono,studentmono,address,email,image)"
						+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				/*ps.setString(1, (String) al.get(i));
				ps.setString(2, (String)al2.get(i));*/
				ps.setString(1, admdate.getContents());
				ps.setString(2, enrno.getContents());
				result=ps.executeUpdate();
				
				
				if(result>0)
				{
					System.out.println("inserted");
				}
				else
				{
					System.out.println("not inserted");					
				}
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally {
			if(workbook!=null)
			{
				workbook.close();
			}
		}
	}

}
