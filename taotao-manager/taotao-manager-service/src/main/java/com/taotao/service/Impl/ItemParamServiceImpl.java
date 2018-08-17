package com.taotao.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.ItemParamService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: shenxingbo
 * @Description:
 * @Date: 2018/8/16下午3:55
 **/
@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    TbItemParamMapper itemParamMapper;
    @Override
    public TaotaoResult getItemParamByCid(long cid) {

        TbItemParamExample example = new TbItemParamExample();
        Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
        if (list != null && list.size() > 0) {
            TaotaoResult result = TaotaoResult.ok(list.get(0));
            return result;
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult insertItemParam(TbItemParam itemParam) {
        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());
        itemParamMapper.insert(itemParam);
        return TaotaoResult.ok();
    }

    @Override
    public EUDataGridResult getItemParamList(int page, int rows) {
        TbItemParamExample example = new TbItemParamExample();
        PageHelper.startPage(page,rows);
        List<TbItemParam> list = itemParamMapper.selectByExample(example);

        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);

        PageInfo<TbItemParam> paramPageInfo = new PageInfo<TbItemParam>(list);
        result.setTotal(paramPageInfo.getTotal());
        return result;
    }
}
