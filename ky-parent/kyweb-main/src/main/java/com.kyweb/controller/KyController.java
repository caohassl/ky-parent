package com.kyweb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Slf4j
public class KyController {

    /**
     * 当输入地址时返回默认地址
     *
     * @return
     */
    @RequestMapping("/")
    public String first() {
        return "ftl/login/login";
    }

    /**
     * 当失败时返回失败信息
     *
     * @return
     */
    @RequestMapping(value = "/err", method = RequestMethod.GET)
    public String error(String errMsg, Model model) {
        String url = "ftl/error";
        log.error("错误信息{},正在跳转{}", errMsg, url);
        model.addAttribute("errMsg", errMsg);
        return url;
    }


}
