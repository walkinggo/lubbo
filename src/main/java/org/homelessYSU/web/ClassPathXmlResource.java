package org.homelessYSU.web;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.Iterator;

public class ClassPathXmlResource implements Resource {
    Document document;
    Element element;
    Iterator<Element> elementIterator;

    public ClassPathXmlResource(URL xmlPath) {
        SAXReader saxReader = new SAXReader();
        try {
            this.document = saxReader.read(xmlPath);
            this.element = document.getRootElement();
            this.elementIterator = element.elementIterator();
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
