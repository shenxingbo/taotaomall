package com.taotao.portal.controller;

import com.taotao.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: shenxingbo
 * @Description:
 * @Date: 2018/8/18下午2:43
 **/

@Controller
public class IndexController {

    @Autowired
    private ContentService service;

    @RequestMapping("/index")
    public String showIndex(Model model) {
        String adJson = service.getContentList();
        System.out.println( adJson);
        model.addAttribute("ad1", adJson);
        return "index";
    }


}
