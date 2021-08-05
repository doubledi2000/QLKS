package com.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

import com.common.EmailUtils;
import com.common.PageInfo;
import com.common.PageType;
import com.dao.BillDao;
import com.dao.BookingDao;
import com.dao.CustomerDao;
import com.dao.EmployeeDao;
import com.dao.HotelInfoDao;
import com.dao.RoomDao;
import com.domain.BookingForm;
import com.domain.CheckOutForm;
import com.domain.Email;
import com.model.Bill;
import com.model.Booking;
import com.model.BookingDetail;
import com.model.Customer;
import com.model.Employee;
import com.model.HotelInfo;
import com.model.Room;

/**
 * Servlet implementation class BookingServlet
 */
@WebServlet({"/BookingServlet","/BookingServlet/edit","/BookingServlet/create",
	"/BookingServlet/update","/BookingServlet/delete",
	"/BookingServlet/reset","/BookingServlet/bookEditing",
	"/BookingServlet/cancel","/BookingServlet/confirmation",
	"/BookingServlet/checkout"})
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = request.getRequestURI().toString();
		if(url.contains("edit")) {
			edit(request, response);
			return;
		}else if(url.contains("checkout")){
			checkout(request, response);
			return;
		}
		List<BookingDetail> list = null;
		request.setAttribute("bookingDetails", list);
		findAll(request, response);
//		findRoomBooked(request, response);
	 	PageInfo.prepareAndForward(request, response,PageType.BOOKING_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		if(url.contains("create")) {
			create(request, response);
			return;
		}else if(url.contains("edit")) {
			edit(request, response);
			return;
		}else if(url.contains("update")) {
			update(request, response);
			return;
		}else if(url.contains("delete")) {
			delete(request, response);
			return;
		}else if(url.contains("book")) {
			bookEditing(request, response);
			return;
		}else if(url.contains("confirmation")) {
			bookingConfirmation(request, response);
			return;
		} else if(url.contains("checkout")) {
			checkout(request, response);
			return;
		}else if(url.contains("reset")) {
			reset(request, response);
			return;
		}else if(url.contains("cancel")) {
			cancel(request, response);
			return;
		}
	}
	
	
	public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Booking booking = new Booking();
		try {
			
			DateTimeConverter dtc = new DateConverter(new Date());
			dtc.setPattern("yyyy-MM-ddTHH:mm");
			ConvertUtils.register(dtc, Date.class);
			//do du lieu vao booking
			BeanUtils.populate(booking, request.getParameterMap());
			//tim kiem khach hang theo so dien thoai
			BookingDao dao = new BookingDao();
			CustomerDao cusDao =  new CustomerDao();
			EmployeeDao emDao = new EmployeeDao();
			String phone = request.getParameter("phoneNumber").toString();
			String emp = request.getParameter("employeeCode").toString();
			Customer customer = cusDao.findById(phone);
			if(customer == null) {
				request.setAttribute("error", "Vui long nhap thong tin khach hang");
				customer = new Customer();
				customer.setPhoneNumber(phone);
				request.setAttribute("customer", customer);
				PageInfo.prepareAndForward(request, response, PageType.CUSTOMER_MANAGEMENT_PAGE);
				return;
				
			}

			HttpSession session = request.getSession();
			
			Employee employee = (Employee) session.getAttribute("employeeLogin");
			
			//chen ngay den check in
			String pattern = "yyyy-MM-dd HH:mm";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			String cDate = request.getParameter("checkinDate").toString();
			cDate = cDate.replace('T', ' ');
			Date checkinDate = dateFormat.parse(cDate);
			request.setAttribute("checkinDate", cDate.replace(' ', 'T'));
			String bookingCode = dao.findBookingCode();
			booking.setBookingCode(bookingCode);
			booking.setBookingDate(new Date());	
			booking.setCheckinDate(checkinDate);
			booking.setCustomer(customer);
			booking.setEmployee(employee);
			
			if(!check(request, response, booking)) {
				PageInfo.prepareAndForward(request, response, PageType.BOOKING_PAGE);
				return;
			}
			
			
			dao.insert(booking);
			
			request.setAttribute("booking", booking);
			request.setAttribute("message", "Booking is inserted");
			findAll(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageInfo.prepareAndForward(request, response, PageType.BOOKING_PAGE);
	}
	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			BookingDao dao = new BookingDao();
			List<Booking> list = dao.findAll();
			request.setAttribute("bookings", list);
			request.setAttribute("currentDate", new Date());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "error: " + e.getMessage()) ;
		}
	}
	private boolean check(HttpServletRequest request, HttpServletResponse respones, Booking booking) throws ServletException, IOException {
		EmployeeDao dao = new EmployeeDao();
		if(booking.getBookingCode() == null || booking.getBookingCode().length() > 5) {
			request.setAttribute("error", "Ma dat phongg khong hop le.");
			return false;
		} else if(booking.getEmployee().getPhoneNumber() == null) {
			request.setAttribute("error", "Vui long nhap thong tin khach hang");
			return false;
		} else if(booking.getCheckinDate() == null) {
			request.setAttribute("error", "vui long nhap ngay den check in");
			return false;
		}
		return true;
	}
	private void findRoomBooked(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("bookingCode");
		BookingDao dao = new BookingDao();
		List<BookingDetail> list = dao.findRoom(id);
		request.setAttribute("bookingDetails", list);
	}
	
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("bookingCode");
		BookingDao dao = new BookingDao();
		Booking booking = dao.findById(id);

		List<BookingDetail> list = dao.findRoom(id);
		
		request.setAttribute("booking", booking);
		request.setAttribute("bookingDetails", list);
		findAll(request, response);
		String pattern = "yyyy-MM-dd HH:mm";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		String checkinDate = dateFormat.format(booking.getCheckinDate());
		request.setAttribute("checkinDate", checkinDate.replace(' ', 'T'));
		PageInfo.prepareAndForward(request, response, PageType.BOOKING_PAGE);
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
		try {
			String id = request.getParameter("bookingCode");
			BookingDao dao = new BookingDao();
			// tim oldBooking
			Booking oldBooking = dao.findById(id);
			if(oldBooking.getStt() == 3) {
				doGet(request, response);
				return;
			}
			DateTimeConverter dtc = new DateConverter(new Date());
			dtc.setPattern("yyyy-MM-dd HH:mm");
			ConvertUtils.register(dtc, Date.class);
			
			Booking newBooking = new Booking();
			BeanUtils.populate(newBooking, request.getParameterMap());
			
			String pattern = "yyyy-MM-dd HH:mm";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			String cDate = request.getParameter("checkinDate").toString();
			cDate = cDate.replace('T', ' ');
			Date checkinDate = dateFormat.parse(cDate);
			String checkinDay = dateFormat.format(checkinDate);
			request.setAttribute("checkinDate", checkinDay.replace(' ', 'T'));
			
			newBooking.setCheckinDate(checkinDate);
			newBooking.setEmployee(oldBooking.getEmployee());
			newBooking.setCustomer(oldBooking.getCustomer());
			newBooking.setBookingDate(oldBooking.getBookingDate());
			newBooking.setTotalRoomCharge(oldBooking.getTotalRoomCharge());
			
			dao.update(newBooking);
			request.setAttribute("message", "booking is updated");
			request.setAttribute("booking", newBooking);
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "booking updated failed");
		}
		findAll(request, response);
		findRoomBooked(request, response);
		PageInfo.prepareAndForward(request, response, PageType.BOOKING_PAGE);
	} 
		
	private void delete(HttpServletRequest request, HttpServletResponse respones) throws ServletException, IOException {
		try {
			String id = request.getParameter("bookingCode");
			BookingDao dao = new BookingDao();
			Booking booking = dao.findById(id);
			if(id == null || booking==null) {
				request.setAttribute("error", "Xoa loi");
				doGet(request, respones);
				return;
			}
			dao.delete(id);
			
			request.setAttribute("booking", new Booking());
			request.setAttribute("message", "Booking deleted");
			
		} catch (Exception e) {
			request.setAttribute("error", "Delete failded");
			e.printStackTrace();
		}
		findAll(request, respones);
		PageInfo.prepareAndForward(request, respones, PageType.BOOKING_PAGE);
	}
	
	private void bookEditing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String bookingCode = request.getParameter("bookingCode").toString();
			BookingDao dao = new BookingDao();
			Booking booking = dao.findById(bookingCode);
			if(booking.getStt() == 3) {
				doGet(request, response);
				return;
			}
			BookingForm bookingForm = new BookingForm();
			bookingForm.setBookingCode(bookingCode);
			bookingForm.setFullname(booking.getCustomer().getFullname());
			request.setAttribute("bookingForm", bookingForm);
			List<BookingDetail> roomBookedList = dao.findRoom(bookingCode);
			request.setAttribute("roomBookedList", roomBookedList);
			PageInfo.prepareAndForward(request, response, PageType.BOOKING_DETAIL_PAGE);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("error", "loi dat phong");
			PageInfo.prepareAndForward(request, response, PageType.BOOKING_PAGE);
		}
		
		
	}
	
	private void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("bookingCode");
			BookingDao dao = new BookingDao();
			List<BookingDetail> list = dao.findRoom(id);
			Booking booking = dao.findById(id);
			if(booking.getStt()==3) {
				doGet(request, response);
				return;
			}
			booking.setStt(3);
			dao.update(booking);
			Bill bill = new Bill();
			BillDao billDao = new BillDao();
			String billCode = billDao.findBillCode();
			bill.setBillCode(billCode);
			bill.setBillType(true);
			bill.setBooking(booking);
			bill.setCheckoutDate(new Date());
			billDao.insert(bill);
			CheckOutForm checkOutForm = dao.findCheckoutForm(id);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			request.setAttribute("checkoutDate", dateFormat.format(bill.getCheckoutDate()));
			request.setAttribute("bill", bill);
			request.setAttribute("bookingDetails", list);
			request.setAttribute("checkoutForm", checkOutForm);
			request.setAttribute("booking", booking);
			request.getRequestDispatcher("/views/checkout.jsp").forward(request, response);
			//PageInfo.prepareAndForward(request, response, PageType.BOOKING_DETAIL_PAGE);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("error", "loi  checkout");
			PageInfo.prepareAndForward(request, response, PageType.BOOKING_PAGE);
		}
		
	}
		

	private void bookingConfirmation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customerId = request.getParameter("phoneNumber");
		CustomerDao dao = new CustomerDao();
	
		Customer customer = dao.findById(customerId);
		String bookingCode = request.getParameter("bookingCode");
		BookingDao bDao = new BookingDao();
		Booking booking = bDao.findById(bookingCode);
		if(booking.getStt() == 2 || booking.getStt() == 3) {
			doGet(request, response);
			return;
		}
		request.setAttribute("booking", booking);
		
		HotelInfoDao htDao = new HotelInfoDao();
		HotelInfo hotel = htDao.find();
		
		try {
			String pattern = "yyyy-MM-dd HH:mm";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			String checkinDate = dateFormat.format(booking.getBookingDate());
			request.setAttribute("checkinDate", checkinDate.replace(' ', 'T'));
			if(customer == null) {
				request.setAttribute("error", "Khách hàng chưa tồn tại");	
			}else {
				Email email = new Email();
				email.setFrom(hotel.getEmail());
				email.setFromPassword(hotel.getEmailPassword() );
				email.setTo(customer.getEmail());
				email.setSubject("BOOKING CONFIRMATION");
				StringBuilder sb = new StringBuilder();
				sb.append("Kính chào quý khách ").append(customer.getFullname()).append(".<br>");
				sb.append("Quý khách nhận được email này, vì đã tiến hành giao dịch đặt phòng thành công của khách sạn ").append(hotel.getFullname()).append(".<br><br>");
				sb.append("Thông tin giao dịch: ").append("<br>");
				sb.append("   -Mã giao dịch: ").append(booking.getBookingCode()).append(".<br>");
				sb.append("   -Thời gian: ").append(dateFormat.format(booking.getBookingDate())).append(".<br>");
				sb.append("   -Thời gian checkin: ").append(dateFormat.format(booking.getCheckinDate())).append(".<br>");
				sb.append("   -Số tiền đã đặt cọc: ").append(booking.getDeposit()).append(".<br>").append(" vnđ");
				sb.append("Vui long đến checkin đúng thời gian đăng kí. Cảm ơn quý khách đã sử dụng dịch vụ của chúng tôi!").append("<br>");
				sb.append("Mọi thắc mắc xin vui lòng liên hệ với khách sạn qua email ").append(hotel.getEmail()).append(" hoặc số điện thoại: ").append(hotel.getPhoneNumber()).append(".<br>");
				sb.append("Trân trọng!").append("<br><br>");
				
				email.setContent(sb.toString());
				EmailUtils.send(email);
				request.setAttribute("message", "Gửi email thành công");
				
				
			}

		}
		catch (Exception e) {
			request.setAttribute("error", "Gửi email không thành công. Kiểm tra lại thông tin tài khoản email.");
			
		}
		findAll(request, response);
		findRoomBooked(request, response);
		PageInfo.prepareAndForward(request, response, PageType.BOOKING_PAGE);


	}

	
	private void cancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String customerId = request.getParameter("phoneNumber");
			CustomerDao dao = new CustomerDao();
		
			Customer customer = dao.findById(customerId);
			String bookingCode = request.getParameter("bookingCode");
			BookingDao bDao = new BookingDao();
			Booking booking = bDao.findById(bookingCode);
			if(booking.getStt() == 3) {
				throw new Exception();
			}
			BillDao billDao = new BillDao();
			String billCode = billDao.findBillCode();
			Bill bill = new Bill();
			bill.setBillCode(billCode);
			bill.setBillType(false);
			bill.setBooking(booking);
			bill.setCheckoutDate(new Date());
			billDao.insert(bill); 
			bill = billDao.findById(billCode);
			
			HotelInfoDao htDao = new HotelInfoDao();
			HotelInfo hotel = htDao.find();
			//gui email
			String pattern = "dd-MM-yyyy HH:mm";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			if(customer == null) {
				request.setAttribute("error", "Khách hàng chưa tồn tại");	
			}else {
				Email email = new Email();
				email.setFrom(hotel.getEmail());
				email.setFromPassword(hotel.getEmailPassword() );
				email.setTo(customer.getEmail());
				email.setSubject("CANCEL RESERVATION");
				StringBuilder sb = new StringBuilder();
				sb.append("Kính chào quý khách ").append(customer.getFullname()).append(".<br>");
				sb.append("Khách sạn ").append(hotel.getFullname()).append(" xin thông báo, quý khách đã hủy đặt phòng thành công").append(".<br><br>");
				sb.append("Thông tin giao dịch: ").append("<br>");
				sb.append("   -Mã hóa đơn: ").append(bill.getBillCode()).append(".<br>");
				sb.append("   -Thời gian: ").append(dateFormat.format(bill.getCheckoutDate())).append(".<br>");
				sb.append("   -Số tiền đã đặt cọc: ").append(booking.getDeposit()).append(" vnđ").append(".<br>");
				sb.append("   -Số tiền bị phạt do hủy phòng muộn: ").append(booking.getFines()).append(" vnđ").append(".<br>");
				sb.append("   -Số tiền quý khách nhận lại được là(").append(booking.getRefund()).append("%) :").append(bill.getTotalPayment()).append( "vnđ. <br>");
				sb.append("	  -Số tiền sẽ được hoàn lại trong vòng 3 ngày kể từ ngày lập hóa đơn.").append("<br>");
				sb.append("Mọi thắc mắc xin vui lòng liên hệ với khách sạn qua email ").append(hotel.getEmail()).append(" hoặc số điện thoại: ").append(hotel.getPhoneNumber()).append(".<br>");
				sb.append("Trân trọng!").append("<br><br>");
				
				email.setContent(sb.toString());
				EmailUtils.send(email);
				
				
			}
			 request.setAttribute("message", "Hủy đặt phòng thành công");
			 request.setAttribute("booking", new Booking());
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("error", "Hủy đặt phòng không thành công");
		}
		PageInfo.prepareAndForward(request, response, PageType.BOOKING_PAGE);
		
	}
	private void reset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

