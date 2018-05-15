/**
 * Copyright (c) 2017 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2017/12/18 09:52
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.dao;

import cn.sixlab.mbx.plugin.api.beans.MsxAssignmentRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface RuleRepo extends JpaRepository<MsxAssignmentRule, Integer> {
    
    @Query(
            " select u from MsxAssignmentRule u where " +
                    " ?1 between u.beginDate and u.endDate " +
                    " or (?1 >= u.beginDate and u.endDate is null )" +
                    " or (u.beginDate is null and ?1 <= u.endDate )" +
                    " or (u.beginDate is null and u.endDate is null )"
    )
    List<MsxAssignmentRule> queryActiveRule(Date date);
    
}
