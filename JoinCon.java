package com.message.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.message.model.MemberDAO;
import com.message.model.MemberDTO;

public class JoinCon implements iCommand {

	public void excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// �̸���, ��й�ȣ, ��ȭ��ȣ, �ּ�
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");

		MemberDAO dao = new MemberDAO();
		
		int cnt = dao.memberJoin(new MemberDTO(email, pw, tel, address));

		if (cnt > 0) { // ȸ������ �������� �� -> join_success.jsp�� �̵�(email��������)
			request.setAttribute("success_data", email);
			
			// forward������� ������ �̵�
			RequestDispatcher dispatchasr = request.getRequestDispatcher("join_success.jsp");
			dispatchasr.forward(request, response);
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('ȸ������ ���� ...');");
			out.print("location.href='main.jsp';");
			out.print("</script>");
			
		}

	}

}
