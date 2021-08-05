package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.model.Bill;
import com.model.Booking;

public class BillDao extends AbstractEntityDao<Bill> {

	public BillDao() {
		super(Bill.class);
		// TODO Auto-generated constructor stub
	}
	public List<Bill> filterBill(String id, int num){
		EntityManager em = JpaUtils.getEntityManager();
		String jpql = "select b from Bill b where (b.billCode like :billCode or b.booking.customer.phoneNumber like : phoneNumber "
				+ " or b.booking.customer.fullname like :fullname) and (b.billType = :num1 or b.billType = :num2) order by b.checkoutDate";
		TypedQuery<Bill> query = em.createQuery(jpql,Bill.class);
		query.setParameter("billCode", "%" + id + "%");
		query.setParameter("phoneNumber", "%" + id +  "%");
		query.setParameter("fullname", "%" + id + "%");
		if(num==1) {
			 query.setParameter("num1", true);
			 query.setParameter("num2", true);
		}else if(num == 2) {
			query.setParameter("num1", false);
			query.setParameter("num2", false);
		}else {
			query.setParameter("num1", true);
			query.setParameter("num2", false);
		}
		return query.getResultList();
	}
	
	
	public String findBillCode() {
		EntityManager em = JpaUtils.getEntityManager();
		try {
			String sSql = "\r\n"
					+ "select max(bill_code) from Bill\r\n"
					+ "where bill_code like CONCAT('E',REPLICATE('[0-9]',4)) \r\n";
				
			Query query = em.createNativeQuery(sSql);
			if(query.getSingleResult()==null) {
				return "E1000";
			}
			String billCode = (String)query.getSingleResult();
			StringBuilder sb = new StringBuilder(billCode);
			String number = sb.deleteCharAt(0).toString();
			int iNumber = Integer.parseInt(number);
			iNumber++;
			return "E"+iNumber;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
			em.close();
		}
	}
}
