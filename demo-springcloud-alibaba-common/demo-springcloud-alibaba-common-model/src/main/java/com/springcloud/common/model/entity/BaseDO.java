package com.springcloud.common.model.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * BaseDO类
 *
 * @author Ke Wang
 */
@Getter
@Setter
public class BaseDO {

    private Date gmtCreate;
    private Date gmtModified;

}
