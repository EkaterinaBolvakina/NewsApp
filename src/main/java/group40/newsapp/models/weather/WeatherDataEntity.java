package group40.newsapp.models.weather;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "weather")
public class WeatherDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String latitude;
    private String longitude;
    private String cityName;
    private String temperature;
    private String icon; // "c03d" ???
    private String description; // "Broken clouds"
    private String wind_cdir; // "NE, NO, S Windrichtung"
    private String wind_spd; // 6.17


    @Column(name = "'create'")
    private LocalDateTime timeCreate;
}
