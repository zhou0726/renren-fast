package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.EmpInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 员工信息
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:57
 */
public interface EmpInfoService extends IService<EmpInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<EmpInfoEntity> searchByKeyword(String keyword) throws Exception;

    List<EmpInfoEntity> searchByKeywordForSalary(String keyword) throws Exception;

    void resign(Long[] ids) throws Exception;
}

