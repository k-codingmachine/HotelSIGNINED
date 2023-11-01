package com.signied.controller.action;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.signied.dao.SigniedSearchDAO;
import com.signied.dto.RoomVO;

public class SearchRoomList implements Action {

   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {

      SigniedSearchDAO sDao = SigniedSearchDAO.getInstance();

      String checkIn = request.getParameter("originCheckIn");
      String checkOut = request.getParameter("originCheckOut");
      
      System.out.println(checkIn);
      System.out.println(checkOut);
      
      request.setAttribute("originCheckIn", checkIn);
      request.setAttribute("originCheckOut", checkOut);
      request.setAttribute("bak", request.getParameter("bak"));
      request.setAttribute("adult", request.getParameter("adultCount"));
      request.setAttribute("child", request.getParameter("childCount"));
      request.setAttribute("checkIn", request.getParameter("checkIn"));
      request.setAttribute("checkOut", request.getParameter("checkOut"));
      int totalAmount = Integer.parseInt(request.getParameter("adultAmount"))
            + Integer.parseInt(request.getParameter("childAmount"));

      List<RoomVO> roomList = sDao.searchRoom(checkIn, checkOut, totalAmount);
      request.setAttribute("roomList", roomList);

      RequestDispatcher dis = request.getRequestDispatcher("roomList.jsp");
      dis.forward(request, response);
   }
}
