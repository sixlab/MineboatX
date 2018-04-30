/*
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
package cn.sixlab.mbx.core.handle;

import cn.sixlab.mbx.core.common.beans.ModelJson;
import cn.sixlab.mbx.core.beans.entity.MbxUser;
import cn.sixlab.mbx.core.common.base.BaseHandler;
import cn.sixlab.mbx.core.common.util.TokenUtil;
import cn.sixlab.mbx.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserHandle extends BaseHandler {
    @Autowired
    private UserService service;

    @ResponseBody
    @RequestMapping("/auth/getInfo")
    public ModelJson getInfo() {
        MbxUser mbxUser = service.getUser(TokenUtil.getUsername());

        return new ModelJson().setSuccess(true).setMessage("成功").setData(mbxUser);
    }

}
