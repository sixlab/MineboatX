/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-05-01 01:46
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "mbx.domain")
public class DomainConfig {
    private HashMap<String, String> sub;
    private List<String> escape;
    private List<String> subEscape;
    
    public HashMap<String, String> getSub() {
        return sub;
    }
    
    public void setSub(HashMap<String, String> sub) {
        this.sub = sub;
    }
    
    public List<String> getEscape() {
        return escape;
    }
    
    public void setEscape(List<String> escape) {
        this.escape = escape;
    }
    
    public List<String> getSubEscape() {
        return subEscape;
    }
    
    public void setSubEscape(List<String> subEscape) {
        this.subEscape = subEscape;
    }
}
