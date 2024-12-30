package com.learning.journalApp.api_response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class weather_response {
    private Current current;

    @Getter
    @Setter
    public static class Current {
        @JsonProperty("temp_c")
        private double TempC;
        @JsonProperty("feelslike_c")
        private double feelsLikeC;
    }
}



