package cn.sixlab.mbx.plugin.api.handler;

import cn.sixlab.mbx.core.common.beans.ModelJson;
import cn.sixlab.mbx.core.common.util.LogUtil;
import cn.sixlab.mbx.plugin.api.mapper.DynamicMapper;
import cn.sixlab.mbx.plugin.api.mapper.MapperVo;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dyna")
public class DynamicHandler {
    private Logger logger = LogUtil.getLogger(this);
    
    @Autowired
    private DynamicMapper mapper;
    
    private void verify(MapperVo vo) {
        String verify = vo.getVerify();
        
        if (!verify.equalsIgnoreCase("qwe123")) {
            throw new RuntimeException("校验错误");
        }
    }
    
    @PostMapping(value = "/list/{startIndex}/{pageSize}")
    public ModelJson list(
            @PathVariable Integer startIndex,
            @PathVariable Integer pageSize,
            @RequestBody MapperVo vo
    ) {
        verify(vo);
        
        ModelJson json = new ModelJson();
        
        int count = mapper.count(vo.getSql());
        json.put("count", count);
        
        if (count > 0) {
            List<Map> mapList = mapper.list(vo.getSql(), startIndex, pageSize);
            
            if (!CollectionUtils.isEmpty(mapList)) {
                json.setData(mapList);
            } else {
                json.setSuccess(false);
            }
        } else {
            json.setSuccess(false);
        }
        
        return json;
    }
    
    @PostMapping(value = "/item")
    public ModelJson item(@RequestBody MapperVo vo) {
        verify(vo);
        
        ModelJson json = new ModelJson();
        
        Map map = mapper.item(vo.getSql());
        
        if (!CollectionUtils.isEmpty(map)) {
            json.setData(map);
        } else {
            json.setSuccess(false);
        }
        
        return json;
    }
    
    @PostMapping(value = "/count")
    public ModelJson count(@RequestBody MapperVo vo) {
        verify(vo);
        
        ModelJson json = new ModelJson();
        
        int count = mapper.count(vo.getSql());
        
        json.setData(count);
        
        return json;
    }
    
    @PostMapping(value = "/insert")
    public ModelJson insert(@RequestBody MapperVo vo) {
        verify(vo);
        
        ModelJson json = new ModelJson();
        
        int count = mapper.insert(vo.getSql());
        
        json.setData(count);
        
        return json;
    }
    
    @PostMapping(value = "/delete")
    public ModelJson delete(@RequestBody MapperVo vo) {
        verify(vo);
        
        ModelJson json = new ModelJson();
        
        int count = mapper.delete(vo.getSql());
        
        json.setData(count);
        
        return json;
    }
    
    @PostMapping(value = "/update")
    public ModelJson update(@RequestBody MapperVo vo) {
        verify(vo);
        
        ModelJson json = new ModelJson();
        
        int count = mapper.update(vo.getSql());
        
        json.setData(count);
        
        return json;
    }
}
