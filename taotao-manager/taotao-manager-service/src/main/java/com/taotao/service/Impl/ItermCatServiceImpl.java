package com.taotao.service.Impl;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItermCatService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: shenxingbo
 * @Description: 商品的分类管理
 * @Date: 2018/8/7上午10:09
 **/

@Service
public class ItermCatServiceImpl implements ItermCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<EUTreeNode> getCatList(long parentId) {

        TbItemCatExample example = new TbItemCatExample();
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> tbItemCatList = tbItemCatMapper.selectByExample(example);
        List<EUTreeNode> list = new ArrayList<EUTreeNode>();
        System.out.println(tbItemCatList.size());
        for (TbItemCat tbItemCat : tbItemCatList) {
            EUTreeNode euTreeNode = new EUTreeNode();
            euTreeNode.setId(tbItemCat.getId());
            euTreeNode.setText(tbItemCat.getName());
            euTreeNode.setState(tbItemCat.getIsParent()? "closed":"open");
            list.add(euTreeNode);
        }
       return list;
    }
}
