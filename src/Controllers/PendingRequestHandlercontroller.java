package Controllers;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dto.PendingRequestDto;
import Model.PendingRequestDao;
import Model.TechTalkDao;


@SuppressWarnings("serial")
@WebServlet("/pendingrequesthandler")
public class PendingRequestHandlercontroller extends HttpServlet{

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		 HttpSession hs=request.getSession();
		try (PrintWriter pw = response.getWriter()) {
			PendingRequestDto prdto=(PendingRequestDto) hs.getAttribute("pendingrequestdetails");
			System.out.println("in pending request handler");
			pw.print("<body bgcolor='cyan'></body>");
			PendingRequestDao dao=new PendingRequestDao();
			if(request.getParameter("disaprove")!=null)
			{
				if(request.getParameter("disaprove").equals("disaprove"))
						{
				int res=dao.deletePendingRequest(prdto.getS_id());
				System.out.println(res);
				pw.print("sorry....!!!<br> ur request could not be processed");
						}
			}
			if(request.getParameter("aprove")!=null)
			{
			if(request.getParameter("aprove").equals("aprove")){
				int res=dao.deletePendingRequest(prdto.getS_id());
				System.out.println(res);
				TechTalkDao daoo=new TechTalkDao();
				int rst=daoo.addTechTalk(prdto.getS_id(), prdto.getDate(),prdto.getTitle(),prdto.getDescription(), prdto.getPresenter());
				System.out.println(rst);
				if(rst==1){
				pw.print(" approved successfully");}
				else
				pw.print("some issues exist in approval try later");
			}
			}
			
		} catch (Exception e) {
			throw new ServletException("err in do-get", e);
		}
	
	}
}
