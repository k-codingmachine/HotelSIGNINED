package com.signied.controller.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.signied.dao.QnADAO;
import com.signied.dto.QnAVO;


public class HotelQnAAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		QnAVO vo = new QnAVO();
		
		vo.setQnaTitle(request.getParameter("title"));
		vo.setEmail(request.getParameter("email"));
		vo.setQnaContent(request.getParameter("content"));
		vo.setQnaPwd(request.getParameter("pwd"));
		vo.setQnaCategory(request.getParameter("QnAcategory"));
		
		QnADAO dao = QnADAO.getInstance();
		int result = dao.insertQnA(vo);
		
		response.sendRedirect("HotelServlet?command=QnAList");
		
	}

}