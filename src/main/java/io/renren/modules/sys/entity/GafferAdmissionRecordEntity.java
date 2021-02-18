package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 老人出入院记录
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:56
 */
@Data
@TableName("tb_gaffer_admission_record")
public class GafferAdmissionRecordEntity implements Serializable {
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
	 * 出院/入院
	 */
	private String type;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
