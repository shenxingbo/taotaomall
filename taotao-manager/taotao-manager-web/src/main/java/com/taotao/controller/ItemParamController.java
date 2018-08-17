package com.taotao.controller;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: shenxingbo
 * @Description:
 * @Date: 2018/8/16下午4:11
 **/
@Controller
@RequestMapping("/item/param")
public class ItemParamController {

    @Autowired
    ItemParamService itemParamService;

    @RequestMapping("/query/itemcatid/{itemCatId}")
    @ResponseBody
    public TaotaoResult getItemParamByCid(@PathVariable Long itemCatId) {
        TaotaoResult taotaoResult = itemParamService.getItemParamByCid(itemCatId);
        return  taotaoResult;
    }

    @RequestMapping("/save/{cid}")
    @ResponseBody
    public TaotaoResult insertItemParam (@PathVariable Long cid, String paramData) {
        TbItemParam tbItemParam = new TbItemParam();
        tbItemParam.setItemCatId(cid);
        tbItemParam.setParamData(paramData);
        TaotaoResult result = itemParamService.insertItemParam(tbItemParam);
        return result;
    }


    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getParamList(Integer page, Integer rows) {
        EUDataGridResult result = itemParamService.getItemParamList(page, rows);
        return result;
    }
}
