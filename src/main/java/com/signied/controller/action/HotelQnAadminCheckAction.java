package com.signied.controller.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.signied.dao.QnADAO;
import com.signied.dto.QnAVO;

public class HotelQnAadminCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		String url = null;
		
		String pass = request.getParameter("pass");
		int num = Integer.parseInt(request.getParameter("num"));
		QnADAO dao = QnADAO.getInstance();
		QnAVO vo = dao.selectOneByNum(num);
		int replyCheck = dao.checkReply(num);
		
		if(pass.equals("admin")) {
			request.setAttribute("replyCheck", replyCheck);
			url = "CheckAdminSuccess.jsp";
		}else {
			url = "QnAadminCheckPass.jsp";
			request.setAttribute("message", "비밀번호가 틀렸습니다.");
		}
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}

}
