package cn.itcast.controller;

import cn.itcast.domain.UserCount;
import net.sf.jasperreports.engine.*;
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
public class JasperController07 {

    /**
     *  父子报表
     */
    @GetMapping("/testJasper7")
    public void createPdf(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.引入jasper文件
        Resource resource = new ClassPathResource("templates/main01.jasper");
        FileInputStream fis = new FileInputStream(resource.getFile());

        //2.创建JasperPrint,向jasper文件中填充数据
        ServletOutputStream os = response.getOutputStream();
        try {
            Map parameters = new HashMap<>();
            //参数  子报表的路径   子报表需要的数据
            Resource subResout = new ClassPathResource("templates/testCharts.jasper");
            parameters.put("sublist",getUserCountList());//子报表需要的数据
            parameters.put("subpath",subResout.getFile().getPath());// 子报表的路径

            JasperPrint print = JasperFillManager.fillReport(fis,parameters,new JREmptyDataSource());
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
