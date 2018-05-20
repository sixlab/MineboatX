/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-04-28 18:30
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.core.common.mq;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

public interface MqContainer {
    
    @Bean
    default RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        
        container.setConnectionFactory(connectionFactory);
        addListener(container);
        
        return container;
    }
    
    void addListener(RedisMessageListenerContainer container);
}
