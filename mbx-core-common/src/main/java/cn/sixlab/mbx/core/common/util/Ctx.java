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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Ctx {
    private static ApplicationContext ctx;
    
    @Autowired
    public void setCtx(ApplicationContext ctx) {
        Ctx.ctx = ctx;
        System.out.println(ctx);
    }
    
    public static <T> T get(Class<T> clz) {
        return ctx.getBean(clz);
    }
    
    public static <T> T getBean(Class<T> clz) {
        return ctx.getBean(clz);
    }
}
