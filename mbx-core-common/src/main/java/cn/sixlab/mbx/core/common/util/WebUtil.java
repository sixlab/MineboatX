/*
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

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
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

    private static String[] devices = new String[]{
            "nokia", "sony", "ericsson", "mot", "samsung", "htc", "sgh", "lg", "sharp", "sie-"
            , "philips", "panasonic", "alcatel", "lenovo", "iphone", "ipod", "blackberry", "meizu",
            "android", "netfront", "symbian", "ucweb", "windowsce", "palm", "operamini",
            "operamobi", "opera mobi", "openwave", "nexusone", "cldc", "midp", "wap", "mobile"
    };

    public static String getDeviceType() {
        HttpServletRequest req = getRequest();
        // app
        String device = req.getHeader("deviceType");

        if (StringUtils.isEmpty(device)) {
            String ua = WebUtil.getUA();
            for (String s : devices) {
                if (ua.contains(s)) {
                    return "h5";
                }
            }
            return "pc";
        }

        return device.toLowerCase();
    }

    public static String getUA() {
        HttpServletRequest req = getRequest();

        return req.getHeader("User-Agent").toLowerCase();
    }

    public static void addCookie(String key, String val) {
        Cookie cookie = new Cookie(key.trim(), val.trim());
        cookie.setMaxAge(30 * 60); // 30min
        cookie.setPath("/");
        getResponse().addCookie(cookie);
    }

    public static void addCookie(String key, String val, int expiry ) {
        Cookie cookie = new Cookie(key.trim(), val.trim());
        cookie.setMaxAge(expiry/1000);
        cookie.setPath("/");
        getResponse().addCookie(cookie);
    }

    public static Cookie[] getCookies(){
        return getRequest().getCookies();
    }

    public static String getCookie(String key) {
        Cookie[] cookies = getCookies();
        if (null != cookies && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (key.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return "";
    }
}
