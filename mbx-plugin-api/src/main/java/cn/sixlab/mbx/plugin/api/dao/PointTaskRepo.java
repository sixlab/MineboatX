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

import cn.sixlab.mbx.plugin.api.beans.MbxPointTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PointTaskRepo extends JpaRepository<MbxPointTask, Integer> {
    
    @Query(value =
            " select u from MbxPointTask u" +
                    " where u.userId = ?1" +
                    " and u.taskDate <= ?2" +
                    " and u.finish = false" +
                    " order by u.taskOrder ")
    List<MbxPointTask> selectToday(Integer id, LocalDate now);
}

