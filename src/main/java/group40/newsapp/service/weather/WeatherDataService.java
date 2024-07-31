package group40.newsapp.service.weather;

import group40.newsapp.DTO.weather.WeatherDataResponseDto;
import group40.newsapp.DTO.weather.WeatherLatLonDTO;
import group40.newsapp.models.weather.WeatherDataEntity;
import group40.newsapp.repository.weather.WeatherRepository;
import group40.newsapp.service.util.weatherMapping.WeatherConverter;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WeatherDataService implements WeatherDataServiceInterface{

    private final WeatherRepository repository;
    private final WeatherConverter weatherConverter;
    private final OutWeatherApi outWeatherApi;
    private final OutGeoLocationApi outGeoLocationApi;

    @Override
    @SneakyThrows
    public WeatherDataResponseDto getWeather(String ipAddress)  {
        // Get latitude and longitude from IP address
        WeatherLatLonDTO dto = getLatLonFromGeoLocation(ipAddress);

        // Retrieve weather data from database
        ArrayDeque<WeatherDataEntity> entityQueue = getFromDatabase(dto.getLat(), dto.getLon());

        if (!entityQueue.isEmpty()) {
            WeatherDataEntity latestWeatherData = entityQueue.getLast();
            LocalDateTime createdTime = latestWeatherData.getTimeCreate();

            long duration = Duration.between(createdTime, LocalDateTime.now()).toMinutes();

            // If the latest data is less than 10 minutes old, return it
            if (duration < 10) {
                return weatherConverter.fromEntityToDto(latestWeatherData);
            }
        }

        // Fetch new data from API and update existing data
        WeatherDataResponseDto response = getFromApi(dto.getLat(), dto.getLon());
        WeatherDataEntity updatedEntity = weatherConverter.fromDtoToEntity(response);

        // Update the existing entity with new data
        if (!entityQueue.isEmpty()) {
            WeatherDataEntity latestWeatherData = entityQueue.getLast();
            updateEntity(latestWeatherData, updatedEntity);
            repository.save(latestWeatherData);
        } else {
            repository.save(updatedEntity);
        }

        return response;
    }

    private ArrayDeque<WeatherDataEntity> getFromDatabase(String lat, String lon) {
        return repository.findAllByLatitudeAndLongitude(lat, lon);
    }


    private WeatherDataResponseDto getFromApi(String lat, String lon) throws MalformedURLException, URISyntaxException {
        return outWeatherApi.receivedFromWeatherApi(lat,lon);
    }

    private WeatherLatLonDTO getLatLonFromGeoLocation(String ipAddress) {
        return outGeoLocationApi.getLatLonFromGeoLocation(ipAddress);
    }

    private void updateEntity(WeatherDataEntity existingEntity, WeatherDataEntity newEntity) {
        existingEntity.setLatitude(newEntity.getLatitude());
        existingEntity.setLongitude(newEntity.getLongitude());
        existingEntity.setCityName(newEntity.getCityName());
        existingEntity.setTemperature(newEntity.getTemperature());
        existingEntity.setAppTemperature(newEntity.getAppTemperature());
        existingEntity.setIcon(newEntity.getIcon());
        existingEntity.setDescription(newEntity.getDescription());
        existingEntity.setHumidity(newEntity.getHumidity());
        existingEntity.setWindCdir(newEntity.getWindCdir());
        existingEntity.setWindCdirFull(newEntity.getWindCdirFull());
        existingEntity.setWindSpd(newEntity.getWindSpd());
        existingEntity.setTimeCreate(newEntity.getTimeCreate());
    }
}
