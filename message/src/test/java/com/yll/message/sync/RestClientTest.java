package com.yll.message.sync;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author：linlin.yang
 * @date：2017/10/26 11:29
 */
public class RestClientTest {
    public static void main(String[] args){
        RestClientTest t = new RestClientTest();
//        try {
//            t.fetchHttpResponse();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        t.fetchHttpResponseByRestTemplate();
    }

    public Object fetchHttpResponse() throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet("https://www.baidu.com/");
        Header[] headers = request.getAllHeaders();
        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(entity.getContent(), Object.class);
    }

    public Object fetchHttpResponseByRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter()));
        Object object = restTemplate.getForObject("https://www.baidu.com/", Object.class);
        return object;
    }
}
