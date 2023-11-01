package com.signied.controller.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.signied.dao.ReservationDAO;
import com.signied.dto.ReservationVO;

public class ReservationSaveAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {

		System.out.println("체크인" + request.getParameter("originCheckIn"));
		System.out.println("체크 아웃" + request.getParameter("originCheckOut"));
		System.out.println("성인 인원" + request.getParameter("adultAmount"));
		System.out.println("아동 인원" + request.getParameter("childAmount"));
		System.out.println("몇 박" + request.getParameter("bak"));
		System.out.println("객실 고유번호" + request.getParameter("roomNum"));
		System.out.println("객실 이름" + request.getParameter("roomName"));
		System.out.println("객실 가격" + request.getParameter("roomPrice"));

		request.setAttribute("originCheckIn", request.getParameter("originCheckIn"));
		request.setAttribute("originCheckOut", request.getParameter("originCheckOut"));
		request.setAttribute("adult", request.getParameter("adultAmount"));
		request.setAttribute("child", request.getParameter("childAmount"));
		request.setAttribute("bak", request.getParameter("bak"));
		request.setAttribute("roomNum", request.getParameter("roomNum"));
		request.setAttribute("roomName", request.getParameter("roomName"));
		request.setAttribute("roomPrice", request.getParameter("roomPrice"));

		RequestDispatcher dis = request.getRequestDispatcher("ReservationForm.jsp");
		dis.forward(request, response);

	}

}
