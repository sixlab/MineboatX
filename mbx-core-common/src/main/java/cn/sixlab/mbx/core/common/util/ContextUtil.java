/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-04-28 16:24
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.core.common.util;

import org.springframework.context.ApplicationContext;

public class ContextUtil {
    private static ApplicationContext ctx;

    public static void setCtx(ApplicationContext ctx) {
        ContextUtil.ctx = ctx;
    }

    public static <T> T getBean(Class<T> clz){
        return ctx.getBean(clz);
    }
}
