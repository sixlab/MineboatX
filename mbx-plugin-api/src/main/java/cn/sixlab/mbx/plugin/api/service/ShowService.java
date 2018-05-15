/**
 * Copyright (c) 2017 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2017/10/26 11:15
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.service;

import cn.sixlab.mbx.plugin.api.CONST;
import cn.sixlab.mbx.plugin.api.beans.MsxShow;
import cn.sixlab.mbx.plugin.api.dao.ShowRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class ShowService {
    private static Logger logger = LoggerFactory.getLogger(ShowService.class);

    @Autowired
    private ShowRepo showRepo;

    public List<MsxShow> search(String keyword, String status) {
        List<MsxShow> showList;

        if (StringUtils.isEmpty(keyword) && StringUtils.isEmpty(status)) {
            showList = showRepo.findAll();
        } else if(StringUtils.isEmpty(keyword)){
            showList = showRepo.findByViewStatus(status);
        }else if(StringUtils.isEmpty(status)){
            showList = showRepo.findByKeyword(keyword);
        }else{
            showList = showRepo.findByStatus(status, keyword);
        }

        return showList;
    }

    public MsxShow addShow(MsxShow show) {
        show.setViewStatus(CONST.SHOW_V_STATUS_ING);
        show.setBeginDate(new Date());
        showRepo.save(show);

        //hisService.beginShow(show);
        return show;
    }

    public MsxShow updateSeason(Integer id, Integer season) {
        MsxShow show = showRepo.getOne(id);
        show.setShowEpisode(1);
        show.setShowSeason(season);
        show.setUpdateDate(new Date());
        showRepo.save(show);

        //hisService.addSeason(show);
        return showRepo.getOne(id);
    }

    public MsxShow updateEpisode(Integer id, Integer episode) {
        MsxShow show = showRepo.getOne(id);
        show.setShowEpisode(episode);
        show.setUpdateDate(new Date());
        showRepo.save(show);

        //hisService.addEpisode(toolsShow);
        return showRepo.getOne(id);
    }

    public MsxShow updateViewStatus(Integer id, String viewStatus) {
        MsxShow show = showRepo.getOne(id);
        show.setViewStatus(viewStatus);
        show.setUpdateDate(new Date());
        showRepo.save(show);
        
        return showRepo.getOne(id);
    }
    
    public MsxShow updateSeasonEpisode(Integer id, Integer season, Integer episode) {
        MsxShow show = showRepo.getOne(id);
        show.setShowSeason(season);
        show.setShowEpisode(episode);
        show.setUpdateDate(new Date());
        showRepo.save(show);
        
        return showRepo.getOne(id);
    }

    public MsxShow fetchShow(Integer id) {
        return showRepo.getOne(id);
    }
}

//ToolsShow show = new ToolsShow();
//show.setViewStatus(Meta.SHOW_V_STATUS_ING);
//
//ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
//        .withIgnoreCase(true) //改变默认大小写忽略方式：忽略大小写
//        .withIgnorePaths("focus");  //忽略属性：是否关注。因为是基本类型，需要忽略掉
//
////创建实例
//Example<ToolsShow> ex = Example.of(show, matcher);
//
////ToolsShow show = new ToolsShow();
////show.setViewStatus(Meta.SHOW_V_STATUS_ING);
////
////ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
////        .withIgnoreCase(true) //改变默认大小写忽略方式：忽略大小写
////        .withIgnorePaths("focus");  //忽略属性：是否关注。因为是基本类型，需要忽略掉
////
//////创建实例
////Example<ToolsShow> ex = Example.of(show, matcher);
//showList = showRepo.findByViewStatus(Meta.SHOW_V_STATUS_ING);
//
//ToolsShow show = new ToolsShow();
//show.setShowName(keyword);
//show.setTv(keyword);
//show.setRemark(keyword);
//
//ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
//        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
//        .withIgnoreCase(true) //改变默认大小写忽略方式：忽略大小写
//        .withIgnorePaths("focus");  //忽略属性：是否关注。因为是基本类型，需要忽略掉
//
////创建实例
//Example<ToolsShow> ex = Example.of(show, matcher);
