package org.homelessYSU.beans.factory.config;

import org.homelessYSU.beans.factory.ListableBeanFactory;

/**
 * @description:将三个接口集成在一起。
 * @param
 * @return:
 * @author: walkinggo
 * @time: 2024/4/4 15:59
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory{
}
