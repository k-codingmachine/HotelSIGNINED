package com.signied.controller.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.driver.json.tree.OracleJsonObjectImpl;
import oracle.sql.json.OracleJsonObject;

public class GetSystemDateAction implements Action {

   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {

      SimpleDateFormat outputFormat = new SimpleDateFormat("MM월 dd일 (E)"); // 화면에 표시되는 체크인, 체크아웃 날짜 형식
      SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd"); // 예약을 할 때 저장할 date형식

      // 현재 날짜 가져오기
      Date currentDate = new Date();

      // Calendar 객체를 사용하여 날짜에 1일을 더함
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(currentDate);
      calendar.add(Calendar.DAY_OF_MONTH, 1); // 날짜에 1일을 더함

      // 날짜 포맷팅
      String dateView = outputFormat.format(currentDate); // 현재 날짜
      String dateView2 = outputFormat.format(calendar.getTime()); // +1일된 날짜
      String dateIn = today.format(currentDate); // 현재 날짜
      String dateIn2 = today.format(calendar.getTime()); // +1일된 날짜

      OracleJsonObject result = new OracleJsonObjectImpl();

      result.put("dateView", dateView); // 화면에 표시되는 체크인
      result.put("dateView2", dateView2); // 화면에 표시되는 체크아웃

      result.put("dateIn", dateIn); // 예약할 때 사용하는 date형식 체크인 
      result.put("dateIn2", dateIn2); // 예약할 때 사용하는 date형식 체크아웃 

      // ajax로 데이터를 주고 받을 때 json형식으로 넘겨줘야 한다.
      response.setContentType("application/json");
      response.setCharacterEncoding("utf-8");
      response.getWriter().write(result.toString());
   }
}
