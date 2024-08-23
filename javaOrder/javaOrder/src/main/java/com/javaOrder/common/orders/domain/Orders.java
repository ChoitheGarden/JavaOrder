package com.javaOrder.common.orders.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.javaOrder.member.domain.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Entity
@Table(name="orders")
@SequenceGenerator(name="orders_generator", sequenceName="orders_seq", initialValue = 10001, allocationSize = 1)
public class Orders {
	@Id
	@Column(name="ord_num")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_generator")
	private Long ordNum;
	
	@ManyToOne
	@JoinColumn(name="m_code")
	private Member mCode;
	
	@CreationTimestamp
	@Column(name="ord_date", nullable=false)
	private LocalDateTime ordDate;
	
	@Column(name="ord_price", nullable=false)
	private int ordPrice;
	
	@Column(name="ord_status", columnDefinition = "CHAR(1) DEFAULT 'W'", nullable=false)
	@ColumnDefault(value="W")
	private String ordStatus;
}

/**********************************************************************************
 * CREATE TABLE Orders (
	    ord_num NUMBER NOT NULL,
	    m_code VARCHAR2(20) NOT NULL,
	    ord_date DATE DEFAULT sysdate NOT NULL,
	    ord_price NUMBER NOT NULL,
	    ord_status CHAR(1) NOT NULL,
	    CONSTRAINT pk_orders PRIMARY KEY (ord_num),
	    CONSTRAINT fk_orders_member FOREIGN KEY (m_code) REFERENCES Member(m_code)
	);
 **********************************************************************************/