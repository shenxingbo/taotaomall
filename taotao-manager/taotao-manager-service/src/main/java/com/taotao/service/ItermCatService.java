package com.taotao.service;

import com.taotao.common.pojo.EUTreeNode;
import java.util.List;

public interface ItermCatService {
     List<EUTreeNode> getCatList(long parentId);
}
