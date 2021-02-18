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

import io.renren.modules.sys.entity.FinancePaymentEntity;
import io.renren.modules.sys.service.FinancePaymentService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 财务收支
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:57
 */
@RestController
@RequestMapping("generator/financepayment")
public class FinancePaymentController {
    @Autowired
    private FinancePaymentService financePaymentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:financepayment:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = financePaymentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:financepayment:info")
    public R info(@PathVariable("id") Long id){
		FinancePaymentEntity financePayment = financePaymentService.getById(id);

        return R.ok().put("financePayment", financePayment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:financepayment:save")
    public R save(@RequestBody FinancePaymentEntity financePayment){
		financePaymentService.save(financePayment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:financepayment:update")
    public R update(@RequestBody FinancePaymentEntity financePayment){
		financePaymentService.updateById(financePayment);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:financepayment:delete")
    public R delete(@RequestBody Long[] ids){
		financePaymentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
