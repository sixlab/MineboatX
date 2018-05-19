/**
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/1/26 16:53
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.service;

import cn.sixlab.mbx.core.common.base.BaseService;
import cn.sixlab.mbx.core.common.util.DigestUtil;
import cn.sixlab.mbx.core.common.util.JsonUtil;
import cn.sixlab.mbx.plugin.api.util.WebhooksParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GitHookService extends BaseService{
    private static Logger logger = LoggerFactory.getLogger(GitHookService.class);
    
    @Autowired
    private WebhooksParam webhooksParam;
    
    @Autowired
    private StringRedisTemplate template;
    
    public void github(String data, String signature) throws IOException {
        Map obj = JsonUtil.toBean(data, Map.class);
    
        String sha1 = "sha1=" + DigestUtil.encodeSHA1(data, webhooksParam.getGithubHeader());
        
        logger.info("length:"+ data.length());
        logger.info("signature:"+signature);
        logger.info("sha1sha1 :"+ sha1);
        
        if(sha1.equals(signature)){
            if ("PatrickRoot/PatrickRoot.github.io".equals(((Map) (obj.get("repository"))).get("full_name"))) {
                template.convertAndSend("git", "hexo");
            }
        } else {
            logger.error("密码错误：" + obj.get("password"));
        }
    }
    
    public void gitee(String data) throws IOException {
        Map obj = JsonUtil.toBean(data, Map.class);
        if (webhooksParam.getGiteeSecret().equals(obj.get("password"))) {
            Object repo = ((Map) (obj.get("repository"))).get("name");
            if ("configs".equals(repo) || "sixlab_config".equals(repo)) {
                List<Map> commits = (List) obj.get("commits");
                for (Map commit : commits) {
                    String msg = commit.get("message").toString();
                    Pattern pattern = Pattern.compile("\\$.*\\(\\s*(.*?)\\s*\\)");
                    Matcher matcher = pattern.matcher(msg);
                    if (matcher.find()) {
                        String text = matcher.group(1);
                        logger.info("操作命令："+text);
                        template.convertAndSend("git", text);
                    }
                }
            }
        } else {
            logger.error("密码错误：" + obj.get("password"));
        }
    }
}
