package com.ssafy.sample.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/coffe", loadOnStartup = 1)
public class CoffeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ICoffeeService coffeeService = new CoffeeService();

	@Override 
	public void init() throws ServletException {
		ServletContext application = getServletContext();
		application.setAttribute("root", application.getContextPath());
		System.out.println("MainServlet init...");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		process(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String domainAction = request.getParameter("action");
		StringTokenizer st = new StringTokenizer(domainAction,"_");
		String domain = st.nextToken();
		String action = st.nextToken();
		
		Object result = null;
		try {
			result = switch (action) {
				case "delete" -> deleteCoffee(request,response);
				case "insert" -> insertCoffee(request,response); 
				case "list" -> getCoffee(request,response);
				case "detail" -> getDetailCoffee(request,response);
				case "modify" -> modifyCoffee(request,response);
				case "insertForm" -> "forward:/register.jsp";
				case "login" -> login(request, response);
				case "logout" -> logout(request, response);
				case "registUser" -> registUser(request, response);
				case "loginForm" -> "forward:/user/login.jsp";
				case "registForm" -> "forward:/user/registUser.jsp";
				case "home" -> "redirect:/index.jsp";
				case "mypage" -> "forward:/user/mypage.jsp";
				case "updateProfile" -> updateProfile(request,response);
				default -> "forward:/index.jsp";
			};
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			result = "forward:/common/error.jsp";
		}
		
		if(result instanceof String) {
			st = new StringTokenizer((String)result, ":");
			boolean isForward = st.nextToken().equals("forward")?true:false;
			String url = st.nextToken();
			
			if(isForward) request.getRequestDispatcher(url).forward(request, response); 
			else  response.sendRedirect(request.getContextPath()+url);
		}
	}
	
	protected String deleteCoffee(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int coffeeId = Integer.parseInt(request.getParameter("coffeeId"));
		coffeeService.deleteCoffee(coffeeId);
		
		return "redirect:/main?action=coffee_list";
	}
}
