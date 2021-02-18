package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.GafferPaymentRecordDao;
import io.renren.modules.sys.entity.GafferPaymentRecordEntity;
import io.renren.modules.sys.service.GafferPaymentRecordService;


@Service("gafferPaymentRecordService")
public class GafferPaymentRecordServiceImpl extends ServiceImpl<GafferPaymentRecordDao, GafferPaymentRecordEntity> implements GafferPaymentRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GafferPaymentRecordEntity> page = this.page(
                new Query<GafferPaymentRecordEntity>().getPage(params),
                new QueryWrapper<GafferPaymentRecordEntity>()
        );

        return new PageUtils(page);
    }

}