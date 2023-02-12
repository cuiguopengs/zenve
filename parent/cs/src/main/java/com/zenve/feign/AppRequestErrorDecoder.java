package com.zenve.feign;

import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * @description:
 * @className: RequestErrorDecoder
 * @author: liwen
 * @date: 2020/8/13 09:36
 */
public class AppRequestErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
//        ProcessChain.create().addRunnableInPlatformThread(() -> {
//            AlertUtil.show(response);
//        }).run();

        return null;
    }
}
