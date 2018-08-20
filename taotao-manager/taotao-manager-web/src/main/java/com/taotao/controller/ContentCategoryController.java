package com.taotao.controller;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: shenxingbo
 * @Description: 内容分类管理的controller
 * @Date: 2018/8/19下午4:23
 **/

@Controller
@RequestMapping("content/category")
public class ContentCategoryController {
    @Autowired
    ContentCategoryService service;

    @RequestMapping("/list")
    @ResponseBody
    public List<EUTreeNode> getContentCateList(@RequestParam(value="id",defaultValue = "0") Long parentId) {
        List<EUTreeNode> result = service.getCategoryList(parentId);
        return result;
    }

    @RequestMapping("create")
    @ResponseBody
    public TaotaoResult createContentCategory(Long parentId, String name) {
        TaotaoResult result = service.insertContentCategory(parentId,name);
        return result;
    }
}
