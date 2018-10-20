package ru.ivmiit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ivmiit.dto.WeatherDto;

@Service
public class WeatherService {

    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    RestTemplate restTemplate;

    public WeatherDto getWeather(String city, String units, int day) {
        StringBuilder resource = new StringBuilder()
                .append(apiUrl).append("daily")
                .append("?city=").append(city)
                .append("&country=ru&days=1&lang=ru")
                .append("&key=").append(apiKey);

        ResponseEntity<String> response = restTemplate.getForEntity(resource.toString(), String.class);
        System.out.println(response.getBody());

        return WeatherDto.builder()
                .day(day)
                .build();
    }
}
