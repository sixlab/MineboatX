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
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DomainInterceptor implements HandlerInterceptor {
    private DomainConfig domainConfig;
    
    public DomainInterceptor(DomainConfig subDomain) {
        this.domainConfig = subDomain;
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) {

        if (null == handler) {
            return true;
        }

        if(handler instanceof HandlerMethod){
            String pName = ((HandlerMethod) handler).getBeanType().getPackage().getName();

            boolean stop = false;
            if (pName.startsWith("cn.sixlab.mbx.plugin.")) {
                String subDomain = WebUtil.getSubDomain();
                if ("".equals(subDomain) || domainConfig.getSubEscape().contains(subDomain)){
                    stop = false;
                }else if (domainConfig.getSub().containsKey(subDomain)) {
                    if (pName.startsWith("cn.sixlab.mbx.plugin." + domainConfig.getSub().get(subDomain))) {
                        stop = false;
                    } else {
                        stop = true;
                    }
                } else {
                    String domain = WebUtil.getDomain();
                    if (domainConfig.getEscape().contains(domain)) {
                        stop = false;
                    } else {
                        stop = true;
                    }
                }
            }

            if (stop) {
                response.setStatus(404);
                return false;
            }
        }

        return true;
    }
}
