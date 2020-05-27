package com.alisls.demo.springcloud.service.product.web;

import com.alisls.demo.springcloud.service.product.dto.SpuDTO;
import com.alisls.demo.springcloud.service.product.service.SpuService;
import com.springcloud.common.model.dto.DataResult;
import com.springcloud.common.model.dto.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spu管理
 *
 * @author Ke Wang
 */
@Api(tags = "商品管理")
@RestController
@RequestMapping("/spu")
@AllArgsConstructor
public class SpuController {

    private final SpuService spuService;

    /**
     * 根据商品标识查询商品
     */
    @ApiOperation(value = "商品查询", notes = "根据商品标识查询商品")
    @ApiImplicitParam(
            name = "id",
            required = true,
            paramType = "path",
            dataType = "Long",
            example = "1234567890"
    )
    @RequestMapping("/getSpuById/{id}")
    public Result getSpuById(@PathVariable Long id) {
        SpuDTO spuDTO = spuService.getSpuById(id);
        return DataResult.ofSuccess(spuDTO);
    }

}
