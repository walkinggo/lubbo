package org.homelessYSU;


import org.dom4j.Element;
import org.homelessYSU.DI.ArgumentValue;
import org.homelessYSU.DI.ArgumentValues;
import org.homelessYSU.DI.PropertyValue;
import org.homelessYSU.DI.PropertyValues;

import java.util.List;

/**
 * @description:将resource里面的bean信息加载进beanFactory里面
 * @param
 * @return:
 * @author: walkinggo
 * @time: 2024/4/2 20:07
 */
public class XmlBeanDefinitionReader {
    SimpleBeanFactory bf;
    public XmlBeanDefinitionReader(SimpleBeanFactory bf) {
        this.bf = bf;
    }
    public void loadBeanDefinitions(Resource res) {
        while (res.hasNext()) {
            Element element = (Element)res.next();
            String beanID=element.attributeValue("id");
            String beanClassName=element.attributeValue("class");

            BeanDefinition beanDefinition=new BeanDefinition(beanID,beanClassName);

            //handle properties
            List<Element> propertyElements = element.elements("property");
            PropertyValues PVS = new PropertyValues();
            for (Element e : propertyElements) {
                String pType = e.attributeValue("type");
                String pName = e.attributeValue("name");
                String pValue = e.attributeValue("value");
                PVS.addPropertyValue(new PropertyValue(pType, pName, pValue));
            }
            beanDefinition.setPropertyValues(PVS);
            //end of handle properties

            //get constructor
            List<Element> constructorElements = element.elements("constructor-arg");
            ArgumentValues AVS = new ArgumentValues();
            for (Element e : constructorElements) {
                String pType = e.attributeValue("type");
                String pName = e.attributeValue("name");
                String pValue = e.attributeValue("value");
                AVS.addArgumentValue(new ArgumentValue(pValue,pType,pName));
            }
            beanDefinition.setConstructorArgumentValues(AVS);
            //end of handle constructor

            this.bf.registerBeanDefinition(beanID,beanDefinition);
        }

    }

}
