package cn.itcast.freemarker.test;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 第一个FreeMarker程序（数据+模板=文件输出）
 *      1.操作步骤
 */
public class FreeMarkerTest01 {

    @Test
    public void test01() throws Exception {
        //1.创建FreeMarker的配置类
        Configuration cfg = new Configuration();
        //2.指定模板加载器：将模板存入缓存中
        //文件路径加载器
        FileTemplateLoader ftl = new FileTemplateLoader(new File("templates"));
        cfg.setTemplateLoader(ftl);
        //3.获取模板
        Template template = cfg.getTemplate("template01.ftl");
        //4.构造数据模型
        Map<String,Object> dataModel = new HashMap<>();
        dataModel.put("username","zhangsan");

        //5.文件输出
        /**
         * 处理模型
         *      参数一：数据模型
         *      参数二：writer（FileWriter（文件输出），printWriter（控制台输出））
         */
        //template.process(dataModel,new FileWriter(new File("C:\\Users\\ThinkPad\\Desktop\\ihrm\\day12\\test\\a.txt")));
        template.process(dataModel,new PrintWriter(System.out));
    }
}
