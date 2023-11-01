package com.signied.controller.action;

import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.signied.dto.RoomVO;

import oracle.jdbc.driver.json.tree.OracleJsonObjectImpl;
import oracle.sql.json.OracleJsonObject;

public class DateInputAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {

		RoomVO vo = new RoomVO();

		System.out.println("달력 : " + request.getParameter("data"));
		String date = request.getParameter("data");
		String dateIn = date.substring(0, 10);
		String dateIn2 = date.substring(12);
		String dateView = null;
		String dateView2 = null;

		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");

		SimpleDateFormat outputFormat = new SimpleDateFormat("MM월 dd일 (E)");

		try {
			dateView = outputFormat.format(inputFormat.parse(dateIn)); // 예: 11월 16일
			dateView2 = outputFormat.format(inputFormat.parse(dateIn2)); // 예: 11월 16일
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(dateView);
		System.out.println(dateView2);

		String bak = request.getParameter("bak"); // night 박을 출력(통째로)
		// String bak = request.getParameter("bak").substring(0,1); 잘라서 박만 출력하는 코드
		System.out.println(bak);

		// 여기에서 데이터를 사용하여 필요한 처리를 합니다.
		// 예: 데이터베이스에서 방 검색 등

		// 처리된 결과를 JSON 형식 등으로 클라이언트에 응답으로 보냅니다.
		OracleJsonObject result = new OracleJsonObjectImpl();
		result.put("dateView", dateView); // 예시 데이터
		result.put("dateView2", dateView2); // 예시 데이터
		result.put("bak", bak); // bak을 출력하려는 json
		result.put("dateIn", dateIn);
		result.put("dateIn2", dateIn2);

		response.setContentType("application/json");

		response.setCharacterEncoding("utf-8");

		response.getWriter().write(result.toString());

	}
}
