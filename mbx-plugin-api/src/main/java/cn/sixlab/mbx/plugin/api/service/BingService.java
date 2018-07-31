/**
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-07-31 13:36:42
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.service;

import cn.sixlab.mbx.plugin.api.beans.MbxBing;
import cn.sixlab.mbx.plugin.api.dao.BingRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BingService {
    private static Logger logger = LoggerFactory.getLogger(BingService.class);
    
    @Autowired
    private BingRepo bingRepo;

    public MbxBing today() {
        return bingRepo.selectLastOne();
    }
}
