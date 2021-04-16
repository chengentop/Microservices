package com.rufeng.common.core.utils;

import com.rufeng.common.core.db.Pager;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionPage {
    /**
     * 分页方法
     *
     * @param list 源数据
     * @return
     */
    public static Map<String, Object> getPageList(List list, Pager page) {
        Map<String, Object> pageMap = new HashMap<>();
        int current = page.getCurrentPage(); // 当前页
        int size = page.getTotalSize();  //每页显示几条
        int total = list.size(); // 总条数
        int pages = total % size;
        List records = new ArrayList<>();
        //计算当前页第一条数据的下标
        int currId = current > 1 ? (current - 1) * size : 0;
        for (int i = 0; i < size && i < total - currId; i++) {
            records.add(list.get(currId + i));
        }
        pageMap.put("current", current);
        pageMap.put("size", size);
        pageMap.put("total", total);
        pageMap.put("pages", pages);
        pageMap.put("records", records);
        return pageMap;
    }
}
