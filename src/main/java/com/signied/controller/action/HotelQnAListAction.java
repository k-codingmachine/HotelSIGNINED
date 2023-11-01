package com.signied.controller.action;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.signied.dao.QnADAO;
import com.signied.dto.PageVO;
import com.signied.dto.QnAReplyVO;
import com.signied.dto.QnAVO;

public class HotelQnAListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		
		int pageNum = 1;
		int amount = 7;
		
		if(request.getParameter("pageNum") != null && request.getParameter("amount") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			amount = Integer.parseInt(request.getParameter("amount"));
		}
		
		QnADAO dao = QnADAO.getInstance();
		
		List<QnAVO> list = dao.getAllQnAList(pageNum, amount);
		List<QnAReplyVO> replyList = dao.getAllReplyList();
		
		int total = dao.getQnACount();
		PageVO pageVO = new PageVO(pageNum, amount, total);
		
		request.setAttribute("pageVO", pageVO);
		request.setAttribute("QnAList", list);
		request.setAttribute("ReplyList", replyList);
		
		RequestDispatcher dis = request.getRequestDispatcher("QnAList.jsp");
		dis.forward(request, response);
	}
}
