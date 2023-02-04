package cn.itcast.test;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.util.Base64Util;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class FaceTest {

    private AipFace client;

    @Before
    public void init() {
        //1.创建java代码和百度云交互的client对象
        client = new AipFace("30098787","eENCL0fxObZiHnAyhkTI9ZMo","fyNDXoHVl9MYrPVFwVjDyb3O0tnEB82H");
    }

    //人脸注册:向百度的人脸库中添加用户人脸照片
    @Test
    public void testFaceRegister() throws Exception {
        //2.参数设置
        HashMap<String,String> options = new HashMap<>();
        options.put("quality_control","NORMAL");//图片质量  NONE  LOW  NORMAL，HIGH
        options.put("liveness_control","LOW");//活体检测
        options.put("action_type","replace");//活体检测
        //3.构造图片
        String path = "E:\\进阶课程\\黑马程序员课件\\1、黑马程序员SaaS移动办公iHRM人力资源管理系统\\day11\\资源\\照片\\001.png";
        //上传的图片  两种格式 ： url地址，Base64字符串形式
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        String encode = Base64Util.encode(bytes);
        //4.调用api方法完成人脸注册
        /**
         * 参数一：（图片的url或者图片的Base64字符串），
         * 参数二：图片形式（URL,BASE64）
         * 参数三：组ID（固定字符串）
         * 参数四：用户ID
         * 参数五：hashMap中的基本参数配置
         */
        JSONObject res = client.addUser(encode, "BASE64", "itcast", "1003", options);
        System.out.println(res.toString());
    }

    /**
     *  人脸更新：更新人脸库中的照片
     */
    @Test
    public void testFaceUpdate() throws Exception {
        //2.参数设置
        HashMap<String,String> options = new HashMap<>();
        options.put("quality_control","NORMAL");//图片质量  NONE  LOW  NORMAL，HIGH
        options.put("liveness_control","LOW");//活体检测
        options.put("action_type","replace");
        //3.构造图片
        String path = "E:\\进阶课程\\黑马程序员课件\\1、黑马程序员SaaS移动办公iHRM人力资源管理系统\\day11\\资源\\照片\\002.png";
        //上传的图片  两种格式 ： url地址，Base64字符串形式
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        String encode = Base64Util.encode(bytes);
        //4.调用api方法完成人脸注册
        /**
         * 参数一：（图片的url或者图片的Base64字符串），
         * 参数二：图片形式（URL,BASE64）
         * 参数三：组ID（固定字符串）
         * 参数四：用户ID
         * 参数五：hashMap中的基本参数配置
         */
        JSONObject res = client.updateUser(encode, "BASE64", "itcast", "10001", options);
        System.out.println(res.toString());
    }

    /**
     * 人脸检测：判断图片中是否具有面部信息
     */
    @Test
    public void testFaceCheck() throws Exception {
        //构造图片
        String path = "E:\\进阶课程\\黑马程序员课件\\1、黑马程序员SaaS移动办公iHRM人力资源管理系统\\day11\\资源\\照片\\001.png";
        //上传的图片  两种格式 ： url地址，Base64字符串形式
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        String image = Base64Util.encode(bytes);

        //调用api方法进行人脸检测
        //参数一：（图片的url或者图片的Base64字符串），
        //参数二：图片形式（URL,BASE64）
        //参数三：hashMap中的基本参数配置（null：使用默认配置）
        JSONObject res = client.detect(image, "BASE64", null);
        System.out.println(res.toString(2));
    }

    /**
     * 人脸搜索：根据用户上传的图片和指定人脸库中的所有人脸进行比较，
     *          获取相似度最高的一个或者某几个的评分
     *
     *  说明：返回值（数据，只需要第一条，相似度最高的数据）
     *       score：相似度评分（80分以上可以认为是同一个人）
     */
    @Test
    public void testFaceSearch() throws Exception {
        //构造图片
        String path = "E:\\进阶课程\\黑马程序员课件\\1、黑马程序员SaaS移动办公iHRM人力资源管理系统\\day11\\资源\\照片\\003.png";
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        String image = Base64Util.encode(bytes);
        //人脸搜索
        JSONObject res = client.search(image, "BASE64", "itcast", null);
        System.out.println(res.toString(2));
    }

    @Test
    public void test123() throws Exception {
        //构造图片

        //人脸搜索
        JSONObject res = client.getUser("1000","itcast",null);
        System.out.println(res.toString(2));
    }
}
