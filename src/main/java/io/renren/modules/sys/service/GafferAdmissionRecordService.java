package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.GafferAdmissionRecordEntity;

import java.util.Map;

/**
 * 老人出入院记录
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:56
 */
public interface GafferAdmissionRecordService extends IService<GafferAdmissionRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

