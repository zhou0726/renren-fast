package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.utils.SerialUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private SerialUtils serialUtils;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:empinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = empInfoService.queryPage(params);

        return R.ok().put("page", page);
    }

    @GetMapping("/listByIds")
    @RequiresPermissions("generator:empinfo:list")
    public R list(@RequestParam List<Long> empIdList) {
        if (CollectionUtil.isEmpty(empIdList)) {
            return R.error("员工数据为空");
        }
        QueryWrapper<EmpInfoEntity> whereParams = new QueryWrapper<>();
        whereParams.in("id",empIdList);
        List<EmpInfoEntity> list = empInfoService.list(whereParams);
        return R.ok().put("empList",list);
    }

    @RequestMapping("/search")
    @RequiresPermissions("generator:empinfo:search")
    public R list(@RequestParam String keyword) {
        try {
            List<EmpInfoEntity> empInfoEntityList = empInfoService.searchByKeyword(keyword);
            return R.ok().put("empList",empInfoEntityList);
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
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
        String empNo = serialUtils.getEmpNo();
        empInfo.setNo(empNo);
        empInfo.setId(null);
        empInfo.setCreateTime(new Date(System.currentTimeMillis()));
        empInfoService.save(empInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:empinfo:update")
    public R update(@RequestBody EmpInfoEntity empInfo){
        empInfo.setCreateTime(null);
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
