package com.alisls.demo.springcloud.service.user.api;

import com.alisls.demo.springcloud.service.user.dto.UserDTO;
import com.alisls.demo.springcloud.service.user.dto.UserPwdDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 暴露的用户服务
 *
 * @author Ke Wang
 */
@RequestMapping("/user")
public interface IUserService {

	@GetMapping("/getUserByUsername/{username}")
	UserDTO getUserByUsername(@PathVariable("username") String username);

	@GetMapping("/getUserWithPwdByUsername/{username}")
    UserPwdDTO getUserWithPwdByUsername(@PathVariable("username") String username);
	
}
