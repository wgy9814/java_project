package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.constant.MessageConstant;
import com.itheima.dao.CheckItemDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 检查项服务
 */

@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;

    //新增检查项
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    //分页查询
    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();//ThreadLocal
        PageHelper.startPage(currentPage,pageSize);//分页插件，会在执行sql之前将分页关键字追加到SQL后面
        Page<CheckItem> page = checkItemDao.findByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    //根据id删除检查项
    public void deleteById(Integer id) {
        long count = checkItemDao.selectCountByCheckItemId(id);
        if(count > 0){
            //已经被关联，不能删除
            throw new RuntimeException(MessageConstant.DELETE_CHECKITEM_FAIL);
        }else{
            //可以删除
            checkItemDao.deleteById(id);
        }
    }

    public CheckItem findById(Integer id) {
        return checkItemDao.findById(id);
    }

    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }

    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }

    //根据检查组id查询关联的检查项id
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer checkgroupId) {
        return checkItemDao.findCheckItemIdsByCheckGroupId(checkgroupId);
    }
}