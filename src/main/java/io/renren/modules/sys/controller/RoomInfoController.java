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

import io.renren.modules.sys.entity.RoomInfoEntity;
import io.renren.modules.sys.service.RoomInfoService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 房间信息
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:57
 */
@RestController
@RequestMapping("generator/roominfo")
public class RoomInfoController {
    @Autowired
    private RoomInfoService roomInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:roominfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = roomInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:roominfo:info")
    public R info(@PathVariable("id") Long id){
		RoomInfoEntity roomInfo = roomInfoService.getById(id);

        return R.ok().put("roomInfo", roomInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:roominfo:save")
    public R save(@RequestBody RoomInfoEntity roomInfo){
		roomInfoService.save(roomInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:roominfo:update")
    public R update(@RequestBody RoomInfoEntity roomInfo){
		roomInfoService.updateById(roomInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:roominfo:delete")
    public R delete(@RequestBody Long[] ids){
		roomInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
