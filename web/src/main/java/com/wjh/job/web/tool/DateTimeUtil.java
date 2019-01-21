package com.wjh.job.web.tool;




import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class DateTimeUtil {

    public static final DateFormat dfDate = new SimpleDateFormat("yyyyMMdd");
    public static final DateFormat dfTime = new SimpleDateFormat("HHmmss");
    public static final DateFormat dfTimeStamp = new SimpleDateFormat("HHmmssSSS");
    public static final DateFormat dfDateTime = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final DateFormat dfDateTimeBX = new SimpleDateFormat("yyyyMMdd HHmmssSSS");
    public static final DateFormat dfDateTime3 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    public static final DateFormat dfDateTimeColon = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");	//带冒号的时间戳

    /**
     * 获取日期 格式举例：20170615
     *
     * @param date：所需format的日期
     * @return：格式化后的字符串
     */
    public static synchronized String getDate(Date date) {
        return dfDate.format(date);
    }

    /**
     * 获取时间 格式举例：095920
     *
     * @param date：所需format的时间
     * @return：格式化后的字符串
     */
    public static synchronized String getTime(Date date) {
        return dfTime.format(date);
    }

    /**
     * 获取时间戳 格式举例：095920768
     *
     * @param date：所需format的时间
     * @return：格式化后的字符串
     */
    public static synchronized String getTimeStamp(Date date) {
        return dfTimeStamp.format(date);
    }

    /**
     * 获取日期时间 格式举例：20170615100523
     *
     * @param date：所需format的时刻
     * @return：格式化后的字符串
     */
    public static synchronized String getDateTime(Date date) {
        return dfDateTime.format(date);
    }

    /**
     * 获取日期时间 格式举例：20170615100523537
     *
     * @param date：所需format的时刻
     * @return：格式化后的字符串
     */
    public static synchronized String getDateTimeBX(Date date) {
        return dfDateTimeBX.format(date);
    }

    /**
     *
     * @param date
     * @return
     */
    public static synchronized String getDateTime3(Date date) {
        return dfDateTime3.format(date);
    }

    /**
     * 获取日期时间 格式举例：20170615100523537
     *
     * @param date：所需format的时刻
     * @return：格式化后的字符串
     */
    public static synchronized String getDateTimeColon(Date date) {
        return dfDateTimeColon.format(date);
    }

    /**
     * 自定义格式化
     *
     * @param date：所需format的日期时间
     * @param formatStr:格式化规则字符串，需要满足SimpleDateFormat的规则要求
     * @return：格式化后的字符串
     */
    public static synchronized String getFormat(Date date, String formatStr) {
        DateFormat df = new SimpleDateFormat(formatStr);
        return df.format(date);
    }

    /**
     * 根据格式化字符串将字符串转换为date对象
     *
     * @param dateStr:代表日期/时间的字符串
     * @param formatStr:格式化规则字符串
     * @return:转换后的Date对象
     * @throws ParseException
     */
    public static synchronized Date getDateByFormatStr(String dateStr, String formatStr)  {
        DateFormat df = new SimpleDateFormat(formatStr);
        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据格式化字符串将字符串转换为date对象
     *
     * @param dateStr:代表日期/时间的字符串
     * @return:转换后的Date对象，无法转换返回null
     * @throws:无
     */
    public static synchronized Date getDateByDateStr(String dateStr) {
        if (dateStr == null || dateStr.length() == 0) {
            return null;
        }
        try {
            return dfDate.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 校验指定字符串是否满足日期的格式
     *
     * @param dateStr:待校验日期字符串
     * @return:是否满足 ture-满足 false-为空或不满足
     */
    public static synchronized boolean validateDateStr(String dateStr) {
        if (dateStr == null || dateStr.length() == 0) {
            return false;
        }
        try {
            Date d = dfDate.parse(dateStr);
            return dateStr.equals(getDate(d));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 校验一个字符串日期是否是未来的日期
     *
     * @param dateStr:待校验日期字符串
     * @param includeToday:是否允许等于今天
     * @return:校验是否通过
     */
    public static synchronized boolean validateFutureDateStr(String dateStr, boolean includeToday) {
        if (dateStr == null || dateStr.length() == 0) {
            return false;
        }
        try {
            Date d = dfDate.parse(dateStr);
            if (dateStr.equals(getDate(d))) {
                String today = getDate(new Date());
                return includeToday ? dateStr.compareTo(today) >= 0 : dateStr.compareTo(today) > 0;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 校验指定字符串是否满足时间的格式 格式举例:101723
     *
     * @param timeStr:待校验时间字符串
     * @return:是否满足 ture-满足 false-为空或不满足
     */
    public static synchronized boolean validateTimeStr(String timeStr) {
        if (timeStr == null || timeStr.length() == 0) {
            return false;
        }
        try {
            dfTime.parse(timeStr);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static synchronized boolean validateHeadTimeStr(String timeStr) {
        if (timeStr == null || timeStr.length() == 0) {
            return false;
        }
        try {
            System.out.println("输入："+timeStr);
            Date d = dfDateTimeBX.parse(timeStr);
            String temp = getDateTimeBX(d);
            System.out.println("转换后："+temp);
            return timeStr.equals(temp);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 日期字符串增加或减去指定天数
     *
     * @param dateStr:YYYYMMDD格式的日期字符串
     * @param nOffSet:偏移天数，正数增加，负数减少
     * @return:YYYYMMDD格式的日期字符串 如果字符串格式不正确，返回null
     */
    public static synchronized String addDate(String dateStr, int nOffSet) {
        Date d = getDateByDateStr(dateStr);
        if (d == null) {
            return null;
        }

        Calendar c1 = Calendar.getInstance();
        c1.setTime(d);
        c1.add(Calendar.DATE, nOffSet);
        return getDate(c1.getTime());
    }

    /**
     * 日期字符串增加或减去指定天数
     *
     * @param date:YYYYMMDD格式的日期字符串
     * @param nOffSet:偏移天数，正数增加，负数减少
     * @return:YYYYMMDD格式的日期字符串 如果字符串格式不正确，返回null
     */
    public static synchronized Date addDate(Date date, int nOffSet) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        c1.add(Calendar.DATE, nOffSet);
        return c1.getTime();
    }


    /**
     * 用于通道查询接口的时间比对
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    public static synchronized boolean compareTime(String startDate, String endDate) throws Exception {
        Date start = dfDateTime.parse(startDate);
        Date end = dfDateTime.parse(endDate);
        long interval = end.getTime() - start.getTime();
        long result = interval / 1000;
        if (result < 0.5 * 60 * 60) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取10位时间戳
     * @return
     * @throws Exception
     */
    public static String getTimeStamp10(){
        long timeStampSec = System.currentTimeMillis()/1000;
        return String.format("%010d", timeStampSec);
    }

    /**
     * 获取10位时间戳
     * @return
     * @throws Exception
     */
    public static String defanxiegang(String date){

        return date.replaceAll("/","");
    }

}
