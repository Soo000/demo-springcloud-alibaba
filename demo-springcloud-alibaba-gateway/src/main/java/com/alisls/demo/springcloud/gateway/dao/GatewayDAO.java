package com.alisls.demo.springcloud.gateway.dao;

import com.alisls.demo.springcloud.gateway.entity.GatewayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 网关DAO
 *
 * @author Ke Wang
 */
public interface GatewayDAO extends JpaRepository<GatewayEntity, String>, JpaSpecificationExecutor<GatewayEntity> {
}
