package com.signied.controller.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.signied.dao.QnADAO;

public class HotelQnADeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		int num = Integer.parseInt(request.getParameter("num"));
		
		QnADAO dao = QnADAO.getInstance();
		int result = dao.delteQnA(num);
		
		if(result == 1) {
			response.sendRedirect("HotelServlet?command=QnAList");
		}else {
			request.setAttribute("result", result);
		}
	}
}
