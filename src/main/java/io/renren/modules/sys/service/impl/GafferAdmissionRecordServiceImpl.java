package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.GafferAdmissionRecordDao;
import io.renren.modules.sys.entity.GafferAdmissionRecordEntity;
import io.renren.modules.sys.service.GafferAdmissionRecordService;


@Service("gafferAdmissionRecordService")
public class GafferAdmissionRecordServiceImpl extends ServiceImpl<GafferAdmissionRecordDao, GafferAdmissionRecordEntity> implements GafferAdmissionRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GafferAdmissionRecordEntity> page = this.page(
                new Query<GafferAdmissionRecordEntity>().getPage(params),
                new QueryWrapper<GafferAdmissionRecordEntity>()
        );

        return new PageUtils(page);
    }

}