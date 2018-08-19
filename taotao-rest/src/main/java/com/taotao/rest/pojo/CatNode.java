package com.taotao.rest.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * @Author: shenxingbo
 * @Description:
 * @Date: 2018/8/18下午11:26
 **/

public class CatNode {

    @JsonProperty("n")
    private String name;

    @JsonProperty("u")
    private String url;

    @JsonProperty("i")
    private List<?>item; //这个问号用的非常的好

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<?> getItem() {
        return item;
    }

    public void setItem(List<?> item) {
        this.item = item;
    }
}
