package com.alisls.demo.springcloud.service.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * 资源服务器配置类
 * 注解 @EnableResourceServer 开启资源服务器（获取资源服务器中的资源，必须带有token才能访问）
 * 注解 @EnableGlobalMethodSecurity(prePostEnabled = true) 开启方法级别权限控制
 *
 * @author Ke Wang
 */
@Configuration
@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    /**
     * 资源标识
     */
    private static final String RESOURCE_ID = "demo-springcloud-service-all";

    /**
     * 客户端标识
     */
    @Value("${oauth.client-id}")
    private String clientId;

    /**
     * 客户端密码
     */
    @Value("${oauth.secret}")
    private String secret;

    /**
     * Token验证地址
     */
    @Value("${oauth.check-token-url}")
    private String checkTokenUrl;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        /*
         * 设置当前资源服务器的ID；
         * 认证服务会认证客户端有没有访问这个资源ID的权限
         */
        resources.resourceId(RESOURCE_ID)
                .tokenServices(tokenService());
    }

    /**
     * 建一个远程TokenService（即连接到认证服务器的进行token校验的服务）
     * @return ResourceServerTokenServices
     */
    public ResourceServerTokenServices tokenService() {
        // 创建一个远程TokenService（即连接到认证服务器的进行token校验的服务），需求设置该端点认证后可访问
        RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl(checkTokenUrl);
        tokenService.setClientId(clientId);
        tokenService.setClientSecret(secret);
        return tokenService;
    }

    /**
     * 控制令牌范围权限和授权规则
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 设定Session策略为不创建HttpSession实例
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 设置资源访问授权规则
        http.authorizeRequests()
                // 设置访问资源/user/getUserWithPwdByUsername/*可以匿名访问
                .antMatchers("/user/getUserWithPwdByUsername/*").anonymous()
                // 设置访问资源/product/listProducts需要权限product:listProducts（注意顺序，不能放到后面来写）
                //.antMatchers("/product/listProducts").hasAuthority("product:listProducts")
                // 设定资源服务器要求所有请求都必须有all范围(在认证服务器上针对客户端配置的范围)
                .antMatchers("/**").access("#oauth2.hasScope('all')");
    }


}

