package com.taotao.service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import java.util.List;

/**
 * @Author: shenxingbo
 * @Description: 这里主要是cms系统图片的分类
 * @Date: 2018/8/19下午3:09
 **/

public interface ContentCategoryService {
    public List<EUTreeNode> getCategoryList(long parentId);

    public TaotaoResult insertContentCategory(long parentId, String name);
}
