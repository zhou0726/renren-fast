package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.EmpAttendanceEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 员工考勤
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:56
 */
@Mapper
public interface EmpAttendanceDao extends BaseMapper<EmpAttendanceEntity> {
	
}
