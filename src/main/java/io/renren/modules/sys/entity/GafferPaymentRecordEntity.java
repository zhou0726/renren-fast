package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 老人缴费记录
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:57
 */
@Data
@TableName("tb_gaffer_payment_record")
public class GafferPaymentRecordEntity implements Serializable {
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
	 * 缴费金额
	 */
	private BigDecimal amount;
	/**
	 * 缴费所属年月
	 */
	private String paymentDate;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
