package com.yifan;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class YftoolsApplicationTests {



    @Test
    void ccc() throws IOException {


        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://logindict.youdao.com/login/acc/login");
        post.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36");

        //登录表单的信息
        List<NameValuePair> qparams = new ArrayList<NameValuePair>();
        qparams.add(new BasicNameValuePair("username", "17683916147@163.com"));
        qparams.add(new BasicNameValuePair("password", "wang19990511"));
        post.setEntity(new UrlEncodedFormEntity(qparams));
        CloseableHttpResponse execute = httpClient.execute(post);
        System.err.println(execute+"----> "+execute.getStatusLine().getStatusCode());
    }

}
