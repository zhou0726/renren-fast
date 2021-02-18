package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 老人信息
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:57
 */
@Data
@TableName("tb_gaffer_info")
public class GafferInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
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
	 * 电话
	 */
	private String telephone;
	/**
	 * 监护人姓名
	 */
	private String guardianName;
	/**
	 * 监护人电话
	 */
	private String guardianTelephone;
	/**
	 * 家庭住址
	 */
	private String address;
	/**
	 * 相片
	 */
	private String pic;
	/**
	 * 钱包余额
	 */
	private BigDecimal walletAmount;
	/**
	 * 健康档案
	 */
	private String healthRecord;
	/**
	 * 操作人
	 */
	private String operationUserId;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
