package com.taotao.common.pojo;

import java.util.List;

/**
 * @Author: shenxingbo
 * @Description:
 * @Date: 2018/8/6下午6:04
 **/

public class EUDataGridResult {
     private long total;
     private List<?> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
