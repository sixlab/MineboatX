/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/5/20 21:44
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.hexo.handler;

import cn.sixlab.mbx.core.common.base.BaseHandler;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/article")
public class AuthArticleHandler extends BaseHandler {
    private Logger logger = getLogger(this);
    
    
    @RequestMapping("/{articleId}")
    public String article(@PathVariable String articleId, ModelMap map) {
        
        return article(map, articleId);
    }
    
    @RequestMapping("/{year}/{month}/{day}/{articleId}")
    public String archive(@PathVariable String year, @PathVariable String month,
            @PathVariable String day,
            @PathVariable String articleId, ModelMap map) {
        articleId = year + "/" + month + "/" + day + "/" + articleId;
    
        return article(articleId, map);
    }
    
    private String article(ModelMap map, String articleId) {
        
        
        return "hexo/article";
    }
}
