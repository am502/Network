package ru.ivmiit.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {

    @RequestMapping(value = "/weather", method = RequestMethod.POST)
    public ResponseEntity<String> getWeather(
            @RequestParam(value = "city") String city,
            @RequestParam(value = "type") String type,
            @RequestParam(value = "day") int day) {
        return ResponseEntity.ok("ok");
    }
}
