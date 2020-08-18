package pl.sda.weather.controller;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import pl.sda.weather.JSONDataFaster;
import pl.sda.weather.model.Current;
import pl.sda.weather.model.MapImage;
import pl.sda.weather.model.Weather;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RootController {

    @FXML
    private TextField city;
    @FXML
    private Button button;
    @FXML
    private Label temp;
    @FXML
    private Label showCity;
    @FXML
    private Label lat;
    @FXML
    private Label lon;
    @FXML
    private ImageView mapView;
    @FXML
    private Label humidity;
    @FXML
    private Label cloudCover;
    @FXML
    private Label isDay;
    @FXML
    private Label windSpeed;

    private JSONDataFaster weatherRaport;

    private MapImage map;

    public RootController() {
        String key = "";
        File f = new File("key.txt");
        Scanner sr = null;
        try {
            sr = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(sr.hasNextLine()) key = sr.nextLine();
        weatherRaport = new JSONDataFaster(
                "http://api.weatherstack.com/current",
                key
        );


    }

    public void getWeather() {
        String checkCity = city.getText();
        checkCity = checkCity.replace(' ', '_');

        weatherRaport.getJSONData(checkCity);
        weatherRaport.setCity(checkCity);
        Weather weather = weatherRaport.getWeather();
        showCity.setText(weather.getLocation().getName());
        float latitude = weather.getLocation().getLat();
        float longitude = weather.getLocation().getLon();
        lat.setText(Float.toString(latitude));
        lon.setText(Float.toString(longitude));
        Current current = weather.getCurrent();
        humidity.setText(Integer.toString(current.getHumidity()));
        windSpeed.setText(Double.toString(current.getWind_speed()));
        isDay.setText(current.getIs_day());
        cloudCover.setText(Integer.toString(current.getCloudcover()));
        map = new MapImage(640, 480, longitude, latitude, (
                current.getIs_day().equals("yes")
                ));

        mapView.setImage(map.getMapImage());
        temp.setText(Float.toString(weather.getCurrent().getTemperature()));
    }
}
