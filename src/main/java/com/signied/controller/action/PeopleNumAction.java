package com.signied.controller.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.driver.json.tree.OracleJsonObjectImpl;
import oracle.sql.json.OracleJsonObject;

public class PeopleNumAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		
	    response.setContentType("application/json");
	    response.setCharacterEncoding("utf-8");
	    
		String adultAm= request.getParameter("adultAm");
		String childAm = request.getParameter("childAm");
		
		System.out.println("어른 인원수" + adultAm); 
		System.out.println("어린이 인원수" + childAm); 
		
	
	    OracleJsonObject amountResult = new OracleJsonObjectImpl();
	    amountResult.put("amountView", adultAm);
	    amountResult.put("amountView2", childAm);
	    
	    System.out.println("amountResult : " + amountResult.toString());	
	    

	    
	    response.getWriter().write(amountResult.toString());
		
	}

}
