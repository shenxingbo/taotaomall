package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;

/**
 * @Author: shenxingbo
 * @Description:
 * @Date: 2018/8/16下午3:52
 **/

public interface ItemParamService {
    public TaotaoResult getItemParamByCid(long cid);

    public TaotaoResult insertItemParam(TbItemParam itemParam);

    public EUDataGridResult getItemParamList(int page, int rows);
}
