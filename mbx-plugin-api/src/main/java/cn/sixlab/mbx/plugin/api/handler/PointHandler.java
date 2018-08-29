/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/8/29 00:28
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.handler;

import cn.sixlab.mbx.plugin.api.service.PointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/point")
public class PointHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private PointService service;
    
    @GetMapping(value = "/apps/{year}/{month}/{day}")
    public String apps(@PathVariable("year") Integer year,
            @PathVariable("month") Integer month,
            @PathVariable("day") Integer day, ModelMap modelMap) {
        
        String scheme = "mineapps://www.sixlab.com/point?year=" + year + "&month=" + month + "&day=" + day;
        
        modelMap.put("year", year);
        modelMap.put("month", month);
        modelMap.put("day", day);
        modelMap.put("scheme", scheme);
        
        return "api/apps";
    }
    
}

