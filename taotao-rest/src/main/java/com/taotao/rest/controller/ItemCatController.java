package com.taotao.rest.controller;

import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.impl.ItemCatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: shenxingbo
 * @Description:
 * @Date: 2018/8/19上午11:17
 **/
@Controller
public class ItemCatController {

    @Autowired
    ItemCatServiceImpl itemCatService;

//    @ResponseBody
//    @RequestMapping(value = "/itemcat/list", produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
//    public String getItemCatList(String callback) {
//        System.out.println("我进来了lelelele。。。。" + callback);
//        CatResult catResult = itemCatService.getItemCatList();
//
//        String json = JsonUtils.objectToJson(catResult);
//        //拼装返回值
//        String result = callback + "(" + json + ");";
//
//        System.out.println(result);
//        return result;
//    }


    @RequestMapping("/itemcat/list")
    @ResponseBody
    public Object getItemCatList(String callback) {
        CatResult catResult = itemCatService.getItemCatList();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }

}
