package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.RoomChangeEntity;

import java.util.Map;

/**
 * 房间变更
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:57
 */
public interface RoomChangeService extends IService<RoomChangeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

