package com.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.PageInfo;
import com.common.PageType;
import com.dao.BillDao;
import com.dao.BookingDao;
import com.dao.HotelInfoDao;
import com.dao.StatisticDao;
import com.domain.CheckOutForm;
import com.domain.StatisticForm;
import com.model.Bill;
import com.model.Booking;
import com.model.BookingDetail;
import com.model.HotelInfo;

/**
 * Servlet implementation class ReportManagementServlet
 */
@WebServlet({"/ReportManagementServlet","/ReportManagementServlet/search","/ReportManagementServlet/statistic",
	"/ReportManagementServlet/seeDetail"})
public class ReportManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		if(url.contains("seeDetail")) {
				findBillDetails(request, response);
				return;
		}
		 PageInfo.prepareAndForward(request, response, PageType.REPORT_MANAGEMENT_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		if(url.contains("search")) {
			findReport(request, response);
			return;
		}else if(url.contains("statistic")) {
			statistic(request, response);
			return;
		}else if(url.contains("seeDetail")) {
			findBillDetails(request, response);
			return;
		}
	}

	protected void findReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BillDao dao = new BillDao();
		String id = request.getParameter("customerId").toString();
		String type = request.getParameter("billType");

		int iType = Integer.parseInt(type);
		List<Bill> bills = dao.filterBill(id,iType);
	
		request.setAttribute("bills", bills);
		request.setAttribute("customerId", id);
		PageInfo.prepareAndForward(request, response, PageType.REPORT_MANAGEMENT_PAGE);
	}
	
	private void statistic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			StatisticDao dao = new StatisticDao();
			String sDate = request.getParameter("startDate");
			String eDate = request.getParameter("endDate");
			Date startDate = dateFormat.parse(sDate);
			Date endDate = dateFormat.parse(eDate);
			String type = request.getParameter("statisticBy");
			List<StatisticForm> list = null;
			if(type.equals("day")) {
				list = dao.dailyStatistic(startDate, endDate);
				request.setAttribute("type", 1);
			}else {
				list = dao.monthlyStatistic(startDate, endDate);	
				request.setAttribute("type", 2);
			}
			request.setAttribute("statistic", list);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		request.getRequestDispatcher("/views/statistic.jsp").forward(request, response);
	}

	protected void findBillDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HotelInfoDao htDao = new HotelInfoDao();
			HotelInfo hotel = htDao.find();
			
			String billCode = request.getParameter("billCode");
			BillDao dao = new BillDao();
			Bill bill = dao.findById(billCode);
			BookingDao bookingDao = new BookingDao();
			List<BookingDetail> list = bookingDao.findRoom(bill.getBooking().getBookingCode());
			Booking booking = bookingDao.findById(bill.getBooking().getBookingCode());
			CheckOutForm checkOutForm = bookingDao.findCheckoutForm(bill.getBooking().getBookingCode());
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			
			request.setAttribute("hotel", hotel);
			request.setAttribute("checkoutDate", dateFormat.format(bill.getCheckoutDate()));
			request.setAttribute("bill", bill);
			request.setAttribute("bookingDetails", list);
			request.setAttribute("checkoutForm", checkOutForm);
			request.setAttribute("booking", booking);
			request.getRequestDispatcher("/views/bill.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("error", "Lỗi tìm kiếm");
			PageInfo.prepareAndForward(request, response, PageType.HOME_PAGE);
		}
	}
}
