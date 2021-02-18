package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 房间变更
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:57
 */
@Data
@TableName("tb_room_change")
public class RoomChangeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 所属老人id
	 */
	private Long theAgedId;
	/**
	 * 变更前房间id
	 */
	private Long beforeRoomId;
	/**
	 * 变更后房间id
	 */
	private Long nowRoomId;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
