package com.signied.controller.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.signied.dao.QnADAO;
import com.signied.dto.QnAVO;

public class HotelQnAViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		QnAVO vo = new QnAVO();
		
		QnADAO dao = QnADAO.getInstance();
		vo = dao.selectOneByNum(Integer.parseInt(request.getParameter("num")));
		
		request.setAttribute("QnAview", vo);
		
		RequestDispatcher dis = request.getRequestDispatcher("QnAview.jsp");
		dis.forward(request, response);
	}

}
