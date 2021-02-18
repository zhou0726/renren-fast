package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.GafferPaymentDao;
import io.renren.modules.sys.entity.GafferPaymentEntity;
import io.renren.modules.sys.service.GafferPaymentService;


@Service("gafferPaymentService")
public class GafferPaymentServiceImpl extends ServiceImpl<GafferPaymentDao, GafferPaymentEntity> implements GafferPaymentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GafferPaymentEntity> page = this.page(
                new Query<GafferPaymentEntity>().getPage(params),
                new QueryWrapper<GafferPaymentEntity>()
        );

        return new PageUtils(page);
    }

}