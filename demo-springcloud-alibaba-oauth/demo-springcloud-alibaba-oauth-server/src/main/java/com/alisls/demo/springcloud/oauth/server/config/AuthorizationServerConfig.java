package com.alisls.demo.springcloud.oauth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;

import javax.sql.DataSource;

/**
 * 认证服务器配置类
 * 注解@EnableAuthorizationServer开启了认证服务器功能
 *
 * @author Ke Wang
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 加解密类
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 数据源
     */
    @Autowired
    private DataSource dataSource;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 授权码管理策略
     * 向容器注入JdbcAuthorizationCodeServices，用来用Jdbc管理授权码
     */
    @Bean
    public AuthorizationCodeServices jdbcAuthorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    /**
     * 客户端管理策略
     * 向容器注入JdbcClientDetailsService类实例，用Jdbc方式管理客户端信息，默认客户端表为oauth_client_details
     */
    @Bean
    public ClientDetailsService jdbcClientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * 配置认证服务器端点
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 设置授权码管理策略为jdbc, 授权码会保存在表oauth_code中（如果授权码被使用，对应的数据就会被删除）
        endpoints.authorizationCodeServices(jdbcAuthorizationCodeServices());

        // 密码模式需要向端点配置 authenticationManager
        endpoints.authenticationManager(authenticationManager);
    }

    /**
     * 配置客户端信息存储方式：数据库方式
     * 数据库方式配置被允许访问此认证服务器的客户端信息
     * 客户端信息配置方式有以下两种：
     *   1）内存方式
     *   2）数据库方式
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 设置用jdbc管理客户端
        clients.withClientDetails(jdbcClientDetailsService());
    }

    /**
     * 令牌端点的安全配置
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 设置检查令牌端点认证后可用
        security.checkTokenAccess("isAuthenticated()");
    }

}

