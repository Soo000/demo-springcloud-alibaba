package com.alisls.demo.springcloud.service.order.service.impl;

import com.alisls.demo.springcloud.service.order.dao.OrderDAO;
import com.alisls.demo.springcloud.service.order.entity.OrderDO;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alisls.demo.springcloud.service.order.dto.OrderDTO;
import com.alisls.demo.springcloud.service.order.service.OrderService;

import java.util.Optional;

/**
 * 订单服务
 *
 * @author Ke Wang
 */
@Service("orderService")
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;

    /**
     * 根据用户标识查询用户
     */
	@Override
	public OrderDTO getOrder(Long id) {
	    OrderDTO orderDTO = new OrderDTO();
        orderDAO.findById(id).ifPresent(orderDO -> {
            BeanUtils.copyProperties(orderDO, orderDTO);
        });
        return orderDTO;
	}
	
}
