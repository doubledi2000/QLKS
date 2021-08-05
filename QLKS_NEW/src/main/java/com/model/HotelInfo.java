package com.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: HotelInfo
 *
 */
@Entity
@Table(name="hotel_info")
@NamedQuery(name="HotelInfo.findAll", query="SELECT b FROM HotelInfo b")
public class HotelInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private String id;

	private String fullname;

	@Column(name="phone_number")
	private String phoneNumber;
	private String email;
	@Column(name="email_password")
	private String emailPassword;
	@Column(name="bank_account_number")
	private String bankAccountNumber;
	@Column(name="logo")
	private String logo;
	
	private String addr;
	public HotelInfo() {
		super();
	}
	
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailPassword() {
		return emailPassword;
	}
	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}
	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	
   
}
