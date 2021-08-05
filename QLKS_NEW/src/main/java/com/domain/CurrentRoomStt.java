package com.domain;

import java.util.Date;

public class CurrentRoomStt {
	private String room_number;
	private String room_type_name;
	private String fullname;
	private int room_stt;
	private Date timeActive;
	
	
	public CurrentRoomStt() {
		
	}
	public CurrentRoomStt(String room_number, int room_stt, String room_type_name, String fullname, Date timeActive) {
		
		this.room_number = room_number;
		this.room_type_name = room_type_name;
		this.fullname = fullname;
		this.room_stt = room_stt;
		this.timeActive = timeActive;
	}
	public String getRoom_number() {
		return room_number;
	}
	public void setRoom_number(String room_number) {
		this.room_number = room_number;
	}
	public String getRoom_type_name() {
		return room_type_name;
	}
	public void setRoom_type_name(String room_type_name) {
		this.room_type_name = room_type_name;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public int getRoom_stt() {
		return room_stt;
	}
	public void setRoom_stt(int room_stt) {
		this.room_stt = room_stt;
	}
	public Date getTimeActive() {
		return timeActive;
	}
	public void setTimeActive(Date timeActive) {
		this.timeActive = timeActive;
	}
	
	
}
