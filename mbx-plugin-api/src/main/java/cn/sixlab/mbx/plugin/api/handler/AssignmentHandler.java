/**
 * Copyright (c) 2017 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2017/12/19 14:42
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.handler;

import cn.sixlab.mbx.core.common.base.BaseHandler;
import cn.sixlab.mbx.core.common.beans.ModelJson;
import cn.sixlab.mbx.plugin.api.util.DateTimeUtil;
import cn.sixlab.mbx.plugin.api.beans.MsxAssignment;
import cn.sixlab.mbx.plugin.api.service.AssignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Controller
@RequestMapping("/assignment")
public class AssignmentHandler extends BaseHandler {
    private static Logger logger = LoggerFactory.getLogger(AssignmentHandler.class);
    
    @Autowired
    private AssignmentService assignmentService;
    
    @GetMapping(value = "/{year}/{month}/{day}")
    public String assignment(@PathVariable("year") Integer year,
            @PathVariable("month") Integer month,
            @PathVariable("day") Integer day, ModelMap modelMap) {
        logger.debug("send");
        
        LocalDate localDate = LocalDate.of(year, month, day);
        
        modelMap.put("year", year);
        modelMap.put("month", month);
        modelMap.put("day", day);
        
        modelMap.put("weekOfMonth", DateTimeUtil.weekOfMonth(localDate));
        modelMap.put("weekOfYear", DateTimeUtil.weekOfYear(localDate));
        modelMap.put("dayOfWeekText", localDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.CHINA));
        
        modelMap.put("assignmentList", assignmentService.getAssignment(Date.valueOf(localDate)));
        
        return "assignment";
    }
    
    @GetMapping(value = "/apps/{year}/{month}/{day}")
    public String apps(@PathVariable("year") Integer year,
            @PathVariable("month") Integer month,
            @PathVariable("day") Integer day, ModelMap modelMap) {
        logger.debug("send");
        
        String scheme = "mineapps://www.sixlab.com/assignment?year="+year+"&month="+month+"&day="+day;
        
        modelMap.put("year", year);
        modelMap.put("month", month);
        modelMap.put("day", day);
        modelMap.put("scheme", scheme);
        
        return "apps";
    }
    
    @GetMapping(value = "/rules")
    public String rules(ModelMap modelMap) {

        modelMap.put("ruleList", assignmentService.getAssignments());
        
        return "rules";
    }
    
    @ResponseBody
    @PutMapping("/finish/{assignmentId}/{status}")
    public ModelJson finish(@PathVariable Integer assignmentId, @PathVariable boolean status) {
        logger.debug("finish");
        
        ModelJson result = new ModelJson();
        
        MsxAssignment assignment = assignmentService.changeStatus(assignmentId, status);
        
        return result.setData(assignment);
    }
}
