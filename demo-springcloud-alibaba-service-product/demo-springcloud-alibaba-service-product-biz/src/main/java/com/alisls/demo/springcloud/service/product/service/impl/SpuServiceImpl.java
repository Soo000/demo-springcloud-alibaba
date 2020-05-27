package com.alisls.demo.springcloud.service.product.service.impl;

import com.alisls.demo.springcloud.service.product.dao.SpuDAO;
import com.alisls.demo.springcloud.service.product.dto.SpuDTO;
import com.alisls.demo.springcloud.service.product.entity.SpuDO;
import com.alisls.demo.springcloud.service.product.service.SpuService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * SpuService
 *
 * @author Ke Wang
 */
@Service
@AllArgsConstructor
public class SpuServiceImpl implements SpuService {

    private final SpuDAO spuDAO;

    @Override
    public SpuDTO getSpuById(Long id) {
        SpuDTO spuDTO = new SpuDTO();
        spuDAO.findById(id).ifPresent(spuDO -> {
            BeanUtils.copyProperties(spuDO, spuDTO);
        });
        return spuDTO;
    }

}
