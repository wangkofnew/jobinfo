package com.wjh.job.web.job;

import com.wjh.job.web.spider.Init;
import com.wjh.job.web.tool.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@EnableScheduling
public class HunterJobStart {

    @Autowired
    private Init init;

    private static final Logger log = LoggerFactory.getLogger(HunterJobStart.class);
    /**
     * * 第一位，表示秒，取值0-59
     * * 第二位，表示分，取值0-59
     * * 第三位，表示小时，取值0-23
     * * 第四位，日期天/日，取值1-31
     * * 第五位，日期月份，取值1-12
     * * 第六位，星期，取值1-7，星期一，星期二...，注：不是第1周，第二周的意思
     *           另外：1表示星期天，2表示星期一。
     * * 第7为，年份，可以留空，取值1970-2099
     */
    @Scheduled(cron="0 0 */3 * * ?")
    public void cronJob(){
        //每1小时执行一次
        log.info(DateTimeUtil.getDateTimeBX(new Date())+" >> 抓取任务开始执行....");
        try {
            init.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
