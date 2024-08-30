package com.javaOrder.common.category.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="category")
public class Category {
	@Id
	@Column(length=4, name="cate_code")
	private String cateCode;
	
	@Column(length=50, nullable=false, name="cate_name")
	private String cateName;
}
