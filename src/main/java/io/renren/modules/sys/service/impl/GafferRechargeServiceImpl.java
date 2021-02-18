package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.GafferRechargeDao;
import io.renren.modules.sys.entity.GafferRechargeEntity;
import io.renren.modules.sys.service.GafferRechargeService;


@Service("gafferRechargeService")
public class GafferRechargeServiceImpl extends ServiceImpl<GafferRechargeDao, GafferRechargeEntity> implements GafferRechargeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GafferRechargeEntity> page = this.page(
                new Query<GafferRechargeEntity>().getPage(params),
                new QueryWrapper<GafferRechargeEntity>()
        );

        return new PageUtils(page);
    }

}