package com.springsecurity.demo.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springsecurity.demo.R;
import com.springsecurity.demo.utils.SendHttp;
import org.jsoup.Connection;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * @program: spring-security
 * @description:
 * @author: DrongRonglong
 * @create: 2019-03-22 13:29
 **/

@Service
public class GetListData {

    private static String typeUrl="http://avggbot.icu/api/categories";
    private static String dataUrl="http://avggbot.icu/api/lists";
    public R getType(){
        String data= null;
        try {
            Connection.Response d = SendHttp.get(typeUrl,null);
            data=d.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        data=data.substring(0,data.length()-1);
        JSONObject jsonObject=JSONObject.parseObject(data);
        JSONArray jsonArray=JSONArray.parseArray(jsonObject.getString("data"));
        return R.ok().put("data",jsonArray);
    }
    @Cacheable(value = "dataList",key = "#p0")
    public R getData(Map params){
        String page="";
        String cate="91porn";
        String order_by="";
        String order_method="";
        String per_page="";

        if (null!=params.get("page")){
            page=params.get("page").toString();
        }
        if (null!=params.get("cate")){
            cate=params.get("cate").toString();
        }
        if (null!=params.get(order_by)){
            order_by=params.get("order_by").toString();
        }
        if (null!=params.get("order_method")){
            order_method=params.get("order_method").toString();
        }
        if (null!=params.get("per_page")){
            per_page=params.get("per_page").toString();
        }
        String url=dataUrl+ "?page="+page+"&cate="+cate+"&order_by="+order_by+"&order_method="+order_method+""+"&per_page="+per_page;
        String dataList= null;
        try {
            System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
            Connection.Response d = SendHttp.get(url);
            dataList=d.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataList=dataList.substring(0,dataList.length()-1);
        JSONObject jsonObject=JSONObject.parseObject(dataList);
        JSONArray jsonArray=JSONArray.parseArray(jsonObject.getString("data"));
        return R.ok().put("data",jsonArray).put("count",jsonObject.getString("total"));
    }
}
