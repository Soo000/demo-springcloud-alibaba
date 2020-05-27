package com.alisls.demo.springcloud.service.product.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SKU值（SKU与SKU值是一个一对多的关系）
 */
@Table(name = "prd_sku_val")
@Entity
@Getter
@Setter
@ToString
public class SkuValDO {

    @Id
    private Long id;

    private Long skuId;

    private String val;

}
