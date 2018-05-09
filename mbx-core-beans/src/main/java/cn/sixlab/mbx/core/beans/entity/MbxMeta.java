/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-05-08 17:26
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.core.beans.entity;


import cn.sixlab.mbx.core.beans.BaseEntity;

import javax.persistence.Entity;

@Entity
public class MbxMeta extends BaseEntity {
    private Integer referId;
    private String metaCode;
    private String metaVal;

    public Integer getReferId() {
        return referId;
    }

    public void setReferId(Integer referId) {
        this.referId = referId;
    }

    public String getMetaCode() {
        return metaCode;
    }

    public void setMetaCode(String metaCode) {
        this.metaCode = metaCode;
    }

    public String getMetaVal() {
        return metaVal;
    }

    public void setMetaVal(String metaVal) {
        this.metaVal = metaVal;
    }
}
