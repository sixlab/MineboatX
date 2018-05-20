/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/5/21 00:56
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.core.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
    
    public static Logger getLogger(Object object) {
        return LoggerFactory.getLogger(object.getClass());
    }
    
}
