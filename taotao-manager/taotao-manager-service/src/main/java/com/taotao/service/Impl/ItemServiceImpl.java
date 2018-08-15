package com.taotao.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemService;
import com.taotao.utils.IDUtils;
import java.util.Date;
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

    @Autowired
    private TbItemDescMapper itemDescMapper;

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

    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        TbItemExample tbItemExample = new TbItemExample();
        PageHelper.startPage(page, rows);
        List<TbItem> list = itemMapper.selectByExample(tbItemExample);
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);

        PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public TaotaoResult createItem(TbItem tbItem, String desc) throws Exception{

        long itemId = IDUtils.genItemId();
        tbItem.setId(itemId);

        //下面设置商品的状态 1 正常  2 下架 3 删除
        tbItem.setStatus((byte) 1);
        tbItem.setCreated(new Date());
        tbItem.setUpdated(new Date());
        itemMapper.insert(tbItem);
        //插入描述
        if (insertItemDesc(itemId,desc).getStatus() != 200) {
            throw new Exception();
        }
        return TaotaoResult.ok();
    }

    public TaotaoResult insertItemDesc(Long itemId, String desc) {
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setItemDesc(desc);
        itemDescMapper.insert(tbItemDesc);
        return  TaotaoResult.ok();
    }



}
