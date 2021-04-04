package io.renren.modules.sys.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        QueryWrapper<EmpInfoEntity> whereParams = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (key != null && key.length() > 0) {
            whereParams.like("name", key);
            whereParams.or().like("department",key);
            whereParams.or().like("job",key);
            whereParams.or().like("description",key);
        }
        whereParams.orderByDesc("status").orderByAsc("id");
        IPage<EmpInfoEntity> page = this.page(
                new Query<EmpInfoEntity>().getPage(params),
                whereParams
        );

        return new PageUtils(page);
    }

    @Override
    public List<EmpInfoEntity> searchByKeyword(String keyword) throws Exception {
        if (keyword == null || keyword.length()==0) {
            throw new Exception("关键字不能为空");
        }
        QueryWrapper<EmpInfoEntity> whereParams = new QueryWrapper<>();
        whereParams.like("name",keyword).or().like("no",keyword).orderByDesc("status").orderByAsc("id");
        return this.list(whereParams);
    }

    @Override
    public List<EmpInfoEntity> searchByKeywordForSalary(String keyword) throws Exception {
        if (keyword == null || keyword.length()==0) {
            throw new Exception("关键字不能为空");
        }
        // 最后入职时间
        DateTime lastOnboardTime = DateUtil.beginOfMonth(new Date());
        // 最后离职时间
        DateTime lastResignTime = DateUtil.beginOfMonth(DateUtil.lastMonth());
        QueryWrapper<EmpInfoEntity> whereParams = new QueryWrapper<>();
        whereParams.gt("onboardTime",lastOnboardTime).and(qw -> qw.lt("resignTime",lastResignTime).or().eq("status",1));
        whereParams.like("name",keyword).or().like("no",keyword).orderByAsc("id");
        return this.list(whereParams);
    }

    @Override
    public void resign(Long[] ids) throws Exception {
        List<EmpInfoEntity> empInfoEntityList = new ArrayList<>();
        for (Long id : ids) {
            EmpInfoEntity empInfoEntity = new EmpInfoEntity();
            empInfoEntity.setStatus(0);
            empInfoEntity.setId(id);
            empInfoEntity.setResignTime(DateUtil.date());
            empInfoEntityList.add(empInfoEntity);
        }
        this.updateBatchById(empInfoEntityList);
    }

}