package com.common;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;

public class PageInfo {
	public static Map<PageType, PageInfo> pageRoute = new HashedMap();
	static {
		pageRoute.put(PageType.CUSTOMER_MANAGEMENT_PAGE,
				new PageInfo("Customer Management","/views/customer.jsp",null));
		pageRoute.put(PageType.HOME_PAGE,
				new PageInfo("Home","/views/home.jsp",null));
		pageRoute.put(PageType.BOOKING_PAGE,
				new PageInfo("Booking","/views/booking.jsp",null));
		pageRoute.put(PageType.BOOKING_DETAIL_PAGE,
				new PageInfo("Booking","/views/bookingDetail.jsp",null));
		pageRoute.put(PageType.EMPLOYEE_MANAGEMENT_PAGE, 
				new PageInfo("Employee Management", "/views/Employee.jsp", null));
		
		pageRoute.put(PageType.ROOM_MANAGEMENT_PAGE,
				new PageInfo("Room Management", "/views/room.jsp", null));
		
		pageRoute.put(PageType.ROOM_TYPE_MANAGEMENT_PAGE,
				new PageInfo("Room type management", "/views/roomType.jsp", null));
		pageRoute.put(PageType.REPORT_MANAGEMENT_PAGE,
				new PageInfo("Report", "/views/report.jsp", null));
		pageRoute.put(PageType.STATISTIC_PAGE, 
				new PageInfo("Statistic", "/views/statistic.jsp", null));
		pageRoute.put(PageType.LOGIN_PAGE, 
				new PageInfo("Login", "/views/login.jsp", null));
		pageRoute.put(PageType.PROFILE_PAGE, 
				new PageInfo("My profile", "/views/profile.jsp", null));
		pageRoute.put(PageType.HOTELINFO_PAGE, 
				new PageInfo("Hotel Infomation", "/views/hotel.jsp", null));
	
	
	}
	
	public static void prepareAndForward(HttpServletRequest request,HttpServletResponse response, PageType pageType) 
			throws ServletException, IOException {
		PageInfo page = pageRoute.get(pageType);
		
		request.setAttribute("page", page);
		
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	public String title;
	public String contentUrl;
	public String scriptUrl;
	public PageInfo(String title, String contentUrl, String scriptUrl) {
		super();
		this.title = title;
		this.contentUrl = contentUrl;
		this.scriptUrl = scriptUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContentUrl() {
		return contentUrl;
	}
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}
	public String getScriptUrl() {
		return scriptUrl;
	}
	public void setScriptUrl(String scriptUrl) {
		this.scriptUrl = scriptUrl;
	}
	
}
