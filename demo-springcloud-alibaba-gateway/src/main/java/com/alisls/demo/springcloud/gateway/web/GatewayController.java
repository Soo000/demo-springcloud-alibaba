package com.alisls.demo.springcloud.gateway.web;

import com.alisls.demo.springcloud.gateway.entity.GatewayEntity;
import com.alisls.demo.springcloud.gateway.service.GatewayService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 路由管理
 *
 * @author Ke Wang
 */
@RestController
@RequestMapping("/gateway")
@AllArgsConstructor
public class GatewayController {

    private final GatewayService gatewayService;

    /**
     * 刷新路由
     * 从数据库中查询所有路由信息并加载到路由内存中
     */
    @GetMapping("/refreshGateway")
    public String refreshGateway() {
        return gatewayService.loadAllRoutes();
    }

    /**
     * 添加路由
     * 添加一条路由配置到路由配置中
     */
    @PostMapping("/addGateway")
    public String addGateway(@RequestBody GatewayEntity gatewayEntity) {
        return gatewayService.loadRoute(gatewayEntity);
    }

}
