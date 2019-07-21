package pl.sda.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter

public class Current {

    private int last_updated_epoch;
    private String last_updated;
    private float temp_c;
    private float temp_f;
    private int is_day;
    private Condition condition;
    private double wind_mph;
    private double wind_kph;
    private int wind_degree;
    private String wind_dir;
    private int pressure_mb;
    private double pressure_in;
    private int precip_mm;
    private int precip_in;
    private int humidity;
    private int cloud;
    private double feelslike_c;
    private double feelslike_f;
    private int vis_km;
    private int vis_miles;
    private int uv;
    private float gust_mph;
    private float gust_kph;
}
