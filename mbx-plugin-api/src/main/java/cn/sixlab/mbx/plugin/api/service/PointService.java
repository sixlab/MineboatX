/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/8/29 00:29
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.service;

import cn.sixlab.mbx.core.beans.entity.MbxUser;
import cn.sixlab.mbx.core.common.util.UserUtil;
import cn.sixlab.mbx.core.dao.repository.UserRepository;
import cn.sixlab.mbx.plugin.api.beans.MbxPoint;
import cn.sixlab.mbx.plugin.api.beans.MbxPointLog;
import cn.sixlab.mbx.plugin.api.beans.MbxPointTask;
import cn.sixlab.mbx.plugin.api.dao.PointLogRepo;
import cn.sixlab.mbx.plugin.api.dao.PointRepo;
import cn.sixlab.mbx.plugin.api.dao.PointTaskRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PointService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private PointRepo pointRepo;
    
    @Autowired
    private PointLogRepo logRepo;
    
    @Autowired
    private PointTaskRepo taskRepo;
    
    @Autowired
    private UserRepository userRepository;
    
    public List<MbxPointTask> today() {
        String username = UserUtil.getUsername();
        MbxUser user = userRepository.getByUsername(username);
        
        return taskRepo.selectToday(user.getId(), LocalDate.now());
    }
    
    public void taskAdd(MbxPointTask pointTask) {
        String username = UserUtil.getUsername();
        MbxUser user = userRepository.getByUsername(username);
        
        pointTask.setFinish(false);
        pointTask.setUserId(user.getId());
        pointTask.setInsertTime(LocalDateTime.now());
        
        taskRepo.save(pointTask);
    }
    
    public void taskModify(MbxPointTask pointTask) {
        MbxPointTask old = taskRepo.getOne(pointTask.getId());
        old.setTitle(pointTask.getTitle());
        old.setContent(pointTask.getContent());
        old.setRepeatType(pointTask.getRepeatType());
        old.setRepeatInterval(pointTask.getRepeatInterval());
        old.setPoint(pointTask.getPoint());
        old.setTaskOrder(pointTask.getTaskOrder());
        old.setTaskDate(pointTask.getTaskDate());
        old.setFinishDate(pointTask.getFinishDate());
        
        taskRepo.save(old);
    }
    
    public void taskFinish(Integer taskId) {
        MbxPointTask task = taskRepo.getOne(taskId);
        
        String repeatType = task.getRepeatType();
        Integer interval = task.getRepeatInterval();
        LocalDate localDate = task.getTaskDate();
        switch (repeatType) {
            case "0":
                task.setFinish(true);
            case "1":
                localDate = localDate.plusDays(interval);
            case "2":
                localDate = localDate.plusWeeks(interval);
            case "3":
                localDate = localDate.plusMonths(interval);
            case "4":
                localDate = localDate.plusYears(interval);
        }
        
        task.setTaskDate(localDate);
        
        taskRepo.save(task);
    
        Integer userId = task.getUserId();
        MbxPoint point = pointRepo.getOne(userId);
        if(null == point){
            point = new MbxPoint();
            point.setUserId(userId);
            point.setPoint(task.getPoint());
            point.setInsertTime(LocalDateTime.now());
            
            pointRepo.save(point);
        }else{
            point.setPoint(point.getPoint()+task.getPoint());
            
            pointRepo.save(point);
        }
    
        MbxPointLog log = new MbxPointLog();
        log.setUserId(userId);
        log.setPoint(task.getPoint());
        log.setAfterPoint(point.getPoint());
        log.setRemark(task.getTitle());
    
        logRepo.save(log);
    }
    
    public void exchange(Integer point, String name) {
        String username = UserUtil.getUsername();
        MbxUser user = userRepository.getByUsername(username);
    
        MbxPoint mbxPoint = pointRepo.getOne(user.getId());
        if (null == point) {
            return;
        } else {
            mbxPoint.setPoint(mbxPoint.getPoint() - point);
        
            pointRepo.save(mbxPoint);
        }
    
        MbxPointLog log = new MbxPointLog();
        log.setUserId(user.getId());
        log.setPoint(0 - point);
        log.setAfterPoint(mbxPoint.getPoint());
        log.setRemark(name);
    
        logRepo.save(log);
    }
    
    public MbxPoint info() {
        String username = UserUtil.getUsername();
        MbxUser user = userRepository.getByUsername(username);
    
        return pointRepo.getOne(user.getId());
    }
}
