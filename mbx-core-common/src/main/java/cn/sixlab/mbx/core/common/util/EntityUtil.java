/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-05-07 19:02
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.core.common.util;

import cn.sixlab.mbx.core.beans.BaseEntity;

import java.time.LocalDateTime;

public class EntityUtil {
    public static void setVal(BaseEntity entity) {
        if (null == entity.getDeleted()) {
            entity.setDeleted('0');
        }
        if (null == entity.getInsertTime()) {
            entity.setInsertTime(LocalDateTime.now());
        }
    }
}
