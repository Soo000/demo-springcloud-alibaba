package com.alisls.demo.springcloud.service.product.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * SpuDTO
 *
 * @author Ke Wang
 */
@Getter
@Setter
@ToString
public class SpuDTO implements Serializable {

    private Long id;

    private String spuName;

}
