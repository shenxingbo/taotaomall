package com.taotao.controller;

import com.taotao.service.PictureService;
import com.taotao.utils.JsonUtils;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: shenxingbo
 * @Description: 上传图片处理
 * @Date: 2018/8/12上午10:35
 **/
@Controller
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public String pictureUpLoad(MultipartFile uploadFile) {
        System.out.println("进来了");
        Map result = pictureService.uploadPicture(uploadFile);
        //为了保证功能的兼容性，需要把result转换成字符串
        String json = JsonUtils.objectToJson(result);
        return json;
    }

}
