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
import cn.sixlab.mbx.plugin.api.service.Exec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Component
public class GitMqListener implements MbxMessageListener {
    private static Logger logger = LoggerFactory.getLogger(GitMqListener.class);
    
    @Override
    public void onMessage(String message) {
        logger.info("消息：" + message);
        if(StringUtils.hasLength(message)){
            try {
                String log;
                switch (message) {
                    case "nginx":
                        log = Exec.run("/var/www/sixlab_config", "git", "pull");
                        logger.info(log);
        
                        log = Exec.run("/usr/sbin/", "service", "nginx", "reload");
                        logger.info(log);
                        break;
                    case "config":
                        log = Exec.run("/var/www/sixlab_config", "git", "pull");
                        logger.info(log);
                        break;
                    case "hexo":
                        log = Exec.run("/var/www/blogs/", "git", "pull");
                        logger.info(log);
    
                        log = Exec.run("/var/www/blogs/", "git", "submodule", "update");
                        logger.info(log);
    
                        log = Exec.run("/var/www/blogs/", "hexo", "g");
                        logger.info(log);
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    
        }
    }
}
