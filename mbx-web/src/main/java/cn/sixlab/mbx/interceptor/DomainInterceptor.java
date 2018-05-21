/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-05-01 00:27
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.interceptor;

import cn.sixlab.mbx.config.DomainConfig;
import cn.sixlab.mbx.core.common.util.WebUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DomainInterceptor implements HandlerInterceptor {
    private static String packageName = "cn.sixlab.mbx.plugin.";

    private DomainConfig domainConfig;
    
    public DomainInterceptor(DomainConfig subDomain) {
        this.domainConfig = subDomain;
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws ModelAndViewDefiningException {

        if (null == handler) {
            return true;
        }

        if(handler instanceof HandlerMethod){
            String subDomain = WebUtil.getSubDomain();
            String pluginName = domainConfig.getSub().get(subDomain);
            //如果所访问的二级域名没有在配置列表中，随便访问
            //只要在 nginx 配置排除通过 ip 访问的，并且不用一级域名来访问，正式环境没问题
            if(StringUtils.hasLength(pluginName)){
                
                //非首页，就要判断是不是可访问了。
                String pName = ((HandlerMethod) handler).getBeanType().getPackage().getName();
                if (pName.startsWith(packageName)) {
                    //只判断 plugin 下的 handler，非 plugin 下的应该是共用的，所有 plugin 都可以访问。
    
                    if (!pName.startsWith(packageName + pluginName + ".")) {
                        response.setStatus(404);
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
