package com.alisls.demo.springcloud.service.order.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * OrderDO
 *
 * @author Ke Wang
 */
@Entity
@Table(name = "ord_order")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDO {

	@Id
	private Long id;

	@NotBlank
	private String orderNo;

	private String orderName;
	
}
