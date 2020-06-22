//
//package com.yifan.util;
//
//import com.alibaba.fastjson.JSONObject;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.lionsoul.ip2region.DataBlock;
//import org.lionsoul.ip2region.DbConfig;
//import org.lionsoul.ip2region.DbMakerConfigException;
//import org.lionsoul.ip2region.DbSearcher;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//public class HtmlParseUtil  {
//
//    public  String getIp() throws IOException, DbMakerConfigException {
//    /*    http://ip.taobao.com/
//        http://chaipip.com/*/
//        Document doc = Jsoup.connect("http://chaipip.com/").get();
//        String ip = doc.select("#ip").attr("value");
//        //根据ip进行位置信息搜索
//        DbConfig config = new DbConfig();
//        //获取ip库的位置（放在src下）（直接通过测试类获取文件Ip2RegionTest为测试类）
//        System.err.println( HtmlParseUtil.class.getPackage());
//        String dbfile = HtmlParseUtil.class.getResource("/ip2region.db").getPath();
//        System.err.println(dbfile+"--->");
//        DbSearcher searcher = new DbSearcher(config, dbfile);
//        //采用Btree搜索
//        DataBlock block = searcher.btreeSearch(ip);
//        //打印位置信息（格式：国家|大区|省份|城市|运营商）
//        JSONObject object = new JSONObject();
//        object.put(ip,block.getRegion());
//        System.out.println(ip+"|"+block.getRegion());
//        return ip+"|"+block.getRegion();
//    }
//}