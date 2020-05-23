package com.alisls.demo.springcloud.service.order.service;

import com.alisls.demo.springcloud.service.order.dto.OrderDTO;

/**
 * 订单Service
 *
 * @author Ke Wang
 */
public interface OrderService {

	OrderDTO getOrder(Long id);
	
}
