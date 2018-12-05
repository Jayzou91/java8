package com.fish.vm;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/26
 */
public class ClassLoaderMain {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = this.getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }

                    byte[] bytes = new byte[is.available()];
                    is.read(bytes);
                    return defineClass(name, bytes, 0, bytes.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException();
                }
            }
        };

        Object obj = classLoader.loadClass("com.fish.vm.ClassLoaderMain").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof com.fish.vm.ClassLoaderMain);
    }
}
