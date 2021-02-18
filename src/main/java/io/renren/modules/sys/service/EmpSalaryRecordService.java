package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.EmpSalaryRecordEntity;

import java.util.Map;

/**
 * 员工工资统计
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:56
 */
public interface EmpSalaryRecordService extends IService<EmpSalaryRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

