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
import cn.sixlab.mbx.plugin.api.beans.MsxFilm;
import cn.sixlab.mbx.plugin.api.service.FilmService;
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
public class AuthFilmHandler extends BaseHandler{
    private static Logger logger = LoggerFactory.getLogger(AuthFilmHandler.class);
    
    @Autowired
    private FilmService service;
    
    /**
     * 获取电影院
     *
     * @return
     */
    @GetMapping(value = "/film/cinema")
    public ModelJson cinema() {
        logger.debug("获取电影列表");
        ModelJson json = new ModelJson();
        
        return json.setData(service.fetchCinema());
    }
    
    /**
     * 获取未拉取豆瓣列表
     *
     * @return
     */
    @GetMapping(value = "/film/db")
    public ModelJson db() {
        logger.debug("获取未拉取豆瓣列表");
        ModelJson json = new ModelJson();
        
        return json.setData(service.fetchDb());
    }
    
    /**
     * 添加一部新观看的电影
     *
     * @param film
     * @return
     */
    @PostMapping(value = "/film")
    public ModelJson add(@RequestBody MsxFilm film) {
        logger.debug("添加电影>>>");
        ModelJson json = new ModelJson();
    
        return json.setData(service.addFilm(film));
    }
    
    /**
     * 更新某一部电影
     *
     * @param id
     * @param film
     * @return
     */
    @PutMapping(value = "/film/{id}")
    public ModelJson update(@PathVariable Integer id, @RequestBody MsxFilm film) {
        logger.debug("更新电影>>>", id);
        
        ModelJson json = new ModelJson();
        
        film.setId(id);
        
        return json.setData(service.updateFilm(film));
    }
    
    /**
     * 获取最近电影
     *
     * @param num
     * @return
     */
    @GetMapping(value = "/film/recent/{num}")
    public ModelJson fetchRecentFilm(@PathVariable Integer num) {
        logger.debug("获取电影>>>", num);
        ModelJson json = new ModelJson();
    
        List<MsxFilm> filmList = service.fetchRecentFilm(num);
        json.setData(filmList);
        
        return json;
    }
    
    /**
     * 获取某一部电影
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/film/{id}")
    public ModelJson fetchFilm(@PathVariable Integer id) {
        logger.debug("获取电影>>>", id);
        ModelJson json = new ModelJson();
        
        MsxFilm film = service.fetchFilm(id);
        json.setData(film);
        
        return json;
    }
    
    /**
     * 搜索电影
     *
     * @param keyword
     * @return
     */
    @GetMapping(value = "/film")
    public ModelJson search(String keyword) {
        logger.debug("搜索电影>>>", keyword);
        ModelJson json = new ModelJson();
        
        keyword = StringUtils.trimWhitespace(keyword);
        List<MsxFilm> movieList = service.searchFilm(keyword);
    
        json.setData(movieList);
    
        return json;
    }
    
    ///**
    // * 观看了 id 电影，名字是 name
    // *
    // * @param id
    // * @param name
    // * @return
    // //*/
    //@PostMapping(value = "/film/{id}/view/{name}")
    //public ModelJson viewFilm(@PathVariable Integer id, @PathVariable String name) {
    //    logger.debug("重看电影>>>", id);
    //    logger.debug("重看电影>>>", name);
    //    ModelJson json = new ModelJson();
    //
    //    service.viewFilm(id, name);
    //
    //    return json;
    //}
}
