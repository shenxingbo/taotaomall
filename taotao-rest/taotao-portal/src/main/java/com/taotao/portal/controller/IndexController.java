package com.taotao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: shenxingbo
 * @Description:
 * @Date: 2018/8/18下午2:43
 **/

@Controller
public class IndexController {
    @RequestMapping("/index")
    public String showIndex() {
        return "index";
    }

}
