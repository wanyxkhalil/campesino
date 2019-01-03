package io.wanyxkhalil.campesino;

import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Objects;

/**
 * 日期工具类
 */
public class DateUtils {

    /**
     * 默认 DATETIME 格式化对象
     */
    private static final DateTimeFormatter DEFAULT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 默认时区
     */
    private static final ZoneId CURRENT_ZONE = ZoneId.systemDefault();

    /**
     * Date对象转为LocalDate对象
     */
    public static LocalDate date2LocalDate(Date date) {
        return date.toInstant().atZone(CURRENT_ZONE).toLocalDate();
    }

    /**
     * Date对象转为LocalDateTime对象
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        return date.toInstant().atZone(CURRENT_ZONE).toLocalDateTime();
    }

    /**
     * 时间戳转为LocalDate对象
     */
    public static LocalDate ts2LocalDate(long timestamp) {
        return Instant.ofEpochMilli(timestamp).atZone(CURRENT_ZONE).toLocalDate();
    }

    /**
     * 时间戳转为LocalDateTime对象
     */
    public static LocalDateTime ts2LocalDateTime(long timestamp) {
        return Instant.ofEpochMilli(timestamp).atZone(CURRENT_ZONE).toLocalDateTime();
    }

    /**
     * LocalDateTime对象转为Date对象
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(CURRENT_ZONE).toInstant();
        return Date.from(instant);
    }

    /**
     * LocalDate对象转为Date对象，零点。
     */
    public static Date localDate2Date(LocalDate localDate) {
        Instant instant = localDate.atStartOfDay().atZone(CURRENT_ZONE).toInstant();
        return Date.from(instant);
    }

    /**
     * DATETIME 格式化
     */
    public static String toString(LocalDateTime x) {
        return DEFAULT_DATETIME_FORMATTER.format(x);
    }

    /**
     * 自定义格式化
     */
    public static String toString(LocalDate localDate, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDate.format(formatter);
    }

    /**
     * 自定义格式化
     */
    public static String toString(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }

    /**
     * 转为int对象
     *
     * @param theDate LocalDate对象
     * @return int对象。如20180101
     */
    public static int toInt(LocalDate theDate) {
        return Integer.valueOf(theDate.format(DateTimeFormatter.BASIC_ISO_DATE));
    }

    /**
     * int对象转为LocalDate对象
     *
     * @param i 日期。如：20180101
     * @return LocalDate对象
     */
    public static LocalDate int2LocalDate(int i) {
        return LocalDate.parse(Objects.toString(i), DateTimeFormatter.BASIC_ISO_DATE);
    }

    /**
     * 年月转为int对象
     *
     * @param theDate LocalDate对象
     * @return int对象。如201801
     */
    public static Integer toYearMonthInt(LocalDate theDate) {
        return Integer.valueOf(StringUtils.substring(theDate.format(DateTimeFormatter.BASIC_ISO_DATE), 0, 6));
    }

    /**
     * 根据年月获取该月第一天
     *
     * @param yearMonth yyyyMM。如：201801
     * @return 该年该月第一天，如："2018-01-01"
     */
    public static LocalDate getFirstDayByYearMonth(int yearMonth) {
        String dateStr = String.format("%s%s", yearMonth, "01");
        return LocalDate.parse(dateStr, DateTimeFormatter.BASIC_ISO_DATE);
    }

    /**
     * 根据年月获取该月最后一天
     *
     * @param yearMonth yyyyMM。如：201801
     * @return 该年该月最后一天，如："2018-01-31"
     */
    public static LocalDate getLastDayByYearMonth(int yearMonth) {
        LocalDate firstDay = getFirstDayByYearMonth(yearMonth);
        return firstDay.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 根据年月获取该月最后一天。不超过当天
     *
     * @param yearMonth yyyyMM。如：201801
     * @return 该年该月最后一天
     */
    public static LocalDate getLastDayByYearMonthUntilToday(int yearMonth) {
        LocalDate lastDay = getLastDayByYearMonth(yearMonth);

        LocalDate today = LocalDate.now();
        return lastDay.isAfter(today) ? today : lastDay;
    }

    /**
     * 时间区间内：前闭后闭
     *
     * @param x     待判断时间对象
     * @param start 开始日期
     * @param stop  结束日期
     * @return ->
     * true: 在区间内
     * false: 不在区间内
     */
    public static boolean isBetween(LocalDate x, LocalDate start, LocalDate stop) {
        return x != null && !x.isBefore(start) && !x.isAfter(stop);
    }

    /**
     * 获取某天零点
     */
    public static String getStartTime(LocalDate theDate) {
        LocalDateTime x = theDate.atStartOfDay();
        return DEFAULT_DATETIME_FORMATTER.format(x);
    }

    /**
     * 获取某天末点
     */
    public static String getEndTime(LocalDate theDate) {
        LocalDateTime x = theDate.atStartOfDay().plusDays(1).minusSeconds(1);
        return DEFAULT_DATETIME_FORMATTER.format(x);
    }

    /**
     * 获取两个日期的较小值
     */
    public static LocalDate min(LocalDate date1, LocalDate date2) {
        if (date1.isAfter(date2)) {
            return date2;
        }
        return date1;
    }

    /**
     * 获取两个日期的较大值
     */
    public static LocalDate max(LocalDate date1, LocalDate date2) {
        if (date1.isAfter(date2)) {
            return date1;
        }
        return date2;
    }


    /**
     * 第一个日期在第二个日期之后（包含）
     *
     * @param x 第一个日期
     * @param y 第二个日期
     * @return ->
     * true：x在y之后
     * false：x不在y之后
     */
    public static boolean afterWithEqual(LocalDate x, LocalDate y) {
        return !x.isBefore(y);
    }

    /**
     * 第一个日期在第二个日期之前（包含）
     *
     * @param x 第一个日期
     * @param y 第二个日期
     * @return ->
     * true：x在y之前
     * false：x不在y之前
     */
    public static boolean beforeWithEqual(LocalDate x, LocalDate y) {
        return !x.isAfter(y);
    }

    /**
     * 时间间隔，秒
     *
     * @deprecated 建议直接使用 {@link ChronoUnit}
     */
    @Deprecated
    public static Long betweenSeconds(LocalDateTime start, LocalDateTime stop) {
        return ChronoUnit.SECONDS.between(start, stop);
    }
}
