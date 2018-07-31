package cn.sixlab.mbx.plugin.api.schedule;

import cn.sixlab.mbx.plugin.api.dao.AssignmentRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.sql.Date;
import java.time.LocalDate;

@Component
public class MailSchedule {
    private static Logger logger = LoggerFactory.getLogger(MailSchedule.class);

    @Autowired
    private AssignmentRepo assignmentRepo;

    @Autowired
    private JavaMailSender mailSender;

    @Scheduled(cron = "0 0 6 * * ?")
    public void sendMail() {
        logger.info("good morning 2");
        try {
            LocalDate localDate = LocalDate.now();

            String date = localDate.getYear() + "/" + localDate.getMonthValue() + "/" + localDate.getDayOfMonth();
            //String url = "https://sixlab.cn/assignment/pub/" + date;
            String url = "https://sixlab.cn/assignment/apps/" + date;

            int count = assignmentRepo.countAllByAssignmentDate(Date.valueOf(localDate));

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("root@sixlab.cn");
            helper.setTo("nianqinianyi@163.com");
            helper.setSubject("今日任务：" + count + " 个。一年之计在于春，一日之计在于晨。" + date);
            helper.setText("<a href='" + url + "'>打开页面</a>", true);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        logger.info("结束。");
    }

}
