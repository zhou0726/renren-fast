package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 财务收支
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:57
 */
@Data
@TableName("tb_finance_payment")
public class FinancePaymentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 收/支
	 */
	private String type;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 费用
	 */
	private BigDecimal amount;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
