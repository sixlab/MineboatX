/**
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-04-28 13:34
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.core.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class JsonUtil {
    private final static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static String toJson(String[] keys, Object[] vals){
        try {
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < keys.length; i++) {
                map.put(keys[i], vals[i]);
            }
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T toBean(String content, Class<T> clz) {

        try {
            return objectMapper.readValue(content, clz);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> T obj2Bean(Object obj, Class<T> clz) {
        try {
            String json = objectMapper.writeValueAsString(obj);
            if (null != json && !"".equals(json)) {
                return objectMapper.readValue(json, clz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
