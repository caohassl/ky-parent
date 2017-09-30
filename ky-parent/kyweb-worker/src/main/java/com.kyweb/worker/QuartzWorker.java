package com.kyweb.worker;

import com.kyweb.model.TbQuartz;
import com.kyweb.service.MailService;
import com.kyweb.service.QuartzService;
import com.kyweb.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Caomr on 2017/9/30.
 */
@Component
@Slf4j
public class QuartzWorker {

    @Resource
    QuartzService quartzServiceImpl;

    @Resource
    MailService mailServiceImpl;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Scheduled(cron="${kyweb.sendmail.cron}")
    public void sendMail(){
        log.info("{}执行sendMail任务开始",DateUtil.getNowTime());
        String currTime= DateUtil.getNowTime();
        final List<TbQuartz> quartzList= quartzServiceImpl.getNeedRunQuartz(currTime);
        log.info("本次需要跑批的个数为{}",quartzList.size());

        //弄一个线程去跑
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                for(TbQuartz tbQuartz:quartzList){
                    mailServiceImpl.sendSimpleMail(tbQuartz.getMessageTarget(),"kyweb定时器",tbQuartz.getMessage());
                    log.info("邮件已经发送给{},信息为{}",tbQuartz.getMessageTarget(),tbQuartz.getMessage());
                    //设置为已经发送
                    tbQuartz.setMessageStatus("1");
                    tbQuartz.setMessageSendTime(DateUtil.getNowTime());
                    quartzServiceImpl.updateByPrimaryKey(tbQuartz);
                    log.info("=========更新邮件{}发送状态和时间成功========",tbQuartz.getId());
                }
            }
        });

    }
}
