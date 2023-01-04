package com.itheima.framework.beans.factory;

/**
 * @version v1.0
 * @ClassName: BeanFactory
 * @Description: IOC容器父接口
 * @Author: 黑马程序员
 */
public interface BeanFactory {

    Object getBean(String name) throws Exception;

    <T> T getBean(String name, Class<? extends T> clazz) throws Exception;
}
