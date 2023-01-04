package com.itheima.framework.context;

import com.itheima.framework.beans.factory.BeanFactory;

/**
 * @version v1.0
 * @ClassName: ApplicationContext
 * @Description: 定义非延时加载功能
 * @Author: 黑马程序员
 */
public interface ApplicationContext extends BeanFactory {

    void refresh() throws Exception;
}
