package com.message.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.message.model.MemberDAO;
import com.message.model.MemberDTO;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, iCommand> map;	
	
	
	@Override
	public void init() throws ServletException{
		// ������ ������ �� Ư�������� �ʱ�ȭ���ִ� �޼ҵ�
		map= new HashMap<String, iCommand>();
		map.put("/LoginCon.do", new LoginCon());
		map.put("/JoinCon.do", new JoinCon());
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
//		System.out.println(requestURI);
//		System.out.println(contextPath);
		System.out.println("���� ���� : "+command);
		
		// "/Login.do" ��û�� ������ ��
		// iCommand com= map.get("/LoginCond.do");
		// iCommand com= new LoginCon();
		iCommand com = map.get(command);
		com.excute(request, response);

// 		=================================================
		
		
//		if(command.equals("/LoginCon.do")) {
//			// �α���ó��
//			LoginCon login = new LoginCon();
//			login.excute(request, response);
//			
//		}else if(command.equals("/JoinCon.do")) {
//			// ȸ������ ó��
//			JoinCon join = new JoinCon();
//			join.excute(request, response);
//		
//		}
	}

}
