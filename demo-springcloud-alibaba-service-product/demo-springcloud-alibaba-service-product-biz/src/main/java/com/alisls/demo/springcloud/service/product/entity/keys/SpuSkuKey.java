package com.alisls.demo.springcloud.service.product.entity.keys;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Spu和Sku的联合主键
 *
 * @author Ke Wang
 */
@Embeddable
@ToString
public class SpuSkuKey implements Serializable {

    @Column(name = "spu_id")
    private Long spuId;

    @Column(name = "sku_id")
    private Long skuId;

}
