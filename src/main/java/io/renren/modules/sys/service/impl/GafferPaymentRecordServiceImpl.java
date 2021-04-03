package io.renren.modules.sys.service.impl;

import io.renren.modules.sys.entity.GafferInfoEntity;
import io.renren.modules.sys.service.GafferInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.GafferPaymentRecordDao;
import io.renren.modules.sys.entity.GafferPaymentRecordEntity;
import io.renren.modules.sys.service.GafferPaymentRecordService;
import org.springframework.util.CollectionUtils;


@Service("gafferPaymentRecordService")
public class GafferPaymentRecordServiceImpl extends ServiceImpl<GafferPaymentRecordDao, GafferPaymentRecordEntity> implements GafferPaymentRecordService {
    @Autowired
    private GafferInfoService gafferInfoService;
    @Autowired
    private GafferPaymentRecordService gafferPaymentRecordService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String keyword = (String) params.get("key");
        QueryWrapper<GafferPaymentRecordEntity> whereParams = new QueryWrapper<>();
        if (StringUtils.isNotBlank(keyword)) {
            QueryWrapper<GafferInfoEntity> gafferParams = new QueryWrapper<>();
            gafferParams.like("name",keyword);
            List<GafferInfoEntity> gafferInfoEntityList = gafferInfoService.list(gafferParams);
            if (!CollectionUtils.isEmpty(gafferInfoEntityList)) {
                List<Long> gafferIdList = gafferInfoEntityList.stream().map(gaffer -> gaffer.getId()).collect(Collectors.toList());
                whereParams.in("id",gafferIdList);
            } else {
                return new PageUtils(null);
            }
        }
        IPage<GafferPaymentRecordEntity> page = this.page(
                new Query<GafferPaymentRecordEntity>().getPage(params),
                whereParams
        );

        return new PageUtils(page);
    }

    @Override
    public void addPayment(GafferPaymentRecordEntity gafferPaymentRecordEntity) throws Exception {
        GafferInfoEntity gafferInfoEntity = gafferInfoService.getById(gafferPaymentRecordEntity.getGafferId());
        if (gafferInfoEntity.getWalletAmount().compareTo(gafferPaymentRecordEntity.getAmount()) == -1) {
            throw new Exception("余额不足,请充值");
        }else {
            gafferInfoEntity.setWalletAmount(gafferInfoEntity.getWalletAmount().subtract(gafferPaymentRecordEntity.getAmount()));
            gafferInfoService.updateById(gafferInfoEntity);
            gafferPaymentRecordEntity.setCreateTime(new Date());
            gafferPaymentRecordService.save(gafferPaymentRecordEntity);
        }
    }

    @Override
    public void updatePayment(GafferPaymentRecordEntity gafferPaymentRecordEntity) throws Exception {
        GafferPaymentRecordEntity gafferPaymentRecordEntityReturn = gafferPaymentRecordService.getById(gafferPaymentRecordEntity.getId());
        GafferInfoEntity gafferInfoEntity = gafferInfoService.getById(gafferPaymentRecordEntity.getGafferId());
        BigDecimal walletResult = gafferInfoEntity.getWalletAmount().subtract(gafferPaymentRecordEntity.getAmount().subtract(gafferPaymentRecordEntityReturn.getAmount()));
        if (walletResult.compareTo(BigDecimal.ZERO) >= 0) {
            gafferInfoEntity.setWalletAmount(walletResult);
            gafferInfoService.updateById(gafferInfoEntity);
            gafferPaymentRecordService.updateById(gafferPaymentRecordEntity);
        } else {
            throw new Exception("余额不足,请充值");
        }
    }

    @Override
    public void removePayment(Long[] ids ) throws Exception {
        try {
            for (Long id : ids) {
                GafferPaymentRecordEntity gafferPaymentRecordEntityReturn = gafferPaymentRecordService.getById(id);
                GafferInfoEntity gafferInfoEntity = gafferInfoService.getById(gafferPaymentRecordEntityReturn.getGafferId());
                BigDecimal walletResult = gafferInfoEntity.getWalletAmount().add(gafferPaymentRecordEntityReturn.getAmount());
                gafferInfoEntity.setWalletAmount(walletResult);
                gafferInfoService.updateById(gafferInfoEntity);
                gafferPaymentRecordService.removeById(id);
            }
        } catch (Exception e) {
            throw new Exception("退款失败");
        }
    }

}