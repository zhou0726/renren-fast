package io.renren.modules.sys.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.utils.DateUtils;
import io.renren.modules.sys.entity.EmpAttendanceEntity;
import io.renren.modules.sys.entity.EmpInfoEntity;
import io.renren.modules.sys.entity.EmpRatingEntity;
import io.renren.modules.sys.service.EmpAttendanceService;
import io.renren.modules.sys.service.EmpInfoService;
import io.renren.modules.sys.service.EmpRatingService;
import org.apache.commons.lang.StringUtils;
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
public class EmpSalaryRecordController extends AbstractController {
    @Autowired
    private EmpSalaryRecordService empSalaryRecordService;
    @Autowired
    private EmpInfoService empInfoService;
    @Autowired
    private EmpAttendanceService empAttendanceService;
    @Autowired
    private EmpRatingService empRatingService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:empsalaryrecord:list")
    public R list(@RequestParam Map<String, Object> params){
        String key = (String) params.get("key");
        if (StringUtils.isNotBlank(key)) {
            // 搜索用户
            QueryWrapper<EmpInfoEntity> empParams = new QueryWrapper<>();
            empParams.like("name",key).eq("status",1);
            List<EmpInfoEntity> empInfoEntityList = empInfoService.list(empParams);
            if (CollectionUtil.isEmpty(empInfoEntityList)) {
                return R.ok();
            } else {
                params.put("empIdList",empInfoEntityList.stream().map(emp -> emp.getId()).collect(Collectors.toList()));
            }
        }
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

    /**
     * 生成上个月员工工资
     *  员工工资 = 考勤工资 + 评级工资
     *      考勤工资 = 基本工资 × （ 实到天数 / 应到天数）
     *      评级工资 =  总star数 / 评级人数 × 1% × 基本工资
     *
     * @return
     */
    @RequestMapping("/generateForLastMonth")
    @RequiresPermissions("generator:empsalaryrecord:delete")
    public R generateForLastMonth() {
        try {
            Long userId = this.getUserId();
            String lastMonthDate = DateUtils.format(DateUtils.addDateMonths(new Date(),-1), "yyyy-MM");
            // 判断是否已经生成
            QueryWrapper<EmpSalaryRecordEntity> params = new QueryWrapper<>();
            params.eq("record_date", lastMonthDate);
            int count = empSalaryRecordService.count(params);
            if (count > 0) {
                throw new Exception("上月工资已生成，请勿重复生成");
            }
            // 判断是否全部参与考勤
            // 最后入职时间
            DateTime lastOnboardTime = DateUtil.beginOfMonth(new Date());
            // 最后离职时间
            DateTime lastResignTime = DateUtil.beginOfMonth(DateUtil.lastMonth());
            QueryWrapper<EmpInfoEntity> empWhereParam = new QueryWrapper<>();
            // 为符合条件的员工考勤：1：本月初前入职 2：上月初后离职或未离职
            empWhereParam.gt("onboardTime",lastOnboardTime).and(qw -> qw.lt("resignTime",lastResignTime).or().eq("status",1));
            int empNums = empInfoService.count(empWhereParam);
            if (empNums == 0) {
                throw new Exception("员工为空，无法生成工资");
            }
            QueryWrapper<EmpAttendanceEntity> empAttParams = new QueryWrapper<>();
            empAttParams.eq("attendance_date",lastMonthDate);
            int empAttNums = empAttendanceService.count(empAttParams);
            if (empNums > empAttNums) {
                throw new Exception("生成失败，有"+(empNums - empAttNums) + "名员工未参与考勤");
            }
            // 获取所有员工信息--1：本月初前入职 2：上月初后离职或未离职
            QueryWrapper<EmpInfoEntity> empWhereParams = new QueryWrapper<EmpInfoEntity>();
            empWhereParams.select("id", "basic_salary");
            empWhereParams.gt("onboardTime",lastOnboardTime).and(qw -> qw.lt("resignTime",lastResignTime).or().eq("status",1));
            List<EmpInfoEntity> empInfoList = empInfoService.list(empWhereParams);
            // 获取所有员工上月考勤信息
            QueryWrapper<EmpAttendanceEntity> empAttenWhereParams = new QueryWrapper<>();
            empAttenWhereParams.eq("attendance_date", lastMonthDate);
            empAttenWhereParams.select("emp_id","should_day","actual_day");
            List<EmpAttendanceEntity> empAttendanceList = empAttendanceService.list(empAttenWhereParams);
            Map<Long,EmpAttendanceEntity> empAttendMap = null;
            if (CollectionUtil.isNotEmpty(empAttendanceList)) {
                empAttendMap = empAttendanceList.parallelStream().collect(Collectors.toMap(EmpAttendanceEntity::getEmpId, item -> item));
            } else {
                throw new Exception("获取员工考勤信息失败");
            }
            // 获取所有员工上月评级信息
            QueryWrapper<EmpRatingEntity> empRatWhereParams = new QueryWrapper<>();
            empRatWhereParams.eq("star_date",lastMonthDate);
            empRatWhereParams.select("id","emp_id","star");
            List<EmpRatingEntity> empRatingList = empRatingService.list(empRatWhereParams);
            Map<Long, List<EmpRatingEntity>> empRatMap = null;
            if (CollectionUtil.isNotEmpty(empRatingList)) {
                empRatMap = empRatingList.parallelStream().collect(Collectors.groupingBy(EmpRatingEntity::getEmpId));
            }
            // 汇总计算
            Map<Long, EmpAttendanceEntity> finalEmpAttendMap = empAttendMap;
            Map<Long, List<EmpRatingEntity>> finalEmpRatMap = empRatMap;
            List<EmpSalaryRecordEntity> saveList = new ArrayList<>();
            empInfoList.stream().forEach(emp -> {
                EmpSalaryRecordEntity empSalaryRecordEntity = new EmpSalaryRecordEntity();
                empSalaryRecordEntity.setEmpId(emp.getId().longValue());
                empSalaryRecordEntity.setRecordDate(lastMonthDate);
                empSalaryRecordEntity.setAmount(emp.getBasicSalary());
                empSalaryRecordEntity.setCreateTime(new Date());
                empSalaryRecordEntity.setOperationUserId(userId);

                EmpAttendanceEntity empAttendanceEntity = finalEmpAttendMap.get(emp.getId().longValue());
                List<EmpRatingEntity> empRatingEntityList = finalEmpRatMap.get(emp.getId().longValue());
                if (empAttendanceEntity != null) {
                    // 考勤工资 = 基本工资 * （ 实到天数 / 应到天数）
                    Integer shouldDay = empAttendanceEntity.getShouldDay();
                    Integer actualDay = empAttendanceEntity.getActualDay();
                    BigDecimal attendAmount = empSalaryRecordEntity.getAmount().
                            multiply(BigDecimal.valueOf(actualDay)).
                            divide(BigDecimal.valueOf(shouldDay), RoundingMode.HALF_UP);
                    empSalaryRecordEntity.setAttendanceAmount(attendAmount);
                } else {
                    empSalaryRecordEntity.setAttendanceAmount(BigDecimal.ZERO);
                }
                if (CollectionUtil.isNotEmpty(empRatingEntityList)) {
                    // 评级工资 =  总star数 / 评级人数 * 1% * 基本工资
                    int sumStar = empRatingEntityList.stream().mapToInt(EmpRatingEntity::getStar).sum();
                    long startCount = empRatingEntityList.stream().count();
                    BigDecimal starAmount = BigDecimal.valueOf(sumStar).
                            divide(BigDecimal.valueOf(startCount),RoundingMode.HALF_UP).
                            multiply(BigDecimal.valueOf(0.01)).
                            multiply(empSalaryRecordEntity.getAmount());
                    empSalaryRecordEntity.setStarAmount(starAmount);
                } else {
                    empSalaryRecordEntity.setStarAmount(BigDecimal.ZERO);
                }
                empSalaryRecordEntity.setTotalAmount(empSalaryRecordEntity.getAttendanceAmount().add(empSalaryRecordEntity.getStarAmount()));
                saveList.add(empSalaryRecordEntity);
            });
            empSalaryRecordService.saveBatch(saveList);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

}
