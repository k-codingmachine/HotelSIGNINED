package com.signied.controller.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.signied.dao.ReservationDAO;
import com.signied.dto.ReservationVO;

public class ReservationCheckPass implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		String url = null;
		
		String pass = request.getParameter("pass");
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println("pass : " + pass);
		System.out.println("num : " + num);
		
		ReservationDAO rDao = ReservationDAO.getInstance();
		ReservationVO vo = rDao.selectOneByNum(num);
		System.out.println("vo : " + vo);
		if(vo.getReservePwd().equals(pass)) {
			url = "ReservationCheckOk.jsp"; // 비번 맞다		
		}else{
			url = "ReservationPassCheck.jsp"; 
			request.setAttribute("message", "비밀번호가 틀렸습니다.");
		}
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);

	}

}
