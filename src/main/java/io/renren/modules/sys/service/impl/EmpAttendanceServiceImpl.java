package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.EmpAttendanceDao;
import io.renren.modules.sys.entity.EmpAttendanceEntity;
import io.renren.modules.sys.service.EmpAttendanceService;


@Service("empAttendanceService")
public class EmpAttendanceServiceImpl extends ServiceImpl<EmpAttendanceDao, EmpAttendanceEntity> implements EmpAttendanceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<EmpAttendanceEntity> page = this.page(
                new Query<EmpAttendanceEntity>().getPage(params),
                new QueryWrapper<EmpAttendanceEntity>()
        );

        return new PageUtils(page);
    }

}