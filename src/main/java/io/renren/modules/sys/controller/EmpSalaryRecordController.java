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

import io.renren.modules.sys.entity.EmpSalaryRecordEntity;
import io.renren.modules.sys.service.EmpSalaryRecordService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 员工工资统计
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:56
 */
@RestController
@RequestMapping("generator/empsalaryrecord")
public class EmpSalaryRecordController {
    @Autowired
    private EmpSalaryRecordService empSalaryRecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:empsalaryrecord:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = empSalaryRecordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:empsalaryrecord:info")
    public R info(@PathVariable("id") Long id){
		EmpSalaryRecordEntity empSalaryRecord = empSalaryRecordService.getById(id);

        return R.ok().put("empSalaryRecord", empSalaryRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:empsalaryrecord:save")
    public R save(@RequestBody EmpSalaryRecordEntity empSalaryRecord){
		empSalaryRecordService.save(empSalaryRecord);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:empsalaryrecord:update")
    public R update(@RequestBody EmpSalaryRecordEntity empSalaryRecord){
		empSalaryRecordService.updateById(empSalaryRecord);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:empsalaryrecord:delete")
    public R delete(@RequestBody Long[] ids){
		empSalaryRecordService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
