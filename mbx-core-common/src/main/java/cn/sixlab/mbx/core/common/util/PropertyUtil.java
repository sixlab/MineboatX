/*
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

import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;

public class PropertyUtil {

    public static String getValue(String key) {
        Environment bean = ContextUtil.getBean(Environment.class);
        if (bean.containsProperty(key)) {
            return bean.getProperty(key);
        }
        return "";
    }

    public static String getValue(String key, String defaultValue) {
        Environment bean = ContextUtil.getBean(Environment.class);
        if (bean.containsProperty(key)) {
            return bean.getProperty(key);
        }
        return defaultValue;
    }

    public static <T>T getValue(String key, Class<T> clz){
        String value = getValue(key);

        DefaultConversionService service = new DefaultConversionService();
        T result = service.convert(value, clz);

        return result;
    }

    public static int getIntValue(String key) {
        String value = getValue(key);

        DefaultConversionService service = new DefaultConversionService();
        Integer result = service.convert(value, Integer.class);

        if (null != result) {
            return result;
        }
        return 0;
    }

    public static int getIntValue(String key, int defaultValue) {
        String value = getValue(key);

        DefaultConversionService service = new DefaultConversionService();
        Integer result = service.convert(value, Integer.class);

        if (null != result) {
            return result;
        }
        return defaultValue;
    }
}
