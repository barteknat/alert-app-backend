package com.alert.app.backend.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AirQualityStatus {

    VERY_GOOD ("Bardzo dobry"),
    GOOD ("Dobry"),
    MODERATE ("Umiarkowany"),
    SUFFICIENT ("Dostateczny"),
    BAD ("Zły"),
    VERY_BAD ("Bardzo zły");

    private final String value;
}
