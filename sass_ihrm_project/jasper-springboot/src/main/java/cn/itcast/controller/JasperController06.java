package cn.itcast.controller;

import cn.itcast.domain.User;
import cn.itcast.domain.UserCount;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class JasperController06 {

    /**
     *  charts图形报表
     */
    @GetMapping("/testJasper6")
    public void createPdf(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.引入jasper文件
        Resource resource = new ClassPathResource("templates/testCharts.jasper");
        FileInputStream fis = new FileInputStream(resource.getFile());

        //2.创建JasperPrint,向jasper文件中填充数据
        ServletOutputStream os = response.getOutputStream();
        try {
            Map parameters = new HashMap<>();
            //构建javaBean的数据源
            //1.获取到对象的list集合
            List<UserCount> userList = getUserCountList();
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

    public List<UserCount> getUserCountList() {
        List<UserCount> list = new ArrayList<>();

        UserCount uc1 = new UserCount("传智播客",1000l);
        UserCount uc2 = new UserCount("黑马程序员",1000l);
        UserCount uc3 = new UserCount("baidu",1000l);
        list.add(uc1);
        list.add(uc2);
        list.add(uc3);
        return list;
    }


}
