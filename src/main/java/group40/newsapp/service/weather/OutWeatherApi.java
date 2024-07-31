package group40.newsapp.service.weather;

import group40.newsapp.DTO.weather.WeatherDataResponseDto;
import group40.newsapp.DTO.weather.weatherJsonDataModel.WeatherJSON;
import group40.newsapp.exception.RestException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@Service
@AllArgsConstructor
public class OutWeatherApi {

    private static Logger log = LoggerFactory.getLogger(OutWeatherApi.class);
    private final RestTemplate restTemplate;

    public WeatherDataResponseDto receivedFromWeatherApi(String lat, String lon) throws MalformedURLException, URISyntaxException {

        String urlRequest = createUrl(lat,lon);

        URL url = new URL(urlRequest);

        log.info("Sending request to {}", url);
        ResponseEntity<WeatherJSON> weatherJSON = restTemplate.getForEntity(url.toURI(), WeatherJSON.class);
        log.info("Received response {}", weatherJSON);

        if (weatherJSON.getBody() != null) {

            String cityFromResponse = weatherJSON.getBody().getData().get(0).getCity_name();
            String tempFromResponse = String.valueOf(Math.round(weatherJSON.getBody().getData().get(0).getTemp()));
            String appTempFromResponse = String.valueOf(Math.round(weatherJSON.getBody().getData().get(0).getApp_temp()));
            String descriptionFromResponse = weatherJSON.getBody().getData().get(0).getWeather().getDescription();
            String windFromResponse = String.format("%.1f", weatherJSON.getBody().getData().get(0).getWind_spd());
            String windDirFromResponse = weatherJSON.getBody().getData().get(0).getWind_cdir();
            String windDirFullFromResponse = weatherJSON.getBody().getData().get(0).getWind_cdir_full();
            String humidityFromResponse = "" + weatherJSON.getBody().getData().get(0).getRh();
            String iconFromResponse = weatherJSON.getBody().getData().get(0).getWeather().getIcon();

            return new WeatherDataResponseDto(lat,lon,cityFromResponse,tempFromResponse, appTempFromResponse,descriptionFromResponse,windFromResponse,windDirFromResponse, windDirFullFromResponse, humidityFromResponse, iconFromResponse);
        } else {
            throw new RestException(HttpStatus.NOT_FOUND, "Response body not found");
        }
    }

    private String createUrl(String lat, String lon) {

        //https://https://api.weatherbit.io/v2.0/current?lat=52.52453&lon=13.41004&key=ae6890f1ffa74302988ce90ffbb4e71a&lang=de

        return UriComponentsBuilder.fromHttpUrl("https://api.weatherbit.io/v2.0/current")
                .queryParam("lat",lat)
                .queryParam("lon",lon)
                .queryParam("key","ae6890f1ffa74302988ce90ffbb4e71a")
                .queryParam("lang","de")
                .build()
                .toString();
    }
}
