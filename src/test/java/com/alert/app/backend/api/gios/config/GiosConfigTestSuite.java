package com.alert.app.backend.api.gios.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GiosConfigTestSuite {

    @Autowired
    private GiosConfig giosConfig;

    @Test
    void shouldGetGiosApiEndpoint() {
        //Given & //When
        String apiEndpoint = giosConfig.getGiosApiEndpoint();

        //Then
        assertEquals("http://api.gios.gov.pl/pjp-api/rest", apiEndpoint);
    }
}
