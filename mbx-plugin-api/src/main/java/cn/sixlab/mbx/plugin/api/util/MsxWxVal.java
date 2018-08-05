/**
 * Copyright (c) 2017 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2017/12/12 16:38
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MsxWxVal {
    @Value("${msx.wx.token}")
    private String wxToken;
    
    @Value("${msx.wx.key}")
    private String wxKey;
    
    @Value("${msx.wx.appId}")
    private String wxAppId;
    
    @Value("${msx.wx.appSecret}")
    private String wxAppSecret;
    
    @Value("${msx.wx.openId}")
    private String wxOpenId;
    
    public String getWxToken() {
        return wxToken;
    }
    
    public void setWxToken(String wxToken) {
        this.wxToken = wxToken;
    }
    
    public String getWxKey() {
        return wxKey;
    }
    
    public void setWxKey(String wxKey) {
        this.wxKey = wxKey;
    }
    
    public String getWxAppId() {
        return wxAppId;
    }
    
    public void setWxAppId(String wxAppId) {
        this.wxAppId = wxAppId;
    }
    
    public String getWxAppSecret() {
        return wxAppSecret;
    }
    
    public void setWxAppSecret(String wxAppSecret) {
        this.wxAppSecret = wxAppSecret;
    }
    
    public String getWxOpenId() {
        return wxOpenId;
    }
    
    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }
}
