package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 员工工资统计
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:56
 */
@Data
@TableName("tb_emp_salary_record")
public class EmpSalaryRecordEntity implements Serializable {
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
	 * 本月基本工资
	 */
	private BigDecimal amount;
	/**
	 * 考评工资
	 */
	private BigDecimal starAmount;
	/**
	 * 考勤工资
	 */
	private BigDecimal attendanceAmount;
	/**
	 * 总工资
	 */
	private BigDecimal totalAmount;
	/**
	 * 操作人
	 */
	private Long operationUserId;
	/**
	 * 记录所属年月
	 */
	private String recordDate;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
