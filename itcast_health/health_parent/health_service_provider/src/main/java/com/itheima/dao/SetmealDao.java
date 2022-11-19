package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Setmeal;

import java.util.List;
import java.util.Map;
public interface SetmealDao {
    public void add(Setmeal setmeal);
    public void setSetmealAndCheckGroup(Map<String, Integer> map);
    public Page<Setmeal> findByCondition(String queryString);
    public List<Setmeal> findAll();
    public Setmeal findById4Detail(Integer id);
    public List<Integer> findCheckGroupIdsBySetmealId(Integer SetmealId);
    public void edit(Setmeal setmeal);
    public void deleteAssociation(Integer setmealId);
    public List<Map<String, Object>> findSetmealCount();
}
