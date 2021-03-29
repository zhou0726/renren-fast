package io.renren.modules.sys.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import io.renren.modules.sys.entity.GafferInfoEntity;
import io.renren.modules.sys.service.GafferInfoService;
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

import io.renren.modules.sys.dao.GafferAdmissionRecordDao;
import io.renren.modules.sys.entity.GafferAdmissionRecordEntity;
import io.renren.modules.sys.service.GafferAdmissionRecordService;


@Service("gafferAdmissionRecordService")
public class GafferAdmissionRecordServiceImpl extends ServiceImpl<GafferAdmissionRecordDao, GafferAdmissionRecordEntity> implements GafferAdmissionRecordService {

    @Autowired
    private GafferInfoService gafferInfoService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        QueryWrapper<GafferInfoEntity> whereParams = new QueryWrapper<>();
        QueryWrapper<GafferAdmissionRecordEntity> gafferParams = new QueryWrapper<>();
        if (StringUtils.isNotBlank(key)) {
            whereParams.like("name",key);
            List<GafferInfoEntity> gafferInfoEntityList = gafferInfoService.list(whereParams);
            if (CollectionUtil.isNotEmpty(gafferInfoEntityList)) {
                gafferParams.in("gaffer_id",gafferInfoEntityList.stream().map(item -> item.getId()).collect(Collectors.toList()));
            }
        }
        IPage<GafferAdmissionRecordEntity> page = this.page(
                new Query<GafferAdmissionRecordEntity>().getPage(params),
                gafferParams
        );

        return new PageUtils(page);
    }

}