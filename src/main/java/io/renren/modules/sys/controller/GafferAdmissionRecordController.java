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

import io.renren.modules.sys.entity.GafferAdmissionRecordEntity;
import io.renren.modules.sys.service.GafferAdmissionRecordService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 老人出入院记录
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:56
 */
@RestController
@RequestMapping("generator/gafferadmissionrecord")
public class GafferAdmissionRecordController {
    @Autowired
    private GafferAdmissionRecordService gafferAdmissionRecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:gafferadmissionrecord:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = gafferAdmissionRecordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:gafferadmissionrecord:info")
    public R info(@PathVariable("id") Long id){
		GafferAdmissionRecordEntity gafferAdmissionRecord = gafferAdmissionRecordService.getById(id);

        return R.ok().put("gafferAdmissionRecord", gafferAdmissionRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:gafferadmissionrecord:save")
    public R save(@RequestBody GafferAdmissionRecordEntity gafferAdmissionRecord){
		gafferAdmissionRecordService.save(gafferAdmissionRecord);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:gafferadmissionrecord:update")
    public R update(@RequestBody GafferAdmissionRecordEntity gafferAdmissionRecord){
		gafferAdmissionRecordService.updateById(gafferAdmissionRecord);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:gafferadmissionrecord:delete")
    public R delete(@RequestBody Long[] ids){
		gafferAdmissionRecordService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
