package cn.itcast.metadata.test;

import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.Properties;

/**
 * 测试参数元数据（ParameterMetaData）
 *      通过PreparedStatement获取
 *      获取sql参数中的属性信息
 */
public class ParameterMetaDataTest {

    private Connection connection;

    @Before
    public void init() throws Exception {

        String driver ="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://127.0.0.1:3306/ihrm?useUnicode=true&characterEncoding=utf8";
        String username="root";
        String password="111111";

        Properties props = new Properties();
        props.put("remarksReporting","true");//获取数据库的备注信息
        props.put("user",username);
        props.put("password",password);

        //1.获取连接
        Class.forName(driver);//注册驱动
        connection = DriverManager.getConnection(url,props);
    }

    //sql = SELECT * FROM bs_user WHERE id="1063705482939731968"
    @Test
    public void test() throws Exception {
        String sql = "select * from bs_user where id=?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, "1063705482939731968");

        //获取参数元数据
        ParameterMetaData metaData = pstmt.getParameterMetaData();

        int count = metaData.getParameterCount();

        System.out.println(count);
    }
}
