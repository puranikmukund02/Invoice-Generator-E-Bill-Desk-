package com.cdac.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "Item")
public class Item extends BaseEntity {

	@Column(name = "ItemName", nullable = false, length = 255)
	private String itemName;

	@Column(name = "Quantity", nullable = false)
	private Integer quantity;

	@Column(name = "Selling Price", nullable = false)
	private double sellingPrice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bill_id")
	private Bill bill;
}
