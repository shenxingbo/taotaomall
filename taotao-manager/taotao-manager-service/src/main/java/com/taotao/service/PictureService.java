package com.taotao.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: shenxingbo
 * @Description:
 * @Date: 2018/8/10下午5:54
 **/

public interface PictureService {

    void uploadPicture(MultipartFile uploadFile) throws IOException;
}
