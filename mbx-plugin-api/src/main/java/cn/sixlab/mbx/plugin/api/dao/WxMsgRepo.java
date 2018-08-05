/**
 * Copyright (c) 2017 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2017/11/2 21:50
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.dao;

import cn.sixlab.mbx.plugin.api.beans.MsxWxMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WxMsgRepo extends JpaRepository<MsxWxMsg, Integer> {
    MsxWxMsg findByMsgId(@Param("msgId") String msgId);
    
    List<MsxWxMsg> findByFromUserNameOrderById(@Param("fromUserName") String fromUserName);
}
