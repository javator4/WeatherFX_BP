package pl.sda.weather.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pl.sda.JSONDataFaster;
import pl.sda.model.Weather;


public class RootController {

    @FXML
    private TextField city;
    @FXML
    private Button button;
    @FXML
    private Label temp;

    public void getWeather() {

        JSONDataFaster weatherService = new JSONDataFaster(
                "http://api.apixu.com/v1/current.json",
                "e62328dd3789489597281354191307");

        weatherService.getJSONData(city.getText());

        Weather weather = weatherService.getWeather();

        temp.setText(Float.toString(weather.getCurrent().getTemp_c()));
    }
}
