/**
 * Copyright (c) 2017 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2017/10/26 11:11
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.handler;

import cn.sixlab.mbx.core.common.base.BaseHandler;
import cn.sixlab.mbx.core.common.beans.ModelJson;
import cn.sixlab.mbx.plugin.api.beans.MsxShow;
import cn.sixlab.mbx.plugin.api.service.ShowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth/movie")
public class AuthShowHandler extends BaseHandler{
    private static Logger logger = LoggerFactory.getLogger(AuthShowHandler.class);
    
    @Autowired
    private ShowService service;
    
    /**
     * @param keyword
     * @param showStatus
     * @return
     */
    @GetMapping(value = "/show")
    public ModelJson search(@RequestParam String keyword, @RequestParam String showStatus) {
        logger.debug("搜索电视剧>>>"+keyword);
        ModelJson json = new ModelJson();
    
        keyword = StringUtils.trimWhitespace(keyword);
        List<MsxShow> showList = service.search(keyword, showStatus);
        
        json.setData(showList);
    
        return json;
    }
    
    /**
     * 添加电视剧
     *
     * @param show
     * @return
     */
    @PostMapping(value = "/show")
    public ModelJson add(@RequestBody MsxShow show) {
        logger.debug("添加剧集>>>");
        ModelJson json = new ModelJson();
    
        show = service.addShow(show);
        json.setData(show);
    
        return json;
    }
    
    /**
     * 获取剧集
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/show/{id}")
    public ModelJson fetchShow(@PathVariable Integer id) {
        logger.debug("获取剧集>>>", id);
        ModelJson json = new ModelJson();
        
        MsxShow show = service.fetchShow(id);
        json.setData(show);
        
        return json;
    }
    
    /**
     * 更新进度
     *
     * @param id
     * @param season
     * @return
     */
    @PutMapping(value = "/show/{id}/season/{season}/episode/{episode}")
    public ModelJson updateSeasonEpisode(@PathVariable Integer id, @PathVariable Integer season,
            @PathVariable Integer episode) {
        logger.debug("更新进度 s>>>", id);
        logger.debug("更新进度 s>>>", season);
        logger.debug("更新进度 s>>>", episode);
        ModelJson json = new ModelJson();
    
        MsxShow show = service.updateSeasonEpisode(id, season, episode);
        json.setData(show);
        
        return json;
    }
    
    /**
     * 更新进度至season季第1集
     *
     * @param id
     * @param season
     * @return
     */
    @PutMapping(value = "/show/{id}/season/{season}")
    public ModelJson updateSeason(@PathVariable Integer id, @PathVariable Integer season) {
        logger.debug("更新进度 s>>>", id);
        logger.debug("更新进度 s>>>", season);
        ModelJson json = new ModelJson();
    
        MsxShow show = service.updateSeason(id, season);
        json.setData(show);
        
        return json;
    }
    
    /**
     * 更新进度至当前季episode集
     *
     * @param id
     * @param episode
     * @return
     */
    @PutMapping(value = "/show/{id}/episode/{episode}")
    public ModelJson updateEpisode(@PathVariable Integer id, @PathVariable Integer episode) {
        logger.debug("更新进度 e>>>", id);
        logger.debug("更新进度 e>>>", episode);
        ModelJson json = new ModelJson();
    
        MsxShow show = service.updateEpisode(id, episode);
        json.setData(show);
        
        return json;
    }
    
    /**
     * 修改观看状态
     *
     * @param id     电视剧的 id
     * @param status 观看的状态
     *               20 正在看
     *               30 未在看
     * @return 处理结果
     * @since 0.1.0
     */
    @PutMapping(value = "/show/{id}/viewStatus/{status}")
    public ModelJson updateEpisode(@PathVariable Integer id, @PathVariable String status) {
        logger.debug("修改观看状态>>>", id);
        logger.debug("修改观看状态>>>", status);
        ModelJson json = new ModelJson();
    
        MsxShow show = service.updateViewStatus(id, status);
        json.setData(show);
        
        return json;
    }
}
