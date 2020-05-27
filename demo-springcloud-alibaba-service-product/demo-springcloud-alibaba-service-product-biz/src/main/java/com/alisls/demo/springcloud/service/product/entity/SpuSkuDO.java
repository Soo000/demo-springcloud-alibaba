package com.alisls.demo.springcloud.service.product.entity;

import com.alisls.demo.springcloud.service.product.entity.keys.SpuSkuKey;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * SPU和SKU的关系实体
 *
 * @author Ke Wang
 */
@Table(name = "prd_spu_sku")
@Entity
public class SpuSkuDO {

    @EmbeddedId
    private SpuSkuKey spuSkuId;

    private Long num;

}
