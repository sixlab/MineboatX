/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-05-03 15:34
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.ms.beans;

import cn.sixlab.mbx.core.beans.BaseEntity;

import javax.persistence.Entity;

@Entity
public class MsCategory extends BaseEntity {
    private Integer parentId;

    private String shortKey;
    private String category;
    private String categoryOrder;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryOrder() {
        return categoryOrder;
    }

    public void setCategoryOrder(String categoryOrder) {
        this.categoryOrder = categoryOrder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
