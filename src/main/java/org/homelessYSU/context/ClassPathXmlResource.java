package org.homelessYSU.context;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.Iterator;

/**
 * @description:负责读取具体的XML文件并保存
 * @param
 * @return:
 * @author: walkinggo
 * @time: 2024/4/2 20:06
 */
public class ClassPathXmlResource implements Resource {
    Document document;
    Element rootElement;
    Iterator<Element> elementIterator;

    public ClassPathXmlResource(String fileName) {
        SAXReader saxReader=new SAXReader();
        URL xmlPath=this.getClass().getClassLoader().getResource(fileName);
        try {
            this.document = saxReader.read(xmlPath);
            this.rootElement=document.getRootElement();
            this.elementIterator=this.rootElement.elementIterator();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasNext() {
        return this.elementIterator.hasNext();
    }

    @Override
    public Object next() {
        return this.elementIterator.next();
    }


}