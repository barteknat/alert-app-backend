package com.alert.app.backend.api.gios.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class GiosApiSensorParamDto {

    private String paramName;
    private String paramFormula;
    private String paramCode;
    private long idParam;
}
