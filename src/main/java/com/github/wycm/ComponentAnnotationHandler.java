package com.github.wycm;


public class ComponentAnnotationHandler implements AnnotationHandler{
    public void handle(Class clazz){
        if (clazz.isAnnotationPresent(Component.class)){
            Object object = null;
            try {
                object = clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            SimpleBeanFactory.getInstance().map.put(clazz.getName(), object);
        }
    }
}
