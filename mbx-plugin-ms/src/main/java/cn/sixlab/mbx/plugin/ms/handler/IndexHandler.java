/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * [图片]https://sixlab.cn/
 *
 * @time: 2018-05-11 18:44
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.ms.handler;

import cn.sixlab.mbx.core.common.base.BaseHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexHandler extends BaseHandler {

    @RequestMapping(value = {"","/","index","home"})
    public String index() {
        return "forward:/page/1";
    }

}
