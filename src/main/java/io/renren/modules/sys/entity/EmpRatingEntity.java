package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 员工评级
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:57
 */
@Data
@TableName("tb_emp_rating")
public class EmpRatingEntity implements Serializable {
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
	 * 评价人id
	 */
	private Long userId;
	/**
	 * 星级
	 */
	private Integer star;
	/**
	 * 评级所属年月
	 */
	private String starDate;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
