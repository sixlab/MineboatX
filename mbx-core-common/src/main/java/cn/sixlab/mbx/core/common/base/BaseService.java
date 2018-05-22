/*
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
package cn.sixlab.mbx.core.common.base;

import cn.sixlab.mbx.core.dao.BaseRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

public abstract class BaseService<E, ID extends Serializable> {
    public BaseRepository<E, ID> getRepository() {
        return null;
    }
    
    @Transactional
    public E save(E entity) {
        return getRepository().saveAndFlush(entity);
    }
    
    @Transactional
    public void delete(ID id) {
        getRepository().deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public E get(ID id) {
        return getRepository().getOne(id);
    }
    
}
