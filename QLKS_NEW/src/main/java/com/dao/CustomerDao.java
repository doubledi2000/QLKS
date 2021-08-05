package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.model.Customer;

public class CustomerDao extends AbstractEntityDao<Customer> {

	public CustomerDao() {
		super(Customer.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Customer> filter(String str, int firstResult, int size) {
		EntityManager em = JpaUtils.getEntityManager();
		String jpql = "select c from Customer c where c.phoneNumber like :str or c.fullname like :str";
		TypedQuery<Customer> query = em.createQuery(jpql,Customer.class);
		query.setParameter("str", "%" + str + "%");
		query.setFirstResult(firstResult);
		query.setMaxResults(size);
		return query.getResultList();
	}
	
	public int count(String str) {
		EntityManager em = JpaUtils.getEntityManager();
		String jpql = "select c from Customer c where c.phoneNumber like :str or c.fullname like :str";
		TypedQuery<Customer> query = em.createQuery(jpql,Customer.class);
		query.setParameter("str", "%" + str + "%");
		List<Customer> list = query.getResultList();
		return list.size();
	}
}
