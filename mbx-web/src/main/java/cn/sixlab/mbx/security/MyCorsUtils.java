/**
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-04-20 18:06
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.security;

import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;

public class MyCorsUtils {

    public static boolean isCorsRequest(HttpServletRequest request) {
        String origin = request.getHeader("Origin");

        if (null != origin) {
            origin = origin.toLowerCase();
            return origin.endsWith(".sixlab.cn") || origin.endsWith("://sixlab.cn");
        }

        return false;
    }

    public static boolean isPreFlightRequest(HttpServletRequest request) {
        return isCorsRequest(request)
                && HttpMethod.OPTIONS.matches(request.getMethod())
                && request.getHeader("Access-Control-Request-Method") != null;
    }
}
