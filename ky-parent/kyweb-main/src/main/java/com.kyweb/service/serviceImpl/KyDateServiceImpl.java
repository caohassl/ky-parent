package com.kyweb.service.serviceImpl;

import com.kyweb.service.KyDateService;
import com.kyweb.utils.DateUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by Caomr on 2017/9/10.
 */
@Service
public class KyDateServiceImpl implements KyDateService{

    /**
     * 2017年考研时间
     */
    private static  final String FINALTIME="2017-12-23 00:00:00";

    /**
     * 当前时间距离考研日期的时间
     * @param date
     * @return
     */
    public int miniTime(Date date) throws ParseException {
        long curTime=date.getTime();
        long fianlTime= DateUtil.parse(FINALTIME,"yyyy-MM-dd HH:mm:ss").getTime();
        long miniDay=(fianlTime-curTime)/(1000*60*60*24);
        double temp=Math.ceil(miniDay);
        return new BigDecimal(temp).intValue();
    }

    public static void main(String[] args) {
//        System.out.println(miniTime(new Date()));
    }
}
