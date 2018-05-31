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
import cn.sixlab.mbx.core.common.beans.ModelJson;
import cn.sixlab.mbx.core.common.util.LogUtil;
import cn.sixlab.mbx.plugin.hexo.bean.HexoArticle;
import cn.sixlab.mbx.plugin.hexo.service.ArticleService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/auth/article")
public class AuthArticleHandler extends BaseHandler {
    private Logger logger = LogUtil.getLogger(this);
    
    @Autowired
    private ArticleService service;
    
    @Autowired
    private StringRedisTemplate template;
    
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
    
    @ResponseBody
    @RequestMapping("/submit")
    public ModelJson submitArticle(String fileId, String content) {
        ModelJson json = new ModelJson();
        
        json.setSuccess(service.submitArticle(fileId, content));
        
        return json;
    }
    
    @ResponseBody
    @RequestMapping("/delete")
    public ModelJson deleteArticle(String fileId) {
        ModelJson json = new ModelJson();
        
        json.setSuccess(service.deleteArticle(fileId));
        
        return json;
    }
    
    @RequestMapping("/new")
    public String newArticle(ModelMap map) {
        
        HexoArticle article = new HexoArticle();
        
        LocalDateTime localDate = LocalDateTime.now();
        String fileId = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-"));
        String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String id = localDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"));
        article.setFileId(fileId);
        article.setContent("---\n" +
                "title: \n" +
                "id: \n" + id +
                "date: \n" + date +
                "categories:\n" +
                "  - 未分类\n" +
                "tags:\n" +
                "  - 未定义\n" +
                "tocnum: false\n" +
                "comments: true\n" +
                "---\n" +
                "{% img lightImg postImg lightCenter /images/fileId/filename.png 400 %}");
        
        map.put("article", article);
        map.put("edit", false);
        
        return "hexo/article/article";
    }
    
    @RequestMapping("/edit/{fileId}")
    public String edit(@PathVariable String fileId, ModelMap map) {
        
        HexoArticle article = service.article(fileId);
        
        map.put("article", article);
        map.put("edit", true);
        
        return "hexo/article/article";
    }
    
    @ResponseBody
    @RequestMapping("/push")
    public ModelJson push() {
        ModelJson json = new ModelJson();
        
        template.convertAndSend("git", "push");
        
        return json;
    }
    
    @ResponseBody
    @RequestMapping("/publish")
    public ModelJson publish() {
        ModelJson json = new ModelJson();
        
        template.convertAndSend("git", "publish");
        
        return json;
    }
}
