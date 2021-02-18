package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.RoomCheckRecordDao;
import io.renren.modules.sys.entity.RoomCheckRecordEntity;
import io.renren.modules.sys.service.RoomCheckRecordService;


@Service("roomCheckRecordService")
public class RoomCheckRecordServiceImpl extends ServiceImpl<RoomCheckRecordDao, RoomCheckRecordEntity> implements RoomCheckRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RoomCheckRecordEntity> page = this.page(
                new Query<RoomCheckRecordEntity>().getPage(params),
                new QueryWrapper<RoomCheckRecordEntity>()
        );

        return new PageUtils(page);
    }

}