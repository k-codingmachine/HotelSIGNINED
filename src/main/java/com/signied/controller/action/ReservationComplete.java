package com.signied.controller.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.signied.dao.ReservationDAO;
import com.signied.dto.ReservationVO;

public class ReservationComplete implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		String url = null;
		System.out.println(url);
		ReservationVO vo = new ReservationVO();
		
		
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		
		
		vo.setReserveEmail(request.getParameter("email"));
		vo.setReservePwd(request.getParameter("pwd"));
		vo.setReserveName(request.getParameter("name"));
		vo.setReservePhone(request.getParameter("phone"));
		vo.setCheckIn(request.getParameter("originCheckIn"));
		vo.setCheckOut(request.getParameter("originCheckOut"));
		vo.setGuestNum(Integer.parseInt(request.getParameter("adult")));
		vo.setBreakfast(Integer.parseInt(request.getParameter("child")));
		vo.setRoomNum(Integer.parseInt(request.getParameter("roomNum")));
		
		ReservationDAO rDao = ReservationDAO.getInstance();
		int result = rDao.insertReservation(vo);
		System.out.println("vo :" + vo);
		
		if(result==1) {
			url = "ReservationSuccessAlert.jsp";
		}
		
		response.sendRedirect(url);
	}	

}
