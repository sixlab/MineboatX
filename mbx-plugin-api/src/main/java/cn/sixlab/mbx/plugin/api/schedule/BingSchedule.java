package cn.sixlab.mbx.plugin.api.schedule;

import cn.sixlab.mbx.core.common.util.HttpUtil;
import cn.sixlab.mbx.plugin.api.beans.MbxBing;
import cn.sixlab.mbx.plugin.api.dao.BingRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class BingSchedule {
    private static Logger logger = LoggerFactory.getLogger(BingSchedule.class);

    @Autowired
    private BingRepo repo;

    @Scheduled(cron = "0 0 6 * * ?")
    public void sendMail() {
        logger.info("bing schedule start");

        String url = "https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&nc=" + new Date().getTime() + "&pid=hp&FORM=Z9FD1";

        Map json = HttpUtil.getJson(url);

        List links = (List) json.get("images");
        Map link = (Map) links.get(0);

        String image = "https://cn.bing.com" + link.get("url");
        String base = (String) link.get("urlbase");
        String start = (String) link.get("startdate");

        MbxBing bing = new MbxBing();
        bing.setImage(image);
        bing.setUrlBase(base);
        bing.setStartDate(start);

        bing.setInsertTime(LocalDateTime.now());
        bing.setDeleted('0');

        repo.save(bing);

        logger.info("bing schedule end");
    }

}
