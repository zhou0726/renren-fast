package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;

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
        whereParams.like("name",keyword).or().like("no",keyword);
        return this.list(whereParams);
    }

}