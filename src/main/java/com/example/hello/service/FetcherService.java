package com.example.hello.service;

import lombok.SneakyThrows;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Async
public class FetcherService {
    @SneakyThrows
    public CompletableFuture<List<String>> fetch(String q) {
        List<String> urls = new ArrayList<>();
        String url = String.format("https://www.google.com/search?q=%s", normalize(q).get());
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Accept-language","en");
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            InputStream in = response.getEntity().getContent();
            String res = new String(in.readAllBytes());
//            System.out.println(res);
            Pattern pattern = Pattern.compile("(http|https)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*.pdf");
            Matcher matcher = pattern.matcher(res);
            while (matcher.find()) {
                urls.add(matcher.group());
            }
//            System.out.println(new String(in.readAllBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(urls.stream()
                .filter(s -> !s.contains("%"))
                .filter(s -> s.endsWith(".pdf"))
                .collect(Collectors.toList()));
    }

    public CompletableFuture<String> normalize(String q) {
        String q1 = q.replace(" ", "+");
        return CompletableFuture.completedFuture(q1.concat("+filetype%3Apdf"));
    }
}
