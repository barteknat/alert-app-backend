package com.alert.app.backend.api.gios.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GiosApiCityDto {

    private long id;

    private String name;

    private GiosApiCommuneDto commune;
}
