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
		ReservationDAO rDao = ReservationDAO.getInstance();

		int roomNum = Integer.parseInt(request.getParameter("roomNum")); // 예약하기 버튼을 클릭했을 때 클릭한 룸 번호
		String checkIn = request.getParameter("checkIn"); // 고객이 입력한 체크인
		String checkOut = request.getParameter("checkOut"); // 고객이 입력한 체크아웃

		// boolean roomAvailable = rDao.isRoomAvailable(roomNum, checkIn, checkOut); // 예약하기를 눌렀을 때 클릭한 룸의 제고가 남아 있는지 체크

		request.setAttribute("checkIn", checkIn); // 화면에 표시되는 체크인 
		request.setAttribute("checkOut", checkOut); // 화면에 표시되는 체크아웃 
		request.setAttribute("originCheckIn", request.getParameter("originCheckIn")); // 데이터베이스에 저장할 date형식 체크인
		request.setAttribute("originCheckOut", request.getParameter("originCheckOut")); // 데이터베이스에 저장할 date형식 체크아웃
		request.setAttribute("adult", request.getParameter("adultCount")); // 어른 수
		request.setAttribute("child", request.getParameter("childCount")); // 어린이 수
		request.setAttribute("bak", request.getParameter("bak")); // 숙박 일 수 
		request.setAttribute("roomNum", roomNum); // 클릭한 룸 번호
		request.setAttribute("roomName", request.getParameter("roomName"));
	    request.setAttribute("img", request.getParameter("img"));
	    
	    String bak = request.getParameter("bak");
	    int numBak = Integer.parseInt(bak.replaceAll("[^0-9]",""));
	    
	    int roomPrice = Integer.parseInt(request.getParameter("roomPrice"));
	    
	    request.setAttribute("total", numBak*roomPrice); // 숙박 일 수 
	    
		RequestDispatcher dis = request.getRequestDispatcher("ReservationForm.jsp");
		dis.forward(request, response);
	}
}
