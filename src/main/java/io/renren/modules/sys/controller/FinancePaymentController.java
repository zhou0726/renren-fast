package io.renren.modules.sys.controller;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.utils.DateUtils;
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
        financePayment.setCreateTime(new Date());
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
    /**
     * 柱形统计信息
     */
    @RequestMapping("/getChartBarInfo")
    public R getChartBarInfo(@RequestParam("startMonth") String startMonth,
                            @RequestParam("endMonth") String endMonth) {
        Date startMonthDate = DateUtils.stringToDate(startMonth, DateUtils.DATA_MONTH_PATTERN);
        Date endMonthDate = DateUtils.stringToDate(endMonth, DateUtils.DATA_MONTH_PATTERN);
        endMonthDate = DateUtils.addDateMonths(endMonthDate,1);
        QueryWrapper<FinancePaymentEntity> whereParams = new QueryWrapper<>();
        whereParams.ge("create_time",startMonthDate);
        whereParams.lt("create_time",endMonthDate);
        List<FinancePaymentEntity> list = financePaymentService.list(whereParams);
        Map<String, List<FinancePaymentEntity>> groupList = list.stream().collect(Collectors.groupingBy(item -> item.getType(), Collectors.mapping(item -> item, Collectors.toList())));
        Map<String,Object> result = new HashMap<>();
        Set<Map.Entry<String, List<FinancePaymentEntity>>> entries = groupList.entrySet();
        List legendData = new ArrayList<>();
        Map<String,List> series = new HashMap<>();
        List serie = new ArrayList();
        List dateList = new ArrayList();
        Date tempDate = startMonthDate;
        do {
            String format = DateUtils.format(tempDate, "yyyy-MM");
            dateList.add(format);
            tempDate = DateUtils.addDateMonths(tempDate,1);
        } while(endMonthDate.after(tempDate));
        for (Map.Entry<String, List<FinancePaymentEntity>> entry : entries) {
            legendData.add(entry.getKey());
            Map<String,Object> serieMap = new HashMap<>();
            serieMap.put("name",entry.getKey());
            serieMap.put("type","bar");
            List serieList = new ArrayList();

            Map<String, DoubleSummaryStatistics> collect = entry.getValue().stream().collect(Collectors.groupingBy(item -> DateUtils.format(item.getCreateTime(), "yyyy-MM"), Collectors.summarizingDouble(item -> item.getAmount().doubleValue())));
            tempDate = startMonthDate;
            do {
                String format = DateUtils.format(tempDate, "yyyy-MM");
                DoubleSummaryStatistics doubleSummaryStatistics = collect.get(format);
                if (doubleSummaryStatistics == null) {
                    serieList.add(0);
                }else {
                    double sumAmout = doubleSummaryStatistics.getSum();
                    serieList.add(sumAmout);
                }
                tempDate = DateUtils.addDateMonths(tempDate,1);
            } while(endMonthDate.after(tempDate));

            serieMap.put("data",serieList);
            serie.add(serieMap);
        }
        series.put("series",serie);
        result.put("legendData",legendData);
        result.put("series",series);
        result.put("dateList",dateList);
        System.out.println(JSON.toJSONString(result));
        return R.ok(result);
    }

    /**
     * 饼状统计信息
     */
    @RequestMapping("/getPieInfo")
    public R getChartBarInfo(@RequestParam("month") String month,
                             @RequestParam("type") Integer type) {
        Date startMonthDate = DateUtils.stringToDate(month, DateUtils.DATA_MONTH_PATTERN);
        Date endMonthDate = DateUtils.addDateMonths(startMonthDate,1);
        QueryWrapper<FinancePaymentEntity> whereParams = new QueryWrapper<>();
        whereParams.ge("create_time",startMonthDate);
        whereParams.lt("create_time",endMonthDate);
        whereParams.eq("type",type.intValue()==1?"支出":"收入");
        List<FinancePaymentEntity> list = financePaymentService.list(whereParams);
        if (CollectionUtil.isEmpty(list)) {
            return R.error("数据为空");
        }
        Map<String, DoubleSummaryStatistics> collect = list.stream().collect(Collectors.groupingBy(item -> item.getContent(), Collectors.summarizingDouble(item -> item.getAmount().doubleValue())));
        Set<Map.Entry<String, DoubleSummaryStatistics>> entries = collect.entrySet();
        List result = new ArrayList();
        for (Map.Entry<String, DoubleSummaryStatistics> entry : entries) {
            Map<String,Object> data = new HashMap<>();
            data.put("name",entry.getKey());
            data.put("value",entry.getValue().getSum());
            result.add(data);
        }
        Map resultMap = new HashMap();
        resultMap.put("data",result);
        System.out.println(JSON.toJSONString(resultMap));
        return R.ok(resultMap);
    }
}
