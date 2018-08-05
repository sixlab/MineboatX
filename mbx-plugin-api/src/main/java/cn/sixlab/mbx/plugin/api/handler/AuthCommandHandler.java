/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/5/23 19:53
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.handler;

import cn.sixlab.mbx.core.common.base.BaseHandler;
import cn.sixlab.mbx.core.common.util.LogUtil;
import cn.sixlab.mbx.plugin.api.service.CommandService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/command")
public class AuthCommandHandler extends BaseHandler {
    private Logger logger = LogUtil.getLogger(this);
    
    @Autowired
    private CommandService service;
    
    @PostMapping(value = "/shell/{command}")
    public String github(@PathVariable String command) {
        logger.info("来自 shell ：" + command);
        if (StringUtils.hasLength(command)) {
            
            service.runCommand(command);
            
        }
        return "ok";
    }
    
}
