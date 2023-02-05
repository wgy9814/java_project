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
import java.util.HashMap;
import java.util.Map;

@RestController
public class JasperController02 {

    /**
     *  基于parameters以Map的形式填充数据
     */
    @GetMapping("/testJasper2")
    public void createPdf(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.引入jasper文件
        Resource resource = new ClassPathResource("templates/testParam.jasper");
        FileInputStream fis = new FileInputStream(resource.getFile());


        //2.创建JasperPrint,向jasper文件中填充数据
        ServletOutputStream os = response.getOutputStream();
        try {
            Map parameters = new HashMap<>();
            //设置参数 参数的key = 模板中使用的parameters参数的name
            parameters.put("username","张三");
            parameters.put("mobile","120");
            parameters.put("dept","讲师");
            parameters.put("company","传智播客");

            JasperPrint print = JasperFillManager.fillReport(fis,parameters,new JREmptyDataSource());
            JasperExportManager.exportReportToPdfStream(print,os);
        } catch (JRException e) {
            e.printStackTrace();
        }finally {
            os.flush();
        }
    }
}
