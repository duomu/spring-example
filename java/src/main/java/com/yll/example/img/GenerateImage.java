package com.yll.example.img;

import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author：linlin.yang
 * @date：2018/5/2 10:35
 */
public class GenerateImage {
    /**
     * 创建一张图片
     * 图片存放路径：E:/学习笔记/createImage.jpg
     * 1.将图片写到磁盘里
     * 2.将图片写到流里
     * 3.拿到图片的base64
     *
     * @throws IOException
     * @author 张爽
     * @date 2017-8-2
     */
    public static void createImage() throws IOException {
        int width = 100;
        int height = 100;
        String content = "有利网";
        File file = new File("E:/createImage.jpg");
        Font font = new Font("Serif", Font.BOLD, 10);

        BufferedImage bufferedImage = new BufferedImage(
                width,
                height,
                BufferedImage.TYPE_INT_RGB
        );

        Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
        graphics2D.setBackground(Color.WHITE);
        graphics2D.clearRect(0, 0, width, height);
        graphics2D.setPaint(Color.RED);
        FontRenderContext fontRenderContext = graphics2D.getFontRenderContext();
        Rectangle2D stringBounds = font.getStringBounds(content, fontRenderContext);
        double x = (width - stringBounds.getWidth()) / 2;
        double y = (height - stringBounds.getHeight()) / 2;
        double ascent = -stringBounds.getY();
        double baseY = y + ascent;
        graphics2D.drawString(content, (int) x, (int) baseY);

        // 1.将图片写到实体图片里
        ImageIO.write(bufferedImage, "jpg", file);
        // 2.将图片写到流里
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
        // 3.将图片以base64的形式展示
        BASE64Encoder base64Encoder = new BASE64Encoder();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        System.out.println("data:image/png;base64," + base64Encoder.encodeBuffer(byteArray).trim());
    }

    // 根据str,font的样式以及输出文件目录
    public static void createImage(String str, Font font, File outFile, Integer width, Integer height) throws Exception {
        // 创建图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.setClip(0, 0, width, height);
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);// 先用黑色填充整张图片,也就是背景
        g.setColor(Color.red);// 在换成黑色
        g.setFont(font);// 设置画笔字体
        /** 用于获得垂直居中y */
        Rectangle clip = g.getClipBounds();
        FontMetrics fm = g.getFontMetrics(font);
        int ascent = fm.getAscent();
        int descent = fm.getDescent();
        int y = (clip.height - (ascent + descent)) / 2 + ascent;
        for (int i = 0; i < 6; i++) {// 256 340 0 680
            g.drawString(str, i * 680, y);// 画出字符串
        }
        g.dispose();
        ImageIO.write(image, "png", outFile);// 输出png图片
    }

    public static void main(String[] args) throws Exception {
        createImage();
        createImage("请A1003到3号窗口", new Font("宋体", Font.BOLD, 30), new File(
                "e:/a.png"), 4096, 64);
        createImage("请A1002到2号窗口", new Font("黑体", Font.BOLD, 35), new File(
                "e:/a1.png"), 4096, 64);
        createImage("请A1001到1号窗口", new Font("黑体", Font.PLAIN, 40), new File(
                "e:/a2.png"), 4096, 64);
    }

}
