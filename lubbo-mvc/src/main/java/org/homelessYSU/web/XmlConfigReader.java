package org.homelessYSU.web;

import org.dom4j.Element;

import java.util.HashMap;
import java.util.Map;

public class XmlConfigReader {

    public Map<String,MappingValue> loadConfig(Resource res){
        HashMap<String, MappingValue> mappings = new HashMap<>();
        while (res.hasNext()){
            Element element = (Element) res.next();
            String beanid = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            String beanMethod = element.attributeValue("value");
            mappings.put(beanid,new MappingValue(beanid,beanClassName,beanMethod));
        }
        return mappings;
    }
}
