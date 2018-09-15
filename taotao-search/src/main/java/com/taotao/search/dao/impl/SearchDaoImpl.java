package com.taotao.search.dao.impl;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.Item;
import com.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrResponse;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SearchDaoImpl implements SearchDao {

    @Autowired
    private SolrServer solrServer;


    @Override
    public SearchResult search(SolrQuery query) throws Exception {
        SearchResult result = new SearchResult();

        QueryResponse response = solrServer.query(query);


        SolrDocumentList documentList = response.getResults();

        result.setRecordCount(documentList.getNumFound());
        List<Item> itemList = new ArrayList<Item>();

        //取高亮显示
        Map<String, Map<String, List<String>>> hightlighting = response.getHighlighting();

        for (SolrDocument document : documentList) {
            Item item = new Item();

            item.setId((String)document.get("id"));
            //取高亮显示的结果
            String title = "";
            List<String> list = hightlighting.get(document.get("id")).get("item_title");
            if (list != null && list.size() > 0) {
                title = list.get(0);
            }else {
                title = (String)document.get("item_title");
            }

            item.setTitle(title);
            item.setImage((String)document.get("item_image"));
            item.setPrice((long)document.get("item_price"));
            item.setSell_point((String)document.get("item_sell_point"));
            item.setCategory_name((String) document.get("item_category_name"));
            itemList.add(item);
        }

        result.setItemList(itemList);
        return result;
    }
}
