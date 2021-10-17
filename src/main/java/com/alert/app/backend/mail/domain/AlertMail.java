package com.alert.app.backend.mail.domain;

import com.alert.app.backend.domain.AirQualityIndex;
import com.alert.app.backend.domain.Subscribe;
import com.alert.app.backend.status.AirQualityStatus;

import static com.alert.app.backend.status.AirQualityStatus.*;

public enum AlertMail {

    ALERT_MAIL;

    public String getSubscribeAlertMessage(Subscribe subscribe, AirQualityIndex airQualityIndex) {
        return "WARNING!!!\n\n" +
                "Air condition in " + subscribe.getAirQualityStation().getCity() +
                " is >>> " + getStatus(airQualityIndex.getLevelName()) + " <<< try to stay at home. \n\n" +
                "Additional weather info: \n" +
                "-> temperature: " + subscribe.getWeatherStation().getTemperature() + " C \n" +
                "-> wind speed: " + subscribe.getWeatherStation().getWindSpeed() + " km/h \n" +
                "-> humidity: " + subscribe.getWeatherStation().getHumidity() + " % \n" +
                "-> pressure: " + subscribe.getWeatherStation().getPressure() + " hPa \n\n" +
                "AlertApp";
    }

    public String getSubscribeAlertSubject() {
        return "AIR CONDITION ALERT!!!";
    }

    private AirQualityStatus getStatus(String airStatus) {
        if (airStatus.equals(VERY_BAD.getValue())) return VERY_BAD;
        return BAD;
    }
}
