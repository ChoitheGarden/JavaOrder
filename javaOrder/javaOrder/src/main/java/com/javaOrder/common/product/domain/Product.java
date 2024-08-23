package com.javaOrder.common.product.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.javaOrder.common.category.domain.Category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="product")
public class Product {
	@Id
	@Column(name="p_id")
	private String pId;
	
	@ManyToOne
	@JoinColumn(name="cate_code")
	private Category cateCode;
	
	@Column(name="p_order")
	@ColumnDefault(value="0")
	private int pOrder;
	
//	@Lob
	@Column(length=500, name="p_ex")
	private String pEx;
	
	@CreationTimestamp
	@Column(name="p_date", nullable=false)
	private LocalDateTime pDate;
	
	@Column(name="p_sell", nullable=false)
	@ColumnDefault(value="1")
	private int pSell;
	
	@Column(name="p_price", nullable=false)
	private int pPrice;
	
	@Column(length=50, name="p_name", nullable=false)
	private String pName;
}
