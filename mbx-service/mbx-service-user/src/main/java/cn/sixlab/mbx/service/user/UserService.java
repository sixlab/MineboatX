/**
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-04-20 12:25
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.service.user;

import cn.sixlab.mbx.core.beans.entity.MbxUser;
import cn.sixlab.mbx.core.common.base.BaseService;
import cn.sixlab.mbx.core.common.exception.MbxException;
import cn.sixlab.mbx.core.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService extends BaseService {
    @Autowired
    private UserRepository repository;

    public MbxUser verifyLogin(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new MbxException("用户名或密码为空");
        }

        MbxUser user = repository.getByUsername(username);

        if(null==user){
            throw new MbxException("用户不存在");
        }

        if (BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }else{
            throw new MbxException("用户名或密码错误");
        }
    }

    public MbxUser getUser(Integer id) {
        MbxUser user = repository.getOne(id);
        return user;
    }

    public MbxUser getUser(String username) {
        MbxUser user = repository.getByUsername(username);
        return user;
    }
}
