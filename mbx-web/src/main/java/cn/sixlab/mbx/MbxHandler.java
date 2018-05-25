/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-04-27 17:26
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx;

import cn.sixlab.mbx.config.DomainConfig;
import cn.sixlab.mbx.core.common.base.BaseHandler;
import cn.sixlab.mbx.core.common.beans.ModelJson;
import cn.sixlab.mbx.core.common.util.TokenUtil;
import cn.sixlab.mbx.core.common.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MbxHandler extends BaseHandler {

    @Autowired
    private DomainConfig domainConfig;

    @RequestMapping(value = {"/index","","/"})
    public String index(ModelMap map){
        String subDomain = WebUtil.getSubDomain();
        String pluginName = domainConfig.getSub().get(subDomain);

        String result = "login";
        if(StringUtils.hasLength(pluginName)){
            result = "forward:/index/" + pluginName;
        }
        map.put("url","https://sixlab.cn/");

        return result;
    }
    
    @RequestMapping(value = "/logout")
    public String logout(ModelMap map) {
        WebUtil.delCookie(TokenUtil.getHeader());
        SecurityContextHolder.clearContext();
        return "result";
    }

    @ResponseBody
    @GetMapping("/auth/login/refresh")
    public ModelJson refreshToken() {
        Map data = new HashMap();
        String subject = TokenUtil.getValue();
        if (StringUtils.hasLength(subject)) {
            long expiration = TokenUtil.getFinalExpiration();
            String token = TokenUtil.createToken(subject, expiration);
            data.put("token", token);
            data.put("expiration", expiration);
        }
        return new ModelJson().setData(data);
    }
}
