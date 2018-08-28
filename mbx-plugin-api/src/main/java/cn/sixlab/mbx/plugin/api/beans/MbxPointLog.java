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

@Entity
public class MbxPointLog extends BaseEntity {
    private Integer userId;
    private Integer point;
    private Integer afterPoint;
    private String remark;
    
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
    
    public Integer getAfterPoint() {
        return afterPoint;
    }
    
    public void setAfterPoint(Integer afterPoint) {
        this.afterPoint = afterPoint;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
