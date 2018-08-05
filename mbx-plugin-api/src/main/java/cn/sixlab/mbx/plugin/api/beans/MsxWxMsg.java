/**
 * Copyright (c) 2017 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2017/11/2 21:49
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.beans;

import cn.sixlab.mbx.core.beans.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class MsxWxMsg extends BaseEntity {
    private String msgType;
    
    private String toUserName;
    private String fromUserName;
    private String createTime;
    
    private String msgId;
    
    // location - Scale
    // link     - Title
    // event    - Event
    private String title;
    
    // voice    - Recognition
    // location - Label
    // link     - Description
    // text     - Content
    // event    - EventKey
    private String content;
    
    // image      - MediaId
    // voice      - MediaId
    // video      - MediaId
    // shortvideo - MediaId
    // event      - Ticket
    private String mediaId;
    
    // video      - ThumbMediaId
    // shortvideo - ThumbMediaId
    private String thumbMediaId;
    
    // image      - PicUrl
    // shortvideo - ThumbMediaId
    private String url;
    
    // voice      - Format
    private String format;
    
    // location   - Location_X
    // event      - Latitude
    private String locationX;
    
    // location   - Location_Y
    // event      - Longitude
    private String locationY;
    
    // location   - Scale
    // event      - Precision
    private String scale;
    
    public String getMsgType() {
        return msgType;
    }
    
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
    
    public String getToUserName() {
        return toUserName;
    }
    
    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }
    
    public String getFromUserName() {
        return fromUserName;
    }
    
    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }
    
    public String getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    
    public String getMsgId() {
        return msgId;
    }
    
    public void setMsgId(String msgId) {
        this.msgId = msgId;
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
    
    public String getMediaId() {
        return mediaId;
    }
    
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
    
    public String getThumbMediaId() {
        return thumbMediaId;
    }
    
    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getFormat() {
        return format;
    }
    
    public void setFormat(String format) {
        this.format = format;
    }
    
    public String getLocationX() {
        return locationX;
    }
    
    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }
    
    public String getLocationY() {
        return locationY;
    }
    
    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }
    
    public String getScale() {
        return scale;
    }
    
    public void setScale(String scale) {
        this.scale = scale;
    }
    
}
