/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/8/29 00:22
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.beans;

import cn.sixlab.mbx.core.beans.BaseEntity;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class MbxPointTask extends BaseEntity {
    private Integer userId;
    private Integer point;
    private String title;
    private String content;
    private LocalDate taskDate;
    private String repeatType;
    private Integer repeatInterval;
    private Integer taskOrder;
    private Boolean finish;
    private LocalDate finishDate;
    
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public Integer getPoint() {
        return point;
    }
    
    public void setPoint(Integer point) {
        this.point = point;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public LocalDate getTaskDate() {
        return taskDate;
    }
    
    public void setTaskDate(LocalDate taskDate) {
        this.taskDate = taskDate;
    }
    
    public String getRepeatType() {
        return repeatType;
    }
    
    public void setRepeatType(String repeatType) {
        this.repeatType = repeatType;
    }
    
    public Integer getRepeatInterval() {
        return repeatInterval;
    }
    
    public void setRepeatInterval(Integer repeatInterval) {
        this.repeatInterval = repeatInterval;
    }
    
    public Integer getTaskOrder() {
        return taskOrder;
    }
    
    public void setTaskOrder(Integer taskOrder) {
        this.taskOrder = taskOrder;
    }
    
    public Boolean getFinish() {
        return finish;
    }
    
    public void setFinish(Boolean finish) {
        this.finish = finish;
    }
    
    public LocalDate getFinishDate() {
        return finishDate;
    }
    
    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }
}
