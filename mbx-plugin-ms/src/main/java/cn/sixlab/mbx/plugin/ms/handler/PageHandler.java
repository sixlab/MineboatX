/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-05-11 11:38
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.ms.handler;

import cn.sixlab.mbx.core.common.base.BaseHandler;
import cn.sixlab.mbx.core.common.util.Ctx;
import cn.sixlab.mbx.plugin.ms.beans.MsArchive;
import cn.sixlab.mbx.plugin.ms.service.ArchiveService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageHandler extends BaseHandler {

    @RequestMapping("/{pageNo}")
    public String archive(@PathVariable Integer pageNo, ModelMap model) {
        ArchiveService service = Ctx.getBean(ArchiveService.class);

        Page<MsArchive> result = service.queryRecent(pageNo, null);

        model.put("result", result);
        return "ms/archives";
    }
}
