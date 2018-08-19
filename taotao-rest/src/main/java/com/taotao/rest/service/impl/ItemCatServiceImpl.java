package com.taotao.rest.service.impl;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: shenxingbo
 * @Description:
 * @Date: 2018/8/18下午11:35
 **/

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    TbItemCatMapper itemCatMapper;

    @Override
    public CatResult getItemCatList() {
        CatResult catResult = new CatResult();
        catResult.setData(getCatList(0));
        return catResult;
    }

    private List<?> getCatList(long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list = itemCatMapper.selectByExample(example);

        List result = new ArrayList();
        int count = 0;
        for (TbItemCat tbItemCat : list) {

            if (tbItemCat.getIsParent()) {
                CatNode catNode = new CatNode();

                if(parentId == 0) {
                    catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
                }else {
                    catNode.setName(tbItemCat.getName());
                }
                catNode.setUrl("/products/"+tbItemCat.getId()+".html");
                catNode.setItem(getCatList(tbItemCat.getId()));
                catNode.setItem(getCatList(tbItemCat.getId()));
                result.add(catNode);
                count++;

                if(count >= 14 && parentId == 0) {
                    break;
                }
            }else {
                result.add("/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName());
            }
        }
        return result;
    }
}
