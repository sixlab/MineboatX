/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-04-30 17:07
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.ms.handler;

import cn.sixlab.mbx.core.common.base.BaseHandler;
import cn.sixlab.mbx.plugin.ms.beans.MsArchive;
import cn.sixlab.mbx.plugin.ms.beans.MsComment;
import cn.sixlab.mbx.plugin.ms.service.ArchiveService;
import cn.sixlab.mbx.plugin.ms.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/archives")
public class ArchiveHandler extends BaseHandler {
    
    @Autowired
    private ArchiveService service;
    
    @Autowired
    private CommentService commentService;

    @RequestMapping("/{archiveId}")
    public String archive(@PathVariable Integer archiveId, ModelMap model) {

        MsArchive archive = service.getArchive(archiveId);

        Page<MsComment> result = commentService.list(null, null, archiveId);

        model.put("archive", archive);
        model.put("result", result);
        return "ms/archive";
    }
    
    @RequestMapping(value = {"/", "", "/index"})
    public String index() {
        
        return "forward:/archives/archives";
    }
    
    @RequestMapping("/archives")
    public String archives(Integer pageNo, Integer pageSize, ModelMap model) {

        Page<MsArchive> result = service.queryRecent(pageNo, pageSize);

        model.put("result", result);
        return "ms/archives";
    }

    @RequestMapping("/category")
    public String categories() {

        return "ms/categories";
    }

    @RequestMapping("/tag")
    public String tags() {

        return "ms/tags";
    }

    @RequestMapping("/category/{category1}/{category2}")
    public String category(@PathVariable String category1, @PathVariable String category2) {

        return "ms/archives";
    }

    @RequestMapping("/category/{category}")
    public String category(@PathVariable String category) {

        return "ms/archives";
    }

    @RequestMapping("/tag/{tag}")
    public String tag(@PathVariable String tag) {

        return "ms/archives";
    }

    @RequestMapping("/date/{year}")
    public String year(@PathVariable Integer year) {

        return "ms/archives";
    }

    @RequestMapping("/date/{year}/{month}")
    public String year(@PathVariable Integer year, @PathVariable Integer month) {

        return "ms/archives";
    }

    @RequestMapping("/date/{year}/{month}/{day}")
    public String year(@PathVariable Integer year, @PathVariable Integer month, @PathVariable Integer day) {

        return "ms/archives";
    }
}
