package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.GafferPaymentEntity;

import java.util.Map;

/**
 * 老人额外消费
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:56
 */
public interface GafferPaymentService extends IService<GafferPaymentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

