/**
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-04-20 18:43
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.core.common.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {
    public static ServletRequestAttributes getRequestAttributes() {
        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

            if (null != requestAttributes) {
                return (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HttpServletRequest getRequest() {
        try {
            ServletRequestAttributes requestAttributes = getRequestAttributes();

            if (null != requestAttributes) {
                return requestAttributes.getRequest();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HttpServletResponse getResponse() {
        try {
            ServletRequestAttributes requestAttributes = getRequestAttributes();

            if (null != requestAttributes) {
                return requestAttributes.getResponse();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
