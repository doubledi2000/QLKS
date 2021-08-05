package com.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import com.model.HotelInfo;

public class HotelInfoDao extends AbstractEntityDao<HotelInfo> {

	public HotelInfoDao() {
		super(HotelInfo.class);
		// TODO Auto-generated constructor stub
	}
	public HotelInfo find() {
		EntityManager em = JpaUtils.getEntityManager();
		try {
			String jpql = "select h from HotelInfo h";
			TypedQuery<HotelInfo> query = em.createQuery(jpql,HotelInfo.class);
			return query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			em.close();
		}
		return null;
		
	}
	
}
