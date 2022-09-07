package com.itheima.mapper;

import com.itheima.pojo.User;

import javax.xml.soap.Detail;
import java.util.List;

public interface UserMapper {
    List<User> selectAll();
}
