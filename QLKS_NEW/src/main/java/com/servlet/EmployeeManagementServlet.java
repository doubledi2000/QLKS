package com.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

import com.common.PageInfo;
import com.common.PageType;
import com.dao.CustomerDao;
import com.dao.EmployeeDao;
import com.model.Customer;
import com.model.Employee;

/**
 * Servlet implementation class EmployeeManagementServlet
 */
@WebServlet({"/EmployeeManagementServlet",
	"/EmployeeManagementServlet/create",
	"/EmployeeManagementServlet/edit",
	"/EmployeeManagementServlet/update",
	"/EmployeeManagementServlet/delete",
	"/EmployeeManagementServlet/reset"})
public class EmployeeManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI().toString();
		
		if(url.contains("edit")) {
			edit(request, response);
			return;
		} else if(url.contains("delete")) {
			delete(request, response);
			return;
		}
		Customer customer = new Customer();
		findSome(request,response);
		request.setAttribute("customer", customer);
		PageInfo.prepareAndForward(request, response, PageType.EMPLOYEE_MANAGEMENT_PAGE);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI().toString();
		if(url.contains("create")) {
			create(request,response);
			return;
		}else if(url.contains("update")) {
			update(request,response);
			return;
		}else if(url.contains("edit")) {
			edit(request, response);
			return;
		}else if(url.contains("delete")) {
			delete(request, response);
			return;
		}else if(url.contains("reset")) {
			reset(request, response);
			return;
		}
		request.setAttribute("employee", new Employee());
		findAll(request, response);
		PageInfo.prepareAndForward(request, response, PageType.EMPLOYEE_MANAGEMENT_PAGE);
	}

	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employee employee = new Employee();
		try {
			DateTimeConverter dtc = new DateConverter(new Date());
			dtc.setPattern("yyyy-MM-dd");
			ConvertUtils.register(dtc, Date.class);
			BeanUtils.populate(employee, request.getParameterMap());
			
			if( employee.getEmployeeCode() == null || employee.getEmployeeCode().equals("")) {
				throw new Exception();
			}
			EmployeeDao dao = new EmployeeDao();
			dao.insert(employee);
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			
			request.setAttribute("dateOfBirth", dateFormat.format(employee.getDateOfBirth()));
			request.setAttribute("employee", employee);
			request.setAttribute("message", "Employee is inserted");
		
		} catch (Exception e) {
			request.setAttribute("error", "Insert employee failed");
			e.printStackTrace();
		}
		findAll(request,response);
		PageInfo.prepareAndForward(request, response, PageType.EMPLOYEE_MANAGEMENT_PAGE);
	}
	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			EmployeeDao dao = new EmployeeDao();
			List<Employee> list = dao.findAll();
			request.setAttribute("employees", list);
		} catch (Exception e) {
			request.setAttribute("error", "error: " + e.getMessage()) ;
		}
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("employeeCode").toString();
		if(id == null) {
			request.setAttribute("error", "Phone number is required");
			PageInfo.prepareAndForward(request, response, PageType.EMPLOYEE_MANAGEMENT_PAGE);
			return;
		}
		
		try {
			EmployeeDao dao = new EmployeeDao();
			Employee employee = new Employee();
			DateTimeConverter dtc = new DateConverter(new Date());
			dtc.setPattern("yyyy-MM-dd");
			ConvertUtils.register(dtc, Date.class);
			BeanUtils.populate(employee, request.getParameterMap());
			String dob = request.getParameter("dateOfBirth");
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			Date dateOfBirth = dateFormat.parse(dob);
			employee.setDateOfBirth(dateOfBirth);
			dao.update(employee);
			dob = dateFormat.format(dateOfBirth);
			System.out.println(dob);
			request.setAttribute("dateOfBirth", dob);
			request.setAttribute("message", "Employee updated");
			
			request.setAttribute("employee", employee);
			
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("error", "update failed");
			e.printStackTrace();
		}
		findSome(request,response);
		PageInfo.prepareAndForward(request, response, PageType.EMPLOYEE_MANAGEMENT_PAGE);
	}
	
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String id = request.getParameter("employeeCode");
			if(id==null ) {
				request.setAttribute("error", "EmployeeCode is required");
				throw new Exception();
			}
			EmployeeDao dao = new EmployeeDao();
			Employee employee = dao.findById(id);
			request.setAttribute("employee", employee);
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			String dateOfBirth = dateFormat.format(employee.getDateOfBirth());
			System.out.println(dateOfBirth);
			request.setAttribute("dateOfBirth", dateOfBirth);	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("error", "Edit failed");
		}
		findSome(request, response);
		PageInfo.prepareAndForward(request, response, PageType.EMPLOYEE_MANAGEMENT_PAGE);
		
	}
	
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
		String id = request.getParameter("employeeCode");
		EmployeeDao dao = new EmployeeDao();
		Employee employee = dao.findById(id);
		
		dao.delete(id);
		
		request.setAttribute("message", "employee is deleted");
		request.setAttribute("employee", new Employee());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("error", "delete failed");
		}
		findSome(request, response);
		PageInfo.prepareAndForward(request, response, PageType.EMPLOYEE_MANAGEMENT_PAGE);
		
	}
	
	
	private void reset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			Employee employee = new Employee();
			request.setAttribute("employee", employee);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("error", "reset failed");
		}
		findSome(request, response);
		PageInfo.prepareAndForward(request, response, PageType.EMPLOYEE_MANAGEMENT_PAGE);
		
	}
	
	private void findSome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Employee> employees = new ArrayList<Employee>();
			EmployeeDao dao = new EmployeeDao();
			String showList = request.getParameter("showList");
			int numberOfEmployee = dao.count().intValue();
			int size = 1;
			int numberOfPage = numberOfEmployee/size;
			if(numberOfEmployee % size != 0) numberOfPage++;
			String pageStr = request.getParameter("page");
			int page;
			if(pageStr == null || pageStr.equals("")) {
				page = 1;
			}else page = Integer.parseInt(pageStr);
			employees = dao.findAll(false, (page-1)*size , size);
			request.setAttribute("showList", showList);
			request.setAttribute("currentPage", page);
			request.setAttribute("employees", employees);
			request.setAttribute("numberOfPage", numberOfPage);
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("error", "Lỗi tìm kiếm danh sách nhân viên");
			e.printStackTrace();
		}
		
	}
}
