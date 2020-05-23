package com.alisls.demo.springcloud.service.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户DTO，带有密码字段
 *
 * @author Ke Wang
 */
@ApiModel(description = "用户密码DTO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class UserPwdDTO extends UserDTO implements Serializable {

    @ApiModelProperty(value = "密码", example = "123456")
    private String password;

}
