package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.FinancePaymentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 财务收支
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:57
 */
@Mapper
public interface FinancePaymentDao extends BaseMapper<FinancePaymentEntity> {
	
}
