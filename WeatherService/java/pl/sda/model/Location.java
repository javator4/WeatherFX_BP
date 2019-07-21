package pl.sda.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Location {

    private String name;
    private String region;
    private String country;
    private float lat, lon;
    private String tz_id;
    private long localtime_epoch;
    private String localtime;
}
