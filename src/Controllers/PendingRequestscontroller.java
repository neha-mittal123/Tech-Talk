package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.PendingRequestDao;
import Dao.TechTalkDao;
import Dto.PendingRequestDto;
import Dto.TechTalkDto;

@SuppressWarnings("serial")
@WebServlet("/pendingrequests")
public class PendingRequestscontroller  extends HttpServlet{

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		 HttpSession hs=request.getSession();
		try (PrintWriter pw = response.getWriter()) 
		{
			PendingRequestDao dao=new PendingRequestDao();
			ArrayList<PendingRequestDto> u1=dao.getPendingRequest();
			pw.print("<body background='images/my page/47407650-plain-background-images.jpg'></body> ");
			
			System.out.println("in pending request");
			for(PendingRequestDto prdto:u1){
				hs.setAttribute("pendingrequestdetails", prdto);
				pw.print("<table><tr><td>"+prdto.getTitle()+"</td><td>"+prdto.getDate()+"</td><td>"+prdto.getDescription()+"</td><td>"+prdto.getPresenter()+"</td><td><form action='aprovetechtalk'><input type='hidden' name='prdto1' value="+prdto.getDate()+"><input type='hidden' name='prdto2' value="+prdto.getTitle()+"><input type='hidden' name='prdto3' value="+prdto.getDescription()+"><input type='hidden' name='prdto4' value="+prdto.getPresenter()+"><input type='submit' name='aprovetechtalk' value='aprovetechtalk'></form></td></tr></table><form action='disaprovetechtalk'><table><input type='hidden' name='prdto4' value="+prdto.getPresenter()+"><input  type='submit' name='disaprovetechtalk' value='disaprovetechtalk' style='background-color:green;'></table></form");			}
							pw.print("</form>");
							
							
			pw.print("<h2 fontcolor='light green'><a href='ATechTalk.jsp'>AddTechTalk</a></h2>");
			
			TechTalkDao daoo=new TechTalkDao();
			ArrayList<TechTalkDto> ttlist=daoo.getTechTalk();
			 HttpSession hs1=request.getSession();
			 hs1.setAttribute("techtalkdetails", ttlist);
			
			for(TechTalkDto ttdto:ttlist){
				System.out.println("value of sid"+ttdto.getS_id());
				pw.print("<table><tr><td>"+ttdto.getS_id()+"</td><td>"+ttdto.getDate()+"</td><td>"+ttdto.getTitle()+"</td><td>"+ttdto.getDescription()+"</td><td>"+ttdto.getPresenter()+"</td><td><form action='updatetechtalk'><input type='hidden' name='ttdto1' value="+ttdto.getDate()+"><input type='hidden' name='ttdto2' value="+ttdto.getTitle()+"><input type='hidden' name='ttdto3' value="+ttdto.getDescription()+"><input type='hidden' name='ttdto4' value="+ttdto.getPresenter()+"><input type='hidden' name='ttdto5' value="+ttdto.getS_id()+"><input type='submit' name='updatetechtalk' value='updatetechtalk' style='background:light green;fontcolor:white'></form></td</tr></table");
			}
			
			
			} catch (Exception e) {
				throw new ServletException("err in do-get", e);
				
			}
		
		}
		
		

}


