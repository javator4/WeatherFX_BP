package pl.sda.weather.model.weather;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Condition {

    private String text;
    private String icon;
    private int code;

}
