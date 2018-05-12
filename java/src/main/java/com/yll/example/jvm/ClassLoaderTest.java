package com.yll.example.jvm;

import java.io.IOException;
import java.io.InputStream;

/**
 * 不同类加载器加载的类即使来自同一个Class文件也是不同的
 * @author：linlin.yang
 * @date：2018/3/30 17:53
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader myLoader=new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    System.out.println("fileName：" + fileName);
                    InputStream input = getClass().getResourceAsStream(fileName);
                    if (input == null) {
                        return super.loadClass(fileName);
                    }

                    byte[] b = new byte[input.available()];
                    input.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    System.out.println("load class exception，error：" + e.getMessage());
                }

                return null;
            }
        };

        Object obj = myLoader.loadClass("com.yll.example.jvm.DeathLoop").newInstance();
        System.out.println(obj instanceof com.yll.example.jvm.DeathLoop);
    }
}
