package com.github.wycm;

import com.github.wycm.util.ScanClassHandler;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by root on 10/25/16.
 * bean工厂，用于存放实例对象
 */
public class SimpleBeanFactory {
    private static volatile SimpleBeanFactory beanFactory;

    private AnnotationHandler componentAnnotationHandler = new ComponentAnnotationHandler();

    private AnnotationHandler autowiredAnnotationHandler = new AutowiredAnnotationHandler();

    public static Map<String, Object> map = new ConcurrentHashMap<String, Object>();

    private SimpleBeanFactory(){
    }
   public static SimpleBeanFactory getInstance(){
        if(beanFactory == null){
            synchronized(SimpleBeanFactory.class){
                if (beanFactory == null){
                    beanFactory = new SimpleBeanFactory();
                }
            }
        }
        return beanFactory;
    }
    public void init(){
        List<String> classAbsolutePath = ScanClassHandler.getClassName("");
        //初始化Component注解
        for(String s : classAbsolutePath){
            try {
                Class clazz = Class.forName(s);
                componentAnnotationHandler.handle(clazz);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        //初始化Autowired注解
        for (Object object : map.values()){
            object.getClass();
            autowiredAnnotationHandler.handle(object.getClass());
        }
    }

}
