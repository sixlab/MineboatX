/**
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/1/26 16:49
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WebhooksParam {
    
    @Value("${mbx.webhooks.github}")
    private String githubHeader;
    
    @Value("${mbx.webhooks.gitee}")
    private String giteeSecret;
    
    public String getGithubHeader() {
        return githubHeader;
    }
    
    public void setGithubHeader(String githubHeader) {
        this.githubHeader = githubHeader;
    }
    
    public String getGiteeSecret() {
        return giteeSecret;
    }
    
    public void setGiteeSecret(String giteeSecret) {
        this.giteeSecret = giteeSecret;
    }
}
