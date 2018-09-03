package com.taotao.httpclient;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

/**
 * @Author: shenxingbo
 * @Description:
 * @Date: 2018/9/1下午5:58
 **/

public class HttpClientTest {

    @Test
    public void doGet() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("http://www.baidu.com");


        CloseableHttpResponse response = httpClient.execute(httpGet);

        if(response.getStatusLine().getStatusCode() == 200){
            HttpEntity entity = response.getEntity();

            String result = EntityUtils.toString(entity, "UTF-8");

            System.out.println(result);

            response.close();
            httpClient.close();
        }
    }

    @Test
    public void doGetWithParam() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        URIBuilder builder = new URIBuilder("http://www.baidu.com");

        builder.addParameter("query", "花千骨");

        HttpGet httpGet = new HttpGet(builder.build());


        CloseableHttpResponse response = httpClient.execute(httpGet);

        if(response.getStatusLine().getStatusCode() == 200){
            HttpEntity entity = response.getEntity();

            String result = EntityUtils.toString(entity, "UTF-8");

            System.out.println(result);

            response.close();
            httpClient.close();
        }

    }
}
