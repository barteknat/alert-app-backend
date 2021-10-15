package com.alert.app.backend.api.imgw.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ImgwConfig {

    @Value("${imgw.api.endpoint}")
    private String imgwApiEndpoint;
}
