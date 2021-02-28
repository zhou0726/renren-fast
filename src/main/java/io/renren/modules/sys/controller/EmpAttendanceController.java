package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.EmpAttendanceEntity;
import io.renren.modules.sys.service.EmpAttendanceService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 员工考勤
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:56
 */
@RestController
@RequestMapping("generator/empattendance")
public class EmpAttendanceController {
    @Autowired
    private EmpAttendanceService empAttendanceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:empattendance:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = empAttendanceService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:empattendance:info")
    public R info(@PathVariable("id") Long id){
		EmpAttendanceEntity empAttendance = empAttendanceService.getById(id);

        return R.ok().put("empAttendance", empAttendance);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:empattendance:save")
    public R save(@RequestBody EmpAttendanceEntity empAttendance){
        empAttendance.setId(null);
        empAttendance.setCreateTime(new Date(System.currentTimeMillis()));
        System.out.println(empAttendance);
		empAttendanceService.save(empAttendance);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:empattendance:update")
    public R update(@RequestBody EmpAttendanceEntity empAttendance){
        empAttendance.setCreateTime(null);
		empAttendanceService.updateById(empAttendance);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:empattendance:delete")
    public R delete(@RequestBody Long[] ids){
		empAttendanceService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
