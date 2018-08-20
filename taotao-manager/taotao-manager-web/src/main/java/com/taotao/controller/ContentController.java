package com.taotao.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: shenxingbo
 * @Description: 内容管理controller
 * @Date: 2018/8/19下午5:49
 **/

@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    ContentService service;

    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult insertContent(TbContent content) {

        TaotaoResult result = service.insertContent(content);
        return result;
    }
}
