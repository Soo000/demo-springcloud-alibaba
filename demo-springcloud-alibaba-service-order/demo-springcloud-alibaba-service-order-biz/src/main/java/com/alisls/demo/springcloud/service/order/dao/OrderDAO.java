package com.alisls.demo.springcloud.service.order.dao;

import com.alisls.demo.springcloud.service.order.entity.OrderDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 订单DAO
 *
 * @author Ke Wang
 */
public interface OrderDAO extends JpaRepository<OrderDO, Long>, JpaSpecificationExecutor<OrderDO> {



}
