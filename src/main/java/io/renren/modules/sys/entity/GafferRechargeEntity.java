package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 老人账户充值记录
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:57
 */
@Data
@TableName("tb_gaffer_recharge")
public class GafferRechargeEntity implements Serializable {
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
	 * 充值金额
	 */
	private BigDecimal amount;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
