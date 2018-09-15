package com.taotao.search.service.impl;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    SearchDao searchDao;

    @Override
    public SearchResult search(String queryString, int page, int rows) throws Exception {
        SolrQuery query = new SolrQuery();
        query.setQuery(queryString);
        query.setStart((page - 1) * rows);
        query.setRows(rows);

        //设置默认的搜索域
        query.set("df", "item_keywords");
        //设置高亮显示
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<em style=\"color:red\">");
        query.setHighlightSimplePost("</em>");


        SearchResult result = searchDao.search(query);

        //计算查询总页数


        long recordCount = result.getRecordCount();
        long pageCount = recordCount / rows;
        if (recordCount % rows > 0) {
            pageCount += 1;
        }

        result.setPageCount(pageCount);

        result.setCurPage(page) ;
        return result;
    }
}
