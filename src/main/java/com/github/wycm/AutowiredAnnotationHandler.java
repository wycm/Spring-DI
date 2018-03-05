package com.github.wycm;

import java.lang.reflect.Field;


public class AutowiredAnnotationHandler implements AnnotationHandler{
    public void handle(Class clazz){
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){
            if (field.isAnnotationPresent(Autowired.class)){
                field.setAccessible(true);
                try {
                    field.set(SimpleBeanFactory.getInstance().map.get(clazz.getName()),
                            SimpleBeanFactory.getInstance().map.get(field.getType().getName()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
