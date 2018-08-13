package com.taotao.controller;

import com.taotao.service.PictureService;
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
    public Map pictureUpLoad(MultipartFile uploadFile) {
        Map result = pictureService.uploadPicture(uploadFile);
        return result;
    } 

}
