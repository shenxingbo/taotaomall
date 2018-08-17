package com.taotao.service.Impl;

import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItemExample.Criteria;
import com.taotao.service.ItemParamItemService;
import com.taotao.utils.JsonUtils;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: shenxingbo
 * @Description:
 * @Date: 2018/8/17下午5:37
 **/
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

    @Autowired
    TbItemMapper itemMapper;

    @Autowired
    TbItemDescMapper itemDescMapper;

    @Autowired
    TbItemParamItemMapper itemParamItemMapper;

    @Override
    public String getItemParamByItemId(Long itemId) {
        TbItemParamItemExample example = new TbItemParamItemExample();
        Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);

        if (list == null || list.size() == 0 ) {
            return "";
        }

        String paramData = list.get(0).getParamData();
        List<Map> paramDataList = JsonUtils.jsonToList(paramData,Map.class);
        //下面拼html
        StringBuffer sb = new StringBuffer();
        sb.append("<table c);ellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">");
        sb.append("	<tbody>");
        for (Map m1 : paramDataList) {
            sb.append("		<tr>");
            sb.append("			<th class=\"tdTitle\" colspan=\"2\">" + m1.get("group")+ "</th>");
            sb.append("		</tr>");
            List<Map> list2 = (List<Map>) m1.get("params");

            for (Map m2 : list2) {
                sb.append("		<tr>");
                sb.append("			<td class=\"tdTitle\">"+ m2.get("k")+"</td>");
                sb.append("			<td>"+m2.get("v")+"</td>");
                sb.append("		</tr>");
            }

        }
        sb.append("	</tbody>");
        sb.append("</table>");
        return sb.toString();
    }
}
