package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

/**
 * @Author shenxingbo
 * @Description TODO
 * @Date 2018/8/5下午4:47
 **/

public interface ItemService {
    TbItem getItemById(long itemId);
    EUDataGridResult getItemList(int page, int rows);
    TaotaoResult createItem(TbItem tbItem, String desc, String itemParams)throws Exception;
}
