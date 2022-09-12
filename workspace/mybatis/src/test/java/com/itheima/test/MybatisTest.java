package com.itheima.test;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wuguangyuan
 * @create-date: 2022/9/10 12:41
 */
public class MybatisTest {

    @Test
    public void testSelectAll() throws IOException {
        //1．获取SqLSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputstream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlsessionFactory = new SqlSessionFactoryBuilder().build(inputstream);
        //2．获取SqlSession对象
        SqlSession sqlsession = sqlsessionFactory.openSession();
        //3．获取Napper接口的代理对象
        BrandMapper brandMapper = sqlsession.getMapper(BrandMapper.class);
        //6．执行方法
        List<Brand> brands = brandMapper.selectAll();
        System.out.println(brands) ;
        //5．释放资源
        sqlsession.close();

    }

    @Test
    public void testSelectByID() throws IOException {
        //接收参数
        int id = 2;


        //1．获取SqLSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputstream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlsessionFactory = new SqlSessionFactoryBuilder().build(inputstream);
        //2．获取SqlSession对象
        SqlSession sqlsession = sqlsessionFactory.openSession();
        //3．获取Napper接口的代理对象
        BrandMapper brandMapper = sqlsession.getMapper(BrandMapper.class);
        //6．执行方法
        Brand brand = brandMapper.selectById(id);
        System.out.println(brand) ;
        //5．释放资源
        sqlsession.close();

    }

    @Test
    public void testSelectByCondition() throws IOException {
        //接收参数
        int status = 1;
        String company_name ="华为";
        String brand_name = "华为";

        //1.处理参数
        company_name = "%" +company_name + "%";
        brand_name = "%" +brand_name + "%";

        //2.封装参数
//        Brand brand = new Brand();
//        brand.setStatus(status);
//        brand.setCompany_name(company_name);
//        brand.setBrand_name(brand_name);

        //3..map集合封装参数
        Map map = new HashMap();
//        map.put("status", status);
//        map.put("company_name", company_name);
//        map.put("brand_name", brand_name);




        //1．获取SqLSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputstream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlsessionFactory = new SqlSessionFactoryBuilder().build(inputstream);
        //2．获取SqlSession对象
        SqlSession sqlsession = sqlsessionFactory.openSession();
        //3．获取Napper接口的代理对象
        BrandMapper brandMapper = sqlsession.getMapper(BrandMapper.class);
        //6．执行方法
        //6.1
//        List<Brand> brands = brandMapper.selectByCondition(status,companyName,brandName);

        //6.2
//        List<Brand> brands = brandMapper.selectByCondition(brand);

        //6.3
        List<Brand> brands = brandMapper.selectByCondition(map);

        System.out.println(brands) ;
        //5．释放资源
        sqlsession.close();

    }



    @Test
    public void testselectByConditionSingle() throws IOException {
        //接收参数
        int status = 1;
        String company_name ="华为";
        String brand_name = "华为";

        //1.处理参数
        company_name = "%" +company_name + "%";
        brand_name = "%" +brand_name + "%";

        //2.封装参数
        Brand brand = new Brand();
        brand.setStatus(status);
//        brand.setCompany_name(company_name);
//        brand.setBrand_name(brand_name);





        //1．获取SqLSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputstream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlsessionFactory = new SqlSessionFactoryBuilder().build(inputstream);
        //2．获取SqlSession对象
        SqlSession sqlsession = sqlsessionFactory.openSession();
        //3．获取Napper接口的代理对象
        BrandMapper brandMapper = sqlsession.getMapper(BrandMapper.class);
        //6．执行方法
        //6.1
//        List<Brand> brands = brandMapper.selectByCondition(status,companyName,brandName);

        //6.2
        List<Brand> brands = brandMapper.selectByConditionSingle(brand);



        System.out.println(brands) ;
        //5．释放资源
        sqlsession.close();

    }



    @Test
    public void testAdd() throws IOException {
        //接收参数
        int status = 1;
        String companyName = "波导手机";
        String brandName = "波导";
        String description = "手机中的战斗机";
        int orderer = 100;




        //2.封装参数
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setCompany_name(companyName);
        brand.setBrand_name(brandName);
        brand.setDescription(description);
        brand.setOrderer(orderer);





        //1．获取SqLSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputstream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlsessionFactory = new SqlSessionFactoryBuilder().build(inputstream);
        //2．获取SqlSession对象
//        SqlSession sqlsession = sqlsessionFactory.openSession();
        SqlSession sqlsession = sqlsessionFactory.openSession(true);
        //3．获取Napper接口的代理对象
        BrandMapper brandMapper = sqlsession.getMapper(BrandMapper.class);
        //4．执行方法

        brandMapper.add(brand);

        sqlsession.commit();

        //5．释放资源
        sqlsession.close();

    }


    @Test
    public void testAdd2() throws IOException {
        //接收参数
        int status = 1;
        String companyName = "波导手机";
        String brandName = "波导";
        String description = "手机中的战斗机";
        int orderer = 100;




        //2.封装参数
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setCompany_name(companyName);
        brand.setBrand_name(brandName);
        brand.setDescription(description);
        brand.setOrderer(orderer);





        //1．获取SqLSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputstream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlsessionFactory = new SqlSessionFactoryBuilder().build(inputstream);
        //2．获取SqlSession对象
//        SqlSession sqlsession = sqlsessionFactory.openSession();
        SqlSession sqlsession = sqlsessionFactory.openSession(true);
        //3．获取Napper接口的代理对象
        BrandMapper brandMapper = sqlsession.getMapper(BrandMapper.class);
        //4．执行方法

        brandMapper.add(brand);
        Integer id = brand.getId();
        System.out.println(id);

        sqlsession.commit();

        //5．释放资源
        sqlsession.close();

    }



    @Test
    public void testUpdate() throws IOException {
        //接收参数
        int status = 1;
        String companyName = "波导手机";
        String brandName = "波导666";
        String description = "手机中的战斗机";
        int orderer = 200;
        int id = 6;




        //2.封装参数
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setCompany_name(companyName);
        brand.setBrand_name(brandName);
        brand.setDescription(description);
        brand.setOrderer(orderer);
        brand.setId(id);





        //1．获取SqLSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputstream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlsessionFactory = new SqlSessionFactoryBuilder().build(inputstream);
        //2．获取SqlSession对象
//        SqlSession sqlsession = sqlsessionFactory.openSession();
        SqlSession sqlsession = sqlsessionFactory.openSession(true);
        //3．获取Napper接口的代理对象
        BrandMapper brandMapper = sqlsession.getMapper(BrandMapper.class);
        //4．执行方法

        int count = brandMapper.update(brand);
        System.out.println(count);

        //事务
        sqlsession.commit();

        //5．释放资源
        sqlsession.close();

    }



    @Test
    public void testdeleteById() throws IOException {
        //接收参数
//        int status = 1;
//        String companyName = "波导手机";
//        String brandName = "波导666";
//        String description = "手机中的战斗机";
//        int orderer = 200;
        int id = 6;






        //1．获取SqLSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputstream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlsessionFactory = new SqlSessionFactoryBuilder().build(inputstream);
        //2．获取SqlSession对象
//        SqlSession sqlsession = sqlsessionFactory.openSession();
        SqlSession sqlsession = sqlsessionFactory.openSession(true);
        //3．获取Napper接口的代理对象
        BrandMapper brandMapper = sqlsession.getMapper(BrandMapper.class);
        //4．执行方法

        brandMapper.deleteById(id);

        //事务
        sqlsession.commit();

        //5．释放资源
        sqlsession.close();

    }



    @Test
    public void testdeleteByIds() throws IOException {
        //接收参数
//        int status = 1;
//        String companyName = "波导手机";
//        String brandName = "波导666";
//        String description = "手机中的战斗机";
//        int orderer = 200;
        int[] ids = {8,9};






        //1．获取SqLSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputstream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlsessionFactory = new SqlSessionFactoryBuilder().build(inputstream);
        //2．获取SqlSession对象
//        SqlSession sqlsession = sqlsessionFactory.openSession();
        SqlSession sqlsession = sqlsessionFactory.openSession(true);
        //3．获取Napper接口的代理对象
        BrandMapper brandMapper = sqlsession.getMapper(BrandMapper.class);
        //4．执行方法

        brandMapper.deleteByIds(ids);

        //事务
        sqlsession.commit();

        //5．释放资源
        sqlsession.close();

    }
}
