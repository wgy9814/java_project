package com.itheima;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.fit.pdfdom.PDFDomTree;
import org.fit.pdfdom.PathSegment;
import org.fit.pdfdom.TextMetrics;
import org.fit.pdfdom.resource.ImageResource;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

import javax.xml.parsers.ParserConfigurationException;

public class MyPdf extends PDFDomTree {
    public MyPdf() throws IOException, ParserConfigurationException {
        super();
    }

    protected void startNewPage(){
        System.out.println("====页码:" + pagecnt);
        super.startNewPage();
    }


    @Override
    protected void renderText(String data, TextMetrics metrics)
    {
        System.out.println("====文本:" + data + "," +  ",x:" + (int)metrics.getX() + ",top:" + (int)metrics.getTop() + ",width:" + (int)metrics.getWidth() + ",height:" + (int)metrics.getHeight() );
        curpage.appendChild(createTextElement(data, metrics.getWidth()));
    }

    @Override
    protected void renderPath(List<PathSegment> path, boolean stroke, boolean fill) throws IOException
    {
        PathSegment path1 = path.get(0);
        System.out.println("====路径1:" + "x1:" + path.get(0).getX1() + ",y1:" + path1.getY1() + ",x2:" + path1.getX2() + ",y2:" + path1.getY2() + ",stroke:" + stroke + ",fill:" + fill);
        super.renderPath(path, stroke, fill);
    }

    @Override
    protected void renderImage(float x, float y, float width, float height, ImageResource resource) throws IOException
    {
        System.out.println("====图片:" + "x:" + x + ",y:" + y + ",width:" + width + ",height:" + height);
        curpage.appendChild(createImageElement(x, y, width, height, resource));
    }

    public void parsePdf(PDDocument doc){
        try
        {
            DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
            DOMImplementationLS impl = (DOMImplementationLS)registry.getDOMImplementation("LS");
            LSSerializer writer = impl.createLSSerializer();
            LSOutput output = impl.createLSOutput();
            writer.getDomConfig().setParameter("format-pretty-print", true);
            createDOM(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        //按坐标读取pdf

        try {
            File pdfFile = new File("F:\\java_project\\test\\会话技术.pdf");
            PDDocument document = PDDocument.load(pdfFile);
            MyPdf pdfDomTree = new MyPdf();
            pdfDomTree.parsePdf(document);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}

