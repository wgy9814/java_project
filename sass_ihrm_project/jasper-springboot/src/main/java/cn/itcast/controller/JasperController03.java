package cn.itcast.controller;

import net.sf.jasperreports.engine.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

@RestController
public class JasperController03 {

    /**
     *  基于JDBC数据源的形式填充数据
     */
    @GetMapping("/testJasper3")
    public void createPdf(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.引入jasper文件
        Resource resource = new ClassPathResource("templates/testConn.jasper");
        FileInputStream fis = new FileInputStream(resource.getFile());

        //2.创建JasperPrint,向jasper文件中填充数据
        ServletOutputStream os = response.getOutputStream();
        try {
            Map parameters = new HashMap<>();
            //查询企业id为1的所有用户
            parameters.put("cid","1");

            //获取数据库连接
            Connection conn= getConnection();
            JasperPrint print = JasperFillManager.fillReport(fis,parameters,conn);
            JasperExportManager.exportReportToPdfStream(print,os);
        } catch (JRException e) {
            e.printStackTrace();
        }finally {
            os.flush();
        }
    }

    public Connection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ihrm","root","111111");
        return conn;
    }
}
