/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-05-08 17:27
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.core.dao.repository;

import cn.sixlab.mbx.core.beans.entity.MbxMeta;
import cn.sixlab.mbx.core.dao.BaseRepository;

import java.util.List;

public interface MetaRepository extends BaseRepository<MbxMeta, Integer> {

    MbxMeta getByMetaCode(String metaCode);

    List<MbxMeta> findByMetaCode(String metaCode);
}
