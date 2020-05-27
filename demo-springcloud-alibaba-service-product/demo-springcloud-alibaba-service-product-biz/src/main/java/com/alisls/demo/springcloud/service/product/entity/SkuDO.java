package com.alisls.demo.springcloud.service.product.entity;

import com.springcloud.common.model.entity.BaseDO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SKU(Stock Keeping Unit)库存量单元
 *
 * @author Ke Wang
 */
@Table(name = "prd_sku")
@Entity
@Getter
@Setter
public class SkuDO extends BaseDO {

    @Id
    private Long id;

    private String skuName;

}
