/**
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-04-20 18:05
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.security;

import cn.sixlab.mbx.core.beans.ModelJson;
import cn.sixlab.mbx.core.beans.entity.MbxUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 验证用户名密码正确后，生成一个token，并将token返回给客户端
 * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法
 * attemptAuthentication ：接收并解析用户凭证。
 * successfulAuthentication ：用户成功登录后，这个方法会被调用，我们在这个方法里生成token。
 * <p>
 * http://blog.csdn.net/sxdtzhaoxinguo/article/details/77965226
 */
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private JwtParam jwtParam;

    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public JwtLoginFilter setJwtParam(JwtParam jwtParam) {
        this.jwtParam = jwtParam;
        return this;
    }

    // 接收并解析用户凭证
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            MbxUser user = new ObjectMapper()
                    .readValue(req.getInputStream(), MbxUser.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 用户成功登录后，这个方法会被调用，我们在这个方法里生成token
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String username = ((User) auth.getPrincipal()).getUsername();
        long expiration = System.currentTimeMillis() + jwtParam.getJwtExpiration();

        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(expiration))
                .signWith(SignatureAlgorithm.HS512, jwtParam.getJwtSecret())
                .compact();

        token = jwtParam.getJwtTokenHead() + token;

        res.addHeader(jwtParam.getJwtHeader(), token);

        ModelJson json = new ModelJson();
        Map data = new HashMap();
        data.put("token", token);
        data.put("expiration", expiration);

        res.setCharacterEncoding("UTF-8");
        res.setContentType("application/json");

        PrintWriter writer = res.getWriter();
        writer.write(json.setData(data).toString());
        writer.flush();
        writer.close();
    }
}