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

import io.renren.modules.sys.entity.ActivityEntity;
import io.renren.modules.sys.service.ActivityService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 活动公告
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:57
 */
@RestController
@RequestMapping("generator/activity")
public class ActivityController extends AbstractController {
    @Autowired
    private ActivityService activityService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:activity:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = activityService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:activity:info")
    public R info(@PathVariable("id") Long id){
		ActivityEntity activity = activityService.getById(id);

        return R.ok().put("activity", activity);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:activity:save")
    public R save(@RequestBody ActivityEntity activity){
        activity.setOperationUserId(getUserId());
        activity.setCreateTime(new Date());
		activityService.save(activity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:activity:update")
    public R update(@RequestBody ActivityEntity activity){
		activityService.updateById(activity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:activity:delete")
    public R delete(@RequestBody Long[] ids){
		activityService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
