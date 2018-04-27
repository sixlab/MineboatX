/**
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-04-20 12:23
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.handle;

import cn.sixlab.mbx.core.beans.ModelJson;
import cn.sixlab.mbx.core.beans.entity.MbxUser;
import cn.sixlab.mbx.core.common.base.BaseHandler;
import cn.sixlab.mbx.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserHandle extends BaseHandler {
    @Autowired
    private UserService service;

    @ResponseBody
    @RequestMapping("/login")
    public ModelJson index(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        MbxUser user = service.verifyLogin(username, password);
        if(null!=user){
            login(user, response);
        }

        return new ModelJson().setSuccess(true).setMessage("成功");
    }

    private void login(MbxUser user, HttpServletResponse response) {
        Cookie cookie = new Cookie("token", user.toString());
        cookie.setMaxAge(30*60*1000);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
