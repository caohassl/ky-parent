package com.kyweb.controller;

import com.kyweb.service.UserService;
import com.kyweb.utils.CommonUtil;
import com.kyweb.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zl on 2015/8/27.
 */
@Controller
@Slf4j
@RequestMapping("/userController")
public class UserController {


    @Resource
    private UserService userServiceImpl;

    /**
     * 登录方法
     * @param userVo
     * @return
     * @author:caomr
     */
    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    @ResponseBody
    public Map checkLogin(UserVo userVo,HttpSession session) {
        String args = "name,password";
        String flag = CommonUtil.checkBeanPropertiesHaveBlank(userVo, args.split(","));
        if (!StringUtils.isEmpty(flag)) {
            log.error("必传字段为空:{}", flag);
        }
        log.info("来自用户名为{}请求登录", userVo.getName());
        boolean result = userServiceImpl.checkUser(userVo);
        if(result){
            session.setAttribute("name",userVo.getName());
            log.info("用户登录成功，将用户{}写入缓存",userVo.getName());
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", result);
        return map;
    }

    /**
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
    public String loginSuccess(HttpSession session) {

        log.info("用户{}登录成功", session.getAttribute("name"));
        return "ftl/time/showTime";
    }


}
