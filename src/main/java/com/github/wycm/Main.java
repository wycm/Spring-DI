package com.github.wycm;

import java.util.Map;


public class Main {
    public static void main(String[] args){
        SimpleBeanFactory beanFactory = SimpleBeanFactory.getInstance();
        beanFactory.init();
        Map<String, Object> beans = beanFactory.map;
        System.out.println(beans.size());
    }
}
