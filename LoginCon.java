package com.message.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.message.model.MemberDAO;
import com.message.model.MemberDTO;

public class LoginCon implements iCommand{

	public void excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String pw = request.getParameter("pw");

		MemberDAO dao = new MemberDAO();
		MemberDTO member = dao.memberLogin(email, pw);

		if (member != null) {
			System.out.println("로그인 성공...");
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
			response.sendRedirect("main.jsp");
		}else {
			// 로그인 실패 알림창 띄운후 main.jsp로 이동
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('로그인 실패 ...');");
			out.print("location.href='main.jsp';");
			out.print("</script>");
		}
	}
}
