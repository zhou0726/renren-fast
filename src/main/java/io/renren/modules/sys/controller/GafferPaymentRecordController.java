package io.renren.modules.sys.controller;

import java.util.Date;
import java.util.Map;

import io.renren.modules.sys.service.GafferInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.GafferPaymentRecordEntity;
import io.renren.modules.sys.service.GafferPaymentRecordService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 老人缴费记录
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-18 20:43:57
 */
@RestController
@RequestMapping("generator/gafferpaymentrecord")
public class GafferPaymentRecordController {
    @Autowired
    private GafferPaymentRecordService gafferPaymentRecordService;
    @Autowired
    private GafferInfoService gafferInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:gafferpaymentrecord:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = gafferPaymentRecordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:gafferpaymentrecord:info")
    public R info(@PathVariable("id") Long id){
		GafferPaymentRecordEntity gafferPaymentRecord = gafferPaymentRecordService.getById(id);

        return R.ok().put("gafferPaymentRecord", gafferPaymentRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:gafferpaymentrecord:save")
    public R save(@RequestBody GafferPaymentRecordEntity gafferPaymentRecord){
        try {
            gafferPaymentRecord.setCreateTime(new Date());
            gafferPaymentRecordService.addPayment(gafferPaymentRecord);
            return R.ok();
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:gafferpaymentrecord:update")
    public R update(@RequestBody GafferPaymentRecordEntity gafferPaymentRecord){
        try {
            gafferPaymentRecordService.updatePayment(gafferPaymentRecord);
            return R.ok();
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:gafferpaymentrecord:delete")
    public R delete(@RequestBody Long[] ids){
		try{
		    gafferPaymentRecordService.removePayment(ids);
            return R.ok();
        } catch (Exception e) {
            return R.error(e.getMessage());
        }

    }

}
