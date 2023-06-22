package com.common;

import lombok.Data;

import java.util.HashMap;

//自定义分页查询
@Data
public class QueryPageParam {
    //区别于Map，使用默认值进行查询（当传入不包含页面显示条数以及当前页面时）
    //每页显示数据条数
    private static int PAGE_SIZE = 20;
    //当前页
    private static int PAGE_NUM = 1;

    //当传入的参数不包含下面两者时，采用默认值，否则为0;
    private int pageSize = PAGE_SIZE;
    private int pageNum = PAGE_NUM;
    //定义param将页面的参数都传入这里保存；
    private HashMap param = new HashMap();
}
