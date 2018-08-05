/**
 * Copyright (c) 2017 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2017/12/12 16:23
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.business;

import cn.sixlab.mbx.core.common.redis.CacheManage;
import cn.sixlab.mbx.core.common.util.DigestUtil;
import cn.sixlab.mbx.core.common.util.JsonUtil;
import cn.sixlab.mbx.plugin.api.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class WxBusiness {
    private static Logger logger = LoggerFactory.getLogger(WxBusiness.class);
    
    @Autowired
    private CacheManage cacheManage;
    
    public boolean checkToken(String signature, String timestamp, String nonce, String wxToken) {
        String[] tmpArr = new String[]{wxToken, timestamp, nonce};
        tmpArr = stringSort(tmpArr);
        
        String tmpStr = "";
        for (int i = 0; i < tmpArr.length; i++) {
            tmpStr += tmpArr[i];
        }
        tmpStr = DigestUtil.encodeSHA1(tmpStr);
        
        logger.info("tmpStrSHA=" + tmpStr);
        logger.info("signature=" + signature);
        
        return StringUtils.startsWithIgnoreCase(tmpStr, signature);
    }
    
    private String[] stringSort(String[] s) {
        List<String> list = new ArrayList<>(s.length);
        for (int i = 0; i < s.length; i++) {
            list.add(s[i]);
        }
        Collections.sort(list);
        return list.toArray(s);
    }
    
    public String accessToken(String appId, String appSecret) {
        String wxAccessToken = cacheManage.get(appId);
        
        if (StringUtils.isEmpty(wxAccessToken)) {
            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                    + appId + "&secret=" + appSecret;
    
            String result = HttpUtil.reqJson(url);
            Map map = JsonUtil.toBean(result, Map.class);
            
            wxAccessToken = String.valueOf(map.get("access_token"));
            
            String expiresIn = String.valueOf(map.get("expires_in"));
            long expires = Long.parseLong(expiresIn);
    
            cacheManage.put(appId, wxAccessToken, expires - 1200);
        }
        return wxAccessToken;
    }
}
