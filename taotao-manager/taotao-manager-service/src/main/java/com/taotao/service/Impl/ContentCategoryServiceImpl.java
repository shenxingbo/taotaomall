package com.taotao.service.Impl;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: shenxingbo
 * @Description:cms系统图片的分类
 * @Date: 2018/8/19下午4:13
 **/

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EUTreeNode> getCategoryList(long parentId) {

        TbContentCategoryExample example = new TbContentCategoryExample();
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);

        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);

        //zuo做pojo的转变，转变成前端需要的类型
        List<EUTreeNode> resultList = new ArrayList<EUTreeNode>();
     for (TbContentCategory contentCategory: list) {
         EUTreeNode node = new EUTreeNode();
         node.setId(contentCategory.getId());
         node.setText(contentCategory.getName());
         //设定当前节点是否有孩子节点
         node.setState(contentCategory.getIsParent()? "closed":"open");
         resultList.add(node);
     }

        return resultList;
    }

    @Override
    public TaotaoResult insertContentCategory(long parentId, String name) {

        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setName(name);
        contentCategory.setIsParent(false);
        contentCategory.setStatus(1);
        contentCategory.setParentId(parentId);
        contentCategory.setSortOrder(1);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());

        contentCategoryMapper.insert(contentCategory);
        //对父节点更新isParent属性
        TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
        //判断是否为true
        if(!parentCat.getIsParent()) {
            parentCat.setIsParent(true);
            contentCategoryMapper.insert(parentCat);
        }

        return TaotaoResult.ok(contentCategory);
    }
}
