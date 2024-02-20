package edu.kh.servlet2.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/ex1")
public class ExampleServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//파라미터얻어옴
		String pizza = req.getParameter("pizza"); // 피자
		String crust = req.getParameter("crust"); // 크러스트
		String name = req.getParameter("name"); //주문자명
		String address = req.getParameter("address"); // 주소
		
		String[] option = req.getParameterValues("opt"); // 옵션 String배열 만들어 반환
		
		System.out.println("[서버] /ex1 요청");
	
		
		// 가격 계산
		
		int price = 0;
		
		
		switch(pizza) {
		case "불고기" : price += 12000; break;
		case "컴비네이션" : price += 13000; break;
		case "페퍼로니" : price += 14000; break;
		case "하와이안" : price += 16000; break;
		}
		
		if(crust.equals("swp")) price+= 500;
		
		if(option !=null) {
			for(String opt : option) {
				
				switch(opt) {
				case "음료" : price += 2000; break;
				case "피클" : price += 500; break;
				case "딥핑소스" : price += 500; break;
				}
			}
		}
		
		resp.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = resp.getWriter();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<!DOCTYPE html>");
		
		sb.append("<html>");
		
		sb.append("	<head>");
		sb.append("		<title>");
		sb.append(String.format("%s 님 주문 내역", name));
		sb.append("		</title>");
		sb.append("	</head>");
		
		sb.append("	<body>");
		sb.append("<h2>주문 내역 </h2>");
		
		sb.append(String.format("<h3>주문자명 : </h3> %s", name));
		sb.append(String.format("<h3>주소 : </h3> %s", address));
		
		sb.append("		<ul>");
		String temp = crust.equals("normal") ? "일반" : "고구마";
		sb.append(String.format("<li>크러스트 종류 : %s</li>", temp));
		
		if(option != null) {
			sb.append("<li>");
			sb.append("선택 옵션 : ");
			for(String opt : option) sb.append(opt + " ");
			
			sb.append("</li>");
		}
		
		sb.append("		</ul>");
		

		sb.append(String.format("<h3>총 금액 : %d원</h3>", price));
		
		sb.append("	</body>");
		
		
		sb.append("</html>");
		
		
		out.print(sb.toString());
		
	}

}
