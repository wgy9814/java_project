package com.itheima.framework.utils;

/**
 * @version v1.0
 * @ClassName: StringUtils
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 黑马程序员
 */
public class StringUtils {
    private StringUtils() {

    }

    // userDao   ==>   setUserDao
    public static String getSetterMethodByFieldName(String fieldName) {
        String methodName = "set" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
        return methodName;
    }
}
