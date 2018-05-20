/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-04-28 18:07
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.core.common.redis;

import cn.sixlab.mbx.core.common.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CacheManage {
    
    @Autowired
    private StringRedisTemplate template;
    
    public String get(String key) {
        return template.opsForValue().get(key);
    }
    
    public <T> T get(String key, Class<T> clz) {
        String value = template.opsForValue().get(key);
        return JsonUtil.toBean(value, clz);
    }
    
    public void del(String key) {
        template.delete(key);
    }
    
    public void put(String key, Object val) {
        this.put(key, JsonUtil.toJson(val));
    }
    
    public void put(String key, Object val, long sec) {
        this.put(key, JsonUtil.toJson(val), sec);
    }
    
    public void put(String key, String val) {
        template.opsForValue().set(key, JsonUtil.toJson(val));
    }
    
    public void put(String key, String val, long sec) {
        template.opsForValue().set(key, val, sec, TimeUnit.SECONDS);
    }
}
