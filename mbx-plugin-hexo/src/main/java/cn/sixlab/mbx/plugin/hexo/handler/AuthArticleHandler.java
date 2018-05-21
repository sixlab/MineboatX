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
import cn.sixlab.mbx.core.common.util.LogUtil;
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
    private Logger logger = LogUtil.getLogger(this);
    
    @Autowired
    private ArticleService service;
    
    @RequestMapping("/list")
    public String list(ModelMap map, Integer pageNo, Integer pageSize) {
        return articleList(map, pageNo, pageSize);
    }
    
    @RequestMapping("/list/{pageSize}")
    public String list(ModelMap map, @PathVariable Integer pageSize) {
        return articleList(map, 0, pageSize);
    }
    
    private String articleList(ModelMap map, Integer pageNo, Integer pageSize) {
        if (null == pageNo) {
            pageNo = 0;
        }
        if (null == pageSize) {
            pageSize = 10;
        }
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        
        Page<HexoArticle> result = service.articleList(pageRequest);
        
        map.put("result", result);
        
        return "hexo/article/list";
    }
    
    @RequestMapping("/new")
    public String newArticle() {
        
        return "hexo/article/article";
    }
    
    @RequestMapping("/edit/{fileId}")
    public String edit(@PathVariable String fileId, ModelMap map) {
        
        HexoArticle article = service.article(fileId);
        
        map.put("article", article);
        
        return "hexo/article/article";
    }
}
