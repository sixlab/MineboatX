/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/5/24 22:19
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.hexo.handler;

import cn.sixlab.mbx.core.common.base.BaseHandler;
import cn.sixlab.mbx.core.common.beans.ModelJson;
import cn.sixlab.mbx.core.common.util.FileUtil;
import cn.sixlab.mbx.core.common.util.LogUtil;
import cn.sixlab.mbx.plugin.hexo.util.HexoUtil;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/auth/images")
public class AuthImagesHandler extends BaseHandler {
    private Logger logger = LogUtil.getLogger(this);
    
    @GetMapping("/upload")
    public String upload(ModelMap map) {
        
        
        return "hexo/images/upload";
    }
    
    @ResponseBody
    @PostMapping("/upload")
    public ModelJson upload(@RequestParam("file") MultipartFile file, String filename, String path, HttpServletRequest request) {
        ModelJson json = new ModelJson();
    
        String contentType = file.getContentType();
        
        String filePath = HexoUtil.imgPath();
        
        if(StringUtils.hasLength(path)){
            filePath += File.separator + path;
        }
        
        if(StringUtils.isEmpty(filename)){
            filename = file.getOriginalFilename();
        }
        
        String msg;
        try {
            msg = FileUtil.uploadFile(file.getBytes(), filePath, filename);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            msg = e.getMessage();
        }
        //返回json
    
        Map map = new HashMap();
        map.put("contentType", contentType);
        map.put("filename", filename);
        map.put("filePath", filePath);
        map.put("msg", msg);
        
        json.setSuccess(StringUtils.isEmpty(msg));
    
        return json.setData(map);
    }
}
