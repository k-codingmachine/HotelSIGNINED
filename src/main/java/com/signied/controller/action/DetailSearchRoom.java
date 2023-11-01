package com.signied.controller.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.signied.dao.SigniedSearchDAO;
import com.signied.dto.RoomVO;

public class DetailSearchRoom implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		SigniedSearchDAO sDao = SigniedSearchDAO.getInstance();
		String sort = request.getParameter("fee");
		
		String[] viewTypes = request.getParameterValues("view");
		List<String> viewTypeList = new ArrayList<>();
		String[] roomTypes = request.getParameterValues("room");
		List<String> roomTypesList = new ArrayList<>();
		System.out.println(sort);
		
		for(int i = 0; i < viewTypes.length; i++) {
			viewTypeList.add(viewTypes[i]);
		}
		for(int i = 0; i < roomTypes.length; i++) {
			roomTypesList.add(roomTypes[i]);
		}

		List<RoomVO> roomList = sDao.detailSearchRoom(sort, viewTypeList, roomTypesList);
		
		request.setAttribute("roomList", roomList);
		
		
		String checkIn = request.getParameter("originCheckIn");
		String checkOut = request.getParameter("originCheckOut");
		request.setAttribute("originCheckIn", checkIn);
		request.setAttribute("originCheckOut", checkOut);
		request.setAttribute("bak", request.getParameter("bak"));
		request.setAttribute("adult", request.getParameter("adultCount"));
		request.setAttribute("child", request.getParameter("childCount"));
		request.setAttribute("checkIn", request.getParameter("checkIn"));
		request.setAttribute("checkOut", request.getParameter("checkOut"));
		System.out.println("detail adultCount : " + request.getParameter("adultCount"));
		System.out.println("detail childCount : " + request.getParameter("childCount"));
		System.out.println("detail checkIn :" + request.getParameter("checkIn"));
		System.out.println("detail checkOut : " + request.getParameter("checkOut"));
		RequestDispatcher dis = request.getRequestDispatcher("roomList.jsp");
		dis.forward(request, response);
	}

}
