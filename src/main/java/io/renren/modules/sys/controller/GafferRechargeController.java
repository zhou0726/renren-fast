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

import io.renren.modules.sys.entity.GafferRechargeEntity;
import io.renren.modules.sys.service.GafferRechargeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 老人账户充值记录
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:57
 */
@RestController
@RequestMapping("generator/gafferrecharge")
public class GafferRechargeController {
    @Autowired
    private GafferRechargeService gafferRechargeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:gafferrecharge:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = gafferRechargeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:gafferrecharge:info")
    public R info(@PathVariable("id") Long id){
		GafferRechargeEntity gafferRecharge = gafferRechargeService.getById(id);

        return R.ok().put("gafferRecharge", gafferRecharge);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:gafferrecharge:save")
    public R save(@RequestBody GafferRechargeEntity gafferRecharge){
        gafferRecharge.setCreateTime(new Date());
		gafferRechargeService.save(gafferRecharge);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:gafferrecharge:update")
    public R update(@RequestBody GafferRechargeEntity gafferRecharge){
		gafferRechargeService.updateById(gafferRecharge);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:gafferrecharge:delete")
    public R delete(@RequestBody Long[] ids){
		gafferRechargeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
