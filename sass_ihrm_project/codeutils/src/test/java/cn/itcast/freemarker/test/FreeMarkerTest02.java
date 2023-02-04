package cn.itcast.freemarker.test;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试字符串模板
 *
 *
 */
public class FreeMarkerTest02 {

    /**
     *  com.${p1}.${p1}.${p1}.User
     *
     */
    @Test
    public void test() throws Exception {
        //1.创建配置对象
        Configuration cfg = new Configuration();
        //2.指定加载器
        cfg.setTemplateLoader(new StringTemplateLoader());
        //3.创建字符串模板
        // i.字符串
        String templateString = "欢迎您：${username}";
        // ii.通过字符串创建模板
        Template template = new Template("name1",new StringReader(templateString),cfg);
        //4.构造数据
        Map<String,Object> dataModel = new HashMap<>();
        dataModel.put("username","张三");
        //5.处理模板
        template.process(dataModel,new PrintWriter(System.out));
    }
}
