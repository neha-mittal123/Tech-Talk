package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import Dto.PendingRequestDto;

import Model.PendingRequestDao;

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
			pw.print("<body bgcolor='cyan'></body> ");
			pw.print("<form action='pendingrequesthandler'>");
			System.out.println("in pending request");
			for(PendingRequestDto prdto:u1){
				hs.setAttribute("pendingrequestdetails", prdto);
				pw.print("<table><tr><td>"+prdto.getS_id()+"</td><td>"+prdto.getTitle()+"</td><td>"+prdto.getDate()+"</td><td>"+prdto.getDescription()+"</td><td>"+prdto.getPresenter()+"</td><td><input type='submit' name='aprove' value='aprove'></td><td><input type='submit' name='disaprove' value='disaprove'></td></tr></table>");			}
							pw.print("</form>");
			
			} catch (Exception e) {
				throw new ServletException("err in do-get", e);
				
			}
		
		}
		
		

}


