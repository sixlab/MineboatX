/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-04-28 18:49
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.core.common.util;

import cn.sixlab.mbx.core.common.util.TokenUtil;
import org.springframework.util.StringUtils;

public class UserUtil {
    public static boolean isLogined() {
        return StringUtils.hasLength(TokenUtil.getUsername());
    }

    public static String getUsername(){
        return TokenUtil.getUsername();
    }
}
