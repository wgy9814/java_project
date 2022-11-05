package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckGroupDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 检查组服务
 */
@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;
    //新增检查组，同时需要关联检查项（设置多对多关系）
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        //新增检查组
        checkGroupDao.add(checkGroup);
        Integer checkGroupId = checkGroup.getId();//检查组id

        //设置检查组和检查项多对多关系
        this.setCheckGroupAndCheckItem(checkGroupId,checkitemIds);
    }

    //分页查询
    public PageResult findPage(QueryPageBean pageBean) {
        Integer currentPage = pageBean.getCurrentPage();
        Integer pageSize = pageBean.getPageSize();
        String queryString = pageBean.getQueryString();
        //使用分页助手插件实现分页查询
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckGroup> page = checkGroupDao.findByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public CheckGroup findById(Integer id) {
        return checkGroupDao.findById(id);
    }

    //编辑检查项信息，同时需要设置关联关系
    public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
        //基本信息修改
        checkGroupDao.edit(checkGroup);
        //清理检查组和检查项的关联关系（操作中间关系表）
        checkGroupDao.deleteAssociation(checkGroup.getId());
        //重新建立检查组和检查项的关联关系
        this.setCheckGroupAndCheckItem(checkGroup.getId(),checkitemIds);
    }

    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }

    //设置检查组和检查项多对多关系
    public void setCheckGroupAndCheckItem(Integer checkGroupId,Integer[] checkitemIds){
        //设置多对多关系
        if(checkitemIds != null && checkitemIds.length > 0){
            for (Integer checkitemId : checkitemIds) {//检查项id
                Map<String,Integer> map = new HashMap<>();
                map.put("checkgroupId",checkGroupId);
                map.put("checkitemId",checkitemId);
                checkGroupDao.setCheckGroupAndCheckItem(map);
            }
        }
    }
}
