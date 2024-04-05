package org.homelessYSU.beans;
/**
 * @description:通过xml的constructor-args进行构造器依赖注入的属性
 * @param
 * @return:
 * @author: walkinggo
 * @time: 2024/4/2 21:53
 */
public class ArgumentValue {
    private Object value;
    private String type;
    private String name;

    public ArgumentValue(String type, Object value) {
        this.value = value;
        this.type = type;
    }
    public ArgumentValue(String type, String name, Object value) {
        this.value = value;
        this.type = type;
        this.name = name;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return this.value;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
