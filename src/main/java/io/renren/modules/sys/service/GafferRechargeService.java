package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.GafferRechargeEntity;

import java.util.Map;

/**
 * 老人账户充值记录
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:57
 */
public interface GafferRechargeService extends IService<GafferRechargeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

