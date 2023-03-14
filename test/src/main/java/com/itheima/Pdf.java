package com.itheima;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Pdf {
    public static void main(String[] args) throws IOException {
        readText();
        readImage();
    }

    private static void readText() {
        //將pdf文字保存在txt文件
        PdfDocument pdf = new PdfDocument("F:\\java_project\\test\\会话技术.pdf");
        StringBuilder sb = new StringBuilder();
        PdfPageBase page;
        for (int i= 0; i<pdf.getPages().getCount();i++) {
            page = pdf.getPages().get(i);
            sb.append(page.extractText(true));
        }
        try (FileWriter writer = new FileWriter("ExtractText.txt")) {
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        pdf.close();
    }

    private static void readImage() throws IOException {
        //將pdf图片保存在image文件夹
        PdfDocument pdf = new PdfDocument("F:\\java_project\\test\\会话技术.pdf");

        PdfPageBase page;
        for (int i= 0; i<pdf.getPages().getCount();i++) {
            page = pdf.getPages().get(i);
            // 使用extractImages方法获取页面上图片
            if(page.extractImages() != null) {
                for (BufferedImage image : page.extractImages()) {
                    // 将图片保存为PNG格式文件
                    File output = new File("F:\\java_project\\test\\image\\" + String.format("Image_%d.png", i));
                    ImageIO.write(image, "PNG", output);
                }
            }
        }
        pdf.close();

    }

    private static void transToWord() {
        //加载PDF
        PdfDocument pdf = new PdfDocument();
        pdf.loadFromFile("Input.pdf");

        //保存为Word格式
//        pdf.saveToFile("ToWord.docx", FileFormat.DOCX);

    }



}
