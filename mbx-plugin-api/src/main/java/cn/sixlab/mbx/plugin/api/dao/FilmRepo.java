/**
 * Copyright (c) 2017 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2017/10/26 11:04
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.dao;

import cn.sixlab.mbx.plugin.api.beans.MsxFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmRepo extends JpaRepository<MsxFilm, Integer>{
    
    @Query(
            " select u from MsxFilm u where " +
                    "    u.movieName   like concat('%',?1,'%') " +
                    " OR u.produceYear like concat('%',?1,'%') " +
                    " OR u.director    like concat('%',?1,'%') " +
                    " OR u.remark      like concat('%',?1,'%') " +
                    " OR u.doubanScore like concat('%',?1,'%') " +
                    " OR u.doubanKey   like concat('%',?1,'%') " +
                    " OR u.doubanInfo  like concat('%',?1,'%') " +
                    " OR u.cinema      like concat('%',?1,'%') "
    )
    List<MsxFilm> queryByKeyword(@Param("keyword") String keyword);
    
    @Query(
            " select u.cinema from MsxFilm u " +
                    "where " +
                    "    u.cinema is not null " +
                    "and u.cinema <> '' " +
                    "group by u.cinema " +
                    "order by u.cinema "
    )
    List<String> queryCinemas();
    
    List<MsxFilm> queryTop10ByDoubanKeyIsNullOrderByIdDesc();
}
