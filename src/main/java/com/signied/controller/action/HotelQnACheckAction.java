package com.signied.controller.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.signied.dao.QnADAO;
import com.signied.dto.QnAVO;

public class HotelQnACheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		String url = null;
		
		String num = request.getParameter("num");
		String pass = request.getParameter("pass");
		
		QnADAO dao = QnADAO.getInstance();
		QnAVO vo = dao.selectOneByNum(Integer.parseInt(num));
		
		if(vo.getQnaPwd().equals(pass)) {
			url = "checkSuccess.jsp";
		}else {
			url = "QnACheckPass.jsp";
			request.setAttribute("message", "비밀번호가 틀렸습니다.");
		}
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}

}
