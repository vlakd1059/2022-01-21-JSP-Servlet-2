package com.message.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.message.model.MemberDTO;
import com.message.model.MessageDAO;

@WebServlet("/MsgDeleteAllCon")
public class MsgDeleteAllCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDTO member= (MemberDTO) session.getAttribute("member");
		
		MessageDAO dao = new MessageDAO();
		
		int cnt= dao.messageDeleteAll(member.getM_email());
		  if(cnt > 0) {
		         response.sendRedirect("main.jsp");
		      }else {
		         response.setContentType("text/html; charset = uth-8");
		         PrintWriter out = response.getWriter();
		         out.print("<script>");
		         out.print("alert('선택한 메시지 삭제 실패...!');"); //alert : 중간에 작은 창을 띄어준다
		         out.print("location.href = 'main.jsp';"); // 확인키 누르면 이동하는 장소(내,외부 전부 가능)
		         out.print("</script>");
		      }

		
	}

}
