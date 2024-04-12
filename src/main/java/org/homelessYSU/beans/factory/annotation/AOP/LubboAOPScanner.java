package org.homelessYSU.beans.factory.annotation.AOP;

import org.homelessYSU.beans.BeanDefinition;
import org.homelessYSU.beans.factory.annotation.LubboComponent;
import org.homelessYSU.beans.factory.support.DefaultListableBeanFactory;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.*;

public class LubboAOPScanner {

    public static String AOP_ATTRIBUTE = "AOP_MAP";
    public List<String> tempAopNames;
    public LubboAOPScanner() {

    }

    public Map loadBeanDefinitions(String filename){
        HashMap<String, AopProxyWrapper> map = new HashMap<>();
        tempAopNames = scanPackages(Collections.singletonList(filename));
        for (String aopName : tempAopNames) {
            try {
                Method[] declaredMethods = Class.forName(aopName).getDeclaredMethods();
                for (Method declaredMethod : declaredMethods) {

                    if(declaredMethod.isAnnotationPresent(AfterRun.class)){
                        String aopUrlAft = getAopUrl(declaredMethod.getAnnotation(AfterRun.class).method(),declaredMethod.getAnnotation(AfterRun.class).className());
                        AopProxyWrapper orDefault = map.getOrDefault(aopUrlAft, new AopProxyWrapper(declaredMethod));
                        orDefault.addProxyAft(Class.forName(aopName).newInstance(),declaredMethod,null);
                        map.put(aopUrlAft,orDefault);
                    }else if(declaredMethod.isAnnotationPresent(BeforeRun.class)){
                        String aopUrlBef = getAopUrl(declaredMethod.getAnnotation(BeforeRun.class).method(),declaredMethod.getAnnotation(BeforeRun.class).className());
                        AopProxyWrapper orDefault = map.getOrDefault(aopUrlBef, new AopProxyWrapper(declaredMethod));
                        orDefault.addProxyBef(Class.forName(aopName).newInstance(),declaredMethod,null);
                        map.put(aopUrlBef,orDefault);
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    private List<String> scanPackages(List<String> packages) {
        List<String> tempAopNames = new ArrayList<>();
        for (String packageName : packages) {
            tempAopNames.addAll(scanPackage(packageName));
        }
        return tempAopNames;
    }

    private List<String> scanPackage(String packageName){
        List<String> tempAopNames = new ArrayList<>();
        URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));
        File dir = null;
        try {
            dir = new File(URLDecoder.decode(url.getFile(), Charset.defaultCharset().toString()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                tempAopNames.addAll(scanPackage(packageName + "." + file.getName()));
            } else {
                String AopName = packageName + "." + file.getName().replace(".class", "");
                try {
                    if(Class.forName(AopName).isAnnotationPresent(LubboAop.class))
                        tempAopNames.add(AopName);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return tempAopNames;
    }

    public static String getAopUrl(String method,String clazz){
        return clazz + "&" + method;
    }

}