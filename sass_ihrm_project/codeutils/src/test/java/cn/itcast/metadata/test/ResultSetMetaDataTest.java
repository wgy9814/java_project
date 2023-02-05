package cn.itcast.metadata.test;

import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.Properties;

/**
 * 测试结果集元数据（ResultSetMetaData）
 *      通过ResultSet获取
 *      获取查询结果的信息
 */
public class ResultSetMetaDataTest {

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


    @Test
    public void test() throws Exception {
        //sql
        String sql = "select * from bs_user where id=?";
        //PreparedStatement
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, "1063705482939731968");
        //查询
        ResultSet rs = pstmt.executeQuery();

        //获取结果集元数据
        ResultSetMetaData metaData = rs.getMetaData();

        //获取查询字段个数
        int count = metaData.getColumnCount();

        for(int i =1;i<=count ;i++) {
            //获取列名
            String columnName = metaData.getColumnName(i);
            //获取字段类型 sql类型
            String columnType = metaData.getColumnTypeName(i);
            //获取java类型
            String columnClassName = metaData.getColumnClassName(i);
            System.out.println(columnName+"--"+columnType+"---"+columnClassName);
        }
    }

}
