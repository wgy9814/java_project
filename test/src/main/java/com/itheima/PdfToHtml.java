package com.itheima;

import java.awt.image.BufferedImage;
import java.io.*;

import com.itheima.utils.*;
import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;

import static com.itheima.utils.PdfConvertHtmlUtil.*;

/**
 * @author 吴广源
 * @date 2023/3/14 9:05
 */
public class PdfToHtml {
    public static void main(String[] args) {
        File file = new File("F:\\java_project\\test\\会话技术.pdf");
        String htmlPath = "F:\\java_project\\test\\会话技术.html";
        InputStream inputStream = null;
        BufferedImage bufferedImage = null;
        try {
            inputStream = new FileInputStream(file);
            bufferedImage = pdfStreamToPng(inputStream);
            String base64_png = bufferedImageToBase64(bufferedImage);
            createHtmlByBase64(base64_png,htmlPath);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if(inputStream != null){inputStream.close();}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //创建Document类的对象 收费 免费的限制10页
//        PdfDocument pdf = new PdfDocument();
//
//        //从磁盘加载PDF文档
//        pdf.loadFromFile("F:\\java_project\\test\\会话技术.pdf");
//
//                //将PDF文档转换为HTML文件并保存
//        pdf.saveToFile("F:\\java_project\\test\\PDFToHTML.html", FileFormat.HTML);
    }

}
