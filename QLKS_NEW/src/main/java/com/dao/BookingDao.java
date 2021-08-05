package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.domain.CheckOutForm;
import com.model.Booking;
import com.model.BookingDetail;
import com.domain.*;
public class BookingDao extends AbstractEntityDao<Booking> {

	public BookingDao() {
		super(Booking.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Booking> findAll() {
		EntityManager em = JpaUtils.getEntityManager();
		String jpql = "select b from Booking b where b.stt = 1 or b.stt = 2 order by b.checkinDate asc";
		TypedQuery<Booking> query = em.createQuery(jpql,Booking.class);
		
		return query.getResultList();
	}
	public List<BookingDetail> findRoom(String bookingCode) {
		EntityManager em = JpaUtils.getEntityManager();
		String jpql = "select u from BookingDetail u where u.booking.bookingCode = :bookingCode order by u.startDay asc";
		TypedQuery<BookingDetail> query = em.createQuery(jpql,BookingDetail.class);
		query.setParameter("bookingCode", bookingCode);
	
		return query.getResultList();
	}
	
	public double findTotalRoomCharge(String id) {
		BookingDao dao = new BookingDao();
		Booking booking = dao.findById(id);
		if(booking == null || booking.getBookingDetails() == null) return 0;
		double total = 0;
		for(BookingDetail item : booking.getBookingDetails()) {
			total += item.getAmount();			
		}
		return total;
	}
	
	public CheckOutForm findCheckoutForm(String bookingCode) {
		String jpql = "select new com.domain.CheckOutForm(f.customer.fullname, "
				+" f.customer.phoneNumber, f.customer.addr, "
				+" f.totalRoomCharge, f.deposit, f.fines, "
				+" f.extraPerson * f.pricePerOne, "
				+ " (f.totalRoomCharge + f.fines + f.extraPerson * f.pricePerOne - f.deposit) "
				+ " ) from Booking f where f.bookingCode = :bookingCode";
		EntityManager em = JpaUtils.getEntityManager();
		CheckOutForm form = null;
		try {
			TypedQuery<CheckOutForm> query = em.createQuery(jpql, CheckOutForm.class);
			query.setParameter("bookingCode", bookingCode);
			form = query.getSingleResult();
			double remaining = form.getTotalRoomCharge() + form.getFines() + form.getPersonFines() - form.getDeposit();
			form.setRemaining(remaining);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
		return form;
		
	}



	public String findBookingCode() {
		EntityManager em = JpaUtils.getEntityManager();
		try {
			String sSql = "\r\n"
					+ "select max(booking_code) from booking\r\n"
					+ "where booking_code like CONCAT('B',REPLICATE('[0-9]',4)) \r\n";
				
			Query query = em.createNativeQuery(sSql);
			if(query.getSingleResult()==null) {
				return "B1000";
			}
			String bookingCode = (String)query.getSingleResult();
			StringBuilder sb = new StringBuilder(bookingCode);
			String number = sb.deleteCharAt(0).toString();
			int iNumber = Integer.parseInt(number);
			iNumber++;
			return "B"+iNumber;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
			em.close();
		}
	}

}
