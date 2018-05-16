/*
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
package cn.sixlab.mbx.plugin.ms.dao;

import cn.sixlab.mbx.core.dao.BaseRepository;
import cn.sixlab.mbx.plugin.ms.beans.MsComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentRepository extends BaseRepository<MsComment, Integer> {

    Page<MsComment> findByArchiveId(Integer archiveId, Pageable pageable);

}
