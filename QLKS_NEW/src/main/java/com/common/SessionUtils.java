package com.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dao.EmployeeDao;
import com.model.Employee;

public class SessionUtils {
	public static void add(HttpServletRequest request, String name, Object value) {
		HttpSession session = request.getSession();
		session.setAttribute(name, value);
	}
	
	public static Object get(HttpServletRequest request, String name) {
		HttpSession session = request.getSession();
		
		return session.getAttribute(name);
	}
	public static void invalidate(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("isLogin");
		session.removeAttribute("employeeLogin");
		session.removeAttribute("username");
		session.invalidate();
	}
	public static boolean isLogin(HttpServletRequest request) {
		return get(request, "username") != null;
	}
	
	public static String getLoginUsername(HttpServletRequest request) {
		String username = get(request, "username").toString();
		return username == null ? null : username.toString();
	}
	public static Employee getLoginEmployee(HttpServletRequest request) {
		String account = get(request,"username").toString();
		EmployeeDao dao = new EmployeeDao();
		return account == null ? null : dao.findByUsername(account);
	}
}
