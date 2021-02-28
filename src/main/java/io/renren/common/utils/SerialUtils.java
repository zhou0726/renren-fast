package io.renren.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
public class SerialUtils {
    @Autowired
    private RedisUtils redisUtils;
    /**
     * 员工编号自增序列过期时间
     */
    private final int EMP_NO_KEY_EXPIRE_TIME = 3600;
    /**
     * 生成员工编号：NO+年月日+3位随机数+4位序列
     * @return
     */
    public String getEmpNo() {
        // 获取年月日
        String date = DateUtils.format(new Date(), "yyyyMMdd");
        String incr = String.format("%04d", redisUtils.incr(date, EMP_NO_KEY_EXPIRE_TIME));
        Random random = new Random();
        String randomResult = String.format("%03d",random.nextInt(1000));
        return "NO" + date + incr + randomResult;
    }
}
