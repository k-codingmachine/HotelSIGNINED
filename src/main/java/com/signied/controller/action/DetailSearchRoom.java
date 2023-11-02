package com.signied.controller.action;

import java.util.Arrays;
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
		
		// 첫 번째 검색 로직
		String checkIn = request.getParameter("originCheckIn");
	    String checkOut = request.getParameter("originCheckOut");
	    
	    System.out.println(request.getParameter("adultCount"));
	    System.out.println(request.getParameter("childCount"));
	    
	    int totalAmount = Integer.parseInt(request.getParameter("adultCount"))
	            + Integer.parseInt(request.getParameter("childCount"));
	    List<RoomVO> roomList = sDao.searchRoom(checkIn, checkOut, totalAmount);

	    // 상세 검색 로직 (roomList를 기반으로 상세 검색)
	    String sort = request.getParameter("fee");
	    String[] viewTypes = request.getParameterValues("view");
	    List<String> viewTypeList = Arrays.asList(viewTypes != null ? viewTypes : new String[]{});
	    
	    String[] roomTypes = request.getParameterValues("room");
	    List<String> roomTypesList = Arrays.asList(roomTypes != null ? roomTypes : new String[]{});
	    
	    roomList = sDao.detailSearchRoom(sort, viewTypeList, roomTypesList, roomList);  // roomList를 인자로 추가
 
		if(roomList.isEmpty()) {
         request.setAttribute("message", "해당 일자에 예약 가능한 객실 및 상품조회 결과가 없습니다.<br><br>"
               + "\n"
               + "상단 예약 검색바에서 일정을 변경하신 후 수정 버튼을 다시 한 번 클릭해 주세요.");
      	}else {
      		request.setAttribute("roomList", roomList);
      	}
		
		// 예약할 때 쓸 date형식의 데이터
		
		request.setAttribute("originCheckIn", checkIn); // yyyy-MM-dd 형식의 데이터 -> 체크인 
		request.setAttribute("originCheckOut", checkOut); // yyyy-MM-dd 형식의 데이터 -> 체크아웃 


		request.setAttribute("bak", request.getParameter("bak")); // 숙박 일 수
		request.setAttribute("adult", request.getParameter("adultCount")); // 어른 
		request.setAttribute("child", request.getParameter("childCount")); // 어린이
		request.setAttribute("checkIn", request.getParameter("checkIn")); // 화면에 표시되는 체크인 
		request.setAttribute("checkOut", request.getParameter("checkOut")); // 화면에 표시되는 체크아웃
		
		System.out.println("detail adultCount : " + request.getParameter("adultCount"));
		System.out.println("detail childCount : " + request.getParameter("childCount"));
		System.out.println("detail checkIn :" + request.getParameter("checkIn"));
		System.out.println("detail checkOut : " + request.getParameter("checkOut"));
		
		RequestDispatcher dis = request.getRequestDispatcher("roomList.jsp");
		dis.forward(request, response);
	}

}
