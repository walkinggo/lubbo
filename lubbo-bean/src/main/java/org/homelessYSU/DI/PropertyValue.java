package org.homelessYSU.DI;
/**
 * @description:通过set方法进行依赖注入的属性
 * @param
 * @return:
 * @author: walkinggo
 * @time: 2024/4/2 21:52
 */
public class PropertyValue {
    private final String type;
    private final String name;
    private final Object value;

    public PropertyValue(String type, String name, Object value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public Object getValue() {
        return this.value;
    }
}
