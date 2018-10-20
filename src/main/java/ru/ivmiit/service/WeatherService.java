package ru.ivmiit.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ivmiit.dto.WeatherDto;
import ru.ivmiit.dto.json.DataJsonDto;
import ru.ivmiit.dto.json.WeatherJsonDto;

@Service
public class WeatherService {

    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    Gson gson;

    public WeatherDto getWeather(String city, String units, int day) {
        StringBuilder resource = new StringBuilder()
                .append(apiUrl).append("daily")
                .append("?city=").append(city)
                .append("&country=ru")
                .append("&days=7")
                .append("&units=").append(units)
                .append("&lang=ru")
                .append("&key=").append(apiKey);

        ResponseEntity<String> response = restTemplate.getForEntity(resource.toString(), String.class);
        String json = response.getBody();

        WeatherJsonDto weatherJsonDto = gson.fromJson(json, WeatherJsonDto.class);

        DataJsonDto data = weatherJsonDto.getData().get(day);

        WeatherDto weatherDto = WeatherDto.builder()
                .city(weatherJsonDto.getCity_name())
                .date(data.getValid_date())
                .relativeHumidity(data.getRh())
                .pressure(data.getPres())
                .windSpeed(data.getWind_spd())
                .windDirection(data.getWind_cdir_full())
                .clouds(data.getClouds())
                .pop(data.getPop())
                .temperature(data.getTemp())
                .weatherDescription(data.getWeather().getDescription())
                .build();

        return weatherDto;
    }
}
