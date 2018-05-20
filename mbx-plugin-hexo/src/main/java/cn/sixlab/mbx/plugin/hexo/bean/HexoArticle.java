/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/5/20 21:53
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.hexo.bean;

public class HexoArticle {
    private String fileId;
    private String archiveId;
    private String title;
    private String content;
    private String subject;
    private String[] tags;
    private String[] categories;
    private String date;
    
    public String getFileId() {
        return fileId;
    }
    
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
    
    public String getArchiveId() {
        return archiveId;
    }
    
    public void setArchiveId(String archiveId) {
        this.archiveId = archiveId;
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
    
    public String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String[] getTags() {
        return tags;
    }
    
    public void setTags(String[] tags) {
        this.tags = tags;
    }
    
    public String[] getCategories() {
        return categories;
    }
    
    public void setCategories(String[] categories) {
        this.categories = categories;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
}
