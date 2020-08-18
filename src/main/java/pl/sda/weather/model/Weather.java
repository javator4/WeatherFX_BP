package pl.sda.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
