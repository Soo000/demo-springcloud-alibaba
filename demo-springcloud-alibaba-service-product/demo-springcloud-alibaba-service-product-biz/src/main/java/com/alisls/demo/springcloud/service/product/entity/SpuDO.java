package com.alisls.demo.springcloud.service.product.entity;

import com.springcloud.common.model.entity.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SPU(Standard Product Unit) 标准化产品单元
 *
 * @author Ke Wang
 */
@Table(name = "prd_spu")
@Entity
@Getter
@Setter
@ToString
public class SpuDO extends BaseDO {

    @Id
    private Long id;

    private String spuName;

}
