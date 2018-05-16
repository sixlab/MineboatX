/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-05-03 15:13
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.ms.beans;

import cn.sixlab.mbx.core.beans.BaseEntity;

import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity
public class MsComment extends BaseEntity {
    private Integer archiveId;
    private Integer replyId;
    private Integer referId;

    private String nickname;
    private String email;
    private String content;
    private String website;
    private Timestamp commentTime;
    private String commentIp;

    private Integer commentFloor;
    private Integer thumbCount;

    public Integer getArchiveId() {
        return archiveId;
    }

    public void setArchiveId(Integer archiveId) {
        this.archiveId = archiveId;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Integer getReferId() {
        return referId;
    }

    public void setReferId(Integer referId) {
        this.referId = referId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentIp() {
        return commentIp;
    }

    public void setCommentIp(String commentIp) {
        this.commentIp = commentIp;
    }
    
    public Integer getCommentFloor() {
        return commentFloor;
    }
    
    public void setCommentFloor(Integer commentFloor) {
        this.commentFloor = commentFloor;
    }
    
    public Integer getThumbCount() {
        return thumbCount;
    }

    public void setThumbCount(Integer thumbCount) {
        this.thumbCount = thumbCount;
    }
}
