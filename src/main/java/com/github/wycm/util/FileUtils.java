package com.github.wycm.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    /**
     * 获取某个文件夹下的所有绝对路径
     * @param path
     * @return
     */
    public static List<String> getFileAbsolutePath(String path){
        List<String> list = new ArrayList<String>();
        File file = new File(path);
        if(file == null || file.isFile()){
            throw new RuntimeException("路径" + path + "异常");
        }
        getFileList(list, file);
        return list;
    }
    public static void getFileList(List<String> fileNameList, File file){
        File[] files = file.listFiles();
        for(File f : files){
            if(f.isFile()){
                fileNameList.add(f.getAbsolutePath());
            }
            if (f.isDirectory()){
                getFileList(fileNameList, f);
            }
        }
    }
}
