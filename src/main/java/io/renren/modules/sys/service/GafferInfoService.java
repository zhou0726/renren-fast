package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.GafferInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 老人信息
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:57
 */
public interface GafferInfoService extends IService<GafferInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<GafferInfoEntity> searchByKeyword(String keyword) throws Exception;
}

