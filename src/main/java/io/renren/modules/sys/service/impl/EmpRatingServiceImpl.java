package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.EmpRatingDao;
import io.renren.modules.sys.entity.EmpRatingEntity;
import io.renren.modules.sys.service.EmpRatingService;


@Service("empRatingService")
public class EmpRatingServiceImpl extends ServiceImpl<EmpRatingDao, EmpRatingEntity> implements EmpRatingService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<EmpRatingEntity> page = this.page(
                new Query<EmpRatingEntity>().getPage(params),
                new QueryWrapper<EmpRatingEntity>()
        );

        return new PageUtils(page);
    }

}