package com.alert.app.backend.api.imgw.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImgwApiStationDto {

    private String id_stacji;
    private String stacja;
    private String data_pomiaru;
    private String temperatura;
    private String predkosc_wiatru;
    private String kierunek_wiatru;
    private String wilgotnosc_wzgledna;
    private String suma_opadu;
    private String cisnienie;
}
