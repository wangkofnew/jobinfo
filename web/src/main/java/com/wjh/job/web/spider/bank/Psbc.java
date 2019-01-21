package com.wjh.job.web.spider.bank;

import java.io.IOException;
import java.util.*;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import com.wjh.job.web.db.JobinfoDao;
import com.wjh.job.web.db.mapper.JobinfoDO;
import com.wjh.job.web.spider.UrlData;
import com.wjh.job.web.tool.DateTimeUtil;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Component
public class Psbc {
    @Autowired
    private JobinfoDao jobinfoDao;
    private static final Logger log = LoggerFactory.getLogger(Psbc.class);
    private static String domains = "www.psbc.com";

//    public static void main(String[] args) throws Exception{
//        //fecthByMap("http://www.jianshu.com/u/bf7b9c013c55","//ul[@class='note-list']/li//a[@class='title']");
//        //UrlData.fecthByMap("http://www.psbc.com/cn/index/rczp/index.html","//ul[@class='listul']/li//a",domains);
//        //fecthAttr("http://www.psbc.com/cn/index/rczp/index.html","//ul[@class='listul']/li","test");
//    }

    public void letsGO()throws Exception{
        Map map = UrlData.fecthByMap("http://www.psbc.com/cn/index/rczp/index.html","//ul[@class='listul']/li//a",domains);
        log.info("----------------"+map.toString());
         //获取数据库该公司以前记录，进行对比
        List<JobinfoDO> jobinfoDOS = jobinfoDao.findByCompanyId(this.getClass().getSimpleName());


        Iterator it = map.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry entry = (Map.Entry)it.next();
            log.info(entry.getKey()+"=");
            log.info(entry.getValue()+";");

            if(checkSame(jobinfoDOS, entry)==true)continue;
            JobinfoDO jobinfoDO = new JobinfoDO();
            jobinfoDO.setCompanyId(this.getClass().getSimpleName());
            jobinfoDO.setCompanyName("中国邮政储蓄银行");
            jobinfoDO.setCreateDate(DateTimeUtil.getDate(new Date()));
            jobinfoDO.setCreateTime(DateTimeUtil.getTime(new Date()));
            jobinfoDO.setJobTitle(entry.getKey().toString());
            jobinfoDO.setJobUrl(domains+entry.getValue().toString());
            jobinfoDao.save(jobinfoDO);


        }

    }

    private boolean checkSame(List<JobinfoDO> jobinfoDOS, Map.Entry entry) {
        for (int i = 0; i < jobinfoDOS.size(); i++) {
            String title = jobinfoDOS.get(i).getJobTitle();
            if(title.equals(entry.getKey().toString()))return true;
            //如果抓取的记录不在之前存储的数据库中，就认为是新的信息，放入数据库

        }
        log.info("-------------------没有重复，是新的信息---------------");
        return false;
    }

}
