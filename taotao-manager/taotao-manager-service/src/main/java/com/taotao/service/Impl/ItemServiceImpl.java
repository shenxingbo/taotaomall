package com.taotao.service.Impl;

import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author shenxingbo
 * @Description 商品管理service
 * @Date 2018/8/5下午4:51
 **/

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public TbItem getItemById(long itemId) {
//        下面这是根据id查询
//        TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);

        //下面根据查询条件进行查询,这个是真的好
        //如果不加查询条件，默认是查询所有的
        TbItemExample tbItemExample = new TbItemExample();
        Criteria criteria = tbItemExample.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> list = itemMapper.selectByExample(tbItemExample);

        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;

    }
}
