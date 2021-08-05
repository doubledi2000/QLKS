package com.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the Room_type database table.
 * 
 */
@Entity
@NamedQuery(name="Room_type.findAll", query="SELECT r FROM Room_type r")
public class Room_type implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="room_type_code")
	private String roomTypeCode;

	private String note;

	@Column(name="price_per_day")
	private double pricePerDay;

	@Column(name="price_per_hour")
	private double pricePerHour;

	@Column(name="room_type_name")
	private String roomTypeName;

	//bi-directional many-to-one association to Room
	@OneToMany(mappedBy="roomType")
	private List<Room> rooms;

	public Room_type() {
	}

	public String getRoomTypeCode() {
		return this.roomTypeCode;
	}

	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
		roomTypeCode.equals(roomTypeCode);
	}


	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public List<Room> getRooms() {
		return this.rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public Room addRoom(Room room) {
		getRooms().add(room);
		room.setRoomType(this);

		return room;
	}

	public Room removeRoom(Room room) {
		getRooms().remove(room);
		room.setRoomType(null);

		return room;
	}

}