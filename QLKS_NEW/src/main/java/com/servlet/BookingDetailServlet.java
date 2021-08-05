package com.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

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
import com.dao.BookingDao;
import com.dao.BookingDetailDao;
import com.dao.RoomDao;
import com.domain.BookingForm;
import com.model.Booking;
import com.model.BookingDetail;
import com.model.Room;

/**
 * Servlet implementation class BookingDetailServlet
 */
@WebServlet({"/BookingDetailServlet","/BookingDetailServlet/book","/BookingDetailServlet/search",
	"/BookingDetailServlet/delete","/BookingDetailServlet/return"})
public class BookingDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();
		System.out.print("get");
		if(url.contains("book")) {
			insert(request, response);
			return;
		}
		PageInfo.prepareAndForward(request, response, PageType.BOOKING_DETAIL_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		System.out.print("post");
		if(url.contains("book")) {
			insert(request, response);
			return;
		} else if(url.contains("search")) {
			search(request, response);
			return;
		}else if(url.contains("delete")) {
			delete(request, response);
			return;
		}else if(url.contains("return")) {
			returnBooking(request, response);
			return;
		}
	}
	protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
			
			BookingDao bDao = new BookingDao();
			try {
				//convert date time
				DateTimeConverter dtc = new DateConverter(new Date());
				dtc.setPattern("dd/MM/yyyy hh:mm");
				ConvertUtils.register(dtc, Date.class);
				BookingForm bookingForm = new BookingForm();
				BookingDetail bookingDetail = new BookingDetail();
				String pattern = "yyyy-MM-dd hh:mm";
				SimpleDateFormat formatDate = new SimpleDateFormat(pattern);
				BeanUtils.populate(bookingForm, request.getParameterMap());
				request.setAttribute("bookingForm", bookingForm); 		
				
				//search room by roomCode 
				
				Room room = new Room();
				RoomDao rDao = new RoomDao();
				String roomCode = request.getParameter("roomCode");
				room = rDao.findById(roomCode);
				
				//search booking by bookingCode
				BookingDetailDao dao = new BookingDetailDao();
				String bookingCode = request.getParameter("bookingCode");
				
				Booking booking = bDao.findById(bookingCode);
				
				//create id for bookingdetail
				int id = dao.findId();
				
				//get datetime from input end convert to date class
				String endDate = request.getParameter("endDate");
				String startDate = request.getParameter("startDate"); 
				String startDateFormat = startDate.replace('T',' ');
				String endDateFormat = endDate.replace('T',' ');
				Date sDate = formatDate.parse(startDateFormat);	
				Date eDate = formatDate.parse(endDateFormat);
				if(sDate.after(eDate)) {
					throw new Exception();
				}
				//insert into booking detail
				bookingDetail.setStartDay(sDate);
				bookingDetail.setEndDay(eDate);
				
				bookingDetail.setId(id);
				bookingDetail.setBooking(booking);
				bookingDetail.setRoom(room);
				double price = dao.findPrice(sDate, eDate, room.getRoomType().getPricePerHour(), room.getRoomType().getPricePerDay());
				bookingDetail.setPrice(price);
				double amount = dao.findAmount(sDate, eDate, room.getRoomType().getPricePerHour(),room.getRoomType().getPricePerDay());
				bookingDetail.setAmount(amount);
				if(booking.getTotalRoomCharge() == 0) {
					booking.setTotalRoomCharge(amount);
				}else {
					double newAmount = booking.getTotalRoomCharge() + amount;
					booking.setTotalRoomCharge(newAmount);
				}
				bookingDetail.setBookingType(true);
				//insert into bookingdetail
				dao.insert(bookingDetail);
				
				//find room empty list after insert
				List<Room> listEmpty = rDao.findRoomEmpty(bookingDetail.getStartDay(), bookingDetail.getEndDay());
				request.setAttribute("roomEmptyList",listEmpty);
				//find room booked list of booking
				List<BookingDetail> roomBookedList = bDao.findRoom(bookingCode);
				request.setAttribute("roomBookedList", roomBookedList);
				search(request, response);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				BookingForm bookingForm = new BookingForm();
				String bookingCode = request.getParameter("bookingCode");
				String fullname = request.getParameter("fullname");
				bookingForm.setBookingCode(bookingCode);
				bookingForm.setFullname(fullname);
				request.setAttribute("bookingForm", bookingForm);
				request.setAttribute("error", "Đặt phòng không thành công");
				e.printStackTrace();

				PageInfo.prepareAndForward(request, response, PageType.BOOKING_DETAIL_PAGE);
			}
		
	}
	
	protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		try {
			DateTimeConverter dtc = new DateConverter(new Date());
			dtc.setPattern("yyyy/MM/dd HH:mm");
			ConvertUtils.register(dtc, Date.class);

			BookingForm bookingForm = new BookingForm();
			BeanUtils.populate(bookingForm, request.getParameterMap());
			
			request.setAttribute("bookingForm", bookingForm);
			RoomDao rDao = new RoomDao();

			String pattern = "yyyy-MM-dd HH:mm";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			String endDate = request.getParameter("endDate");
			String startDate = request.getParameter("startDate");
			request.setAttribute("startDate", startDate);		
			request.setAttribute("endDate", endDate);
			String startDateFormat = startDate.replace('T',' ');
			String endDateFormat = endDate.replace('T',' ');
		

			
			Date sDate = dateFormat.parse(startDateFormat);	
			Date eDate = dateFormat.parse(endDateFormat);
			List<Room> listRoom = rDao.findRoomEmpty(sDate, eDate);

			BookingDao bDao = new BookingDao();
			List<BookingDetail> roomBookedList = bDao.findRoom(bookingForm.getBookingCode());
			request.setAttribute("roomBookedList", roomBookedList);
			if(sDate.after(eDate)) {
				throw new Exception();
			}
			request.setAttribute("roomList", listRoom);
			PageInfo.prepareAndForward(request, response, PageType.BOOKING_DETAIL_PAGE);
			
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("error", "Tìm kiếm không thành công");
			BookingForm bookingForm = new BookingForm();
			String bookingCode = request.getParameter("bookingCode");
			String fullname = request.getParameter("fullname");
			bookingForm.setBookingCode(bookingCode);
			bookingForm.setFullname(fullname);
			request.setAttribute("bookingForm", bookingForm);
			PageInfo.prepareAndForward(request, response, PageType.BOOKING_DETAIL_PAGE);
			e.printStackTrace();
		}
		
	}	

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");

		if(idStr==null) {
			request.setAttribute("error", "id is required");
			PageInfo.prepareAndForward(request, response, PageType.BOOKING_DETAIL_PAGE);
			
			return;
		}
		int id = Integer.parseInt(idStr);

		
		try {
			DateTimeConverter dtc = new DateConverter(new Date());
			dtc.setPattern("dd/MM/yyyy HH:mm");
			ConvertUtils.register(dtc, Date.class);
			BookingForm bookingForm = new BookingForm();
			BeanUtils.populate(bookingForm, request.getParameterMap());
			request.setAttribute("bookingForm", bookingForm);
			String pattern = "yyyy-MM-dd HH:mm";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			String endDate = request.getParameter("endDate");
			String startDate = request.getParameter("startDate"); 
			
			request.setAttribute("startDate", startDate);
			
			request.setAttribute("endDate", endDate);
			try {
				String startDateFormat = startDate.replace('T',' ');
				String endDateFormat = endDate.replace('T',' ');
				Date sDate = dateFormat.parse(startDateFormat);	
				Date eDate = dateFormat.parse(endDateFormat);
				RoomDao rDao = new RoomDao();
				List<Room> listRoom = rDao.findRoomEmpty(sDate, eDate);
				request.setAttribute("roomList", listRoom);
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			BookingDetailDao dao = new BookingDetailDao();
			dao.delete(id);
			
			
			BookingDao bDao = new BookingDao();
			List<BookingDetail> roomBookedList = bDao.findRoom(bookingForm.getBookingCode());
			request.setAttribute("roomBookedList", roomBookedList);
			PageInfo.prepareAndForward(request, response, PageType.BOOKING_DETAIL_PAGE);		
		} catch (Exception e) {
			BookingForm bookingForm = new BookingForm();
			String bookingCode = request.getParameter("bookingCode");
			String fullname = request.getParameter("fullname");
			bookingForm.setBookingCode(bookingCode);
			bookingForm.setFullname(fullname);
			request.setAttribute("bookingForm", bookingForm);
			request.setAttribute("error", "Hủy phòng không thành công");
			e.printStackTrace();
		}
	}

	protected void returnBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String bookingCode = request.getParameter("bookingCode");
			BookingDao bookingDao = new BookingDao();
			Booking booking = new Booking();
			try {
				booking = bookingDao.findById(bookingCode);
				request.setAttribute("booking", booking);
				BookingDao dao = new BookingDao();
				List<BookingDetail> list = dao.findRoom(bookingCode);
				request.setAttribute("bookingDetails", list);
				String pattern = "yyyy-MM-dd HH:mm";
				SimpleDateFormat dateFormate = new SimpleDateFormat(pattern);
				request.setAttribute("checkinDate", dateFormate.format(booking.getCheckinDate()).replace(' ', 'T'));
				
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("error", "Không thể chuyển tiếp");
			}
			PageInfo.prepareAndForward(request, response, PageType.BOOKING_PAGE);
		
	}
}
