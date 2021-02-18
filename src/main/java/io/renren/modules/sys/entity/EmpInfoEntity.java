package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 员工信息
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:57
 */
@Data
@TableName("tb_emp_info")
public class EmpInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 编号
	 */
	private String no;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 年龄
	 */
	private Long age;
	/**
	 * 科室
	 */
	private String department;
	/**
	 * 职称
	 */
	private String job;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 相片
	 */
	private String pic;
	/**
	 * 基本工资
	 */
	private BigDecimal basicSalary;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
