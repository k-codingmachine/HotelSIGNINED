package com.signied.controller.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.signied.dao.QnADAO;
import com.signied.dto.QnAReplyVO;
import com.signied.dto.QnAVO;

public class HotelQnAReplyCheckPass implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		int qnaNum = Integer.parseInt(request.getParameter("num"));
		String pass = request.getParameter("pass");
		String url = "CheckAdminSuccess.jsp";
		
		QnADAO dao = QnADAO.getInstance();
		
		QnAVO Qvo = dao.selectOneByNum(qnaNum);
		
		if(Qvo.getQnaPwd().equals(pass)) {
			url = "QnAReplyCheckSuccess.jsp";
		}else {
			url = "QnAReplyCheck.jsp";
			request.setAttribute("message", "비밀번호가 맞지 않습니다.");
		}
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}
}
