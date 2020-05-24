package com.alisls.demo.springcloud.oauth.server.client;

import com.alisls.demo.springcloud.service.user.api.IUserService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户服务客户端
 *
 * @author Ke Wang
 */
@FeignClient(name = "demo-springcloud-alibaba-service-user")
public interface UserClient extends IUserService {
}
