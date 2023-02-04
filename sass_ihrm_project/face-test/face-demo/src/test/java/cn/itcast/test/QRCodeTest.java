package cn.itcast.test;

import com.baidu.aip.util.Base64Util;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Path;
import java.util.Base64;

public class QRCodeTest {

//    public static void main(String[] args) throws Exception {
//        //1.二维码中的信息
//        String content = "http://www.itcast.cn";
//        //2.通过zxing生成二维码(保存到本地图片，支持以data url的形式体现)
//
//        //创建QRCodeWriter对象
//        QRCodeWriter writer = new QRCodeWriter();
//        //基本配置
//        /**
//         * 1.二维码信息
//         * 2.图片类型
//         * 3.宽度
//         * 4.长度
//         */
//        BitMatrix bt = writer.encode(content, BarcodeFormat.QR_CODE, 200, 200);
//        //保存二维码到本地
//        Path path = new File("C:\\Users\\ThinkPad\\Desktop\\ihrm\\day11\\test.png").toPath();
//        MatrixToImageWriter.writeToPath(bt,"png",path);
//    }

    //生成dataUrl形式的二维码
    public static void main(String[] args) throws Exception {
        //1.二维码中的信息
        String content = "http://www.itcast.cn";
        //2.通过zxing生成二维码(保存到本地图片，支持以data url的形式体现)
        //创建QRCodeWriter对象
        QRCodeWriter writer = new QRCodeWriter();
        //基本配置
        BitMatrix bt = writer.encode(content, BarcodeFormat.QR_CODE, 200, 200);
        //创建ByteArrayOutputstream
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        //将二维码数据以byte数组的形式保存到ByteArrayOutputstream
        /**
         * 1：image对象
         * 2：图片格式
         * 3：Outputstream
         */
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bt);
        ImageIO.write(image,"png",os);
        //对byte数组进行base64处理
        String encode = Base64Util.encode(os.toByteArray());
        System.out.println(new String("data:image/png;base64,"+encode));
    }
}
