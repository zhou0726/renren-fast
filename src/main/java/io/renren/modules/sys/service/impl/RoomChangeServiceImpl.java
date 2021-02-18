package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.RoomChangeDao;
import io.renren.modules.sys.entity.RoomChangeEntity;
import io.renren.modules.sys.service.RoomChangeService;


@Service("roomChangeService")
public class RoomChangeServiceImpl extends ServiceImpl<RoomChangeDao, RoomChangeEntity> implements RoomChangeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RoomChangeEntity> page = this.page(
                new Query<RoomChangeEntity>().getPage(params),
                new QueryWrapper<RoomChangeEntity>()
        );

        return new PageUtils(page);
    }

}