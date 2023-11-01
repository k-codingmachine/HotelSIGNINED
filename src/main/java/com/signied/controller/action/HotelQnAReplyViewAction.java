package com.signied.controller.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.signied.dao.QnADAO;
import com.signied.dto.QnAReplyVO;
import com.signied.dto.QnAVO;

public class HotelQnAReplyViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		int qnaNum = Integer.parseInt(request.getParameter("num"));
		
		QnADAO dao = QnADAO.getInstance();

		QnAVO Qvo = dao.selectOneByNum(qnaNum);
		QnAReplyVO Avo = dao.selectOneByReply(qnaNum);
		
		request.setAttribute("result", Qvo);
		request.setAttribute("QnAReply", Avo);
		
		RequestDispatcher dis = request.getRequestDispatcher("QnAReplyView.jsp");
		dis.forward(request, response);
	}
}