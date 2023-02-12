package com.zenve.feign;

import com.zenve.store.ApplicatonStore;
import feign.RequestInterceptor;
import feign.RequestTemplate;

public class AppRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {

        template.header("Authorization", ApplicatonStore.getToken());
        template.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36");
        template.header("tenant-id", "1");
    }
}