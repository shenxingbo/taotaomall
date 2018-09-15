package com.taotao.search.controller;


import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//索引库维护
@Controller
public class ItemController {
    //导入商品数据到索引库

    @Autowired
    private ItemService itemService;

    @ResponseBody
    @RequestMapping("manager/importall")
    public TaotaoResult importAllItem() {
        TaotaoResult result = itemService.importAllItems();

        return result;
    }
}
