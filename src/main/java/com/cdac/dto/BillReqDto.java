package com.cdac.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BillReqDto {

	private String invoiceNo;
	private Date date;
	private String customerName;
	private Integer quantity;
//	private List<ItemDto> items;
	private Double amount;
	// Getters and Setters

	
}
