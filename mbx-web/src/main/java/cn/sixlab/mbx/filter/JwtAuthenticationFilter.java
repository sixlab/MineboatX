/*
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
package cn.sixlab.mbx.filter;

import cn.sixlab.mbx.core.common.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * token的校验
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 * <p>
 * http://blog.csdn.net/sxdtzhaoxinguo/article/details/77965226
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    private static Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        logParam(request);

        //验证
        String token = TokenUtil.getToken();

        if (StringUtils.isEmpty(token)) {
            chain.doFilter(request, response);
            // 403
            return;
        }

        //使用jwt
        if (token.startsWith(TokenUtil.getBearer())) {
            try {
                UsernamePasswordAuthenticationToken authentication = getAuthentication(token);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        chain.doFilter(request, response);// 没有的话else里边会返回 空白的200
    }

    private void logParam(HttpServletRequest request) {
        //输出参数
        String result = "\n>>>>>\n";
        //try {
        String method = request.getMethod();
        result += method;

        result += " | ";

        String inComeUrI = request.getRequestURI();
        result += inComeUrI;
        //    result += " | ";
        //    //
        //    //Enumeration<String> params = request.getParameterNames();
        //    //String emptyStr = "";
        //    //String paraStr = "";
        //    //
        //    //while (params.hasMoreElements()) {
        //    //    String name = params.nextElement();
        //    //    String[] values = request.getParameterValues(name);
        //    //    for (String value : values) {
        //    //        if (StringUtils.isEmpty(value)) {
        //    //            emptyStr += "\n\t『" + name + "』";
        //    //        } else {
        //    //            if (value.length() > 50) {
        //    //                paraStr += "\n\t『" + name + "』=『length>50，不输出』";
        //    //            } else {
        //    //
        //    //                paraStr += "\n\t『" + name + "』=『" + value + "』";
        //    //            }
        //    //        }
        //    //    }
        //    //}
        //    //result += ("".equals(paraStr) ? "" : ("\n\n参数值：" + paraStr));
        //    //result += ("".equals(emptyStr) ? "" : ("\n\nEmpty值为：" + emptyStr));
        ////} catch (Exception e) {
        ////    result += "输出URL参数错误";
        ////}
        result += "\n<<<<<\n";

        logger.info(result);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        String username = TokenUtil.getUsername(token);

        if (username != null) {
            return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
        }
        return null;
    }
}