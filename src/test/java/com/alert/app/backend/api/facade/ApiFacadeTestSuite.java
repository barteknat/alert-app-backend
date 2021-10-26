package com.alert.app.backend.api.facade;

import com.alert.app.backend.api.gios.dto.GiosApiAirQualityDto;
import com.alert.app.backend.api.gios.dto.GiosApiSensorDto;
import com.alert.app.backend.api.gios.dto.GiosApiStationDto;
import com.alert.app.backend.api.gios.service.GiosService;
import com.alert.app.backend.api.imgw.dto.ImgwApiStationDto;
import com.alert.app.backend.api.imgw.service.ImgwService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApiFacadeTestSuite {

    @InjectMocks
    private ApiFacade apiFacade;

    @Mock
    private ImgwService imgwService;

    @Mock
    private GiosService giosService;

    @Test
    void shouldGetAllWeatherStations() {
        //Given
        List<ImgwApiStationDto> imgwApiStationDtoList = Arrays.asList(new ImgwApiStationDto());
        when(imgwService.getAndSaveAllStations()).thenReturn(imgwApiStationDtoList);

        //When
        List<ImgwApiStationDto> list = apiFacade.getAllWeatherStations();

        //Then
        assertEquals(1, list.size());
    }

    @Test
    void shouldGetAllAirQualityStations() {
        //Given
        List<GiosApiStationDto> giosApiStationDtoList = Arrays.asList(new GiosApiStationDto());
        when(giosService.getAndSaveAllStations()).thenReturn(giosApiStationDtoList);

        //When
        List<GiosApiStationDto> list = apiFacade.getAllAirQualityStations();

        //Then
        assertEquals(1, list.size());
    }

    @Test
    void shouldGetSensorsByStationId() {
        //Given
        List<GiosApiSensorDto> giosApiSensorDtoList = Arrays.asList(new GiosApiSensorDto());
        when(giosService.getAndSaveSensorsByStationId(1)).thenReturn(giosApiSensorDtoList);

        //When
        List<GiosApiSensorDto> list = apiFacade.getSensorsByStationId(1);

        //Then
        assertEquals(1, list.size());
    }

    @Test
    void shouldGetAirQualityIndexByStationId() {
        //Given
        GiosApiAirQualityDto giosApiAirQualityDto = new GiosApiAirQualityDto();
        when(giosService.getAndSaveAirQualityIndexByStationId(1)).thenReturn(giosApiAirQualityDto);

        //When
        GiosApiAirQualityDto giosApiAirQualityDto1 = apiFacade.getAirQualityIndexByStationId(1);

        //Then
        assertNotNull(giosApiAirQualityDto1);
    }
}
