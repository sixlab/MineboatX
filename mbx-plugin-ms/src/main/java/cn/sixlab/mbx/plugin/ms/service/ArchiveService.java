/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-04-30 17:06
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.ms.service;

import cn.sixlab.mbx.core.common.base.BaseService;
import cn.sixlab.mbx.core.dao.BaseRepository;
import cn.sixlab.mbx.plugin.ms.beans.MsArchive;
import cn.sixlab.mbx.plugin.ms.dao.ArchiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ArchiveService extends BaseService<MsArchive, Integer> {

    @Autowired
    private ArchiveRepository repository;

    @Override
    public BaseRepository getRepository() {
        return repository;
    }

    public MsArchive getArchive(Integer archiveId) {
        return repository.getOne(archiveId);
    }
    
    public Page<MsArchive> queryRecent(Integer pageNo, Integer pageSize) {
        if (null == pageNo) {
            pageNo = 0;
        } else if (pageNo > 0) {
            pageNo--;
        }
        if (null == pageSize) {
            pageSize = 10;
        }
    
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "postOrder", "postTime");
    
        Page<MsArchive> archivePage = repository.findAll(pageable);
    
        return archivePage;
    }
}
