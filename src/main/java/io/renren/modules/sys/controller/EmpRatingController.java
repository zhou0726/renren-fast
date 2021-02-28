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

import io.renren.modules.sys.entity.EmpRatingEntity;
import io.renren.modules.sys.service.EmpRatingService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 员工评级
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:57
 */
@RestController
@RequestMapping("generator/emprating")
public class EmpRatingController {
    @Autowired
    private EmpRatingService empRatingService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:emprating:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = empRatingService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:emprating:info")
    public R info(@PathVariable("id") Long id){
		EmpRatingEntity empRating = empRatingService.getById(id);

        return R.ok().put("empRating", empRating);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:emprating:save")
    public R save(@RequestBody EmpRatingEntity empRating){
        empRating.setCreateTime(new Date());
		empRatingService.save(empRating);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:emprating:update")
    public R update(@RequestBody EmpRatingEntity empRating){
		empRatingService.updateById(empRating);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:emprating:delete")
    public R delete(@RequestBody Long[] ids){
		empRatingService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
