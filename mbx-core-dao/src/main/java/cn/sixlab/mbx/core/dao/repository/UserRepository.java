/**
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-04-20 11:51
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.core.dao.repository;

import cn.sixlab.mbx.core.beans.entity.MbxUser;
import cn.sixlab.mbx.core.dao.BaseRepository;

public interface UserRepository extends BaseRepository<MbxUser, Integer> {
    MbxUser getByUsername(String usernmae);
}
