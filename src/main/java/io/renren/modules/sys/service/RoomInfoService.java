package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.RoomInfoEntity;

import java.util.Map;

/**
 * 房间信息
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:57
 */
public interface RoomInfoService extends IService<RoomInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

