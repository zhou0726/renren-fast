package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.EmpSalaryRecordDao;
import io.renren.modules.sys.entity.EmpSalaryRecordEntity;
import io.renren.modules.sys.service.EmpSalaryRecordService;


@Service("empSalaryRecordService")
public class EmpSalaryRecordServiceImpl extends ServiceImpl<EmpSalaryRecordDao, EmpSalaryRecordEntity> implements EmpSalaryRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<EmpSalaryRecordEntity> page = this.page(
                new Query<EmpSalaryRecordEntity>().getPage(params),
                new QueryWrapper<EmpSalaryRecordEntity>()
        );

        return new PageUtils(page);
    }

}