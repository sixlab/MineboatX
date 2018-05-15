/**
 * Copyright (c) 2017 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2017/12/19 13:36
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;

public class DateTimeUtil {
    private static Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);
    
    /**
     * 当前周是本月第几周
     *
     * @param date
     * @return
     */
    public static int weekOfMonth(LocalDate date) {
        int day = date.get(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH);
        int dayOfWeek = date.getDayOfWeek().getValue();
        
        if (day <= dayOfWeek) {
            return date.get(ChronoField.ALIGNED_WEEK_OF_MONTH);
        } else {
            return date.get(ChronoField.ALIGNED_WEEK_OF_MONTH) + 1;
        }
    }
    
    /**
     * 当前周是本年第几周
     *
     * @param date
     * @return
     */
    public static int weekOfYear(LocalDate date) {
        int day = date.get(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR);
        int dayOfWeek = date.getDayOfWeek().getValue();
        
        if (day <= dayOfWeek) {
            return date.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
        } else {
            return date.get(ChronoField.ALIGNED_WEEK_OF_YEAR) + 1;
        }
    }
    
    /**
     * 当前周是本月第-几周
     *
     * @param date
     * @return
     */
    public static int weekOfMonthLast(LocalDate date) {
        int weekOfMonth = weekOfMonth(date);
        int weekOfMonthLast = weekOfMonth(date.with(TemporalAdjusters.lastDayOfMonth()));
        return weekOfMonth - weekOfMonthLast - 1;
    }
    
    /**
     * 当前周是本年第-几周
     *
     * @param date
     * @return
     */
    public static int weekOfYearLast(LocalDate date) {
        int weekOfYear = weekOfYear(date);
        int weekOfYearLast = weekOfYear(date.with(TemporalAdjusters.lastDayOfYear()));
        return weekOfYear - weekOfYearLast - 1;
    }
    
    /**
     * 当前日期是本月第-几个周X
     *
     * @param date
     * @return
     */
    public static int weekdayOfMonthLast(LocalDate date) {
        int dayOfWeek = date.getDayOfWeek().getValue();
        int alignedWeekOfMonth = date.get(ChronoField.ALIGNED_WEEK_OF_MONTH);
        
        LocalDate first = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate last = date.with(TemporalAdjusters.lastDayOfMonth());
        
        int firstDayOfWeek = first.getDayOfWeek().getValue();
        int lastDayOfWeek = last.getDayOfWeek().getValue();
        int lastAlignedWeekOfMonth = last.get(ChronoField.ALIGNED_WEEK_OF_MONTH);
    
        return countDay(dayOfWeek, alignedWeekOfMonth, firstDayOfWeek, lastDayOfWeek, lastAlignedWeekOfMonth);
    }
    
    /**
     * 当前日期是本年第-几个周X
     *
     * @param date
     * @return
     */
    public static int weekdayOfYearLast(LocalDate date) {
        int dayOfWeek = date.getDayOfWeek().getValue();
        int alignedWeekOfYear = date.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
        
        LocalDate first = date.with(TemporalAdjusters.firstDayOfYear());
        LocalDate last = date.with(TemporalAdjusters.lastDayOfYear());
        
        int firstDayOfWeek = first.getDayOfWeek().getValue();
        int lastDayOfWeek = last.getDayOfWeek().getValue();
        int lastAlignedWeekOfYear = last.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
        
        return countDay(dayOfWeek, alignedWeekOfYear, firstDayOfWeek, lastDayOfWeek, lastAlignedWeekOfYear);
    }
    
    private static int countDay(int dayOfWeek, int alignedWeekOfTarget, int firstDayOfWeek,
            int lastDayOfWeek, int lastAlignedWeekOfTarget) {
        if (firstDayOfWeek <= lastDayOfWeek) {
            if (dayOfWeek >= firstDayOfWeek && dayOfWeek <= lastDayOfWeek) {
                return alignedWeekOfTarget - lastAlignedWeekOfTarget - 1;
            } else {
                return alignedWeekOfTarget - lastAlignedWeekOfTarget;
            }
        } else {
            if (dayOfWeek >= firstDayOfWeek || dayOfWeek <= lastDayOfWeek) {
                return alignedWeekOfTarget - lastAlignedWeekOfTarget - 1;
            } else {
                return alignedWeekOfTarget - lastAlignedWeekOfTarget;
            }
        }
    }
    
    /**
     * 今年还剩多少天
     * @param localDate
     * @return
     */
    public static int yearLeftDays(LocalDate localDate) {
        int days = localDate.getDayOfYear();
        
        days = 365 - days;
        
        return localDate.isLeapYear() ? days + 1 : days;
    }
    
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now().withDayOfMonth(1).withMonth(2).withYear(2016);
    
        logger.info(yearLeftDays(localDate)+"");
    }
    
    public static LocalDate date2Local(Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        ZoneId zoneId = ZoneId.systemDefault();
        
        return LocalDateTime.ofInstant(instant, zoneId).toLocalDate();
    }
}
