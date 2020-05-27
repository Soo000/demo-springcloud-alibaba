package com.alisls.demo.springcloud.gateway.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 自定义全局过滤器
 *
 * @author Ke Wang
 */
@Log4j2
public class CustomFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("执行了全局过滤器方法CustomFilter#filter");
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        log.info("headers = {}", headers);

        List<String> authList = headers.get("Authorization");
        if (CollectionUtils.isEmpty(authList)) {
            throw new RuntimeException("Token数据异常！");
        }

        String token = authList.get(0);
        log.info("从参数中获取Token值{}", token);

        MultiValueMap<String, String> queryParams = request.getQueryParams();
        log.info("queryParams = {}", queryParams);

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }

}
