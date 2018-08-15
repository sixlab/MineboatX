/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/5/20 22:44
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.hexo.handler;

import cn.sixlab.mbx.core.common.base.BaseHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index/hexo")
public class IndexHexoHandler extends BaseHandler {
    private static Logger logger = LoggerFactory.getLogger(IndexHexoHandler.class);

    @RequestMapping(value = {"", "/"})
    public String index(ModelMap modelMap) {
        String uri = "/auth/article/list";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean logined = false;
        if ( null != authentication.getPrincipal() ) {
            String username = (String) authentication.getPrincipal();

            if( StringUtils.hasLength(username) && !"anonymousUser".equalsIgnoreCase(username) ){
                logined = true;
            }
        }

        logger.info("hexo登录状态:"+logined);

        if ( logined ) {
            return "redirect:"+uri;
        }
        
        modelMap.put("sub", "hexo");
        modelMap.put("url", uri);
        return "login";
    }
}
