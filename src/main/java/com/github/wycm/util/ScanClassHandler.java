package com.github.wycm.util;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 扫描某个包下的所有Class文件
 */
public class ScanClassHandler {
    public static List<String> getClassName(String packagePath){
        String basePackage = packagePath;
        basePackage = basePackage.replaceAll("\\.", "/");
        URL url = ScanClassHandler.class.getClassLoader().getResource(basePackage);
        String path = url.getPath();
        try {
            path = URLDecoder.decode(path,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<String> fileNameList = FileUtils.getFileAbsolutePath(path);
        return handleClassPath(fileNameList);
    }

    /**
     * 返回class文件的对应package路径
     * @param list
     * @return
     */
    private static List<String> handleClassPath(List<String> list){
        List<String> packagePath = new ArrayList<String>();
        URL url = ScanClassHandler.class.getClassLoader().getResource("");
        String path = url.getPath();
        try {
            path = URLDecoder.decode(path, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        /**
         * windows系统两种路径不一样
         * path格式 /D:/path/
         * 而绝对路径 D:\path\
         */
        for(String s : list){
            s = s.replaceAll("\\\\", "/");
            if (!s.startsWith("/")){
                s = "/" + s;
            }
            String temp = s.substring(path.length());
            if(temp.endsWith(".class")){
                temp = temp.substring(0, temp.length() - 6);
                packagePath.add(temp.replaceAll("/", "."));
            }
        }
        return packagePath;
    }
}
