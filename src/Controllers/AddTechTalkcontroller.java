package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.TechTalkDao;

@SuppressWarnings("serial")
@WebServlet("/addtechtalk")
public class AddTechTalkcontroller extends HttpServlet{

	public void doPost(HttpServletRequest rq,HttpServletResponse rs) throws IOException{
		rs.setContentType("text/html");
		
		try(PrintWriter pw=rs.getWriter()){
			TechTalkDao dao=new TechTalkDao();
			pw.print("<body bgcolor='cyan'></body> ");
			System.out.println("in tech talk controller");
			
			SimpleDateFormat sdf=new SimpleDateFormat("YYYY-DD-MM");
			Date date= sdf.parse(rq.getParameter("date"));
			java.sql.Date sql = new java.sql.Date(date.getTime());
			System.out.println("after parsing date tech talk");
			int id=Integer.parseInt(rq.getParameter("id"));
			
			System.out.println("after int id"+id);
			
			dao.addTechTalk(id,sql,rq.getParameter("title"),rq.getParameter("description"),rq.getParameter("presenter"));
			String url=rs.encodeURL("LogoutServlet");
			pw.print("request added successfully");
			
			
		
			
			//if(rd!=null)
			//	rd.forward(rq, rs);
			pw.print("<h5><a href='"+url+"'>Log-Out</a></h5>");
		}catch(Exception e){
			try {
				throw new ServletException("err in doget"+e);
			} catch (ServletException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
