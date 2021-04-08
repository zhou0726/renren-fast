package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 房间信息
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:57
 */
@Data
@TableName("tb_room_info")
public class RoomInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 房间号
	 */
	private String roomNo;
	/**
	 * 房间类型
	 */
	private String type;
	/**
	 * 房间价格（按天）
	 */
	private BigDecimal price;
	/**
	 * 总床位数
	 */
	private Integer totalNum;
	/**
	 * 剩余床位数
	 */
	private Integer leftNum;

}
