package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: shenxingbo
 * @Description: 商品列表的控制器
 * @Date: 2018/8/6上午10:30
 **/
@Controller
public class PageController {

    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }

    //展示所有的页面
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }
}
