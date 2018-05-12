package com.yll.example.commonuse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author：linlin.yang
 * @date：2018/3/5 11:09
 */
public class FileUtil {
    public static void main(String[] args) {
        String dirPath = "D:/contract/";
        getFileNames(dirPath, false);
    }

    public static List<File> getFileNames(String dirPath, boolean isDepth) {
        List<File> files = getFileNames(new ArrayList<File>(), dirPath, isDepth);
        if (files != null && files.size() > 0) {
            for (File file : files) {
                System.out.println(file.getName());
            }
        }

        return files;
    }

    /**
     * 获取指定目录下的文件名
     * @param dirPath
     * @return
     */
    public static List<File> getFileNames(List<File> fileList, String dirPath, boolean isDepth) {
        File file = new File(dirPath);
        if (!file.exists()) {
            return null;
        }

        File[] files = file.listFiles();
        if (files == null) {
            return null;
        }

        for (File f : files) {
            if (f.isFile()) {
                fileList.add(f);
            } else if (f.isDirectory()) {
                if (isDepth) {
                    getFileNames(fileList, f.getAbsolutePath(), isDepth);
                }
            }
        }

        return fileList;
    }
}
