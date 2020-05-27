package com.alisls.demo.springcloud.gateway.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
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
@Component
@Log4j2
public class CustomFilter implements GlobalFilter, Ordered {

    @Value("${server.port}")
    private int serverPort;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("执行了全局过滤器方法CustomFilter#filter");
        ServerHttpRequest request = exchange.getRequest();

        HttpHeaders headers = request.getHeaders();
        log.info("headers = {}", headers);

        List<String> authList = headers.get("Authorization");
        if (CollectionUtils.isEmpty(authList)) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);

            String msg = "Token 不能为空！";
            DataBuffer buffer = response.bufferFactory().wrap(msg.getBytes());
            return response.writeWith(Mono.just(buffer));
        }

        String token = authList.get(0);
        log.info("从参数中获取Token值{}", token);

        MultiValueMap<String, String> queryParams = request.getQueryParams();
        log.info("queryParams = {}", queryParams);

        // 在请求头中存放端口
        request = request.mutate().header("serverPort", String.valueOf(serverPort)).build();
        return chain.filter(exchange.mutate().request(request).build());
    }

    @Override
    public int getOrder() {
        return -1;
    }

}
