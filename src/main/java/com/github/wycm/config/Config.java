package com.github.wycm.config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;




public class Config {
    public static String SCAN_PACKAGE;
    static{
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(Config.class.getResourceAsStream("/applicationContext.xml"));
            Element root = document.getRootElement();
            Element element = root.element("component-scan");
            SCAN_PACKAGE = element.attribute("base-package").getValue();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
