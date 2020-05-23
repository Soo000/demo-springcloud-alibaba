package com.alisls.demo.springcloud.service.user.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sample Controller
 *
 * @author Ke Wang
 */
@RefreshScope
@Getter
@Setter
@RestController
@RequestMapping("/sample")
public class SampleController {

    @Value("${version}")
    private String version;

    @GetMapping("/getVersion")
    public String getVersion() {
        return "version = " + version;
    }

}
