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

import io.renren.modules.sys.entity.EmpInfoEntity;
import io.renren.modules.sys.service.EmpInfoService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 员工信息
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:57
 */
@RestController
@RequestMapping("generator/empinfo")
public class EmpInfoController {
    @Autowired
    private EmpInfoService empInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:empinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = empInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:empinfo:info")
    public R info(@PathVariable("id") Long id){
		EmpInfoEntity empInfo = empInfoService.getById(id);

        return R.ok().put("empInfo", empInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:empinfo:save")
    public R save(@RequestBody EmpInfoEntity empInfo){
		empInfoService.save(empInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:empinfo:update")
    public R update(@RequestBody EmpInfoEntity empInfo){
		empInfoService.updateById(empInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:empinfo:delete")
    public R delete(@RequestBody Long[] ids){
		empInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
