package com.taotao.rest.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.rest.service.ContentService;
import com.taotao.utils.ExceptionUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: shenxingbo
 * @Description:
 * @Date: 2018/8/28下午11:20
 **/

@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    ContentService contentService;

    @RequestMapping("/list/{contentCategoryId}")
    @ResponseBody
    public TaotaoResult getContentList(@PathVariable Long contentCategoryId) {
        try{
            List<TbContent> list = contentService.getContentList(contentCategoryId);
            return TaotaoResult.ok(list);

        } catch (Exception e) {
            e.printStackTrace();
            return  TaotaoResult.build(500,ExceptionUtil.getStackTrace(e));
        }

    }


}
