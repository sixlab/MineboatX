/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/5/19 20:31
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.mq;

import cn.sixlab.mbx.core.common.mq.MbxMessageListener;
import cn.sixlab.mbx.plugin.api.service.CommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class GitMqListener implements MbxMessageListener {
    private static Logger logger = LoggerFactory.getLogger(GitMqListener.class);
    
    @Autowired
    private CommandService service;
    
    @Override
    public void onMessage(String message) {
        logger.info("消息：" + message);
        if (StringUtils.hasLength(message)) {
            
            service.runCommand(message);
            
        }
    }
}
