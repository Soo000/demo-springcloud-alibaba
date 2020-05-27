package com.alisls.demo.springcloud.service.product.dao;

import com.alisls.demo.springcloud.service.product.entity.SpuDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * SpuDAO
 *
 * @author Ke Wang
 */
public interface SpuDAO extends JpaRepository<SpuDO, Long>, JpaSpecificationExecutor<SpuDO> {
}
