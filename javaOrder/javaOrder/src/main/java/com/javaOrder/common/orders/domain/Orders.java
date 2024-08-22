package com.javaOrder.common.orders.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Orders {
	@Id
	private Long ordNum;
	
	@ManyToOne
	@JoinColumn(name="m_code")
	private String mCode;
	
	@CreationTimestamp
	private LocalDateTime ordDate;
	
	@Column(nullable=false)
	private int ordPrice;
	
	@Column(nullable=false)
	@ColumnDefault(value="0")
	private String ordStatus;
}
