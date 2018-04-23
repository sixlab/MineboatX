/**
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-04-20 18:07
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.service.user;

import cn.sixlab.mbx.core.beans.entity.MbxUser;
import cn.sixlab.mbx.core.beans.entity.MbxUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JWTUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService service;

    @Override
    public UserDetails loadUserByUsername(String username) {

        MbxUser user = service.getUser(username);

        if (null == user) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            List<MbxUserRole> userRoleList = service.getUserRole(user.getId());

            List<GrantedAuthority> authorityList;
            if (CollectionUtils.isEmpty(userRoleList)) {
                authorityList = new ArrayList<>();
                authorityList.add(new SimpleGrantedAuthority("USER"));
            } else {
                authorityList = userRoleList.stream()
                        .map(msxUserRole -> new SimpleGrantedAuthority(msxUserRole.getRoleCode()))
                        .collect(Collectors.toList());
            }

            return new User(
                    username, user.getPassword(),
                    true,//账户可用为true
                    true,//账户未过期为true
                    true,//证书不过期为true
                    true,//账户未锁定为true
                    authorityList);
        }
    }
}