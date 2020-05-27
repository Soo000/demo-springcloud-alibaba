package com.alisls.demo.springcloud.gateway.service;

import com.alisls.demo.springcloud.gateway.entity.GatewayEntity;

import java.util.List;

/**
 * GatewayService
 *
 * @author Ke Wang
 */
public interface GatewayService {

    String loadAllRoutes();

    String loadRoute(GatewayEntity gatewayEntity);

}
