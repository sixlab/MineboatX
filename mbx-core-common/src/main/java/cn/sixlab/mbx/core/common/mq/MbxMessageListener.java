/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-04-28 18:31
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.core.common.mq;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public interface MbxMessageListener extends MessageListener {
    
    @Override
    default void onMessage(Message message, byte[] bytes) {
        String msg = "";
        if (null != message) {
            byte[] body = message.getBody();
            if (null != body) {
                msg = new String(body);
            }
        }
        onMessage(msg);
    }
    
    void onMessage(String message);
}
