package com.taotao.service.Impl;

import com.taotao.service.PictureService;
import com.taotao.utils.FtpUtil;
import com.taotao.utils.IDUtils;
import java.util.HashMap;
import java.util.Map;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: shenxingbo
 * @Description:
 * @Date: 2018/8/10下午5:55
 **/


public class PictureServiceImpl implements PictureService {

    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;

    @Value("${FTP_PORT}")
    private int FTP_PORT;

    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;

    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;

    @Value("${FTP_BASEPATH}")
    private String FTP_BASEPATH;

    @Value("$(IMAGE_BASE_URL)")
    private String IMAGE_BASE_URL;

    @Override
    public Map uploadPicture(MultipartFile uploadFile) {
        Map<String, String> map = new HashMap<String,String>();
        try{
            String imagePath = new DateTime().toString("/yyyy/MM/dd");
            String oldName = uploadFile.getOriginalFilename();
            //对文件进行重新的命名
            String newName = IDUtils.genImageName();
            newName = newName + oldName.substring(oldName.lastIndexOf("."));
            boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASEPATH, imagePath, newName, uploadFile.getInputStream());

            if (!result) {
                map.put("error", "1");
                map.put("message", "文件上传失败");
                return map;
            }

            map.put("error", "1");
            map.put("url", IMAGE_BASE_URL+ imagePath + "/" + newName);
        }catch (Exception e) {
            System.out.println("文件传输错误！！");
        }

        return map;

    }
}
