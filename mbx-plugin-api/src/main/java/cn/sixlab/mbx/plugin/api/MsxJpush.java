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
package cn.sixlab.mbx.plugin.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MsxJpush {
    @Value("${msx.jpush.secret}")
    private String jpushSecret;
    
    @Value("${msx.jpush.key}")
    private String jpushKey;
    
    public String getJpushSecret() {
        return jpushSecret;
    }
    
    public void setJpushSecret(String jpushSecret) {
        this.jpushSecret = jpushSecret;
    }
    
    public String getJpushKey() {
        return jpushKey;
    }
    
    public void setJpushKey(String jpushKey) {
        this.jpushKey = jpushKey;
    }
}
