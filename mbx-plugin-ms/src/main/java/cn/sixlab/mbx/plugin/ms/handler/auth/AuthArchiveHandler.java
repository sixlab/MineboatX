/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/5/8 23:09
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.ms.handler.auth;

import cn.sixlab.mbx.core.common.base.BaseHandler;
import cn.sixlab.mbx.plugin.ms.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/archive")
public class AuthArchiveHandler extends BaseHandler {
    
    @Autowired
    private ArchiveService service;
    
    @RequestMapping("/{archiveId}")
    public String archive(@PathVariable Integer archiveId, ModelMap model) {
        
        return "ms/auth/archive";
    }
    
    @RequestMapping(value = {"/", "", "/index"})
    public String archives() {
        
        return "ms/auth/archives";
    }
}
