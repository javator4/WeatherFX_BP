package pl.sda.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

    private Location location;
    private Current current;
}
