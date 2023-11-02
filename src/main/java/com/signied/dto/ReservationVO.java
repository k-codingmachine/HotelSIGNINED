package com.signied.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReservationVO {

	private int reserveNum;
	private String reserveEmail;
	private String reservePwd;
	private String reserveName;
	private String reservePhone;
	private String checkIn;
	private String checkOut;
	private int guestNum;
	private int breakfast;
	private int roomNum;
}
