package com.itheima;


import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.Image;

import javax.imageio.ImageIO;

/**
 * @author 吴广源
 * @date 2023/3/14 9:33
 */


public class HtmlToPdf {
    public static void main(String[] args) throws IOException {
//        org.jsoup.nodes.Document doc = Jsoup.parse(new File("F:\\java_project\\test\\会话技术.html"), "UTF-8");
//        Elements elements = doc.select("img[src^='data:image/png']");
//        String base64Image = elements.attr("src");
//
//        byte[] decodedBytes = Base64.getDecoder().decode(base64Image.split(",")[1]);
//        BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
//
//        PdfDocument pdfDoc = new PdfDocument(new PdfWriter("F:\\java_project\\test\\6666.pdf"));
////        String pdfContent = pdfDoc.toString();
//        Document document = new Document(pdfDoc);
//        Image pdfImage = new Image(ImageDataFactory.create(image, null));
//        document.add(pdfImage);
//        document.close();




    }

}

