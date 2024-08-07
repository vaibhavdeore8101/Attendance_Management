package com.report;

import java.io.IOException;
import java.io.PrintWriter;
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

public class GenerateStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		con = Conn.getCon();
		String branch,division, sem, year, month, subject, action;

		year = request.getParameter("year");
		month = request.getParameter("month");
		branch = request.getParameter("branch");
		division=request.getParameter("division");
		sem = request.getParameter("sem");
		subject = request.getParameter("subject");
		action = request.getParameter("action");
		PrintWriter out = response.getWriter();
		ResultSet rs ;
		float status = 0;
		String from,to;
		int totalsize = 0, attendsize = 0, result, update = 0, insert = 0;

		List<String> rollnoList = new ArrayList<String>();
		List<String> nameList = new ArrayList<String>();
		List<String> subjectList = new ArrayList<String>();

		if (action.equals("Generate For Monthly")) {
			System.out.println("Generate For Monthly");
			try {

				String sql = "select DISTINCT rollno from studentregistration where  branch=? and division=? and sem=?";

				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, branch);
				ps.setString(2, division);
				ps.setString(3, sem);
				
				rs = ps.executeQuery();

				while (rs.next()) {

					// Add rollno into rollnolist
					rollnoList.add(rs.getString("rollno"));
				}
				System.out.println("ROll nO List:-" + rollnoList);
				/*		*******************************************/
				for (int i = 0; i < rollnoList.size(); i++) {
					String sql1 = "select studentname from studentregistration where rollno=? and division=? and sem=? and branch=?";

					PreparedStatement ps1 = con.prepareStatement(sql1);
					ps1.setString(1, String.valueOf(rollnoList.get(i)));
					ps1.setString(2, division);
					ps1.setString(3, sem);
					ps1.setString(4, branch);
					rs = ps1.executeQuery();

					while (rs.next()) {

						// Add name into namelist
						nameList.add(rs.getString("studentname"));
					}
				}
				System.out.println("Name List:-" + nameList);

				/*
				 * *****************************************************************************
				 * **
				 */
				
				String subjestqry="select sub from branchdetail where branch=? and sem=?";
				PreparedStatement p=con.prepareStatement(subjestqry);
				p.setString(1, branch);
				p.setString(2, sem);
				rs=p.executeQuery();
				while(rs.next())
				{
					subjectList.add(rs.getString("sub"));
				}
				
				System.out.println(subjectList);
				
/*				****************************************
*/
				for(int j=0;j<subjectList.size();j++)
				{	// total subject  
				String sql2 = "SELECT count(DISTINCT date)from attendance where year=? and month=? and branch=? and division=? and sem=? and subject=?";
				PreparedStatement ps2 = con.prepareStatement(sql2);
				ps2.setString(1, year);
				ps2.setString(2, month);
				ps2.setString(3, branch);
				ps2.setString(4, division);
				ps2.setString(5, sem);
				ps2.setString(6, subjectList.get(j));

				rs = ps2.executeQuery();

				while (rs.next()) {

					totalsize = (Integer.parseInt(rs.getString(1)));

				}
				System.out.println("Total Dates size" + totalsize);

				/*			*************************/

				for (int k = 0; k < rollnoList.size(); k++) {

					String sql3 = "select count(DISTINCT date) from attendance where year=? and month=? and branch=? and division=? and sem=? and subject=? and rollno=?";
					PreparedStatement ps3 = con.prepareStatement(sql3);
					ps3.setString(1, year);
					ps3.setString(2, month);
					ps3.setString(3, branch);
					ps3.setString(4, division);
					ps3.setString(5, sem);
					ps3.setString(6, subjectList.get(j));
					ps3.setString(7, String.valueOf(rollnoList.get(k)));

					rs = ps3.executeQuery();

					while (rs.next()) {

						attendsize = (Integer.parseInt(rs.getString(1)));

					}

					System.out.println("Attend Size " + attendsize);
					if (attendsize > 0) {
						status = attendsize * 100 / totalsize;

					}
					System.out.println(status);

					String sql4 = "select rollno  from monthstatus where year=? and month=? and branch=? and division=? and sem=? and subject=? and rollno=?";
					PreparedStatement ps4 = con.prepareStatement(sql4);
					ps4.setString(1, year);
					ps4.setString(2, month);
					ps4.setString(3, branch);
					ps4.setString(4, division);
					ps4.setString(5, sem);
					ps4.setString(6, subjectList.get(j));
					ps4.setString(7, String.valueOf(rollnoList.get(k)));

					rs = ps4.executeQuery();

					if (rs.next()) {
						// update
						String sql5 = "update monthstatus set status=? where rollno=? and branch=? and sem=? and division=?";
						PreparedStatement pstmt = con.prepareStatement(sql5);
						
						pstmt.setFloat(1, status);
						pstmt.setString(2, String.valueOf(rollnoList.get(k)));
						pstmt.setString(3, branch);
						pstmt.setString(4, sem);
						pstmt.setString(5, division);
						result = pstmt.executeUpdate();
						if (result > 0) {
							update = update + 1;
						}

					}

					else {
						String sql6 = "insert into monthstatus(rollno,studentname,branch,division,sem,subject,status,year,month)values(?,?,?,?,?,?,?,?,?)";
						PreparedStatement pst = con.prepareStatement(sql6);
						pst.setString(1, String.valueOf(rollnoList.get(k)));
						pst.setString(2, String.valueOf(nameList.get(k)));
						pst.setString(3, branch);
						pst.setString(4, division);
						pst.setString(5, sem);
						pst.setString(6, subjectList.get(j));
						pst.setFloat(7 ,status);
						pst.setString(8, year);
						pst.setString(9, month);

						result = pst.executeUpdate();
						if (result > 0) {
							insert = insert + 1;
						}

					}

				} // for close
			}//subject for close
				request.setAttribute("msg", update + " " + "Updated And" + " " + insert + " " + "Are Inserted");
				RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
				rd.forward(request, response);

			} catch (Exception e) {

				out.println(e);
				e.printStackTrace();
			}
			
/*			*****************************/

		} else if (action.equals("Generate Sor Sem")) {
			System.out.println("Generate Sor Sem");
			
			
			try {
				
				if(Integer.parseInt(sem)%2 == 0)
				{
					from="01";
					to="04";
				}
				{
					from="06";
					to="10";
				}

				String qry = "select DISTINCT rollno from studentregistration where branch=? and sem=? and division=?";

				PreparedStatement pst = con.prepareStatement(qry);
				pst.setString(1, branch);
				pst.setString(2, sem); 
				pst.setString(3, division);
				
				rs=pst.executeQuery();
				while (rs.next()) {

					// Add rollno into rollnolist
					rollnoList.add(rs.getString("rollno"));
				}
				System.out.println("ROll nO List:-" + rollnoList);
				/*		*******************************************/
				for (int i = 0; i < rollnoList.size(); i++) {
					String qry2 = "select studentname from studentregistration where rollno=? and sem=? and branch=? and division=?";

					PreparedStatement pst1 = con.prepareStatement(qry2);
					pst1.setString(1, String.valueOf(rollnoList.get(i)));
					pst1.setString(2, sem);
					pst1.setString(3, branch);
					pst1.setString(4, division);

					rs = pst1.executeQuery();

					while (rs.next()) {

						// Add name into namelist
						nameList.add(rs.getString("studentname"));
					}
				}
				System.out.println("Name List:-" + nameList);

				/* *****************************************************************************/

				String subjestqry="select sub from branchdetail where branch=? and sem=?";
				PreparedStatement p=con.prepareStatement(subjestqry);
				p.setString(1, branch);
				p.setString(2, sem);
				rs=p.executeQuery();
				while(rs.next())
				{
					subjectList.add(rs.getString("sub"));
				}
				
				System.out.println(subjectList);
				
/*				****************************************
*/
				
				
				for(int j=0;j<subjectList.size();j++)
				{	// total subject 
				String qry2 = "SELECT count(DISTINCT date)from attendance where year=? and month between ? and ? and branch=? and division=?  and sem=? and subject=?";
				
				PreparedStatement pst2 = con.prepareStatement(qry2);
				pst2.setString(1, year);
				pst2.setString(2, String.valueOf(from));
				pst2.setString(3, String.valueOf(to));
				pst2.setString(4, branch); // total lecture date Date
				pst2.setString(5, division);
				pst2.setString(6, sem);
				pst2.setString(7, subjectList.get(j));

				rs = pst2.executeQuery();

				while (rs.next()) {

					totalsize = (Integer.parseInt(rs.getString(1)));

				}
				System.out.println("Total Dates size" + totalsize);

				/*			*************************/

				for (int k = 0; k < rollnoList.size(); k++) {

					String qry3 = "select count(DISTINCT date) from attendance where year=? and month between ? and ? and branch=? and division=? and sem=? and subject=? and rollno=?";
					PreparedStatement pst3 = con.prepareStatement(qry3);
					pst3.setString(1, year);
					pst3.setString(2, String.valueOf(from));
					pst3.setString(3, String.valueOf(to));
					pst3.setString(4, branch);
					pst3.setString(5, division);
					pst3.setString(6, sem);
					pst3.setString(7, subjectList.get(j));
					pst3.setString(8, String.valueOf(rollnoList.get(k)));

					rs = pst3.executeQuery();

					while (rs.next()) {

						attendsize = (Integer.parseInt(rs.getString(1)));

					}

					System.out.println("Attend Size " + attendsize);
					if (attendsize > 0) {
						status = attendsize * 100 / totalsize;

					}
					System.out.println(status);

					String qry4 = "select rollno  from semstatus where year=? and branch=? and division=? and sem=? and subject=? and rollno=?";
					PreparedStatement pst4 = con.prepareStatement(qry4);
					pst4.setString(1, year);
					pst4.setString(2, branch);
					pst4.setString(3, division);
					pst4.setString(4, sem);
					pst4.setString(5, subjectList.get(j));
					pst4.setString(6, String.valueOf(rollnoList.get(k)));

					rs = pst4.executeQuery();

					if (rs.next()) {
						// update
						String qry5 = "update semstatus set status=? where rollno=? and sem=? and branch=? and division=? and year=?";
						PreparedStatement pstmt1 = con.prepareStatement(qry5);

						pstmt1.setFloat(1, status);
						pstmt1.setString(2, String.valueOf(rollnoList.get(k)));
						pstmt1.setString(3, sem);
						pstmt1.setString(4, branch);
						pstmt1.setString(5, division);
						pstmt1.setString(6, year);

						result = pstmt1.executeUpdate();
						if (result > 0) {
							update = update + 1;
						}

					}

					else {
						String qry6 = "insert into semstatus(rollno,studentname,branch,division,sem,subject,status,year)values(?,?,?,?,?,?,?,?)";
						PreparedStatement pstmt2 = con.prepareStatement(qry6);
						pstmt2.setString(1, String.valueOf(rollnoList.get(k)));
						pstmt2.setString(2, String.valueOf(nameList.get(k)));
						pstmt2.setString(3, branch);
						pstmt2.setString(4, division);
						pstmt2.setString(5, sem);
						pstmt2.setString(6, subjectList.get(j));
						pstmt2.setFloat(7, status);
						pstmt2.setString(8, year);

						result = pstmt2.executeUpdate();
						if (result > 0) {
							insert = insert + 1;
						}

					}

				} // for close
				}  // total subject close
				request.setAttribute("msg", update + " " + "Updated And" + " " + insert + " " + "Are Inserted");
				RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
				rd.forward(request, response);

			} catch (Exception e) {

				out.println(e);
				e.printStackTrace();
			}

		}
	}

}
