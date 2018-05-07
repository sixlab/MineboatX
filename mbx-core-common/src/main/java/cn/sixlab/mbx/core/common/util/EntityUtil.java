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

public class EntityUtil {
    public static void setVal(BaseEntity entity) {
        entity.setDeleted('0');
        entity.setInsertTime(TimeUtil.now());
    }
}
