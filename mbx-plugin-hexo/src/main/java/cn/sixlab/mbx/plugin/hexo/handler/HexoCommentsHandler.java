/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/5/26 23:47
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.hexo.handler;

import cn.sixlab.mbx.core.common.base.BaseHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hexo/comments")
public class HexoCommentsHandler extends BaseHandler {
    
    @RequestMapping("/page")
    public String page(ModelMap modelMap) {
        
        
        return "hexo/comments/page";
    }
    
    @RequestMapping("/list/${path}")
    public String index(ModelMap modelMap, @PathVariable String path, String token) {
        // 校验 Token
        
        // 读取评论
        
        return "hexo/comments/list";
    }
    
}
