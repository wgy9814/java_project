package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealService {
    public void add(Setmeal setmeal,Integer[] checkgroupIds);
    public PageResult findPage(QueryPageBean queryPageBean);
    public void edit(Setmeal setmeal,Integer[] checkgroupIds);
    public List<Setmeal> findAll();
    public Setmeal findById(Integer id);
    public List<Integer> findCheckGroupIdsBySetmealId(Integer SetmealId);
    public List<Map<String,Object>> findSetmealCount();
}
