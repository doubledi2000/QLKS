package com.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.model.Employee;

public class EmployeeDao extends AbstractEntityDao<Employee> {

	public EmployeeDao() {
		super(Employee.class);
		// TODO Auto-generated constructor stub
	}
	
	
	public Employee findByUsername(String account) {
		EntityManager em = JpaUtils.getEntityManager();
		try {
			String jpql = "select b from Employee b where b.account = :account";
			TypedQuery<Employee> query = em.createQuery(jpql,Employee.class);
			query.setParameter("account", account);
			return query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
		return null;
		
	}
	
}
