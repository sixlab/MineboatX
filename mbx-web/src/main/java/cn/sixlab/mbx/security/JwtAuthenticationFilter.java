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

import cn.sixlab.mbx.core.common.util.TokenUtil;
import io.jsonwebtoken.Jwts;
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

    private JwtParam jwtParam;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public JwtAuthenticationFilter setJwtParam(JwtParam jwtParam) {
        this.jwtParam = jwtParam;
        return this;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
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

        //验证
        String token = TokenUtil.readToken(request, jwtParam.getJwtHeader(), jwtParam.getJwtSecret());

        //if(WebUtil.checkToken(token, jwtParam.getJwtTokenHead(), jwtParam.getJwtSecret(), request.getRequestURI())){
        //    chain.doFilter(request, response);
        //    logger.info("登录失败，token：" + token);
        //    return;
        //}

        if (StringUtils.isEmpty(token)) {
            chain.doFilter(request, response);
            return;
        }

        if (token.startsWith(jwtParam.getJwtTokenHead())) {
            try {
                UsernamePasswordAuthenticationToken authentication = getAuthentication(token);

                SecurityContextHolder.getContext().setAuthentication(authentication);
                chain.doFilter(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                chain.doFilter(request, response);
            }
        }

//        String fakeMD5 = DigestUtil.fakeMD5(request.getRequestURI(), jwtParam.getJwtSecret());
        String fakeMD5 = request.getRequestURI() + jwtParam.getJwtSecret();
        if (fakeMD5.equals(token)) {
            String username = request.getParameter("username");
            if (StringUtils.hasLength(username)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                chain.doFilter(request, response);
            }
        }

        return;
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        // parse the token.
        String user = Jwts.parser()
                .setSigningKey(jwtParam.getJwtSecret())
                .parseClaimsJws(token.replace(jwtParam.getJwtTokenHead(), ""))
                .getBody()
                .getSubject();

        if (user != null) {
            return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        }
        return null;
    }
}