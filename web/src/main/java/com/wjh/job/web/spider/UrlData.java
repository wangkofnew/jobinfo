package com.wjh.job.web.spider;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.util.*;

public class UrlData {
    public static Object fecthNode(String url, String xpath, HashMap map, String sendType) throws Exception {
        String html = null;
        Connection connect = Jsoup.connect(url);

        connect.data(map);
        try {
            if(sendType.equals("GET")){

                html = connect.get().body().html();
            }else if(sendType.equals("POST")){
                html = connect.post().body().html();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        HtmlCleaner hc = new HtmlCleaner();
        TagNode tn = hc.clean(html);
        Document dom = new DomSerializer(new CleanerProperties()).createDOM(tn);
        XPath xPath = XPathFactory.newInstance().newXPath();

        Object result = xPath.evaluate(xpath, dom, XPathConstants.NODESET);

        return result;
    }

    public static Map<String, String> fecthByMap(String url, String xpath,String domains,HashMap map,String sendType) throws Exception {
        Map<String, String> nodeMap = new LinkedHashMap<>();

        Object result = fecthNode(url, xpath,map,sendType);

        if (result instanceof NodeList) {
            NodeList nodeList = (NodeList) result;

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

//    public static List<String> fecthAttr(String url, String xpath, String attr) throws Exception {
//        List<String> list = new ArrayList<>();
//
//        Object result = fecthNode(url, xpath);
//
//        if (result instanceof NodeList) {
//            NodeList nodeList = (NodeList) result;
//
//            for (int i = 0; i < nodeList.getLength(); i++) {
//                Node node = nodeList.item(i);
//                if(node == null){
//                    continue;
//                }
//                list.add(node.getAttributes().getNamedItem(attr).getTextContent());
//
//                System.out.println(node.getTextContent() + " : " + node.getAttributes().getNamedItem("href"));
//            }
//        }
//
//        return list;
//    }
}
