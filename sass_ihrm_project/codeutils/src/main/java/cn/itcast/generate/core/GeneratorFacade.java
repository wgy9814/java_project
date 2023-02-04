package cn.itcast.generate.core;

import cn.itcast.generate.entity.DataBase;
import cn.itcast.generate.entity.Settings;
import cn.itcast.generate.entity.Table;
import cn.itcast.generate.utils.DataBaseUtils;
import cn.itcast.generate.utils.PropertiesUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1.采集用户UI界面输入的数据
 *      模板位置
 *      代码生成路径
 *      工程配置对象 setting
 *      数据库对象   DataBase
 * 2.准备数据模型
 *      1.自定义配置
 *      2.元数据
 *      3.setting
 * 3.调用核心处理类完成代码生成工作
 *      方法：Generator
 */
public class GeneratorFacade {

    private String templatePath;
    private String outPath;
    private Settings settings;
    private DataBase db;
    private Generator generator;

    public GeneratorFacade(String templatePath, String outPath, Settings settings, DataBase db) throws Exception {
        this.templatePath = templatePath;
        this.outPath = outPath;
        this.settings = settings;
        this.db = db;
        this.generator = new Generator(templatePath,outPath);
    }

    /**
     * 1.准备数据模型
     * 2.调用核心处理类完成代码生成工作
     */
    public void generatorByDataBase() throws  Exception {
        List<Table> tables = DataBaseUtils.getDbInfo(db);
        for (Table table : tables) {
            //对每一个Table对象进行代码生成
            /**
             * 数据模型
             * 调用Generator核心处理类
             */
            Map<String,Object> dataModel = getDataModel(table);
//
//            for(Map.Entry<String,Object> entry:dataModel.entrySet()) {
//                System.out.println(entry.getKey() + "--" + entry.getValue());
//            }
//            System.out.println("------------------------");
            generator.scanAndGenerator(dataModel);
        }
    }

    /**
     * 根据table对象获取数据模型
     */
    private  Map<String,Object> getDataModel(Table table) {
        Map<String,Object> dataModel = new HashMap<>();
        //1.自定义配置
        dataModel.putAll(PropertiesUtils.customMap);
        //2.元数据
        dataModel.put("table",table);  //table.name2
        //3.setting
        dataModel.putAll(this.settings.getSettingMap());
        //4.类型
        dataModel.put("ClassName",table.getName2());
        return dataModel;
    }
}
