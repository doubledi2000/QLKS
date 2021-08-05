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

import com.dao.CustomerDao;
import com.model.Customer;

/**
 * Servlet implementation class CustomerManagementServlet
 */
@WebServlet({"/CustomerManagementServlet","/CustomerManagementServlet/create",
	"/CustomerManagementServlet/edit","/CustomerManagementServlet/update",
	"/CustomerManagementServlet/delete","/CustomerManagementServlet/reset",
	"/CustomerManagementServlet/search"})
public class CustomerManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI().toString();
		if(url.contains("edit")) {
			edit(request,response);
			return;
		} else if(url.contains("delete")) {
			delete(request,response);
			return;
		}else if(url.contains("reset")) {
			reset(request,response);
			return;
		}
		Customer customer = new Customer();
		
		//findAll(request,response);
		findSome(request, response);
		request.setAttribute("customer", customer);
		PageInfo.prepareAndForward(request, response, PageType.CUSTOMER_MANAGEMENT_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String url = request.getRequestURI().toString();
		if(url.contains("create")) {
			create(request,response);
			return;	
		}else 	if(url.contains("update")) {
			update(request,response);
			return;	
		}else if(url.contains("delete")) {
			delete(request,response);
			return;
		}else 	if(url.contains("reset")) {
			reset(request,response);
			return;
		}else if(url.contains("search")) {
			search(request, response);
			return;
		}
	
	}
	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer customer = new Customer();
		CustomerDao dao = new CustomerDao();
		try {
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String id = request.getParameter("phoneNumber");
			customer = dao.findById(id);
			if(customer != null) {
				request.setAttribute("customer", customer);
				String date = simpleDateFormat.format(customer.getDateOfBirth());
				request.setAttribute("date", date);
				request.setAttribute("message", "Thông tin khách hàng đã được lưu trong hệ thống");
				findAll(request,response);
				PageInfo.prepareAndForward(request, response, PageType.CUSTOMER_MANAGEMENT_PAGE);
				return;
			}
			DateTimeConverter dtc = new DateConverter(new Date());
			dtc.setPattern("yyyy-MM-dd");
			ConvertUtils.register(dtc, Date.class);
			BeanUtils.populate(customer, request.getParameterMap());
			
			String date = simpleDateFormat.format(customer.getDateOfBirth());
			request.setAttribute("date", date);
			dao.insert(customer);
			request.setAttribute("customer", customer);
			request.setAttribute("message", "Customer is inserted");
			
		} catch (Exception e) {
			request.setAttribute("error","Thêm thông tin khách hàng không thành công.");
			e.printStackTrace();
		}
		findSome(request,response);
		PageInfo.prepareAndForward(request, response, PageType.CUSTOMER_MANAGEMENT_PAGE);
	}
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("phoneNumber").toString();

		if(id==null) {
			request.setAttribute("error", "Số điện thoại là bắt buộc.");
			PageInfo.prepareAndForward(request, response, PageType.CUSTOMER_MANAGEMENT_PAGE);
			return;
		}
		try {
			CustomerDao dao = new CustomerDao();
			Customer cus = dao.findById(id);
			request.setAttribute("customer",cus);
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(cus.getDateOfBirth());
			request.setAttribute("date", date);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
		findSome(request,response);
		PageInfo.prepareAndForward(request, response, PageType.CUSTOMER_MANAGEMENT_PAGE);
	}
	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			CustomerDao dao = new CustomerDao();
			List<Customer> list = dao.findAll();
			request.setAttribute("customers", list);
		} catch (Exception e) {
			request.setAttribute("error", "error: " + e.getMessage()) ;
		}
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("phoneNumber");
		if(id==null) {
			request.setAttribute("error", "Phone number is required");
			PageInfo.prepareAndForward(request, response, PageType.CUSTOMER_MANAGEMENT_PAGE);
			return;
		}
		try {
			CustomerDao dao = new CustomerDao();
			Customer customer = dao.findById(id);
			
			dao.delete(id);
			
			request.setAttribute("message", "Customer is deleted");
			request.setAttribute("customer",new Customer());
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
		}
		findSome(request, response);
		PageInfo.prepareAndForward(request, response, PageType.CUSTOMER_MANAGEMENT_PAGE);
	}
	private void reset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer customer = new Customer();
		request.setAttribute("customer", customer);
		findSome(request, response);
		PageInfo.prepareAndForward(request, response, PageType.CUSTOMER_MANAGEMENT_PAGE);
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer customer = new Customer();
		try {
			DateTimeConverter dtc = new DateConverter(new Date());
			dtc.setPattern("dd/MM/yyyy");
			ConvertUtils.register(dtc, Date.class);
			BeanUtils.populate(customer, request.getParameterMap());
			CustomerDao dao = new CustomerDao();
			dao.update(customer);
			request.setAttribute("customer", customer);
			request.setAttribute("message", "Customer is updated");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		findSome(request,response);
		PageInfo.prepareAndForward(request, response, PageType.CUSTOMER_MANAGEMENT_PAGE);
	}

	public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			findSome(request, response);
			request.setAttribute("showList", "true");
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("error", "Tìm kiếm thất bại");
		}
		PageInfo.prepareAndForward(request, response, PageType.CUSTOMER_MANAGEMENT_PAGE);
		
	}
	public void findSome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Customer> customers = new ArrayList<Customer>();
		String str = request.getParameter("id");
		if(str == null) str = "";	
		String pageStr = request.getParameter("page");
		
		CustomerDao dao = new CustomerDao();
		int page ;
		page = pageStr != null ? Integer.parseInt(pageStr) : 1;
		int size = 4;
		String showList =request.getParameter("showList");
		
		customers = dao.filter(str, (page - 1)*size, size);
		
		int numberOfCustomer = dao.count(str);
		if(numberOfCustomer == 1) request.setAttribute("customer", customers.get(0));
		int numberOfPage = numberOfCustomer/size;
		if(numberOfCustomer % size != 0) numberOfPage++;

		request.setAttribute("showList", showList);
		if(str!=null || !str.equals("")) {
			request.setAttribute("showList", "true");
		}
		request.setAttribute("str", str);
		request.setAttribute("currentPage", page);
		request.setAttribute("customers", customers);
		request.setAttribute("numberOfPage", numberOfPage);
		//PageInfo.prepareAndForward(request, response, PageType.CUSTOMER_MANAGEMENT_PAGE);
	}
	
	public void filter(HttpServletRequest requets, HttpServletResponse response) {
		
	}
	/*
	 * <property name="hibernate.dialect" value="org.hibernate.dialect.SQLServerDialect" />
	 */
}
