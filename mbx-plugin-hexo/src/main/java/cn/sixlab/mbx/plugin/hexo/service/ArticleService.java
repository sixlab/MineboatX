/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/5/20 21:50
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.hexo.service;

import cn.sixlab.mbx.core.common.base.BaseService;
import cn.sixlab.mbx.core.common.util.LogUtil;
import cn.sixlab.mbx.plugin.hexo.bean.HexoArticle;
import cn.sixlab.mbx.plugin.hexo.util.HexoUtil;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService extends BaseService{
    private Logger logger = LogUtil.getLogger(this);
    
    public Page<HexoArticle> articleList(PageRequest pageRequest) {
        List<HexoArticle> articleList = HexoUtil.getArticles();
        int total = articleList.size();
    
        pageRequest = PageRequest.of(0, total);
    
        PageImpl<HexoArticle> result = new PageImpl(articleList, pageRequest, total);
        
        return result;
    }
    
    public HexoArticle article(String fileId) {
        return HexoUtil.getArticle(fileId);
    }
}
