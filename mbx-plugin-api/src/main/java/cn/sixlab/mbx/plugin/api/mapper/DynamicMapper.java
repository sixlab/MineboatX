package cn.sixlab.mbx.plugin.api.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DynamicMapper {
    
    Map item(@Param("sql") String sql);
    
    List<Map> list(@Param("sql") String sql, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);
    
    int count(@Param("sql") String sql);
    
    int insert(@Param("sql") String sql);
    
    int delete(@Param("sql") String sql);
    
    int update(@Param("sql") String sql);
}
