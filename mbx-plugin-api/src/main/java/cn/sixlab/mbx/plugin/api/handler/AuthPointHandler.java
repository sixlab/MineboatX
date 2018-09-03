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

import cn.sixlab.mbx.core.common.beans.ModelJson;
import cn.sixlab.mbx.plugin.api.beans.MbxPointTask;
import cn.sixlab.mbx.plugin.api.service.PointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/point")
public class AuthPointHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private PointService service;
    
    @PostMapping(value = "/info")
    public ModelJson info() {
        ModelJson json = new ModelJson();
        
        json.setData(service.info());
        
        return json;
    }
    
    @PostMapping(value = "/today")
    public ModelJson today() {
        ModelJson json = new ModelJson();
        
        json.setData(service.today());
        
        return json;
    }
    
    @PostMapping(value = "/exchange")
    public ModelJson exchange(Integer point, String name) {
        
        service.exchange(point, name);
        
        return new ModelJson();
    }
    
    @PostMapping(value = "/task/add")
    public ModelJson taskAdd(@RequestBody MbxPointTask pointTask) {
        
        service.taskAdd(pointTask);
        
        return new ModelJson();
    }
    
    @PostMapping(value = "/task/modify")
    public ModelJson taskModify(@RequestBody MbxPointTask pointTask) {
    
        service.taskModify(pointTask);
    
        return new ModelJson();
    }
    
    @PostMapping(value = "/task/finish")
    public ModelJson taskFinish(Integer taskId) {
        
        service.taskFinish(taskId);
        
        return new ModelJson();
    }
    
}

