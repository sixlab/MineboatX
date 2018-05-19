/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/5/19 21:33
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.core.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface BaseBean {
    default Logger getLogger(BaseBean bean){
        return LoggerFactory.getLogger(bean.getClass());
    }
}
