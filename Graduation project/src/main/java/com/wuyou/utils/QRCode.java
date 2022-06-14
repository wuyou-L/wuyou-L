package com.wuyou.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.common.StringUtils;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import io.netty.util.internal.StringUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class QRCode {

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;
    private static final int MARGIN = 0;
    private static final int LOGOPART = 4;

    /**
     * 生成二维码矩阵
     * @param content   二维码内容
     * @param width     宽
     * @param height    高
     * @return  二维码矩阵信息
     */
    private static BitMatrix setBitMatrix(String content, int width, int height){
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");   // 指定编码方式
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); // 指定纠错等级
        hints.put(EncodeHintType.MARGIN, MARGIN);       // 指定二维码四周白色区域大小
        BitMatrix bitMatrix = null;

        try {
            bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return bitMatrix;
    }


    /**
     * 生成二维码图片信息
     * @param bitMatrix 二维码矩阵信息
     * @return
     */
    private static BufferedImage toBufferedImage(BitMatrix bitMatrix){
        int x = bitMatrix.getWidth();
        int y = bitMatrix.getHeight();
        BufferedImage bufferedImage = new BufferedImage(x, y, BufferedImage.TYPE_3BYTE_BGR);
        // 画出黑白点
        for(int i = 0; i < x; i ++){
            for(int j = 0; j < y; j ++){
                bufferedImage.setRGB(i, j, bitMatrix.get(i, j) ? BLACK : WHITE);
            }
        }
        return bufferedImage;
    }


    /**
     * 将二维码图片输出
     * @param bitMatrix 二维码矩阵信息
     * @param format    图片格式
     * @param stream    输出流
     * @param logoPath  logo图片路径
     * @throws IOException
     */
    public static void writeFile(BitMatrix bitMatrix, String format, OutputStream stream, String logoPath) throws IOException {
        BufferedImage bufferedImage = toBufferedImage(bitMatrix);
        // 加入logo水印效果
        if (StringUtil.isNullOrEmpty(logoPath)) {
//            bufferedImage.add
        }
        ImageIO.write(bufferedImage, format, stream);
    }

    /**
     * 添加logo
     * @param image
     * @param logoPath
     * @return
     * @throws IOException
     */
    private static BufferedImage addLogo(BufferedImage image, String logoPath) throws IOException {
        Graphics2D graphics = image.createGraphics();
        BufferedImage logoImage = ImageIO.read(new File(logoPath));
        // 计算logo图片大小，可适应长方形图片，根据较短边生成正方形
        int width = image.getWidth() < image.getHeight() ? image.getWidth() / LOGOPART : image.getHeight() / LOGOPART;
        int height = width;
        // 计算logo图片放置位置
        int x = (image.getWidth() - width) / 2;
        int y = (image.getHeight() - height) / 2;
        // 在二维码图片上绘制logo图片
        graphics.drawImage(logoImage, x, y, width, height, null);
        // 绘制logo边框可选
//        graphics.drawRoundRect(x, y, logoImage.getWidth(), logoImage.getHeight(), 10 ,10);
        graphics.setStroke(new BasicStroke(2)); // 画笔粗细
        graphics.setColor(Color.WHITE);     // 边框颜色
        graphics.drawRect(x, y, width, height); // 矩形边框
        logoImage.flush();
        graphics.dispose();
        return image;
    }

    /**
     * 为图片添加文字
     * @param pressText     文字
     * @param newImage      带文字的图片
     * @param targetImage   需要添加文字的图片
     * @param fontStyle     字体样式
     * @param color         颜色
     * @param fontSize      大小
     * @param width         图片宽度
     * @param height        图片高度
     * @throws IOException
     */
    private static void pressText(String pressText, String newImage, String targetImage, int fontStyle, Color color, int fontSize, int width, int height) throws IOException {
        // 计算文字开始的位置
        // x开始的位置：（图片宽度-字体大小*字体个数）/2
        int startX = (width - (fontSize * pressText.length()) /2);
        // y开始的位置：（图片高度-字体大小*字体个数）/2
        int startY = (height - (height - width) / 2 + fontSize);
        File file = new File(targetImage);
        if (!file.exists()) {
            file.mkdirs();
        }
        // 读取文件
        BufferedImage src = ImageIO.read(file);
        int imageWidth = src.getWidth();
        int imageHeight = src.getHeight();

        BufferedImage bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.drawImage(src, 0, 0, imageWidth, imageHeight, null);
        graphics.setColor(color);
        graphics.setFont(new Font(null, fontStyle, fontSize));
        graphics.drawString(pressText, startX, startY);
        graphics.dispose();
        FileOutputStream fileOutputStream = new FileOutputStream(newImage);
        ImageIO.write(bufferedImage, "png", fileOutputStream);
        fileOutputStream.close();
    }


    public static void getQRCode(int width, int height, String content, String path, String imageName){
        BitMatrix bitMatrix = setBitMatrix(content, width, height);
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File(path));
            writeFile(bitMatrix, "jpg", outputStream, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        getQRCode(180, 220, "test", "D:/", new Timestamp(new Date().getTime()) + "");
//        int width = 180;
//        int height = 220;
//        String content = "琳琳别生气了，我也不生气了，好不好";
//        String format = "jpg";
//        BitMatrix bitMatrix = setBitMatrix(content, width, height);
//        String path = "D:/qr" + new Date().getTime() + ".png";
//        OutputStream outputStream = null;
//        try {
//            outputStream = new FileOutputStream(new File(path));
//            writeFile(bitMatrix, format, outputStream, null);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                outputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        // 添加文字效果
//        int fontSize = 12; // 字体大小
//        int fontStyle = 1; // 字体风格
//        String text = "测试二维码";
//        String withTextPath = "D:/text" + new Date().getTime() + ".png";
//        pressText(text, withTextPath, path, fontStyle, Color.BLUE, fontSize, width, height);
        String s = deEncodeByPath("D:\\qr1646882530331.png");
        System.out.println(s);
    }


    /**
     * 解析二维码,此方法解析一个路径的二维码图片
     * path:图片路径
     */
    public static String deEncodeByPath(String path) {
        String content = null;
        BufferedImage image;
        try {
            image = ImageIO.read(new File(path));
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            com.google.zxing.Result result = new MultiFormatReader().decode(binaryBitmap, hints);//解码
            System.out.println("图片中内容：  ");
            System.out.println("content： " + result.getText());
            content = result.getText();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            //这里判断如果识别不了带LOGO的图片，重新添加上一个属性
            try {
                image = ImageIO.read(new File(path));
                LuminanceSource source = new BufferedImageLuminanceSource(image);
                Binarizer binarizer = new HybridBinarizer(source);
                BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
                Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
                //设置编码格式
                hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
                //设置优化精度
                hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
                //设置复杂模式开启（我使用这种方式就可以识别微信的二维码了）
                hints.put(DecodeHintType.PURE_BARCODE,Boolean.TYPE);
                Result result = new MultiFormatReader().decode(binaryBitmap, hints);//解码
                System.out.println("图片中内容：  ");
                System.out.println("content： " + result.getText());
                content = result.getText();
            } catch (IOException x) {
                x.printStackTrace();
            } catch (NotFoundException y) {
                y.printStackTrace();
            }
        }
        return content;
    }
}
