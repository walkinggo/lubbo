package org.homelessYSU.beans.factory.annotation;

import org.homelessYSU.beans.BeanDefinition;
import org.homelessYSU.beans.factory.support.DefaultListableBeanFactory;
import org.homelessYSU.web.AnnotationConfigWebApplicationContext;
import org.homelessYSU.web.WebApplicationContext;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LubboComponentScanner {
    DefaultListableBeanFactory bf;


    public LubboComponentScanner(DefaultListableBeanFactory bf) {
        this.bf = bf;
    }

    public void loadBeanDefinitions(String fileName){
        List<String> scanPackages = scanPackages(Arrays.asList(fileName));
        for (String scanPackage : scanPackages) {
            BeanDefinition beanDefinition = bf.getBeanDefinition(scanPackage);
            if(beanDefinition == null){
                bf.registerBeanDefinition(scanPackage,new BeanDefinition(scanPackage,scanPackage));
            }
        }
    }

    private List<String> scanPackages(List<String> packages) {
        List<String> tempControllerNames = new ArrayList<>();
        for (String packageName : packages) {
            tempControllerNames.addAll(scanPackage(packageName));
        }
        return tempControllerNames;
    }

    private List<String> scanPackage(String packageName){
        List<String> tempControllerNames = new ArrayList<>();
        URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));
        File dir = null;
        try {
            dir = new File(URLDecoder.decode(url.getFile(),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                tempControllerNames.addAll(scanPackage(packageName + "." + file.getName()));
            } else {
                String controllerName = packageName + "." + file.getName().replace(".class", "");
                try {
                    if(Class.forName(controllerName).isAnnotationPresent(LubboComponent.class))
                        tempControllerNames.add(controllerName);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return tempControllerNames;
    }


}
