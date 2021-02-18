package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 员工考勤
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:56
 */
@Data
@TableName("tb_emp_attendance")
public class EmpAttendanceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 员工id
	 */
	private Long empId;
	/**
	 * 考勤所属年月
	 */
	private String attendanceDate;
	/**
	 * 应到天数
	 */
	private Integer shouldDay;
	/**
	 * 实到天数
	 */
	private Integer actualDay;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
