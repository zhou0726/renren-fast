package io.renren.modules.sys.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import io.renren.modules.sys.dao.EmpInfoDao;
import io.renren.modules.sys.entity.EmpInfoEntity;
import io.renren.modules.sys.service.EmpInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private EmpInfoDao empInfoDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        QueryWrapper<EmpAttendanceEntity> whereParams = new QueryWrapper<>();
        if (StringUtils.isNotBlank(key)) {
            QueryWrapper<EmpInfoEntity> empParams = new QueryWrapper<>();
            empParams.like("name",key);
            List<EmpInfoEntity> empInfoEntityList = empInfoDao.selectList(empParams);
            if (CollectionUtil.isNotEmpty(empInfoEntityList)) {
                List<Long> empIdList = empInfoEntityList.parallelStream().map(item -> item.getId()).collect(Collectors.toList());
                whereParams.in("emp_id",empIdList);
            } else {
                return new PageUtils(null);
            }
        }
        IPage<EmpAttendanceEntity> page = this.page(
                new Query<EmpAttendanceEntity>().getPage(params),
                whereParams
        );

        return new PageUtils(page);
    }

}