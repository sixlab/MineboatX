/**
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-04-28 11:56
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.security;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Test {
    public static void main(String[]args){
        System.out.println(BCrypt.checkpw("123456","$2a$10$uPj1dPTyDIqO3FY/NbSMveBGqadjt002RzB218MLTUBEb3qV3DZZS"));
    }
}
