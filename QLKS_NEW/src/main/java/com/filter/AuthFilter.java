package com.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.common.SessionUtils;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter(urlPatterns = {"/BookingServlet/*","/BookingDetailServlet/*","/CustomerManagementServlet/*",
		"/EmployeeManagementServlet/*","/ProfileServlet/*",
		"/ReportManagementServlet/*","/RoomManagementServlet/*","/RoomTypeManagementServlet/*","/Home"})
public class AuthFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		SessionUtils.add((HttpServletRequest) request, "isLogin", SessionUtils.isLogin((HttpServletRequest) request));
		request.setAttribute("isLogin", SessionUtils.isLogin((HttpServletRequest) request));
		HttpSession session = ((HttpServletRequest) request).getSession();
		boolean isLogin = (boolean) session.getAttribute("isLogin");
		if(!isLogin) {
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}


}
