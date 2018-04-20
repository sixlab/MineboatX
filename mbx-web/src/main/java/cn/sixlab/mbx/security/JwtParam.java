/**
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-04-20 18:34
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtParam {

    @Value("${msb.jwt.header}")
    private String jwtHeader;

    @Value("${msb.jwt.secret}")
    private String jwtSecret;

    @Value("${msb.jwt.expiration}")
    private int jwtExpiration;

    @Value("${msb.jwt.tokenHead}")
    private String jwtTokenHead;

    public String getJwtHeader() {
        return jwtHeader;
    }

    public void setJwtHeader(String jwtHeader) {
        this.jwtHeader = jwtHeader;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }

    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public int getJwtExpiration() {
        return jwtExpiration;
    }

    public void setJwtExpiration(int jwtExpiration) {
        this.jwtExpiration = jwtExpiration;
    }

    public String getJwtTokenHead() {
        return jwtTokenHead;
    }

    public void setJwtTokenHead(String jwtTokenHead) {
        this.jwtTokenHead = jwtTokenHead;
    }
}