package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckItem;

import java.util.List;

public interface CheckItemDao {
    public void add(CheckItem checkItem);
    public Page<CheckItem> findByCondition(String queryString);
    public long selectCountByCheckItemId(Integer checkItemId);
    public void deleteById(Integer id);
    public CheckItem findById(Integer id);
    public void edit(CheckItem checkItem);
    public List<CheckItem> findAll();
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer checkgroupId);
}
