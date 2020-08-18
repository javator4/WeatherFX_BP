package pl.sda.weather.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Condition {

    private String text;
    private String icon;
    private int code;

}
