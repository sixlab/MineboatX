/**
 * Copyright (c) 2017 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2017/10/26 10:59
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.beans;

import cn.sixlab.mbx.core.beans.BaseEntity;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class MsxShow extends BaseEntity{
    private String showName;
    private Integer showSeason;
    private Integer showEpisode;
    private String viewStatus;
    private String tv;
    private String remark;
    private String doubanKey;
    private LocalDate beginDate;
    private LocalDate updateDate;
    
    public String getShowName() {
        return showName;
    }
    
    public void setShowName(String showName) {
        this.showName = showName;
    }
    
    public Integer getShowSeason() {
        return showSeason;
    }
    
    public void setShowSeason(Integer showSeason) {
        this.showSeason = showSeason;
    }
    
    public Integer getShowEpisode() {
        return showEpisode;
    }
    
    public void setShowEpisode(Integer showEpisode) {
        this.showEpisode = showEpisode;
    }
    
    public String getViewStatus() {
        return viewStatus;
    }
    
    public void setViewStatus(String viewStatus) {
        this.viewStatus = viewStatus;
    }
    
    public String getTv() {
        return tv;
    }
    
    public void setTv(String tv) {
        this.tv = tv;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public String getDoubanKey() {
        return doubanKey;
    }
    
    public void setDoubanKey(String doubanKey) {
        this.doubanKey = doubanKey;
    }
    
    public LocalDate getBeginDate() {
        return beginDate;
    }
    
    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }
    
    public LocalDate getUpdateDate() {
        return updateDate;
    }
    
    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }
}
