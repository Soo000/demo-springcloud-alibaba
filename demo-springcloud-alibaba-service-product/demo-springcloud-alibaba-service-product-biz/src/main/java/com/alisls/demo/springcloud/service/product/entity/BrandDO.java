package com.alisls.demo.springcloud.service.product.entity;

import com.springcloud.common.model.entity.BaseDO;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 品牌表
 * 品牌与SPU的关系1:N
 *
 * @author Ke Wang
 */
@Table(name = "prd_brand")
@Entity
public class BrandDO extends BaseDO {

    @Id
    private Long id;

    private String brandName;
}
