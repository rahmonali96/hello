package com.example.hello.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Developed by Rahmonali Yoqubov
 * Email: rahmonaliyoqubov@gmail.com
 * Date: 18.08.2022
 * Project: hello
 */
@Service
@AllArgsConstructor
public class Fetcher2Service {
    private WebClient webClient;

    public Mono<List<String>> fetch(String q) {
        List<String> urls = new ArrayList<>();
        Pattern pattern = Pattern.compile("(http|https)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*.pdf");
        String res = webClient
                .get()
                .header("Accept-language", "en")
                .attribute("q", normalize(q))
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(String.class)).block(Duration.ofSeconds(5));

        assert res != null;
        Matcher matcher = pattern.matcher(res);
        while (matcher.find()) {
            urls.add(matcher.group());
        }

        return Mono.just(urls.stream()
                .filter(s -> !s.contains("%"))
                .filter(s -> s.endsWith(".pdf"))
                .collect(Collectors.toList()));
    }

    public String normalize(String q) {
        String q1 = q.replace(" ", "+");
        return q1.concat("+filetype%3Apdf");
    }
}
