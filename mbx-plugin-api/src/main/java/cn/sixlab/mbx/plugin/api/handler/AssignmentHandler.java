/**
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/1/15 16:07
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.handler;

import cn.sixlab.mbx.core.common.base.BaseHandler;
import cn.sixlab.mbx.core.common.beans.ModelJson;
import cn.sixlab.mbx.plugin.api.beans.MsxAssignment;
import cn.sixlab.mbx.plugin.api.beans.MsxAssignmentRule;
import cn.sixlab.mbx.plugin.api.service.AssignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/auth/assignment")
public class AssignmentHandler extends BaseHandler {
    private static Logger logger = LoggerFactory.getLogger(AssignmentHandler.class);
    
    @Autowired
    private AssignmentService assignmentService;
    
    @GetMapping(value = "/{year}/{month}/{day}")
    public ModelJson assignment(@PathVariable("year") Integer year,
            @PathVariable("month") Integer month,
            @PathVariable("day") Integer day) {
    
        LocalDate localDate = LocalDate.of(year, month, day);
        List<List<MsxAssignment>> assignmentList = assignmentService.getAssignment(Date.valueOf(localDate));
        
        ModelJson json = new ModelJson();
        return json.setData(assignmentList);
    }
    
    @GetMapping(value = "/rules")
    public ModelJson rules() {
        logger.debug("rules");
    
        List<MsxAssignmentRule> ruleList = assignmentService.getAssignments();
    
        ModelJson json = new ModelJson();
        return json.setData(ruleList);
    }
    
    @PutMapping("/finish/{assignmentId}/{status}")
    public ModelJson finish(@PathVariable Integer assignmentId,
            @PathVariable boolean status) {
        logger.debug("finish");
        
        ModelJson result = new ModelJson();
        
        MsxAssignment assignment = assignmentService.changeStatus(assignmentId, status);
        
        return result.setData(assignment);
    }
}
