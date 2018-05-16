/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-05-03 15:36
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.ms.beans;

import cn.sixlab.mbx.core.beans.BaseEntity;

import javax.persistence.Entity;

@Entity
public class MsTag extends BaseEntity {
    private Integer parentId;

    private String shortKey;
    private String tag;
    private String tagOrder;
    private String description;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getShortKey() {
        return shortKey;
    }

    public void setShortKey(String shortKey) {
        this.shortKey = shortKey;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTagOrder() {
        return tagOrder;
    }

    public void setTagOrder(String tagOrder) {
        this.tagOrder = tagOrder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
