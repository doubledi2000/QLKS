package com.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.model.BookingDetail;

public class BookingDetailDao extends AbstractEntityDao<BookingDetail> {

	public BookingDetailDao() {
		super(BookingDetail.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<BookingDetail> findByBookingCode(String id){
		EntityManager em = JpaUtils.getEntityManager();
		String jpql = "SELECT o FROM BOOKINGDETAIL where o.roomCode = :id";
		TypedQuery<BookingDetail> query = em.createQuery(jpql,BookingDetail.class);
		query.setParameter("id", id);
		return query.getResultList();
	}
	public int findId() {
		EntityManager em = JpaUtils.getEntityManager();
		String sSQL = "select max(id) from Booking_detail";
		Query query = em.createNativeQuery(sSQL);
		if(query.getSingleResult() == null) return 1;
		int id = (int)query.getSingleResult();
		return id + 1;
	}
	
	public double findPrice(Date startDate, Date endDate, double pricePerHour, double pricePerDay) {
		double diffInHour = (double)(startDate.getTime() - endDate.getTime())/(1000*60.0 * 60);
		if(diffInHour < 0) {
			return pricePerHour;
		}
		return pricePerDay;
	}
	
	public double findAmount(Date startDate, Date endDate, double pricePerHour, double pricePerDay) {
		
		double diffInHour = (double)(endDate.getTime() - startDate.getTime())/(1000*60.0 * 60);
		diffInHour = Math.ceil(diffInHour);
		if(diffInHour < 6) {
			System.out.println("hour < 6");
			return diffInHour * pricePerHour;
		}

		long startDay = (startDate.getTime() + 7 * 60 * 60*1000)/(1000*24*60*60);
		long endDay = (endDate.getTime() + 7 * 60 * 60  *1000)/(1000*24*60*60);
		System.out.println(startDay);
		System.out.println(endDay);

		double diffInDay = endDay - startDay;
		int checkinTime = startDate.getHours();
		int checkoutTime = endDate.getHours();
		if(checkinTime >=0 && checkinTime < 5) {
			diffInDay +=1.0;
		}else if(checkinTime >=5 && checkinTime <9) {
			diffInDay +=0.5;
		}else if(checkinTime >= 9 && checkinTime < 14) {
			diffInDay +=0.3;
		}
		if(checkoutTime >=12 && checkoutTime <15) {
			diffInDay += 0.3;
		}else if(checkoutTime >=15 && checkoutTime <18) {
			diffInDay +=0.5;
		}else if(checkoutTime > 18) {
			diffInDay +=1.0;
		}
		System.out.println(diffInDay * pricePerDay);
		return diffInDay * pricePerDay;
	}
	

}
