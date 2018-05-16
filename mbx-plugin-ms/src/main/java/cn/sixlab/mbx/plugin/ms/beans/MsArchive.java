/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-05-03 15:06
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.ms.beans;

import cn.sixlab.mbx.core.beans.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity
public class MsArchive extends BaseEntity {
    private String shortKey;

    private String title;
    
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Column(columnDefinition = "TEXT")
    private String mdContent;
    private String author;
    private Timestamp postTime;
    private Timestamp lastModifyTime;
    
    private String category;
    private Integer categoryId;

    private String postStatus;
    private String commentStatus;
    private String postPassword;
    private Integer postOrder;
    
    private Integer charCount;
    private Integer commentCount;
    private Integer viewCount;
    private Integer thumbCount;

    public String getShortKey() {
        return shortKey;
    }

    public void setShortKey(String shortKey) {
        this.shortKey = shortKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public String getMdContent() {
        return mdContent;
    }
    
    public void setMdContent(String mdContent) {
        this.mdContent = mdContent;
    }
    
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Timestamp getPostTime() {
        return postTime;
    }

    public void setPostTime(Timestamp postTime) {
        this.postTime = postTime;
    }

    public Timestamp getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Timestamp lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }

    public String getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    public String getPostPassword() {
        return postPassword;
    }

    public void setPostPassword(String postPassword) {
        this.postPassword = postPassword;
    }

    public Integer getPostOrder() {
        return postOrder;
    }

    public void setPostOrder(Integer postOrder) {
        this.postOrder = postOrder;
    }
    
    public Integer getCharCount() {
        return charCount;
    }
    
    public void setCharCount(Integer charCount) {
        this.charCount = charCount;
    }
    
    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getThumbCount() {
        return thumbCount;
    }

    public void setThumbCount(Integer thumbCount) {
        this.thumbCount = thumbCount;
    }
}
