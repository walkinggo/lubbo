package org.homelessYSU.env;
/**
 * @description:环境的实例
 * @param
 * @return:
 * @author: walkinggo
 * @time: 2024/4/4 16:16
 */
public interface Environment extends PropertyResolver {
    String[] getActiveProfiles();

    String[] getDefaultProfiles();

    boolean acceptsProfiles(String... profiles);

}