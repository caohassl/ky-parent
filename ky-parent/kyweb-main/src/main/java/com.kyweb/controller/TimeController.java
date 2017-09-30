package com.kyweb.controller;

import com.alibaba.fastjson.JSON;
import com.kyweb.service.QuartzService;
import com.kyweb.utils.CommonUtil;
import com.kyweb.utils.DateUtil;
import com.kyweb.vo.PageVo;
import com.kyweb.vo.QuartzVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/25.
 */
@Controller
@Slf4j
@RequestMapping("/timeController")
public class TimeController {

    @Resource
    private QuartzService quartzServiceImpl;

    /**
     * 插入一条记录
     * @param quartzVo
     * @param session
     * @return
     */
    @RequestMapping("/addTime")
    @ResponseBody
    public Map addTime(QuartzVo quartzVo, HttpSession session){
        Map<String,Object> map=new HashMap<String,Object>();
        //session
        if(StringUtils.isEmpty((String)session.getAttribute("name"))){
            map.put("result",false);
            map.put("message","用户未登陆");
            return map;
        }else {
            quartzVo.setUserName((String)session.getAttribute("name"));
        }


        if(!StringUtils.isEmpty(quartzVo.getMessageTarget())){
            quartzVo.setMessageType("1");//发邮件
            quartzVo.setMessageStatus("0");
        }
        else {
            quartzVo.setMessageType("0");//发留言
        }
        try {
            DateUtil.parse(quartzVo.getQuartzTime(),DateUtil.DATE_FULL_STR);
        } catch (ParseException e) {
            e.printStackTrace();
            map.put("result",false);
            map.put("message","日期格式为yyyy-MM-dd HH:mm:ss");
            return map;
        }
        //校验非空
        String args[]=new String[]{"message","quartzTime"};
        String result=CommonUtil.checkBeanPropertiesHaveBlank(quartzVo,args);
        if(!StringUtils.isEmpty(result)){
            map.put("result",false);
            map.put("message",result);
            return map;
        }
        log.info("{}开始插入数据{}",session.getAttribute("name"), JSON.toJSONString(quartzVo));
        int i=quartzServiceImpl.insertQuarzMessage(quartzVo);
        map.put("result",true);
    return map;
    }

    /**
     * 传入PageVo返回字段
     * @param pageVo
     * @return
     */
    @RequestMapping("/showTime")
    @ResponseBody
    public Map showTime(PageVo pageVo){
        Map<String,Object> map=new HashMap<String,Object>();
        PageVo page=quartzServiceImpl.selectQuartzPage(pageVo);
        List<QuartzVo> list= quartzServiceImpl.showQuartzMessage(pageVo);
        if(list==null||list.size()==0){
            map.put("result",false);
            map.put("msg","消息为空");
        }else {
            map.put("result",true);
            map.put("msg",list);
            map.put("page",page);
        }
        return map;
    }
}
