package com.signied.controller.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RoomReservation implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		System.out.println("dateIn : " + request.getParameter("checkIn"));
		System.out.println("dateIn : " + request.getParameter("checkOut"));
		System.out.println("adultAmount : " + request.getParameter("adultAmount"));
		System.out.println("childAmount : " + request.getParameter("childAmount"));
		System.out.println("roomnum : " + request.getParameter("roomNum"));
		request.setAttribute("adultAmount", request.getParameter("adultAmount"));

		RequestDispatcher dis = request.getRequestDispatcher("roomReservation.jsp");
		dis.forward(request, response);
	}
	

}
