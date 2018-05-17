/**
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/1/23 15:32
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.handler;

import cn.sixlab.mbx.core.common.base.BaseHandler;
import cn.sixlab.mbx.plugin.api.service.GitHookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/git/hooks")
public class GitHooksHandler extends BaseHandler {
    private static Logger logger = LoggerFactory.getLogger(GitHooksHandler.class);
    
    @Autowired
    private GitHookService gitHookService;
    
    @PostMapping(value = "/github/{repo}")
    public String github(@RequestBody String data,
            @RequestHeader("X-Hub-Signature") String signature,
            @PathVariable String repo) throws IOException {
        logger.info("来自github：" + repo);
        logger.info(data);
        if (StringUtils.hasLength(data)) {
            gitHookService.github(data, signature);
        }
        return "ok";
    }
    
    @PostMapping(value = "/gitee/{repo}")
    public String gitee(@RequestBody String data, HttpServletRequest request,
            @PathVariable String repo) throws IOException {
        logger.info("来自gitee：" + repo);
        logger.info(data);
        if (StringUtils.hasLength(data)) {
            gitHookService.gitee(data);
        }
        return "ok";
    }
}
