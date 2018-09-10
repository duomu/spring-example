package com.yll.example.img;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataFormatImpl;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

/**
 * @author：linlin.yang
 * @date：2018/5/2 10:59
 */
public class ImageTest {

    public static void testReadImage() {
        BufferedImage image = null;

        try {
            URL url = new URL("https://www.baidu.com/img/baidu_jgylogo3.gif");
            image = ImageIO.read(url);

            ImageIO.write(image, "jpg", new File("E:\\test.jpg"));
            ImageIO.write(image, "gif", new File("E:\\test.gif"));
            ImageIO.write(image, "png", new File("E:\\test.png"));
        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }

    public static void test() throws IOException {
        String fileName = "E:\\nomarkers.jpg";
//        String sep = System.getProperty("file.separator");
//        String dir = System.getProperty("test.src", ".");
//        String filePath = dir+sep+fileName;
        String filePath = fileName;
        System.out.println("Test file: " + filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("文件不存在，新建文件");
            file.createNewFile();
        }
        ImageInputStream stream = ImageIO.createImageInputStream(file);
        Iterator<ImageReader> readers = ImageIO.getImageReaders(stream);

        if(readers.hasNext()) {
            ImageReader reader = readers.next();
            reader.setInput(stream);
            IIOMetadata metadata = reader.getImageMetadata(0);

            IIOMetadataNode standardTree = (IIOMetadataNode)
                    metadata.getAsTree
                            (IIOMetadataFormatImpl.standardMetadataFormatName);
            IIOMetadataNode colorSpaceType = (IIOMetadataNode)
                    standardTree.getElementsByTagName("ColorSpaceType").item(0);
            String colorSpaceName = colorSpaceType.getAttribute("name");
            if(colorSpaceName.equals("RGB"))
                throw new RuntimeException("Identified incorrect ColorSpace");
        }
    }

    private static ImageInputStream createTestImage(int type) throws IOException  {
        int w = 100;
        int h = 100;

        BufferedImage img = new BufferedImage(w, h, type);
//        int dx = w / colors.length;
//
//        for (int i = 0; i < colors.length; i++) {
//            for (int x = i *dx; (x < (i + 1) * dx) && (x < w) ; x++) {
//                for (int y = 0; y < h; y++) {
//                    img.setRGB(x, y, colors[i].getRGB());
//                }
//            }
//        }

        img.setRGB(50, 50, Color.RED.getRGB());

        File pwd = new File(".");
        File out = File.createTempFile("rgba_", ".png", pwd);
        System.out.println("Create file: " + out.getAbsolutePath());
        ImageIO.write(img, "PNG", out);
        return ImageIO.createImageInputStream(out);
    }

    public static void testCreateImage() throws IOException {
    }

    public static void main(String[] args) throws IOException {
//        testReadImage();
//        test();
        createTestImage(1);
    }
}
