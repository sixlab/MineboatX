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
import cn.sixlab.mbx.plugin.hexo.bean.HexoArticle;
import cn.sixlab.mbx.plugin.hexo.service.ArticleService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/article")
public class AuthArticleHandler extends BaseHandler {
    private Logger logger = getLogger(this);
    
    @Autowired
    private ArticleService service;
    
    @RequestMapping("/list")
    public String list(ModelMap map, Integer pageNo, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
    
        Page<HexoArticle> content = service.articleList(pageRequest);
    
        map.put("content", content);
        
        return "hexo/article/list";
    }
    
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
