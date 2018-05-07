/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-05-07 19:01
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.core.common.util;

import java.sql.Timestamp;
import java.time.Instant;

public class TimeUtil {

    public static Timestamp now() {
        return Timestamp.from(Instant.now());
    }
}
