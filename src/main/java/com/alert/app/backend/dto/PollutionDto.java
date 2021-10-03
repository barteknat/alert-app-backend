package com.alert.app.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PollutionDto {

    private long id;

    private String name;

    private String sulphurDioxide;

    private String nitrogenDioxide;

    private String pM10;

    private String pM2_5;

    private String carbonMonoxide;

    private String benzene;

    private String ozone;
}
