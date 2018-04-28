/**
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-04-28 17:05
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.core.common.util;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySources;

import java.util.Iterator;

public class PropertyUtil {

    public static Object getValue(String key) {
        PropertySourcesPlaceholderConfigurer bean = ContextUtil.getBean(PropertySourcesPlaceholderConfigurer.class);
        PropertySources propertySources = bean.getAppliedPropertySources();
        Iterator<PropertySource<?>> iterator = propertySources.iterator();
        while (iterator.hasNext()) {
            PropertySource<?> next = iterator.next();
            if (next.containsProperty(key)) {
                return next.getProperty(key);
            }
        }
        return null;
    }

    public static <T> T getValue(String key, Class<T> clz) {
        PropertySourcesPlaceholderConfigurer bean = ContextUtil.getBean(PropertySourcesPlaceholderConfigurer.class);
        PropertySources propertySources = bean.getAppliedPropertySources();
        Iterator<PropertySource<?>> iterator = propertySources.iterator();
        while (iterator.hasNext()) {
            PropertySource<?> next = iterator.next();
            if (next.containsProperty(key)) {
                if(clz.isInstance(next.getProperty(key))){
                    return (T)next.getProperty(key);
                }
            }
        }
        return null;
    }
}
