package ru.ivmiit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ivmiit.dto.WeatherDto;

import java.util.Date;

@Service
public class WeatherService {

    @Autowired
    RestTemplate restTemplate;

    public WeatherDto getWeather(String city, String type, Date date) {
        return WeatherDto.builder()
                .date(date)
                .build();
    }
}
