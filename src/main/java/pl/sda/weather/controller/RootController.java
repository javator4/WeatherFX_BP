package pl.sda.weather.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RootController {

    @FXML
    private TextField city;
    @FXML
    private Button button;
    @FXML
    private Label temp;

    public void getWeather() {
        temp.setText(city.getText());
    }
}
