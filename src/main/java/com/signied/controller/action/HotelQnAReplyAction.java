package com.signied.controller.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.signied.dao.QnADAO;
import com.signied.dto.QnAReplyVO;

public class HotelQnAReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		QnAReplyVO vo = new QnAReplyVO();
		
		vo.setReplyContent(request.getParameter("replyContent"));
		vo.setQnaNum(Integer.parseInt(request.getParameter("qnaNum")));
		
		QnADAO dao = QnADAO.getInstance();
		int result = dao.insertReply(vo);
		
		if(result == 1) {
			response.sendRedirect("HotelServlet?command=QnAList");
		}else {
			RequestDispatcher dis = request.getRequestDispatcher("AdminQnAReply.jsp");
			dis.forward(request, response);
	
		}
	}
}
