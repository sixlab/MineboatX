/**
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-04-20 11:48
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.core.beans.vo;

import cn.sixlab.mbx.core.beans.entity.MbxUserRole;

import java.util.List;

public class UserInfoVo {

    private Integer userId;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String status;

    private List<MbxUserRole> roleList;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MbxUserRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<MbxUserRole> roleList) {
        this.roleList = roleList;
    }
}
