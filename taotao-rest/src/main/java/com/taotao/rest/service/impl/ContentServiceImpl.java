package com.taotao.rest.service.impl;

import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ContentService;
import java.util.List;

import com.taotao.utils.JsonUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author: shenxingbo
 * @Description:
 * @Date: 2018/8/28下午11:04
 **/
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    TbContentMapper contentMapper;

    @Autowired
    JedisClient jedisClient;

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;

    @Override
    public List<TbContent> getContentList(long contentCid) {
        //从缓存中取

        try {
            String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, contentCid + "");
            if (!StringUtils.isBlank(result)) {
                 List<TbContent> resultList = JsonUtils.jsonToList(result, TbContent.class);
                 return resultList;
            }
        }catch (Exception e) {

        }

        TbContentExample example = new TbContentExample();
        Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(contentCid);
        List<TbContent> list = contentMapper.selectByExample(example);
        //向缓存中添加内容
        try {
            String cacheString = JsonUtils.objectToJson(list);
            jedisClient.hset(INDEX_CONTENT_REDIS_KEY,contentCid + "",cacheString);
        }catch (Exception e ) {

        }

        return list;
    }
}
