package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

/**
 * @Author: shenxingbo
 * @Description:页面内容的service
 * @Date: 2018/8/19下午5:44
 **/

public interface ContentService {
    public TaotaoResult insertContent(TbContent content);
}
