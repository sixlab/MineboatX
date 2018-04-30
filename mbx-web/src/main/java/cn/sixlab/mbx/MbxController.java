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
import cn.sixlab.mbx.core.common.util.ContextUtil;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySources;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Iterator;

@Controller
public class MbxController extends BaseHandler {

    @RequestMapping("/index")
    public String index(){
        System.out.println("1");
        System.out.println("2");

        PropertySourcesPlaceholderConfigurer bean = ContextUtil.getBean(PropertySourcesPlaceholderConfigurer.class);
        PropertySources propertySources = bean.getAppliedPropertySources();
        Iterator<PropertySource<?>> iterator = propertySources.iterator();
        while (iterator.hasNext()){
            PropertySource<?> next = iterator.next();
            System.out.println(next.getName());
            System.out.println(next.getProperty("mbx.jwt.header"));
        }

        System.out.println("3");
        return "login";
    }
}
