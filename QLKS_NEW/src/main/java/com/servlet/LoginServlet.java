package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.common.CookieUtils;
import com.common.PageInfo;
import com.common.PageType;
import com.common.SessionUtils;
import com.dao.EmployeeDao;
import com.domain.LoginForm;
import com.model.Employee;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet({"/Login","/"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = CookieUtils.get("username", request);
		if(username == null) {
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			return;
		}
		EmployeeDao dao = new EmployeeDao();
		Employee employee = dao.findByUsername(username);
		SessionUtils.add(request, "employeeLogin", employee);
		request.getRequestDispatcher("/Home").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			LoginForm form = new LoginForm();
			BeanUtils.populate(form, request.getParameterMap());
			EmployeeDao dao = new EmployeeDao();
			Employee employee = dao.findByUsername(form.getAccount());
			if(employee != null && employee.getPassword().equals(form.getPassword())) {
				SessionUtils.add(request, "username", employee.getAccount());
				SessionUtils.add(request, "employeeLogin", employee);
				if(form.isRemember()) {
					CookieUtils.add("username", form.getAccount(), 24, response);
				}else {
					CookieUtils.add("username", form.getAccount(), 0, response);
				}
				request.getRequestDispatcher("/Home").forward(request, response);
				return;
			}
			request.setAttribute("error", "Thông tin tài khoản hoặc mật khẩu không chính xác.");

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/views/login.jsp").forward(request, response);
	}

}
