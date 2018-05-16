/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-05-04 18:34
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.ms.handler;

import cn.sixlab.mbx.core.common.base.BaseHandler;
import cn.sixlab.mbx.core.common.beans.ModelJson;
import cn.sixlab.mbx.core.common.util.WebUtil;
import cn.sixlab.mbx.plugin.ms.beans.MsComment;
import cn.sixlab.mbx.plugin.ms.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/comment")
public class CommentHandler extends BaseHandler {

    @Autowired
    private CommentService service;

    @ResponseBody
    @RequestMapping("/add")
    public ModelJson add(MsComment comment) {
        ModelJson json = new ModelJson();

        String ip = WebUtil.getIP();
        comment.setCommentIp(ip);
        service.addComment(comment);

        return json;
    }
    
    @ResponseBody
    @RequestMapping("/thumb")
    public ModelJson thumb(Integer commentId) {
        ModelJson json = new ModelJson();
        
        service.thumbComment(commentId);
        
        return json;
    }

    @RequestMapping("/list")
    public String list(Integer pageNo, Integer pageSize, Integer archiveId, ModelMap map) {

        Page<MsComment> result = service.list(pageNo, pageSize, archiveId);

        map.put("result", result);
        
        return "ms/comments";
    }

}
