/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/5/19 20:46
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.mq;

import cn.sixlab.mbx.core.common.mq.MqContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
public class ApiMqContainer implements MqContainer{
    @Autowired
    private GitMqListener gitMqListener;
    
    @Override
    public void addListener(RedisMessageListenerContainer container) {
        container.addMessageListener(gitMqListener, new ChannelTopic("git"));
    }
}
