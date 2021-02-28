package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.FinancePaymentDao;
import io.renren.modules.sys.entity.FinancePaymentEntity;
import io.renren.modules.sys.service.FinancePaymentService;


@Service("financePaymentService")
public class FinancePaymentServiceImpl extends ServiceImpl<FinancePaymentDao, FinancePaymentEntity> implements FinancePaymentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        QueryWrapper<FinancePaymentEntity> whereParams = new QueryWrapper<FinancePaymentEntity>();
        if (params.get("key") != null) {
            whereParams.like(params.get("key") != null, "type", "%" + params.get("key") + "%");
            whereParams.or().like(params.get("key") != null, "content", "%" + params.get("key") + "%");
        }
        IPage<FinancePaymentEntity> page = this.page(
                new Query<FinancePaymentEntity>().getPage(params),whereParams
        );
        return new PageUtils(page);
    }

}