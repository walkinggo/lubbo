package org.homelessYSU.factory.xml;


import org.dom4j.Element;
import org.homelessYSU.BeanDefinition;
import org.homelessYSU.factory.config.ArgumentValue;
import org.homelessYSU.factory.config.ArgumentValues;
import org.homelessYSU.factory.config.PropertyValue;
import org.homelessYSU.factory.config.PropertyValues;
import org.homelessYSU.Resource;
import org.homelessYSU.factory.support.SimpleBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @param
 * @description:将resource里面的bean信息加载进beanFactory里面
 * @return:
 * @author: walkinggo
 * @time: 2024/4/2 20:07
 */
public class XmlBeanDefinitionReader {
    SimpleBeanFactory bf;

    public XmlBeanDefinitionReader(SimpleBeanFactory bf) {
        this.bf = bf;
    }

    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanID = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");

            BeanDefinition beanDefinition = new BeanDefinition(beanID, beanClassName);

            List<Element> constructorElements = element.elements("constructor-args");
            ArgumentValues AVS = new ArgumentValues();
            for (Element e : constructorElements) {
                String aType = e.attributeValue("type");
                String aName = e.attributeValue("name");
                String aValue = e.attributeValue("value");
                AVS.addArgumentValue(new ArgumentValue(aValue,aType,aName));
            }
            beanDefinition.setConstructorArgumentValues(AVS);

            List<Element> propertyElements = element.elements("property");
            PropertyValues PVS = new PropertyValues();
            ArrayList<String> refs = new ArrayList<>();
            for (Element e : propertyElements) {
                String pType = e.attributeValue("type");
                String pName = e.attributeValue("name");
                String pValue = e.attributeValue("value");
                String pRef = e.attributeValue("ref");
                String pV = "";
                boolean isRef = false;
                if(pValue != null && !pValue.equals("")){
                    isRef = false;
                    pV = pValue;
                }else if(pRef != null && !pRef.equals("")){
                    isRef = true;
                    pV = pRef;
                    refs.add(pRef);
                }
                PVS.addPropertyValue(new PropertyValue(pType,pName,pV,isRef));

            }
            beanDefinition.setPropertyValues(PVS);
            String[] refArray = refs.toArray(new String[0]);
            beanDefinition.setDependsOn(refArray);
            this.bf.registerBeanDefinition(beanID, beanDefinition);
        }

    }

}
