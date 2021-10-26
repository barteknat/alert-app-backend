package com.alert.app.backend.api.imgw.mapper;

import com.alert.app.backend.api.imgw.dto.ImgwApiStationDto;
import com.alert.app.backend.domain.WeatherStation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ImgwMapperTestSuite {

    @Autowired
    private ImgwMapper imgwMapper;

    @Test
    void shouldMapToStation() {
        //Given
        ImgwApiStationDto imgwApiStationDto = ImgwApiStationDto.builder()
                .id_stacji("1")
                .id_stacji("1")
                .stacja("test")
                .data_pomiaru("1900-01-01")
                .godzina_pomiaru("1")
                .temperatura("1")
                .predkosc_wiatru("1")
                .kierunek_wiatru("1")
                .wilgotnosc_wzgledna("1")
                .suma_opadu("1")
                .cisnienie("1")
                .build();

        //When
        WeatherStation weatherStation = imgwMapper.mapToStation(imgwApiStationDto);

        //Then
        assertEquals(Long.parseLong(imgwApiStationDto.getId_stacji()), weatherStation.getId());
        assertEquals(Long.parseLong(imgwApiStationDto.getId_stacji()), weatherStation.getStationId());
        assertEquals(imgwApiStationDto.getStacja(), weatherStation.getCity());
        assertEquals(LocalDate.parse(imgwApiStationDto.getData_pomiaru()), weatherStation.getDate());
        assertEquals(Long.parseLong(imgwApiStationDto.getGodzina_pomiaru()), weatherStation.getTime());
        assertEquals(Long.parseLong(imgwApiStationDto.getTemperatura()), weatherStation.getTemperature());
        assertEquals(Long.parseLong(imgwApiStationDto.getPredkosc_wiatru()), weatherStation.getWindSpeed());
        assertEquals(Long.parseLong(imgwApiStationDto.getKierunek_wiatru()), weatherStation.getWindDirection());
        assertEquals(Long.parseLong(imgwApiStationDto.getWilgotnosc_wzgledna()), weatherStation.getHumidity());
        assertEquals(Long.parseLong(imgwApiStationDto.getSuma_opadu()), weatherStation.getRainAmount());
        assertEquals(Long.parseLong(imgwApiStationDto.getCisnienie()), weatherStation.getPressure());
    }
}
