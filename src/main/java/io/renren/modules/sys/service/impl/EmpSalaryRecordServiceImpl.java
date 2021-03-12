package io.renren.modules.sys.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
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
        List<Long> empIdList = (List<Long>) params.get("empIdList");
        String recordDate = (String) params.get("recordDate");
        QueryWrapper<EmpSalaryRecordEntity> whereParams = new QueryWrapper<>();
        if (CollectionUtil.isNotEmpty(empIdList)) {
            whereParams.in("emp_id",empIdList);
        }
        if (StringUtils.isNotBlank(recordDate)) {
            whereParams.eq("record_date",recordDate);
        }
        IPage<EmpSalaryRecordEntity> page = this.page(
                new Query<EmpSalaryRecordEntity>().getPage(params),
                whereParams
        );

        return new PageUtils(page);
    }

}