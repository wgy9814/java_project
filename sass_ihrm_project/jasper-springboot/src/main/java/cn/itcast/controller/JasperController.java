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

@RestController
public class JasperController {

    @GetMapping("/testJasper")
    public void createPdf(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.引入jasper文件
        Resource resource = new ClassPathResource("templates/test.jasper");
        FileInputStream fis = new FileInputStream(resource.getFile());
        //2.创建JasperPrint,向jasper文件中填充数据

        ServletOutputStream os = response.getOutputStream();
        try {
            /**
             * fis: jasper 文件输入流
             * new HashMap ：向模板中输入的参数
             * JasperDataSource：数据源（和数据库数据源不同）
             *              填充模板的数据来源（connection，javaBean，Map）
             *              填充空数据来源：JREmptyDataSource
             */
            JasperPrint print = JasperFillManager.fillReport(fis, new HashMap<>(),new JREmptyDataSource());
            //3.将JasperPrint已PDF的形式输出
            JasperExportManager.exportReportToPdfStream(print,os);

        } catch (JRException e) {
            e.printStackTrace();
        }finally {
            os.flush();
        }
    }
}
