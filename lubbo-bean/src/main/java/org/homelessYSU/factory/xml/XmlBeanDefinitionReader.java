package org.homelessYSU.factory.xml;


import org.dom4j.Element;
import org.homelessYSU.BeanDefinition;
import org.homelessYSU.PropertyValue;
import org.homelessYSU.PropertyValues;
import org.homelessYSU.Resource;
import org.homelessYSU.factory.config.ConstructorArgumentValue;
import org.homelessYSU.factory.config.ConstructorArgumentValues;
import org.homelessYSU.factory.support.DefaultListableBeanFactory;

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
    DefaultListableBeanFactory bf;

    public XmlBeanDefinitionReader(DefaultListableBeanFactory bf) {
        this.bf = bf;
    }

    public void loadBeanDefinitions(Resource res) {
        while (res.hasNext()) {
            Element element = (Element) res.next();
            String beanID = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");

            BeanDefinition beanDefinition = new BeanDefinition(beanID, beanClassName);

            //get constructor
            List<Element> constructorElements = element.elements("constructor-arg");
            ConstructorArgumentValues AVS = new ConstructorArgumentValues();
            for (Element e : constructorElements) {
                String pType = e.attributeValue("type");
                String pName = e.attributeValue("name");
                String pValue = e.attributeValue("value");
                AVS.addArgumentValue(new ConstructorArgumentValue(pType, pName, pValue));
            }
            beanDefinition.setConstructorArgumentValues(AVS);
            //end of handle constructor

            //handle properties
            List<Element> propertyElements = element.elements("property");
            PropertyValues PVS = new PropertyValues();
            List<String> refs = new ArrayList<>();
            for (Element e : propertyElements) {
                String pType = e.attributeValue("type");
                String pName = e.attributeValue("name");
                String pValue = e.attributeValue("value");
                String pRef = e.attributeValue("ref");
                String pV = "";
                boolean isRef = false;
                if (pValue != null && !pValue.equals("")) {
                    isRef = false;
                    pV = pValue;
                } else if (pRef != null && !pRef.equals("")) {
                    isRef = true;
                    pV = pRef;
                    refs.add(pRef);
                }
                PVS.addPropertyValue(new PropertyValue(pType, pName, pV, isRef));
            }
            beanDefinition.setPropertyValues(PVS);
            String[] refArray = refs.toArray(new String[0]);
            beanDefinition.setDependsOn(refArray);
            //end of handle properties

            this.bf.registerBeanDefinition(beanID, beanDefinition);
        }
    }


}