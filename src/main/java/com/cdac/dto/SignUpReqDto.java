package com.cdac.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignUpReqDto {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Long phoneNo;
	
}
