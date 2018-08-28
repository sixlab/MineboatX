/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/8/29 00:27
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.dao;

import cn.sixlab.mbx.plugin.api.beans.MbxPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepo extends JpaRepository<MbxPoint, Integer> {
    
    MbxPoint findByUserId(Integer userId);
}

