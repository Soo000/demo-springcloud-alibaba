package com.alisls.demo.springcloud.gateway.service.impl;

import com.alisls.demo.springcloud.gateway.dao.GatewayDAO;
import com.alisls.demo.springcloud.gateway.entity.GatewayEntity;
import com.alisls.demo.springcloud.gateway.service.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GatewayService
 *
 * @author Ke Wang
 */
@Service("gatewayService")
public class GatewayServiceImpl implements GatewayService, ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Autowired
    private GatewayDAO gatewayDAO;

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;

    @Override
    public String loadAllRoutes() {
        List<GatewayEntity> allRoutes = gatewayDAO.findAll();
        if (CollectionUtils.isEmpty(allRoutes)) {
            return "failed";
        }

        allRoutes.stream().forEach(gatewayEntity -> {
            loadRoute(gatewayEntity);
        });

        return "success";
    }

    @Override
    public String loadRoute(GatewayEntity gatewayEntity) {
        RouteDefinition definition = new RouteDefinition();
        Map<String, String> predicateParams = new HashMap<>(8);
        PredicateDefinition predicate = new PredicateDefinition();
        FilterDefinition filterDefinition = new FilterDefinition();
        Map<String, String> filterParams = new HashMap<>(8);
        URI uri = null;
        if ("0".equals(gatewayEntity.getRouteType())) {
            // 如果配置路由type为0的话 则从注册中心获取服务地址
            uri = UriComponentsBuilder.fromUriString("lb://" + gatewayEntity.getRouteUrl() + "/").build().toUri();
        } else {
            uri = UriComponentsBuilder.fromHttpUrl(gatewayEntity.getRouteUrl()).build().toUri();
        }

        // 定义的路由唯一的id
        definition.setId(gatewayEntity.getRouteId());
        predicate.setName("Path");
        // 路由转发地址
        predicateParams.put("pattern", gatewayEntity.getRoutePattern());
        predicate.setArgs(predicateParams);

        // 名称是固定的, 路径去前缀
        filterDefinition.setName("StripPrefix");
        filterParams.put("_genkey_0", "1");
        filterDefinition.setArgs(filterParams);
        definition.setPredicates(Arrays.asList(predicate));
        definition.setFilters(Arrays.asList(filterDefinition));
        definition.setUri(uri);
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        return "success";
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

}
