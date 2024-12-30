package com.learning.journalApp.service;

import com.learning.journalApp.api_response.weather_response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherServices {
    private static final String apikey = "bd57a591bc2f4b0c91e135206240406";

    private static final String API = "http://api.weatherapi.com/v1/current.json?key=API_KEY&q=CITY";

    @Autowired
    private RestTemplate restTemplate;

    public weather_response getWeather(String city) {
        String finalapi = API.replace("CITY", city).replace("API_KEY", apikey);
        ResponseEntity<weather_response> responseEntity = restTemplate.exchange(finalapi, HttpMethod.GET, null, weather_response.class);
        weather_response body = responseEntity.getBody();
        return body;
    }


}
