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
package cn.sixlab.mbx.plugin.ms.handler;

import cn.sixlab.mbx.core.common.base.BaseHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index/ms")
public class IndexMsHandler extends BaseHandler {
    @RequestMapping("")
    public String index(ModelMap modelMap) {
        modelMap.put("sub", "ms");
        return "login";
    }
}
