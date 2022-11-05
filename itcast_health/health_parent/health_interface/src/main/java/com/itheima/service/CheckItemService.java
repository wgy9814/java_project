package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;

import java.util.List;

/**
 * @author: wuguangyuan
 * @create-date: 2022/11/2 23:51
 */
//服务接口
public interface CheckItemService {
    public void add(CheckItem checkItem);
    PageResult findPage(QueryPageBean queryPageBean);
    public void deleteById(Integer id);
    public void edit(CheckItem checkItem);
    public CheckItem findById(Integer id);
    public List<CheckItem> findAll();
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer checkgroupId);
}
