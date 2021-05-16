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

import io.renren.modules.sys.entity.GafferPaymentEntity;
import io.renren.modules.sys.service.GafferPaymentService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 老人额外消费
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:56
 */
@RestController
@RequestMapping("generator/gafferpayment")
public class GafferPaymentController {
    @Autowired
    private GafferPaymentService gafferPaymentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:gafferpayment:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = gafferPaymentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:gafferpayment:info")
    public R info(@PathVariable("id") Long id){
		GafferPaymentEntity gafferPayment = gafferPaymentService.getById(id);

        return R.ok().put("gafferPayment", gafferPayment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:gafferpayment:save")
    public R save(@RequestBody GafferPaymentEntity gafferPayment){
        gafferPayment.setCreateTime(new Date());
		gafferPaymentService.save(gafferPayment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:gafferpayment:update")
    public R update(@RequestBody GafferPaymentEntity gafferPayment){
		gafferPaymentService.updateById(gafferPayment);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:gafferpayment:delete")
    public R delete(@RequestBody Long[] ids){
		gafferPaymentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
