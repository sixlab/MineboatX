/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 *
 * License information see the LICENSE file in the project's root directory.
 *
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-04-20 18:05
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.filter;

import cn.sixlab.mbx.core.common.beans.ModelJson;
import cn.sixlab.mbx.core.beans.entity.MbxUser;
import cn.sixlab.mbx.core.common.util.TokenUtil;
import cn.sixlab.mbx.core.common.util.WebUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

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

    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    // 接收并解析用户凭证
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        MbxUser user = new MbxUser();
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));

        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword(),
                        new ArrayList<>())
        );
    }

    /**
     * 用户成功登录后，这个方法会被调用，我们在这个方法里生成token
      */
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse resp,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {

        String username = ((User) auth.getPrincipal()).getUsername();
        String deviceType = WebUtil.getDeviceType();
        String token = TokenUtil.createToken(username, deviceType);

        if ("app".equals(deviceType)){
            resp.addHeader(TokenUtil.getHeader(), token);
        }else{
            WebUtil.addCookie(TokenUtil.getHeader(), token, TokenUtil.getExpiration());
        }

        ModelJson json = new ModelJson();
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("expiration", TokenUtil.getExpiration());

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        PrintWriter writer = resp.getWriter();
        writer.write(json.setData(data).toString());
        writer.flush();
        writer.close();
    }
}
