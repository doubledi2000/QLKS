package com.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.resource.cci.ResultSet;

import com.domain.CurrentRoomStt;
import com.model.Room;

public class RoomDao extends AbstractEntityDao<Room> {

	public RoomDao() {
		super(Room.class);
		// TODO Auto-generated constructor stub
	}
	/*
	 * public List<Room> findRoomEmpty(Date startDate, Date endDate){ EntityManager
	 * em = JpaUtils.getEntityManager(); String jpql =
	 * "select distinct b.room.roomCode from BookingDetail b where b.startDate" +
	 * " between :startDate and :endDate or b.endDate between :startDate and :endDate"
	 * ;
	 * 
	 * TypedQuery<Room> query = em.createQuery(jpql,Room.class);
	 * 
	 * return query.getResultList(); }
	 */
	
	public List<Room> findRoomEmpty(Date startDate, Date endDate){
		EntityManager em = JpaUtils.getEntityManager();
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("Room.spFindRoomEmpty");
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		return query.getResultList();	
	}
	
	public List<CurrentRoomStt> findCurrentRoomStt(Date time) throws SQLException{
		EntityManager em = JpaUtils.getEntityManager();
		String sSql = "select room.room_number, iif(room_stt.stt = 1,1,IIF(room_stt.stt=2,2,0)) as room_stt,Room_type.room_type_name,\r\n"
				+ "	IIF(room_stt.stt != 0,room_stt.fullname,null) as fullname, iif(room_stt.stt=1,room_stt.start_day,iif(room_stt.stt=2,room_stt.end_day,null)) as timeActive\r\n"
				+ "	from room left join \r\n"
				+ "	(select distinct room.room_code, booking.stt, customer.fullname,booking_detail.start_day,booking_detail.end_day\r\n"
				+ "	from room left join booking_detail on room.room_code = booking_detail.room_code\r\n"
				+ "			join booking on booking.booking_code = booking_detail.booking_code\r\n"
				+ "			join customer on customer.phone_number = booking.customer_code\r\n"
				+ "	where booking_detail.start_day <= ? and booking_detail.end_day >= ?) as room_stt on room.room_code = room_stt.room_code\r\n"
				+ "		\r\n"
				+ "	left join Room_type on Room_type.room_type_code = room.room_type_code"
				+" where room.active != 0"
				+" order by Room_type.room_type_name";
		
		Query query = em.createNativeQuery(sSql);
		query.setParameter(1, time);
		query.setParameter(2, time);
		List<Object[]> list = query.getResultList();
		List<CurrentRoomStt> roomList = new ArrayList<CurrentRoomStt>();
		for(Object[] a: list) {
			CurrentRoomStt item = new CurrentRoomStt((String)a[0],(int)a[1],(String)a[2],(String)a[3],(Date)a[4]);
			roomList.add(item);
		}
		
		return roomList;
	}
}
