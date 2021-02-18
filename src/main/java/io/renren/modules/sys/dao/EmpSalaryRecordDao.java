package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.EmpSalaryRecordEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 员工工资统计
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:56
 */
@Mapper
public interface EmpSalaryRecordDao extends BaseMapper<EmpSalaryRecordEntity> {
	
}
