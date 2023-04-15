package com.itheima.picture;


import com.madgag.gif.fmsware.AnimatedGifEncoder;
import com.madgag.gif.fmsware.GifDecoder;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompressData {

    public static void main(String[] args) throws IOException {
//        String imgPath = "F:\\java_project\\test\\333.gif";
//        File source = new File(imgPath);
//        System.out.println(source.getAbsolutePath());
//        System.out.println(source.getName());
//        String newFilePath = "F:\\java_project\\test\\image\\"+source.getName();
//
////
//        try {
//            //压缩图片清晰度
////            zoomGifByQuality(imgPath,"gif",0.1F,newFilePath);
//            //压缩图片大小
//            zoomGifBySize(imgPath,"gif",120,210,newFilePath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        String imagePath = "F:\\java_project\\test\\000.gif";
        String outputPath = "F:\\java_project\\test\\002.gif";
        reverseGif(imagePath,outputPath);
        // Gif转图片
//        String dirPath = "/home/lab/test/22/";
//        gifToImages(imagePath,dirPath);
//        List<BufferedImage> images = new ArrayList<>();
//        for (int i = 0 ; i < 111;i++) {
//            File outFile = new File(dirPath + i + ".png");
//            BufferedImage image = ImageIO.read(outFile);
//            images.add(image);
//        }
//        imagesToGif(images,"/home/lab/test/res.gif");

    }


    public static byte[] zoomImg(){
        String imgPath = "C:\\Users\\xxy\\Desktop\\图片\\8506f48e8d8fedbd8b972184992940be.jpg";
        File source = new File(imgPath);
        System.out.println("source file size====>"+getPrintSize(source.length()));
        System.out.println(source.getAbsolutePath());
        System.out.println(source.getName());
        String newFilePath = "C:\\Users\\xxy\\Desktop\\图片\\new\\"+source.getName();
        try {
            Thumbnails.of(source).scale(1f).outputQuality(0.3f).toFile(newFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File newFile = new File(newFilePath);
        System.out.println("new file size====>"+getPrintSize(newFile.length()));
        //将file转换成BufferedImage
        //BufferedImage bimg = ImageIO.read(newFile);

        return File2byte(newFile);
    }


    public static byte[] zoomImg2Byte(BufferedImage bimg, float quality, String tagFilePath){
        try {
            Thumbnails.of(bimg).scale(1f).outputQuality(quality).toFile(tagFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File newFile = new File(tagFilePath);
        System.out.println("new file size====>"+getPrintSize(newFile.length()));
        return File2byte(newFile);
    }

    /**
     * 质量压缩
     * @param bimg
     * @param quality
     * @param tagFilePath
     * @return
     */
    public static File zoomImg2File(BufferedImage bimg, float quality, String tagFilePath){
        try {
            Thumbnails.of(bimg).scale(1f).outputQuality(quality).toFile(tagFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File newFile = new File(tagFilePath);
        System.out.println("new file size====>"+getPrintSize(newFile.length()));
        return newFile;
    }

    /**
     * 压缩指定宽、高
     * @param bimg
     * @param width
     * @param height
     * @param tagFilePath
     * @return
     */
    public static File zoomImg2File(BufferedImage bimg, int width, int height, String tagFilePath){
        try {
            Thumbnails.of(bimg).size(width,height).toFile(tagFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File newFile = new File(tagFilePath);
        System.out.println("new file size====>"+getPrintSize(newFile.length()));
        return newFile;
    }

    /**
     * GIF压缩质量，尺寸不变
     * @param imagePath 原图片路径地址，如：F:\\a.png
     * @param imgStyle 目标文件类型
     * @param quality 输出的图片质量，范围：0.0~1.0，1为最高质量。
     * @param outputPath 输出文件路径（不带后缀），如：F:\\b，默认与原图片路径相同，为空时将会替代原文件
     * @throws IOException
     */
    public static void zoomGifByQuality(String imagePath, String imgStyle, float quality, String outputPath) throws IOException {
        // 防止图片后缀与图片本身类型不一致的情况
        outputPath = outputPath + "." + imgStyle;
        // GIF需要特殊处理
        GifDecoder decoder = new GifDecoder();
        int status = decoder.read(imagePath);
        if (status != GifDecoder.STATUS_OK) {
            throw new IOException("read image " + imagePath + " error!");
        }
        // 拆分一帧一帧的压缩之后合成
        AnimatedGifEncoder encoder = new AnimatedGifEncoder();
        encoder.start(outputPath);// 设置合成位置
        //encoder.setRepeat(decoder.getLoopCount());// 设置GIF重复次数
        encoder.setRepeat(0);// 设置GIF重复次数
        encoder.setQuality(1);//质量1~10，从低到高
        encoder.setFrameRate(200);//针率，内部会以100作为除数，这里是200，实际=100/200
        int frameCount = decoder.getFrameCount();// 获取GIF有多少个frame
        for (int i = 0; i < frameCount; i++) {
            encoder.setDelay(decoder.getDelay(i));// 设置GIF延迟时间
            BufferedImage bufferedImage = decoder.getFrame(i);
            // 利用java SDK压缩BufferedImage
            //byte[] tempByte = zoomBufferedImageByQuality(bufferedImage, quality);
            //ByteArrayInputStream in = new ByteArrayInputStream(tempByte);
            //BufferedImage zoomImage = ImageIO.read(in);
            File file = zoomImg2File(bufferedImage, quality,outputPath+i+".jpg");
            BufferedImage zoomImage = ImageIO.read(file);
            encoder.addFrame(zoomImage);// 合成
        }
        encoder.finish();
        File outFile = new File(outputPath);
        BufferedImage image = ImageIO.read(outFile);
        ImageIO.write(image, outFile.getName(), outFile);
    }


    public static byte[] zoomBufferedImageByQuality(BufferedImage bufferedImage, float quality) throws IOException {
        // 得到指定Format图片的writer
        Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpg");// 得到迭代器
        ImageWriter writer = (ImageWriter) iter.next(); // 得到writer


        // 得到指定writer的输出参数设置(ImageWriteParam)
        ImageWriteParam iwp = writer.getDefaultWriteParam();
        iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); // 设置可否压缩
        iwp.setCompressionQuality(quality); // 设置压缩质量参数，0~1，1为最高质量
        iwp.setProgressiveMode(ImageWriteParam.MODE_DISABLED);
        //ColorModel colorModel = bufferedImage.getColorModel();
        ColorModel colorModel = ColorModel.getRGBdefault();
        // 指定压缩时使用的色彩模式
        iwp.setDestinationType(new ImageTypeSpecifier(colorModel, colorModel.createCompatibleSampleModel(16, 16)));
        // 开始打包图片，写入byte[]
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // 取得内存输出流
        IIOImage iioImage = new IIOImage(bufferedImage, null, null);
        // 此处因为ImageWriter中用来接收write信息的output要求必须是ImageOutput
        // 通过ImageIo中的静态方法，得到byteArrayOutputStream的ImageOutput
        writer.setOutput(ImageIO.createImageOutputStream(byteArrayOutputStream));
        writer.write(null, iioImage, iwp);

        // 获取压缩后的btye
        byte[] tempByte = byteArrayOutputStream.toByteArray();
        return tempByte;
    }

    /**
     * GIF压缩尺寸大小
     * @param imagePath 原图片路径地址，如：F:\\a.png
     * @param imgStyle 目标文件类型
     * @param width 目标文件宽
     * @param height 目标文件高
     * @param outputPath 输出文件路径（不带后缀），如：F:\\b，默认与原图片路径相同，为空时将会替代原文件
     * @throws IOException
     */
    public static void zoomGifBySize(String imagePath, String imgStyle, int width, int height, String outputPath) throws IOException {
        // 防止图片后缀与图片本身类型不一致的情况
        outputPath = outputPath + "." + imgStyle;
        // GIF需要特殊处理
        GifDecoder decoder = new GifDecoder();
        int status = decoder.read(imagePath);
        if (status != GifDecoder.STATUS_OK) {
            throw new IOException("read image " + imagePath + " error!");
        }
        // 拆分一帧一帧的压缩之后合成
        AnimatedGifEncoder encoder = new AnimatedGifEncoder();
        encoder.start(outputPath);
        encoder.setRepeat(decoder.getLoopCount());
        for (int i = 0; i < decoder.getFrameCount(); i++) {
            encoder.setDelay(decoder.getDelay(i));// 设置播放延迟时间
            BufferedImage bufferedImage = decoder.getFrame(i);// 获取每帧BufferedImage流
            BufferedImage zoomImage = new BufferedImage(width, height, bufferedImage.getType());
            Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            Graphics gc = zoomImage.getGraphics();
            gc.setColor(Color.WHITE);
            gc.drawImage(image, 0, 0, null);
            encoder.addFrame(zoomImage);
        }
        encoder.finish();
        File outFile = new File(outputPath);
        BufferedImage image = ImageIO.read(outFile);
        ImageIO.write(image, outFile.getName(), outFile);
    }

    /**
     * 将文件转换成byte数组
     * @param tradeFile
     * @return
     */
    public static byte[] File2byte(File tradeFile){
        byte[] buffer = null;
        try
        {
            FileInputStream fis = new FileInputStream(tradeFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * 获取文件大小
     *
     * @param size
     * @return
     */
    public static String getPrintSize(long size) {
        // 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return String.valueOf(size) + "B";
        } else {
            size = size / 1024;
        }
        // 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        // 因为还没有到达要使用另一个单位的时候
        // 接下去以此类推
        if (size < 1024) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            // 因为如果以MB为单位的话，要保留最后1位小数，
            // 因此，把此数乘以100之后再取余
            size = size * 100;
            return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "MB";
        } else {
            // 否则如果要以GB为单位的，先除于1024再作同样的处理
            size = size * 100 / 1024;
            return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "GB";
        }
    }

    /**
     * 多图片转gif
     * @param imageList
     * @param outputPath
     * @throws IOException
     */
    static void imagesToGif(List<BufferedImage> imageList, String outputPath) throws IOException {
        // 拆分一帧一帧的压缩之后合成
        AnimatedGifEncoder encoder = new AnimatedGifEncoder();
        encoder.start(outputPath);
        encoder.setRepeat(0);
        for (BufferedImage bufferedImage :
                imageList) {
            encoder.setDelay(100);
            int height = bufferedImage.getHeight();
            int width = bufferedImage.getWidth();
            BufferedImage zoomImage = new BufferedImage(width, height, 3);
            Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            Graphics gc = zoomImage.getGraphics();
            gc.setColor(Color.WHITE);
            gc.drawImage(image, 0, 0, null);
            encoder.addFrame(zoomImage);
        }
        encoder.finish();
        File outFile = new File(outputPath);
        BufferedImage image = ImageIO.read(outFile);
        ImageIO.write(image, outFile.getName(), outFile);
    }

    /**
     * Gif转图片集
     * @param imagePath
     * @param outputDirPath
     * @throws IOException
     */
    static void gifToImages(String imagePath,String outputDirPath) throws IOException {
        GifDecoder decoder = new GifDecoder();
        int status = decoder.read(imagePath);
        if (status != GifDecoder.STATUS_OK) {
            throw new IOException("read image " + imagePath + " error!");
        }
        for (int i = 0; i < decoder.getFrameCount();i++) {
            BufferedImage bufferedImage = decoder.getFrame(i);// 获取每帧BufferedImage流
            File outFile = new File(outputDirPath + i + ".png");
            ImageIO.write(bufferedImage, "png", outFile);
        }
    }

    /**
     * 视频倒放
     * @param imagePath
     * @param outputPath
     * @throws IOException
     */
    public static void reverseGif(String imagePath,String outputPath) throws IOException {
        GifDecoder decoder = new GifDecoder();
        int status = decoder.read(imagePath);
        if (status != GifDecoder.STATUS_OK) {
            throw new IOException("read image " + imagePath + " error!");
        }
        // 拆分一帧一帧的压缩之后合成
        AnimatedGifEncoder encoder = new AnimatedGifEncoder();
        encoder.start(outputPath);
        encoder.setRepeat(decoder.getLoopCount());
        for (int i = decoder.getFrameCount() -1; i >= 0; i--) {
            encoder.setDelay(decoder.getDelay(i));// 设置播放延迟时间
            BufferedImage bufferedImage = decoder.getFrame(i);// 获取每帧BufferedImage流
            int height = bufferedImage.getHeight();
            int width = bufferedImage.getWidth();
            BufferedImage zoomImage = new BufferedImage(width, height, bufferedImage.getType());
            Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            Graphics gc = zoomImage.getGraphics();
            gc.setColor(Color.WHITE);
            gc.drawImage(image, 0, 0, null);
            encoder.addFrame(zoomImage);
        }
        encoder.finish();
        File outFile = new File(outputPath);
        BufferedImage image = ImageIO.read(outFile);
        ImageIO.write(image, outFile.getName(), outFile);
    }

}