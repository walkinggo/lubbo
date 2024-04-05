package org.homelessYSU.beans.factory.config;

import java.util.ArrayList;
import java.util.List;
/**
 * @description:管理一个bean的所有构造器的参数
 * @param
 * @return:
 * @author: walkinggo
 * @time: 2024/4/4 16:03
 */
public class ConstructorArgumentValues {
    private final List<ConstructorArgumentValue> argumentValueList = new ArrayList<ConstructorArgumentValue>();

    public ConstructorArgumentValues() {
    }

    public void addArgumentValue(ConstructorArgumentValue argumentValue) {
        this.argumentValueList.add(argumentValue);
    }

    public ConstructorArgumentValue getIndexedArgumentValue(int index) {
        ConstructorArgumentValue argumentValue = this.argumentValueList.get(index);
        return argumentValue;
    }

    public int getArgumentCount() {
        return (this.argumentValueList.size());
    }

    public boolean isEmpty() {
        return (this.argumentValueList.isEmpty());
    }
}