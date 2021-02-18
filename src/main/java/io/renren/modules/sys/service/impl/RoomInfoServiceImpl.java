package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.RoomInfoDao;
import io.renren.modules.sys.entity.RoomInfoEntity;
import io.renren.modules.sys.service.RoomInfoService;


@Service("roomInfoService")
public class RoomInfoServiceImpl extends ServiceImpl<RoomInfoDao, RoomInfoEntity> implements RoomInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RoomInfoEntity> page = this.page(
                new Query<RoomInfoEntity>().getPage(params),
                new QueryWrapper<RoomInfoEntity>()
        );

        return new PageUtils(page);
    }

}