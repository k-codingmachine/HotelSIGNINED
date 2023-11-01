package com.signied.controller.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.signied.dao.QnADAO;
import com.signied.dto.QnAVO;

public class HotelQnAReplyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		QnADAO dao = QnADAO.getInstance();
		
		QnAVO vo = dao.selectOneByNum(Integer.parseInt(request.getParameter("num")));
		
		request.setAttribute("result", vo);
		
		RequestDispatcher dis = request.getRequestDispatcher("AdminQnAReply.jsp");
		dis.forward(request, response);
	}

}
