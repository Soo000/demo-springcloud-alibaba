package com.alisls.demo.springcloud.oauth.server.util;

import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * PasswordEncoderUtil 工具测试类
 *
 * @author Ke Wang
 */
@Log4j2
public class PasswordEncoderUtilTest {

    private PasswordEncoder passwordEncoder;

    @Before
    public void init() {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Test
    public void encode() {
        String sourcePassword = "123456";
        String encodedPassword = passwordEncoder.encode(sourcePassword);
        log.info("原始密码：{}，加密后：{}", sourcePassword, encodedPassword);
    }

}
