package com.alisls.demo.springcloud.service.product.entity;

import com.springcloud.common.model.entity.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品分类表
 *
 * @author Ke Wang
 */
@Table(name = "prd_cat")
@Entity
@Getter
@Setter
@ToString
public class CatDO extends BaseDO {

    @Id
    private Long id;

    private String catName;

}
