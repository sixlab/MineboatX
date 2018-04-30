/**
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-04-20 18:42
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.core.common.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class TokenUtil {

    public static String TOKEN_JWT_HEADER = "mbx.jwt.header";
    public static String TOKEN_JWT_SECRET = "mbx.jwt.secret";
    public static String TOKEN_JWT_EXPIRATION = "mbx.jwt.expiration";
    public static String TOKEN_JWT_BEARER = "mbx.jwt.bearer";

    public static String getHeader() {
        return PropertyUtil.getStrValue(TOKEN_JWT_HEADER);
    }

    public static String getSecret() {
        return PropertyUtil.getStrValue(TOKEN_JWT_SECRET);
    }

    public static int getExpiration() {
        return PropertyUtil.getIntValue(TOKEN_JWT_EXPIRATION);
    }

    public static String getBearer() {
        return PropertyUtil.getStrValue(TOKEN_JWT_BEARER);
    }

    public static String createToken(String username, String deviceType) throws UnsupportedEncodingException {

        long expiration = System.currentTimeMillis() + getExpiration();

        String subject = JsonUtil.toJson(new String[]{
                "username",
                "deviceType"
        }, new String[]{
                username,
                deviceType
        });

        String token = Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, getSecret())
                .compact();

        token = getBearer() + token;

        return token;
    }

    public static String getToken() {
        HttpServletRequest request = WebUtil.getRequest();
        String jwtHeader = getHeader();

        // 先读取 Header
        String token = request.getHeader(jwtHeader);

        // 如果没有，再读取 cookie
        if (StringUtils.isEmpty(token)) {
            token = WebUtil.getCookie(jwtHeader);
        }

        // 如果没有，再读取 request
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(jwtHeader);
        }

        return token;
    }

    public static String getValue() {
        return getValue(getToken());
    }

    public static String getValue(String token) {
        String value = Jwts.parser()
                .setSigningKey(getSecret())
                .parseClaimsJws(token.replace(getBearer(), ""))
                .getBody()
                .getSubject();

        return value;
    }

    public static String getUsername() {
        return getUsername(getToken());
    }

    public static String getUsername(String token) {
        String value = getValue(token);
        Map<String, String> map = JsonUtil.toBean(value, Map.class);
        return map.get("username");
    }
}
