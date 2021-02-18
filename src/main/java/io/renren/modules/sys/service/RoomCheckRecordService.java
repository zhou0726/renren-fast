package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.RoomCheckRecordEntity;

import java.util.Map;

/**
 * 查房记录
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:56
 */
public interface RoomCheckRecordService extends IService<RoomCheckRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

