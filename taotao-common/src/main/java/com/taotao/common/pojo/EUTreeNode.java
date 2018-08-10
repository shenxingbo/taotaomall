package com.taotao.common.pojo;

/**
 * @Author: shenxingbo
 * @Description: easyui 树形控件节点
 * @Date: 2018/8/7上午10:03
 **/

public class EUTreeNode {
    private long id;
    private String state;
    private String text;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {

        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
