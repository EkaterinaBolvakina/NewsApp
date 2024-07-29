package group40.newsapp.DTO.weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherLatLonDTO {
    private String lat;
    private String lon;
}
