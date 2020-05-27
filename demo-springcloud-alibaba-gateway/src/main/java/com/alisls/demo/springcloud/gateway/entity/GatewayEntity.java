package com.alisls.demo.springcloud.gateway.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 网关实体
 *
 * @author Ke Wang
 */
@Table(name = "gat_gateway")
@Entity
@Getter
@Setter
public class GatewayEntity {

    /**
     * 路由标识
     */
    @Id
    private String routeId;

    /**
     * 路由名称
     */
    private String routeName;

    /**
     * 路由URL
     */
    private String routeUrl;

    /**
     * 路由匹配规则
     */
    private String routePattern;

    /**
     * 路由类型
     */
    private String routeType;

}
