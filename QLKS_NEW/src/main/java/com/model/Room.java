package com.model;
import com.domain.CurrentRoomStt;
import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the room database table.
 * 
 */
@Entity
@Table(name="room")
@NamedQuery(name="Room.findAll", query="SELECT r FROM Room r")
@NamedStoredProcedureQueries(
		{@NamedStoredProcedureQuery(name = "Room.spFindRoomEmpty",
		procedureName = "sp_FindRoomEmpty",
		resultClasses = Room.class,
		parameters = {@StoredProcedureParameter(type=Date.class,name="startDate",mode=ParameterMode.IN),
				@StoredProcedureParameter(type=Date.class,name="endDate",mode=ParameterMode.IN)}
		)
		})
public class Room implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="room_code")
	private String roomCode;

	private String note;

	@Column(name="room_number")
	private String roomNumber;
	private boolean active;
	//bi-directional many-to-one association to BookingDetail
	@OneToMany(mappedBy="room")
	private List<BookingDetail> bookingDetails;

	//bi-directional many-to-one association to Room_type
	@ManyToOne
	@JoinColumn(name="room_type_code")
	private Room_type roomType;

	public Room() {
	}

	public String getRoomCode() {
		return this.roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getRoomNumber() {
		return this.roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public List<BookingDetail> getBookingDetails() {
		return this.bookingDetails;
	}

	public void setBookingDetails(List<BookingDetail> bookingDetails) {
		this.bookingDetails = bookingDetails;
	}

	public BookingDetail addBookingDetail(BookingDetail bookingDetail) {
		getBookingDetails().add(bookingDetail);
		bookingDetail.setRoom(this);

		return bookingDetail;
	}

	public BookingDetail removeBookingDetail(BookingDetail bookingDetail) {
		getBookingDetails().remove(bookingDetail);
		bookingDetail.setRoom(null);

		return bookingDetail;
	}

	public Room_type getRoomType() {
		return this.roomType;
	}

	public void setRoomType(Room_type roomType) {
		this.roomType = roomType;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}