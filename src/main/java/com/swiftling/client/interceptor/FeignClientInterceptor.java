package com.swiftling.client.interceptor;

import com.swiftling.service.KeycloakService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    private final KeycloakService keycloakService;

    public FeignClientInterceptor(KeycloakService keycloakService) {
        this.keycloakService = keycloakService;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", keycloakService.getAccessToken());
    }

}
