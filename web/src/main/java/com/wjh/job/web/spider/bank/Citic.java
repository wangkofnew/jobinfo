package com.wjh.job.web.spider.bank;

import com.sun.org.apache.xml.internal.dtm.DTMIterator;
import com.sun.org.apache.xml.internal.dtm.ref.DTMNodeList;
import com.sun.org.apache.xpath.internal.objects.XNodeSet;
import com.wjh.job.web.db.JobinfoDao;
import com.wjh.job.web.db.mapper.JobinfoDO;
import com.wjh.job.web.spider.UrlData;
import com.wjh.job.web.tool.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.*;
@Component
public class Citic {
    //job.bank.ecitic.com
    @Autowired
    private JobinfoDao jobinfoDao;
    private static final Logger log = LoggerFactory.getLogger(Psbc.class);
    private static String domains = "job.bank.ecitic.com";
    private static String fecthUrl = "http://job.bank.ecitic.com/zpweb/planList.do";

//    public static void main(String[] args) throws Exception{
//        //fecthByMap("http://www.jianshu.com/u/bf7b9c013c55","//ul[@class='note-list']/li//a[@class='title']");
//        //UrlData.fecthByMap("http://www.psbc.com/cn/index/rczp/index.html","//ul[@class='listul']/li//a",domains);
//        //fecthAttr("http://www.psbc.com/cn/index/rczp/index.html","//ul[@class='listul']/li","test");
//    }

    public void letsGO()throws Exception{
        HashMap hashMap = new HashMap();
        hashMap.put("zpType","U0g-");
        hashMap.put("actPara","allPlanList");
        Map map = fecthByMap("http://job.bank.ecitic.com/zpweb/planList.do","//*[@id=\"pageQueryForm\"]/div/div/div/div/table/tbody/tr[2]/td[1]/a",domains,hashMap,"POST");
        /////    /tr[2]/td[1]/a
        ////*[@id="pageQueryForm"]/div/div/div/div/table/tbody/tr[2]/td[1]/a
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
            jobinfoDO.setCompanyName("中信银行");
            jobinfoDO.setCreateDate(DateTimeUtil.getDate(new Date()));
            jobinfoDO.setCreateTime(DateTimeUtil.getTime(new Date()));
            jobinfoDO.setJobTitle(entry.getKey().toString());
            jobinfoDO.setJobUrl(fecthUrl);
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

    public static Map<String, String> fecthByMap(String url, String xpath,String domains,HashMap map,String sendType) throws Exception {
        Map<String, String> nodeMap = new LinkedHashMap<>();

        Object result = UrlData.fecthNode(url, xpath,map,sendType);

        if (result instanceof NodeList) {
            NodeList nodeList = (NodeList) result;
            log.info("----------"+result.toString());
            XNodeSet xNodeSet = (XNodeSet)((DTMNodeList) nodeList).getDTMIterator();
            log.info("-----"+xNodeSet.toString());
            DTMIterator iterator =  xNodeSet.iter();


            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if(node == null){
                    continue;
                }
                nodeMap.put(node.getTextContent(), node.getAttributes().getNamedItem("href")!=null ?
                        node.getAttributes().getNamedItem("href").getTextContent() : "");

                System.out.println(node.getTextContent() + ":" + domains+node.getAttributes().getNamedItem("href").getTextContent());
            }
        }

        return nodeMap;
    }


    public static void main(String args[]){
        try {
            new Citic().letsGO();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
