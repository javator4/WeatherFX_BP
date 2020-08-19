package pl.sda.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pl.sda.weather.model.weather.Current;
import pl.sda.weather.model.weather.Location;
import pl.sda.weather.model.weather.Request;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

    @JsonIgnore
    private Request request;
    private Location location;
    private Current current;
}
