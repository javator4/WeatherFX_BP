package pl.sda.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter

public class Current {

    private int last_updated_epoch;
    private String last_updated;
    private float temperature;
    private String is_day;
    private Condition condition;
    private double wind_speed;
    private double wind_kph;
    private int wind_degree;
    private String wind_dir;
    private int pressure;
    private double pressure_in;
    private int precip;
    private int precip_in;
    private int humidity;
    private int cloudcover;
    private double feelslike;
    private double feelslike_f;
    private int visibility;
    private int vis_miles;
    private int uv_index;
    private float gust_mph;
    private float gust_kph;
    private String observation_time;
    private String weather_code;
    @JsonIgnore
    private String weather_icons, weather_descriptions;
}
