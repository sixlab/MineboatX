package cn.sixlab.mbx.plugin.api.schedule;

import cn.sixlab.mbx.core.beans.entity.MbxUser;
import cn.sixlab.mbx.core.dao.repository.UserRepository;
import cn.sixlab.mbx.plugin.api.beans.MbxPoint;
import cn.sixlab.mbx.plugin.api.beans.MbxPointTask;
import cn.sixlab.mbx.plugin.api.dao.PointRepo;
import cn.sixlab.mbx.plugin.api.dao.PointTaskRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class MailSchedule {
    private static Logger logger = LoggerFactory.getLogger(MailSchedule.class);
    
    @Autowired
    private PointTaskRepo pointTaskRepo;
    
    @Autowired
    private PointRepo pointRepo;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Scheduled(cron = "0 0 6 * * ?")
    public void sendMail() {
        logger.info("good morning 2");
        try {
            LocalDate localDate = LocalDate.now();
            String date = localDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    
            List<MbxPoint> all = pointRepo.findAll();
            for (MbxPoint mbxPoint : all) {
                MbxUser one = userRepository.getOne(mbxPoint.getUserId());
                if(StringUtils.isEmpty(one.getEmail())){
                    continue;
                }
                String url = "https://api.sixlab.cn/point/apps/" + date;
    
                int count = pointTaskRepo.countAllByUserIdAndTaskDate(mbxPoint.getUserId(), localDate);
    
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setFrom("root@sixlab.cn");
                helper.setTo(one.getEmail());
                helper.setSubject("今日任务：" + count + " 个。一年之计在于春，一日之计在于晨。" + date);
    
    
                List<MbxPointTask> taskList = pointTaskRepo.selectToday(mbxPoint.getUserId(), localDate);
    
                StringBuilder sb = new StringBuilder("<a href='" + url + "'>打开页面</a>");
                sb.append("<table><thead><tr><th width='30%'>标题</th><th width='70%'>描述</th></tr></thead><tbody>");
                for (MbxPointTask task : taskList) {
                    sb.append("<tr><td>");
                    sb.append(task.getTitle());
                    sb.append("</td><td>");
                    sb.append(task.getContent());
                    sb.append("</td></tr>");
                }
                sb.append("</tbody></table>");
                
                helper.setText(sb.toString(), true);
    
                mailSender.send(message);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        logger.info("结束。");
    }

}
