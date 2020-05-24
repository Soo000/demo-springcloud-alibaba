package com.alisls.demo.springcloud.service.user.web;

import com.alisls.demo.springcloud.service.order.dto.OrderDTO;
import com.alisls.demo.springcloud.service.user.client.order.OrderClient;
import com.alisls.demo.springcloud.service.user.dto.UserDTO;
import com.alisls.demo.springcloud.service.user.service.UserService;
import com.alisls.demo.springcloud.service.user.vo.UserOrderVO;
import com.springcloud.common.model.dto.DataResult;
import com.springcloud.common.model.dto.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户管理
 *
 * @author Ke Wang
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Log4j2
public class UserController {

    /**
     * 用户服务
     */
    private final UserService userService;

    /**
     * 订单服务Feign客户端
     */
    private final OrderClient orderClient;

    /**
     * 根据用户标识查询用户
     */
    @ApiOperation(value = "查询用户", notes = "根据用户标识查询用户")
    @ApiImplicitParam(
            name = "id",
            required = true,
            paramType = "path",
            dataType = "Integer",
            example = "1234567890123456789"
    )
	@GetMapping("/getUserById/{id}")
	public Result getUserById(@PathVariable Long id) {
		UserDTO userDTO = userService.getUser(id);
		return DataResult.ofSuccess(userDTO);
	}

    /**
     * 根据用户名查询用户
     */
    @ApiOperation(value = "查询用户", notes = "根据用户名查询用户")
    @ApiImplicitParam(
            name = "username",
            required = true,
            paramType = "path",
            dataType = "String",
            example = "wangke"
    )
	@GetMapping("/getUserByUsername/{username}")
	public ResponseEntity<UserDTO> getUserByName(@PathVariable String username) {
        UserDTO userDTO = userService.getUser(username);
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}

    /**
     * 根据用户名查询用户（用户信息带有密码信息）
     */
    @ApiOperation(value = "查询用户", notes = "根据用户名查询用户（用户信息带有密码信息")
    @ApiImplicitParam(
            name = "username",
            required = true,
            paramType = "path",
            dataType = "String",
            example = "wangke"
    )
    @GetMapping("/getUserWithPwdByUsername/{username}")
    public ResponseEntity<UserDTO> getUserWithPwdByUsername(@PathVariable String username) {
        UserDTO userDTO = userService.getUserWithPwd(username);
        return ResponseEntity.ok(userDTO);
    }

	/**
	 * 根据用户标识查询用户和订单信息(使用了Hystrix方法级的服务降级配置commandProperties)
	 */
	@ApiOperation(value = "查询用户订单", notes = "根据用户标识查询用户和订单信息")
    @ApiImplicitParam(
            name = "userId",
            required = true,
            paramType = "path",
            dataType = "Long",
            example = "1234567890123456789"
    )
	@GetMapping("/getUserOrder/{userId}")
	public Result getUserOrder(@PathVariable Long userId) {
	    // 查询用户信息
		UserDTO userDTO = userService.getUser(userId);
        // 查询订单信息
        OrderDTO orderDTO = orderClient.getOrderById(1L);

        // 返回数据
        return DataResult.ofSuccess(new UserOrderVO(userDTO, orderDTO));
	}

    /**
     * 查询用户订单降级方法
     */
	public Result getUserOrderFallback(@PathVariable Long id) {
		log.info("根据用户标识{}查询用户和订单信息接口调用超时", id);
		return Result.ofTimeout();
	}

	/**
	 * 保存用户
	 */
	@PostMapping("/save")
	@ApiOperation(value = "保存用户", notes = "保存用户信息")
	public Result saveUser(@RequestBody @Valid UserDTO userDTO) {
		userDTO = userService.save(userDTO);
		return DataResult.ofSuccess(userDTO);
	}

    /**
     * 修改用户
     * @param userDTO
     */
    @ApiOperation(value = "修改用户", notes = "修改用户信息")
	@PutMapping("/update/{id}")
	public Result updateUser(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO) {
        userDTO.setId(id);
        userDTO = userService.update(userDTO);
        return DataResult.ofSuccess(userDTO);
    }
	
}
