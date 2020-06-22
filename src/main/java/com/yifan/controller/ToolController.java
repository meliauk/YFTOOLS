package com.yifan.controller;

import com.yifan.util.baiduTranslate.TransApi;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("tools")
public class ToolController {

    @Value("${baidu.APP_ID}")
    private   String APP_ID ;
    @Value("${baidu.SECURITY_KEY}")
    private   String SECURITY_KEY ;

    private final  String URL = "http://www.youdao.com/wordbook/ajax?action=addword&q=";

    /*----------工具-翻译 */
    @GetMapping("AddWords")
    @ResponseBody
    public String AddWords(String word) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("https://logindict.youdao.com/login/acc/login");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("app", "web"));
        nvps.add(new BasicNameValuePair("tp", "urstoken"));
        nvps.add(new BasicNameValuePair("fr", "1"));
        nvps.add(new BasicNameValuePair("ru", " http://dict.youdao.com/wordbook/wordlist?keyfrom=dict2.index#/"));
        nvps.add(new BasicNameValuePair("product", "DICT"));
        nvps.add(new BasicNameValuePair("type", "1"));
        nvps.add(new BasicNameValuePair("um", "true"));
        nvps.add(new BasicNameValuePair("username", "17683916147@163.com"));
        nvps.add(new BasicNameValuePair("cf", "7"));
        nvps.add(new BasicNameValuePair("password", "wang19990511"));
        nvps.add(new BasicNameValuePair("agreePrRule", "1"));
        nvps.add(new BasicNameValuePair("savelogin", "1"));

        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        httpPost.setHeader("Cookie","DICT_FORCE=true; OUTFOX_SEARCH_USER_ID=1651755494@117.153.23.228; UM_distinctid=17250acda7236f-0b382833ef52ba-d373666-144000-17250acda73468; DICT_SESS=v2|URSM|DICT||m17683916147@163.com||urstoken||ZzBoKjWz.nPMv6R3McZrTHH.v6B.0LL_sa0eaL9BHbL5tFe_JvndTO2gRF3K2WO68HORS6Wb2YouZM9OCd24f94g2Rd5j83etHQhhm1cs7kqMCgZwQEFZ8g6rkU3Lps1qvLaYapxr0IKg3qG5ZBZ9o.QR7zYP2S6B5u17RuVk.FWjOoulP_Eb_GTeRjeDAWE8pfHktQsziBSC2YrmffQjFjsiWKjO58Rx||604800000||guOfUE6Lg4RwyhMPBP4UMRgSOMQZOLqL0qF6Mp4h4PBRJFkfpy0MQK0Qu0fpBn46u0eZ64gL64q4RQzOfUMRHeS0; DICT_PERS=v2|urstoken||DICT||web||-1||1590491816889||117.153.23.228||m17683916147@163.com||JuOfJK6LQy0T4hfzMkLU50kGOMwFn4kG06yPMQzOMQL0OfPLpB6LwL0QzhHUWP4qF0wKOLQy0HOfRkG6MwL0MO50; DICT_LOGIN=3||1590491816932; OUTFOX_SEARCH_USER_ID_NCOO=53788412.8059541");
        httpPost.setHeader("Access-Control-Allow-Credentials","true");
        httpPost.setHeader("Access-Control-Allow-Origin","http://account.youdao.com");
        httpPost.setHeader("Content-Type","application/x-www-form-urlencoded");
        CloseableHttpResponse response2 = httpclient.execute(httpPost);
        System.err.println("--code-->" +response2.getStatusLine().getStatusCode() );
        System.err.println("--post-->" + EntityUtils.toString(response2.getEntity(), "UTF-8") );



        HttpGet httpGet = new HttpGet(URL+word.trim());
        CloseableHttpResponse response = httpclient.execute(httpGet);
        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity1 = response.getEntity();
            System.err.println( EntityUtils.toString(entity1, "UTF-8") );

        } finally {
            response.close();
        }

        return "成功";
    }

    /*----------工具-翻译 */
    @PostMapping("FanYi")
    @ResponseBody
    public String FanYi(String query){
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);
        return api.getTransResult(query.trim(), "auto", "auto");
    }

    /*----------工具-视频api */
    @GetMapping("movie/course")
    public String course(){
        return "course";
    }

    /*----------工具-视频播放 */
    @GetMapping("movie/mPlayer")
    public String mPlayer(){
        return "mPlayer";
    }

    /*----------工具- 聊天*/
    @GetMapping("chat")
    public String chat(){
        return "chat";
    }

    /*----------工具- 翻译*/
    @GetMapping("translate")
    public String translate(){
        return "translate";
    }



}


