/**
 * Copyright (c) 2017 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2017/12/18 14:50
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.dao;

import cn.sixlab.mbx.plugin.api.beans.MsxAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface AssignmentRepo extends JpaRepository<MsxAssignment, Integer> {
    List<MsxAssignment> findByAssignmentDateOrderByAssignmentHourAscId(Date date);
    int countAllByAssignmentDate(Date date);
}
