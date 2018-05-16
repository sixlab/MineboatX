/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-05-05 17:57
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.ms.service;

import cn.sixlab.mbx.core.common.base.BaseService;
import cn.sixlab.mbx.core.common.util.EntityUtil;
import cn.sixlab.mbx.core.common.util.TimeUtil;
import cn.sixlab.mbx.core.dao.BaseRepository;
import cn.sixlab.mbx.plugin.ms.beans.MsComment;
import cn.sixlab.mbx.plugin.ms.dao.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;

@Service
public class CommentService extends BaseService<MsComment, Integer> {
    
    @Autowired
    private CommentRepository repository;
    
    @Override
    public BaseRepository<MsComment, Integer> getRepository() {
        return repository;
    }
    
    public Page<MsComment> list(Integer pageNo, Integer pageSize, Integer archiveId) {
        if (null == pageNo) {
            pageNo = 0;
        }
        if (null == pageSize) {
            pageSize = 10;
        }
        
        Specification<MsComment> specification = (Specification<MsComment>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate1 = criteriaBuilder.equal(root.get("archiveId"), archiveId);
            Predicate predicate2 = criteriaBuilder.equal(root.get("deleted"), '0');
            criteriaQuery.where(predicate1, predicate2);
            return criteriaQuery.getRestriction();
        };
        
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "commentTime");
        
        Page<MsComment> commentPage = repository.findAll(specification, pageable);
        
        return commentPage;
    }
    
    public void addComment(MsComment comment) {
        if (comment.getId() != null) {
            comment.setId(null);
        }
        
        if (StringUtils.isEmpty(comment.getNickname())) {
            comment.setNickname("匿名");
        }
        
        comment.setCommentTime(TimeUtil.now());
        
        EntityUtil.setVal(comment);
        repository.save(comment);
    }
    
    public void thumbComment(Integer commentId) {
        MsComment comment = repository.getOne(commentId);
        if (comment != null) {
            comment.setThumbCount(comment.getThumbCount() + 1);
            repository.save(comment);
        }
    }
}
