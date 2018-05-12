package com.yll.example.io;

import org.springframework.core.io.Resource;

import javax.swing.tree.TreeNode;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author：linlin.yang
 * @date：2018/3/19 19:57
 */
public class IOTest {
    public static void main(String[] args) throws IOException {
//        FileOutputStream fileOutputStream = new FileOutputStream(FileDescriptor.out);
//        fileOutputStream.write('a');
//        fileOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream(FileDescriptor.in);
        byte[] buf = new byte[1024];
        fileInputStream.read(buf);
        System.out.println(buf);
//        while ((fileInputStream.read(buf)>0)) {
//
//        }

    }
}
