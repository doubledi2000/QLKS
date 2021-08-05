package com.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.domain.CurrentRoomStt;
import com.domain.StatisticForm;

public class StatisticDao {
	
	public List<StatisticForm> dailyStatistic(Date startDate, Date endDate){
		EntityManager em = JpaUtils.getEntityManager();
		try {
			String sSql = "select iif(bookingReport.ngay is not null,bookingReport.ngay,billReport.ngay) as ngay,"
					+ " iif(bookingReport.thang is not null,bookingReport.thang,billReport.thang) as thang, "
					+ "iif(bookingReport.nam is not null, bookingReport.nam,billReport.nam) as nam,"
					+ " iif(bookingReport.datcoc is not null,bookingReport.datcoc,0) as datcoc,"
					+ " iif(billReport.phatphong is not null,billReport.phatphong,0) as phatphong,\r\n"
					+ "iif(billReport.phatNguoi is not null,billReport.phatNguoi,0)  as phatnguoi,  \r\n"
					+ "iif(billReport.doanhthuphong is not null,billReport.doanhthuphong,0) as doanhthuphong ,  \r\n"
					+ " iif(billReport.hoantien is not null,billReport.hoantien,0) as hoantien  \r\n"
					+ " from      \r\n"
					+ " (select DAY(booking.booking_date) as ngay, MONTH(booking.booking_date) as thang, YEAR(booking.booking_date) as nam, SUM(booking.deposit) as datcoc      \r\n"
					+ " from booking      \r\n"
					+ " where booking.booking_date  >= ? and booking.booking_date <= ?      \r\n"
					+ " group by day(booking.booking_date),MONTH(booking.booking_date), YEAR(booking.booking_date)) bookingReport      \r\n"
					+ "  full outer join      \r\n"
					+ " (select Day(bill.checkout_date) as ngay, MONTH(bill.checkout_date) as  thang, YEAR(bill.checkout_date) as nam,SUM(booking.fines) as phatphong,SUM(booking.extraPerson * booking.price_per_one) as phatNguoi,      \r\n"
					+ "   SUM(iif(bill.bill_type = 1,booking.total_room_charge - booking.deposit,null)) as doanhthuphong, sum(iif(bill.bill_type = 0, (booking.deposit - booking.fines -booking.extraPerson) * booking.refund / 100,null)) as hoantien      \r\n"
					+ " from bill inner join booking on bill.booking_code = booking.booking_code      \r\n"
					+ " where booking.stt = 3 and bill.checkout_date >=? and bill.checkout_date <= ?      \r\n"
					+ " group by day(bill.checkout_date), MONTH(bill.checkout_date), YEAR(bill.checkout_date)) billReport on (bookingReport.thang =billReport.thang and bookingReport.nam = billReport.nam and bookingReport.ngay = billReport.ngay)      \r\n"
					+ " order by nam asc, thang asc, ngay asc";
			Query query = em.createNativeQuery(sSql);
			query.setParameter(1, startDate);
			query.setParameter(2, endDate);
			query.setParameter(3, startDate);
			query.setParameter(4, endDate);

			List<Object[]> list = query.getResultList();
			List<StatisticForm> statisticList = new ArrayList<StatisticForm>();
			for(Object[] a: list) {
				BigDecimal[] arr = new BigDecimal[5];
				Double[] dArr = new Double[5];
				for(int i = 0 ;i < 5; i++) {
					arr[i] = (BigDecimal) a[i + 3];
					dArr[i] = arr[i].doubleValue();
				}

				StatisticForm item = new StatisticForm((int)a[0],(int)a[1],(int)a[2],dArr[0],dArr[1],dArr[2],dArr[3],dArr[4]);
				statisticList.add(item);
			}
			return statisticList;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
			em.close();
		}
	}

	public List<StatisticForm> monthlyStatistic(Date startDate, Date endDate){
		EntityManager em = JpaUtils.getEntityManager();
		try {
			
			String sSql = "select iif(bookingReport.thang is not null,bookingReport.thang,billReport.thang) as thang,"
					+ " iif(bookingReport.nam is not null, bookingReport.nam,billReport.nam) as nam,"
					+ " iif(bookingReport.datcoc is not null,bookingReport.datcoc,0) as datcoc,"
					+ " iif(billReport.phatphong is not null,billReport.phatphong,0) as phatphong, "
					+ "iif(billReport.phatNguoi is not null,billReport.phatNguoi,0)\r\n"
					+ "  \r\n"
					+ " as phatnguoi,"
					+ "iif(billReport.doanhthuphong is not null,billReport.doanhthuphong,0) as doanhthuphong ,\r\n"
					+ "	iif(billReport.hoantien is not null,billReport.hoantien,0) as hoantien\r\n"
					+ " from    \r\n"
					+ " (select MONTH(booking.booking_date) as thang, YEAR(booking.booking_date) as nam, SUM(booking.deposit) as datcoc    \r\n"
					+ " from booking    \r\n"
					+ " where booking.booking_date  >= ? and booking.booking_date <= ?    \r\n"
					+ " group by MONTH(booking.booking_date), YEAR(booking.booking_date)) bookingReport    \r\n"
					+ "  full outer join    \r\n"
					+ " (select MONTH(bill.checkout_date) as  thang, YEAR(bill.checkout_date) as nam,SUM(booking.fines) as phatphong,SUM(booking.extraPerson * booking.price_per_one) as phatNguoi,    \r\n"
					+ "   SUM(iif(bill.bill_type = 1,booking.total_room_charge - booking.deposit,null)) as doanhthuphong, sum(iif(bill.bill_type = 0, booking.deposit - booking.fines -booking.extraPerson,null)) as hoantien    \r\n"
					+ " from bill inner join booking on bill.booking_code = booking.booking_code    \r\n"
					+ " where booking.stt = 3 and bill.checkout_date >? and bill.checkout_date <= ?    \r\n"
					+ " group by MONTH(bill.checkout_date), YEAR(bill.checkout_date)) billReport on (bookingReport.thang =billReport.thang and bookingReport.nam = billReport.nam)    \r\n"
					+ " order by nam asc, thang asc ";
			
			Query query = em.createNativeQuery(sSql);
			query.setParameter(1, startDate);
			query.setParameter(2, endDate);
			query.setParameter(3, startDate);
			query.setParameter(4, endDate);
			List<Object[]> list = query.getResultList();
			List<StatisticForm> statisticList = new ArrayList<StatisticForm>();
			for(Object[] a: list) {
				BigDecimal[] arr = new BigDecimal[5];
				Double[] dArr = new Double[5];
				for(int i = 0 ;i < 5; i++) {
					arr[i] = (BigDecimal) a[i + 2];
					dArr[i] = arr[i].doubleValue();
				}
				
				StatisticForm item = new StatisticForm((int)a[0],(int)a[1],dArr[0],dArr[1],dArr[2],dArr[3],dArr[4]);
				statisticList.add(item);
				
			}
			return statisticList;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
			em.close();
		}
		
	}
}
