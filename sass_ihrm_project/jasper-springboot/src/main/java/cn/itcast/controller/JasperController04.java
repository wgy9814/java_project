package cn.itcast.controller;

import cn.itcast.domain.User;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class JasperController04 {

    /**
     *  基于javaBean数据源的形式填充数据
     */
    @GetMapping("/testJasper4")
    public void createPdf(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.引入jasper文件
        Resource resource = new ClassPathResource("templates/testBean.jasper");
        FileInputStream fis = new FileInputStream(resource.getFile());

        //2.创建JasperPrint,向jasper文件中填充数据
        ServletOutputStream os = response.getOutputStream();
        try {
            Map parameters = new HashMap<>();
            //构建javaBean的数据源
            //1.获取到对象的list集合
            List<User> userList = getUserList();
            //2.通过list集合创建javaBean的数据源对象
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(userList);
            JasperPrint print = JasperFillManager.fillReport(fis,parameters,ds);
            JasperExportManager.exportReportToPdfStream(print,os);
        } catch (JRException e) {
            e.printStackTrace();
        }finally {
            os.flush();
        }
    }

    public List<User> getUserList() {
        List<User> list = new ArrayList<>();
        for(int i=0;i<10;i++) {
            User user = new User(i+"", "用户"+i, "传智播客","讲师", "1380000000"+i);
            list.add(user);
        }
        return list;
    }


}
