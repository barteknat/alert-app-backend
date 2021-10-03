package com.alert.app.backend.api.gios.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class GiosConfig {

    @Value("${gios.api.endpoint}")
    private String giosApiEndpoint;


}
