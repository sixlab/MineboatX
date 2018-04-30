/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-04-23 14:21
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.core.dao.repository;

import cn.sixlab.mbx.core.beans.entity.MbxUserRole;
import cn.sixlab.mbx.core.dao.BaseRepository;

import java.util.List;

public interface UserRoleRepository extends BaseRepository<MbxUserRole, Integer> {
    List<MbxUserRole> findAllByUserId(Integer userId);
}
