package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 查房记录
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:56
 */
@Data
@TableName("tb_room_check_record")
public class RoomCheckRecordEntity implements Serializable {
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
	 * 查房人id
	 */
	private Long operationId;
	/**
	 * 查房内容
	 */
	private String content;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
