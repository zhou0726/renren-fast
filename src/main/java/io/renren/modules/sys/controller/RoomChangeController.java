package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.RoomChangeEntity;
import io.renren.modules.sys.service.RoomChangeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 房间变更
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:57
 */
@RestController
@RequestMapping("generator/roomchange")
public class RoomChangeController {
    @Autowired
    private RoomChangeService roomChangeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:roomchange:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = roomChangeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:roomchange:info")
    public R info(@PathVariable("id") Long id){
		RoomChangeEntity roomChange = roomChangeService.getById(id);

        return R.ok().put("roomChange", roomChange);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:roomchange:save")
    public R save(@RequestBody RoomChangeEntity roomChange){
        roomChange.setCreateTime(new Date());
		roomChangeService.save(roomChange);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:roomchange:update")
    public R update(@RequestBody RoomChangeEntity roomChange){
		roomChangeService.updateById(roomChange);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:roomchange:delete")
    public R delete(@RequestBody Long[] ids){
		roomChangeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
