package com.alert.app.backend.api.imgw.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ImgwConfigTestSuite {

    @Autowired
    private ImgwConfig imgwConfig;

    @Test
    void shouldGetImgwApiEndpoint() {
        //Given & //When
        String apiEndpoint = imgwConfig.getImgwApiEndpoint();

        //Then
        assertEquals("https://danepubliczne.imgw.pl/api/data/synop", apiEndpoint);
    }
}
