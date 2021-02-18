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

import io.renren.modules.sys.entity.GafferInfoEntity;
import io.renren.modules.sys.service.GafferInfoService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 老人信息
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:57
 */
@RestController
@RequestMapping("generator/gafferinfo")
public class GafferInfoController {
    @Autowired
    private GafferInfoService gafferInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:gafferinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = gafferInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:gafferinfo:info")
    public R info(@PathVariable("id") Long id){
		GafferInfoEntity gafferInfo = gafferInfoService.getById(id);

        return R.ok().put("gafferInfo", gafferInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:gafferinfo:save")
    public R save(@RequestBody GafferInfoEntity gafferInfo){
		gafferInfoService.save(gafferInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:gafferinfo:update")
    public R update(@RequestBody GafferInfoEntity gafferInfo){
		gafferInfoService.updateById(gafferInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:gafferinfo:delete")
    public R delete(@RequestBody Long[] ids){
		gafferInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
