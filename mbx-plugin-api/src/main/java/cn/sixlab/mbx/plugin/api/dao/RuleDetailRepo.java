/**
 * Copyright (c) 2017 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2017/12/18 09:53
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.dao;

import cn.sixlab.mbx.plugin.api.beans.MsxAssignmentRuleDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RuleDetailRepo extends JpaRepository<MsxAssignmentRuleDetail, Integer> {
    
    //@Query(
    //        " select u from MsxAssignmentRuleDetail u where " +
    //                " u.ruleId in (?1) " +
    //                " order by u.id desc "
    //)
    //List<MsxAssignmentRuleDetail> queryActiveRuleDetail(List<Integer> ruleIds);
    
    
    List<MsxAssignmentRuleDetail> findByRuleId(Integer ruleId);
    
    List<MsxAssignmentRuleDetail> findByRuleIdInOrderByRuleHour(List<Integer> ruleIdList);
    
}
