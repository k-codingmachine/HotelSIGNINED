package com.signied.controller.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HotelQnAadminCheckForm implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		RequestDispatcher dis = request.getRequestDispatcher("QnAadminCheckPass.jsp");
		dis.forward(request, response);
	}

}
