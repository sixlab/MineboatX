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

import cn.sixlab.mbx.core.common.base.BaseHandler;
import cn.sixlab.mbx.core.common.beans.ModelJson;
import cn.sixlab.mbx.core.common.util.TokenUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MbxController extends BaseHandler {

    @RequestMapping("/index")
    public String index(){
        //System.out.println("1");
        //System.out.println("2");
        //
        //PropertySourcesPlaceholderConfigurer bean = ContextUtil.getBean(PropertySourcesPlaceholderConfigurer.class);
        //PropertySources propertySources = bean.getAppliedPropertySources();
        //Iterator<PropertySource<?>> iterator = propertySources.iterator();
        //while (iterator.hasNext()){
        //    PropertySource<?> next = iterator.next();
        //    System.out.println(next.getName());
        //    System.out.println(next.getProperty("mbx.jwt.header"));
        //}
        //
        //System.out.println("3");
        return "login";
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
