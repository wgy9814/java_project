package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {
    public void add(CheckGroup checkGroup, Integer[] checkitemIds);
    public PageResult findPage(QueryPageBean pageBean);
    public CheckGroup findById(Integer id);
    public void edit(CheckGroup checkGroup, Integer[] checkitemIds);
    public List<CheckGroup> findAll();
}
