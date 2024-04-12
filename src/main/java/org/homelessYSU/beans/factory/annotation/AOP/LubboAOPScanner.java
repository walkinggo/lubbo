package org.homelessYSU.beans.factory.annotation.AOP;

import org.homelessYSU.beans.BeanDefinition;
import org.homelessYSU.beans.factory.annotation.LubboComponent;
import org.homelessYSU.beans.factory.support.DefaultListableBeanFactory;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.*;

public class LubboAOPScanner {

    public static String AOP_ATTRIBUTE = "AOP_MAP";

    public LubboAOPScanner() {

    }

    public Map loadBeanDefinitions(String fileName){
        return new HashMap<Method, AopProxyWrapper>();
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
            dir = new File(URLDecoder.decode(url.getFile(), Charset.defaultCharset().toString()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                tempControllerNames.addAll(scanPackage(packageName + "." + file.getName()));
            } else {
                String controllerName = packageName + "." + file.getName().replace(".class", "");
                try {
                    if(Class.forName(controllerName).isAnnotationPresent(LubboAop.class))
                        tempControllerNames.add(controllerName);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return tempControllerNames;
    }

}