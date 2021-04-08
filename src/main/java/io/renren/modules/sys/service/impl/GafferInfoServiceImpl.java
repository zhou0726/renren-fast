package io.renren.modules.sys.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.GafferInfoDao;
import io.renren.modules.sys.entity.GafferInfoEntity;
import io.renren.modules.sys.service.GafferInfoService;


@Service("gafferInfoService")
public class GafferInfoServiceImpl extends ServiceImpl<GafferInfoDao, GafferInfoEntity> implements GafferInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String gafferIdListStr = (String) params.get("gafferIdList");
        QueryWrapper<GafferInfoEntity> whereParams = new QueryWrapper<>();
        if (StringUtils.isNotBlank(gafferIdListStr)) {
            String[] gafferIds = gafferIdListStr.split(",");
            whereParams.in("id", Arrays.stream(gafferIds).toArray());
        }
        IPage<GafferInfoEntity> page = this.page(
                new Query<GafferInfoEntity>().getPage(params),
                whereParams
        );

        return new PageUtils(page);
    }

    @Override
    public List<GafferInfoEntity> searchByKeyword(String keyword) throws Exception {
        if (StringUtils.isBlank(keyword)) {
            throw new Exception("关键字为空");
        }
        QueryWrapper<GafferInfoEntity> whereParams = new QueryWrapper<>();
        whereParams.like("name",keyword);
        whereParams.or().like("telephone",keyword);
        return this.list(whereParams);
    }

}