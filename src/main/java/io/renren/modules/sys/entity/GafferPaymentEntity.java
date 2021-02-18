package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 老人额外消费
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:56
 */
@Data
@TableName("tb_gaffer_payment")
public class GafferPaymentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 老人账户id
	 */
	private Long gafferId;
	/**
	 * 消费金额
	 */
	private BigDecimal amount;
	/**
	 * 消费详情
	 */
	private Long detail;
	/**
	 * 审核状态：0-提交 1-审核通过 2-审核拒绝
	 */
	private String status;
	/**
	 * 提交医护id
	 */
	private Long operationId;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
