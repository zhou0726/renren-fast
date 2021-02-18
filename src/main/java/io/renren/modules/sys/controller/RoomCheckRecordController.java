package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.RoomCheckRecordEntity;
import io.renren.modules.sys.service.RoomCheckRecordService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 查房记录
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:56
 */
@RestController
@RequestMapping("generator/roomcheckrecord")
public class RoomCheckRecordController {
    @Autowired
    private RoomCheckRecordService roomCheckRecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:roomcheckrecord:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = roomCheckRecordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:roomcheckrecord:info")
    public R info(@PathVariable("id") Long id){
		RoomCheckRecordEntity roomCheckRecord = roomCheckRecordService.getById(id);

        return R.ok().put("roomCheckRecord", roomCheckRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:roomcheckrecord:save")
    public R save(@RequestBody RoomCheckRecordEntity roomCheckRecord){
		roomCheckRecordService.save(roomCheckRecord);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:roomcheckrecord:update")
    public R update(@RequestBody RoomCheckRecordEntity roomCheckRecord){
		roomCheckRecordService.updateById(roomCheckRecord);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:roomcheckrecord:delete")
    public R delete(@RequestBody Long[] ids){
		roomCheckRecordService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
