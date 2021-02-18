package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.EmpInfoDao;
import io.renren.modules.sys.entity.EmpInfoEntity;
import io.renren.modules.sys.service.EmpInfoService;


@Service("empInfoService")
public class EmpInfoServiceImpl extends ServiceImpl<EmpInfoDao, EmpInfoEntity> implements EmpInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<EmpInfoEntity> page = this.page(
                new Query<EmpInfoEntity>().getPage(params),
                new QueryWrapper<EmpInfoEntity>()
        );

        return new PageUtils(page);
    }

}