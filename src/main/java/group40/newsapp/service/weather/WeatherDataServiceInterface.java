package group40.newsapp.service.weather;


import group40.newsapp.DTO.weather.WeatherDataResponseDto;

public interface WeatherDataServiceInterface {

    WeatherDataResponseDto getWeather(String ipAddress);

}
