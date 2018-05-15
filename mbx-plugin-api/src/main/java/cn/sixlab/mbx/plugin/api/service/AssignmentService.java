/**
 * Copyright (c) 2017 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2017/12/19 16:01
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.service;

import cn.sixlab.mbx.plugin.api.beans.MsxAssignment;
import cn.sixlab.mbx.plugin.api.beans.MsxAssignmentRule;
import cn.sixlab.mbx.plugin.api.dao.AssignmentRepo;
import cn.sixlab.mbx.plugin.api.dao.RuleRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssignmentService {
    private static Logger logger = LoggerFactory.getLogger(AssignmentService.class);
    
    @Autowired
    private AssignmentRepo assignmentRepo;
    
    @Autowired
    private RuleRepo ruleRepo;
    
    public List<List<MsxAssignment>> getAssignment(Date date) {
        List<MsxAssignment> assignmentList = assignmentRepo.findByAssignmentDateOrderByAssignmentHourAscId(date);
    
        List<List<MsxAssignment>> list = new ArrayList<>();
        
        int hourFlag = -1;
        List<MsxAssignment> tempList = null;
        for (MsxAssignment assignment : assignmentList) {
            Integer hour = assignment.getAssignmentHour();
            if (hour != hourFlag) {
                hourFlag = hour;
                tempList = new ArrayList<>();
                list.add(tempList);
            }
            tempList.add(assignment);
        }
        
        return list;
    }
    
    public MsxAssignment changeStatus(Integer assignmentId, boolean status) {
        MsxAssignment assignment = assignmentRepo.getOne(assignmentId);
    
        assignment.setFinishCheck(status);
    
        assignmentRepo.save(assignment);
    
        return assignment;
    }
    
    public List<MsxAssignmentRule> getAssignments() {
        Date date = Date.valueOf(LocalDate.now());
        List<MsxAssignmentRule> ruleList = ruleRepo.queryActiveRule(date);
        return ruleList;
    }
}
