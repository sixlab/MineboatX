// /**
//  * Copyright (c) 2017 Sixlab. All rights reserved.
//  * <p>
//  * License information see the LICENSE file in the project's root directory.
//  * <p>
//  * For more information, please see
//  * https://sixlab.cn/
//  *
//  * @time: 2017/12/18 14:38
//  * @author: Patrick <root@sixlab.cn>
//  */
// package cn.sixlab.mbx.plugin.api.schedule;
//
// import cn.sixlab.mbx.plugin.api.util.DateTimeUtil;
// import cn.sixlab.mbx.plugin.api.beans.MsxAssignment;
// import cn.sixlab.mbx.plugin.api.beans.MsxAssignmentRule;
// import cn.sixlab.mbx.plugin.api.beans.MsxAssignmentRuleDetail;
// import cn.sixlab.mbx.plugin.api.dao.AssignmentRepo;
// import cn.sixlab.mbx.plugin.api.dao.RuleDetailRepo;
// import cn.sixlab.mbx.plugin.api.dao.RuleRepo;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.mail.javamail.MimeMessageHelper;
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Component;
// import org.springframework.util.CollectionUtils;
//
// import javax.mail.MessagingException;
// import javax.mail.internet.MimeMessage;
// import java.sql.Date;
// import java.sql.Timestamp;
// import java.time.DayOfWeek;
// import java.time.Instant;
// import java.time.LocalDate;
// import java.time.LocalDateTime;
// import java.time.temporal.ChronoField;
// import java.time.temporal.ChronoUnit;
// import java.time.temporal.TemporalAdjusters;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
//
// @Component
// public class ScheduleService {
//     private static Logger logger = LoggerFactory.getLogger(ScheduleService.class);
//
//     //@Autowired
//     //private MsxJpush jpush;
//     //
//     //private JPushClient jPushClient;
//
//     @Autowired
//     private RuleRepo ruleRepo;
//
//     @Autowired
//     private RuleDetailRepo ruleDetailRepo;
//
//     @Autowired
//     private AssignmentRepo assignmentRepo;
//
//     //@Autowired
//     //IWxMsgService wxMsgService;
//
//     @Scheduled(cron = "0 0 5 * * ?")
//     public void morning() throws InterruptedException {
//         logger.info("good morning");
//
//         Map<Integer, List<MsxAssignmentRule>> assignmentMap = getToadyAssignment();
//
//         int count = initDb(assignmentMap);
//
//         //Map<String, Map<String, String>> data = new HashMap<>();
//         //
//         //Map<String, String> first = new HashMap<>();
//         //first.put("value", "一年之计在于春，一日之计在于晨。\n");
//         //data.put("first", first);
//         //
//         //Map<String, String> keyword1 = new HashMap<>();
//         //keyword1.put("value", "任务数量");
//         //data.put("keyword1", keyword1);
//         //
//         //Map<String, String> keyword2 = new HashMap<>();
//         //keyword2.put("value", "共 " + count + " 个");
//         //data.put("keyword2", keyword2);
//         //
//         //String msg = "今天是第 "+localDate.getDayOfYear()+" 天\n第"
//         //        + DateTimeUtil.weekOfYear(localDate) + "周\n今年还剩下  "
//         //        + DateTimeUtil.yearLeftDays(localDate) + "  天\n";
//         //
//         //Map<String, String> remark = new HashMap<>();
//         //remark.put("value", msg);
//         //remark.put("color", "#FF4500");
//         //data.put("remark", remark);
//         //
//         //SendMsgVo vo = new SendMsgVo();
//         //vo.setData(data);
//         //vo.setUrl(url);
//         //
//         //wxMsgService.sendMsg(vo);
//
//         //if (null == jPushClient) {
//         //    jPushClient = new JPushClient(jpush.getJpushSecret(), jpush.getJpushKey(), null, ClientConfig.getInstance());
//         //}
//         //
//         //// For push, all you need do is to build PushPayload object.
//         //PushPayload payload = buildMsg(url);
//         //
//         //try {
//         //    PushResult result = jPushClient.sendPush(payload);
//         //
//         //    logger.info("-----------");
//         //    logger.info(JsonUtl.toJson(result));
//         //    logger.info("-----------");
//         //} catch (APIConnectionException e) {
//         //    e.printStackTrace();
//         //} catch (APIRequestException e) {
//         //    e.printStackTrace();
//         //}
//
//         logger.info("结束。");
//     }
//
//     private int initDb(Map<Integer, List<MsxAssignmentRule>> assignmentMap) {
//         Date assignmentDate = Date.valueOf(LocalDate.now());
//         Timestamp insertTime = Timestamp.valueOf(LocalDateTime.now());
//
//         int count = 0;
//         for (Integer hour : assignmentMap.keySet()) {
//             List<MsxAssignmentRule> ruleList = assignmentMap.get(hour);
//
//             for (MsxAssignmentRule rule : ruleList) {
//                 MsxAssignment assignment = new MsxAssignment();
//                 assignment.setAssignmentDate(assignmentDate);
//                 assignment.setAssignmentHour(hour);
//                 assignment.setRemark(rule.getRuleRemark());
//                 assignment.setAssignmentName(rule.getRuleName());
//                 assignment.setFinishCheck(false);
//                 assignment.setInsertTime(LocalDateTime.now());
//                 assignment.setRuleId(rule.getId());
//
//                 assignmentRepo.save(assignment);
//                 count++;
//             }
//         }
//
//         return count;
//     }
//
//     //private PushPayload buildMsg(String url) {
//     //    return PushPayload.alertAll("今日任务：" + url);
//     //}
//
//     private Map<Integer, List<MsxAssignmentRule>> getToadyAssignment() {
//         LocalDate now = LocalDate.now();
//
//         int year = now.getYear();
//         int monthOfYear = now.getMonthValue();
//
//         int dayOfWeek = now.getDayOfWeek().getValue();
//         int dayOfMonth = now.getDayOfMonth();
//
//         int weekOfMonth = DateTimeUtil.weekOfMonth(now);
//         int weekOfYear = DateTimeUtil.weekOfYear(now);
//         int weekOfMonthLast = DateTimeUtil.weekOfMonthLast(now);
//         int weekOfYearLast = DateTimeUtil.weekOfYearLast(now);
//
//         int weekdayOfMonth = now.get(ChronoField.ALIGNED_WEEK_OF_MONTH);
//         int weekdayOfMonthLast = DateTimeUtil.weekdayOfMonthLast(now);
//         int weekdayOfYear = now.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
//         int weekdayOfYearLast = DateTimeUtil.weekdayOfYearLast(now);
//
//         Map<Integer, List<MsxAssignmentRule>> assignmentMap = new HashMap<>();
//
//         //找出所有有效期内任务
//         List<MsxAssignmentRule> ruleList = ruleRepo.queryActiveRule(new Date(Instant.now().toEpochMilli()));
//
//         if (!CollectionUtils.isEmpty(ruleList)) {
//             for (MsxAssignmentRule rule : ruleList) {
//
//                 LocalDate beginDate = DateTimeUtil.date2Local(rule.getBeginDate());
//
//                 long betweenDay = ChronoUnit.DAYS.between(beginDate, now);
//                 long betweenWeek = ChronoUnit.DAYS.between(beginDate, now) / 7;
//                 long betweenMonth = ChronoUnit.MONTHS.between(beginDate.with(TemporalAdjusters.firstDayOfMonth())
//                         , now.with(TemporalAdjusters.firstDayOfMonth()));
//
//                 //找出所有任务规则
//                 List<MsxAssignmentRuleDetail> detailList = ruleDetailRepo.findByRuleId(rule.getId());
//
//                 if (!CollectionUtils.isEmpty(detailList)) {
//
//                     // 循环任务规则，找出是今天的任务，
//                     for (MsxAssignmentRuleDetail detail : detailList) {
//                         boolean ruleMatch = false;
//                         //先算公立，不算农历
//
//                         Integer yearNum = detail.getYearNum();
//                         Integer monthNum = detail.getMonthNum();
//                         Integer weekNum = detail.getWeekNum();
//                         Integer dayNum = detail.getDayNum();
//
//                         if (null == yearNum) {
//                             if (null == monthNum) {
//                                 if (null == weekNum) {
//                                     if (null == dayNum) { // 每天此时
//                                         ruleMatch = true;
//                                     } else { // day 每隔 dayNum 天
//                                         if (betweenDay % dayNum == 0) {
//                                             ruleMatch = true;
//                                         }
//                                     }
//                                 } else { // week
//                                     if (null == dayNum) { // 不存在的 每 weekNum 周
//                                         markError(detail, "[不存在]每 weekNum 周");
//                                     } else { //  day 每隔 weekNum 周的周 dayNum
//                                         if (dayOfWeek == dayNum) {
//                                             if (betweenWeek % weekNum == 0) {
//                                                 ruleMatch = true;
//                                             }
//                                         }
//                                     }
//                                 }
//                             } else {
//                                 // 月
//                                 if (null == weekNum) {
//                                     if (null == dayNum) { // 不存在 每 monthNum 月
//                                         markError(detail, "[不存在]每 monthNum 月");
//                                     } else { // day 每 monthNum 月 dayNum 号
//                                         if (dayOfMonth == dayNum) {
//                                             if (betweenMonth % monthNum == 0) {
//                                                 ruleMatch = true;
//                                             }
//                                         }
//                                     }
//                                 } else {
//                                     // week
//                                     if (null == dayNum) { // 不存在 每 monthNum 月 weekNum 周
//                                         markError(detail, "[不存在]每 monthNum 月 weekNum 周");
//                                     } else { // day 每 monthNum 月第 weekNum 个周 dayNum
//                                         // 周几对的上
//                                         //TODO 如果是每月第5个周日，不一定每个月都有
//                                         if (dayNum == dayOfWeek) {
//                                             // 周对的上
//                                             if (detail.getWeekType() == null) {
//                                                 if (weekNum == weekdayOfMonth || weekNum == weekdayOfMonthLast) {
//                                                     if (betweenMonth % monthNum == 0) {
//                                                         ruleMatch = true;
//                                                     }
//                                                 }
//                                             } else {
//                                                 if (dayOfWeek == 1 && weekdayOfMonth == weekNum) {
//                                                     ruleMatch = true;
//                                                 } else {
//                                                     LocalDate lastMonday = now.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
//                                                     if (lastMonday.get(ChronoField.ALIGNED_WEEK_OF_MONTH) == weekNum) {
//                                                         ruleMatch = true;
//                                                     }
//                                                 }
//                                             }
//                                         }
//                                     }
//                                 }
//                             }
//                         } else {
//                             // 每年的
//
//                             //年对的上才继续，不然不算了
//                             Integer beginYear = beginDate.getYear();
//                             if ((year - beginYear) % yearNum != 0) {
//                                 continue;
//                             }
//
//                             if (null == monthNum) {
//                                 if (null == weekNum) {
//                                     if (null == dayNum) { // 不存在 每 yearNum 年
//                                         markError(detail, "[不存在]每 yearNum 年");
//                                     } else { // day 每 yearNum 年第 dayNum 天
//                                         // 天对的上
//                                         //TODO 每年366天，每隔四年才出一次
//                                         //TODO 加上倒数第几天
//                                         if (now.getDayOfYear() == dayNum) {
//                                             ruleMatch = true;
//                                         }
//                                     }
//                                 } else { // week
//                                     if (null == dayNum) { // 不存在  每 yearNum 年第 weekNum 周
//                                         markError(detail, "[不存在]每 yearNum 年第 weekNum 周");
//                                     } else { // day 每 yearNum 年第 weekNum 个周 dayNum
//                                         //TODO 如果是每月第5个周日，不一定每个月都有
//                                         if (dayNum == dayOfWeek) {
//                                             if (weekNum == weekdayOfYear || weekNum == weekdayOfYearLast) {
//                                                 ruleMatch = true;
//                                             }
//                                         }
//                                     }
//                                 }
//                             } else {
//                                 // month
//                                 if (null == weekNum) {
//                                     if (null == dayNum) { // 不存在  每 yearNum 年第 monthNum 月
//                                         markError(detail, "[不存在]每 yearNum 年第 monthNum 月");
//                                     } else { // day 每 yearNum 年第 monthNum 月 dayNum 号
//                                         //TODO 加上倒数
//                                         if (monthNum == monthOfYear && dayNum == dayOfMonth) {
//                                             ruleMatch = true;
//                                         }
//                                     }
//                                 } else { // week
//                                     if (null == dayNum) { // 不存在  每 yearNum 年第 monthNum 月 weekNum 周
//                                         markError(detail, "[不存在]每 yearNum 年第 monthNum 月 weekNum 周");
//                                     } else {// day 每 yearNum 年第 monthNum 月第 weekNum 个周 dayNum
//                                         //加上倒数
//                                         if (monthNum == monthOfYear
//                                                 && (weekNum == weekdayOfMonth || weekNum == weekdayOfMonthLast)
//                                                 && dayNum == dayOfWeek) {
//                                             ruleMatch = true;
//                                         }
//                                     }
//                                 }
//                             }
//                         }
//
//                         if (ruleMatch) {
//                             addAssignment(assignmentMap, detail.getRuleHour(), rule);
//                         }
//                     }
//
//                     logger.info("内部 for 循环结束");
//                 }
//             }
//             logger.info("外部 for 循环结束");
//         }
//
//         return assignmentMap;
//     }
//
//     private void markError(MsxAssignmentRuleDetail detail, String msg) {
//         detail.setRemark(detail.getRemark() + "@@:消息" + msg);
//         ruleDetailRepo.save(detail);
//     }
//
//     private void addAssignment(Map<Integer, List<MsxAssignmentRule>> assignmentMap,
//             Integer ruleHour, MsxAssignmentRule rule) {
//         List<MsxAssignmentRule> ruleList = assignmentMap.get(ruleHour);
//         if (null == ruleList) {
//             ruleList = new ArrayList<>();
//         }
//         ruleList.add(rule);
//
//         assignmentMap.put(ruleHour, ruleList);
//     }
// }
//
//
// //ruleDate = localDate.withMonth(localDate.getMonthValue()).withDayOfMonth(detail.getWeekNum());