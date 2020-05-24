package com.alisls.demo.springcloud.oauth.server.service.impl;

import com.alisls.demo.springcloud.oauth.server.client.UserClient;
import com.alisls.demo.springcloud.service.user.dto.UserDTO;
import com.alisls.demo.springcloud.service.user.dto.UserPwdDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * 获取用户信息的实现
 *
 * @author Ke Wang
 */
@Service("userDetailsService")
@Log4j2
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("调用用户服务根据用户名查询用户信息");
        UserPwdDTO userDTO = (UserPwdDTO) userClient.getUserWithPwdByUsername(username);
        if (userDTO != null) {
            log.info("查询到用户信息{}", userDTO);
            String pwd = userDTO.getPassword();
            Set<GrantedAuthority> authorities = new HashSet<>();
            return new User(userDTO.getUsername(), passwordEncoder.encode(pwd), authorities);
        }
        return null;
    }

}
