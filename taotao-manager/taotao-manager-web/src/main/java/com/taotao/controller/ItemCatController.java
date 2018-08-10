package com.taotao.controller;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.service.ItermCatService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: shenxingbo
 * @Description:
 * @Date: 2018/8/7上午10:42
 **/
@RequestMapping("/item/cat")
@Controller
public class ItemCatController {

    @Autowired
    ItermCatService itermCatService;

    @RequestMapping("/list")
    @ResponseBody
    private List<EUTreeNode> getCatList(@RequestParam(value = "id", defaultValue = "0")Long parentId) {
        List<EUTreeNode> list = itermCatService.getCatList(parentId);
        System.out.println(list);
        return list;
    }

}
