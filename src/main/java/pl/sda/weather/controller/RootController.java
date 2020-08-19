package pl.sda.weather.controller;

<<<<<<< HEAD
import com.sun.javafx.css.Declaration;
import com.sun.javafx.css.Stylesheet;
import com.sun.javafx.css.parser.CSSParser;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;
import pl.sda.weather.model.JSONDataFaster;
import pl.sda.weather.model.weather.Current;
import pl.sda.weather.model.weather.Location;
import pl.sda.weather.view.MapImage;
import pl.sda.weather.model.Weather;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.Normalizer;
import java.util.*;

public class RootController implements Initializable {
@FXML
private AnchorPane anchorPane;
=======
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pl.sda.JSONDataFaster;
import pl.sda.model.Weather;


public class RootController {

>>>>>>> 7bc9f7f37b9166336a30117b48ac23da009fb132
    @FXML
    private TextField city;
    @FXML
    private Button button;
    @FXML
    private Label temp;
<<<<<<< HEAD
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
    @FXML
    private ImageView mapViewSwap;

    private JSONDataFaster weatherRaport;

    private Image map;
    private Image mapBuffer;

    private int color1;

    private final Object lock = new Object();
    public RootController() {
        String key = "";
        File f = new File("key.txt");
        Scanner sr = null;

        try {
            sr = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.err.println("key.txt not included go to weatherstack.com");
        }

        if(sr != null ? sr.hasNextLine() : false)
            key = sr.nextLine();
        weatherRaport = new JSONDataFaster(
                "http://api.weatherstack.com/current",
                key
        );
    }

    public void getWeather() {
        String cityTemp = weatherRaport.getCity();
        String cityName = city.getText();
        cityName = cityName.replace(' ', '_');
        cityName = Normalizer
                .normalize(cityName, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
        weatherRaport.setCity(cityName);
        Weather weather = weatherRaport.getWeather();
        Location location = weather.getLocation();
        Current current = weather.getCurrent();

        if(map != null) {
            mapBuffer = map;
        }
        if(location != null)
            map = new MapImage(
                    640,
                    480,
                    location.getLon(),
                    location.getLat(),
                    current.getIs_day().equals("yes")
            ).getMapImage();

        mapView.setImage(map);
        mapViewSwap.setImage(mapBuffer);

        SequentialTransition sq = new SequentialTransition();

        if(!cityTemp.equals("")) {
            fadeOutData(sq, location, current);
        } else if(location != null)
            getData(location, current);

        sq.getChildren().add(backgroundAnim(current));

        sq.getChildren().addAll(
                fadeOutElement(mapViewSwap, 350),
                fadeInElement(mapView, 350)
        );

        fadeInData(sq);

        sq.setCycleCount(1);
        sq.setAutoReverse(false);
        sq.play();
    }

    private Animation backgroundAnim(Current current) {
        Animation anim;
        int colorRange = 256;
        double duration = 1500d;
        Stylesheet sheet =  new CSSParser().parse(anchorPane.getStyle());
        Declaration d = sheet.getRules().get(0).getDeclarations().get(0);
        String s = d.getParsedValue().getValue().toString();
        s = s.substring(2).toUpperCase();
        color1 = Integer.parseInt(s.substring(0, 2), 16);

        if(current != null && current.getIs_day().equals("no")) {
            anim = new Transition() {
                {
                    setCycleDuration(Duration.millis(duration));
                    setInterpolator(Interpolator.EASE_OUT);
                }
                @Override
                protected void interpolate(double frac) {
                    int range = 0-color1;
                    color1 += Math.round((float) frac * range);
                    anchorPane.setStyle(
                            ".root{\n" +
                                    "    -fx-base: rgb(" + color1 + ", " + color1 + ", " + color1 + ");\n" +
                                    "    -fx-background: rgb(" + color1 + ", " + color1 + ", " + color1 + ");\n" +
                                    "}"
                    );
                }
            };
        }
        else {
            anim = new Transition() {
                {
                    setCycleDuration(Duration.millis(duration));
                    setInterpolator(Interpolator.EASE_OUT);
                }
                @Override
                protected void interpolate(double frac) {
                    int range = colorRange - color1;
                    color1 += Math.round((float) frac * range);
                    anchorPane.setStyle(
                            ".root{\n" +
                                    "    -fx-base: rgb(" + color1 + ", " + color1 + ", " + color1 + ");\n" +
                                    "    -fx-background: rgb(" + color1 + ", " + color1 + ", " + color1 + ");\n" +
                                    "}"
                    );
                }
            };
        }
        anim.setAutoReverse(false);
        anim.setCycleCount(1);
        return anim;
    }

    private void fadeInData(SequentialTransition sq) {
        double time = 200.0f;
        if(!showCity.isVisible()) {
            showCity.setVisible(true);
            lat.setVisible(true);
            lon.setVisible(true);
            humidity.setVisible(true);
            windSpeed.setVisible(true);
            cloudCover.setVisible(true);
            isDay.setVisible(true);
            temp.setVisible(true);
        }

        sq.getChildren().addAll(
                fadeInElement(showCity, time),
                fadeInElement(temp, time),
                fadeInElement(lat, time),
                fadeInElement(lon, time),
                fadeInElement(isDay, time),
                fadeInElement(windSpeed, time),
                fadeInElement(humidity, time),
                fadeInElement(cloudCover, time)
        );
        sq.setAutoReverse(false);
        sq.setCycleCount(1);
    }

    private void fadeOutData(SequentialTransition sq, Location location, Current current) {
        double time = 100;

        FadeTransition ft = fadeOutElement(cloudCover, time);
        ft.setOnFinished(v -> getData(location, current));
        sq.getChildren().addAll(
                fadeOutElement(showCity, time),
                fadeOutElement(temp, time),
                fadeOutElement(lat, time),
                fadeOutElement(lon, time),
                fadeOutElement(isDay, time),
                fadeOutElement(windSpeed, time),
                fadeOutElement(humidity, time),
                ft
        );
        sq.setAutoReverse(false);
        sq.setCycleCount(1);

    }

    void getData(Location location, Current current) {
        showCity.setText(location.getName());
        lat.setText(Float.toString(location.getLat()));
        lon.setText(Float.toString(location.getLon()));
        humidity.setText(Integer.toString(current.getHumidity()));
        windSpeed.setText(Double.toString(current.getWind_speed()));
        temp.setText(Float.toString(current.getTemperature()));
        cloudCover.setText(Integer.toString(current.getCloudcover()));
        isDay.setText(current.getIs_day());
    }

    private <T extends Node> FadeTransition fadeInElement(T t, double time) {
        FadeTransition ft = new FadeTransition(new Duration(time), t);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.setAutoReverse(false);
        ft.setCycleCount(1);
        return ft;
    }

    private <T extends Node> FadeTransition fadeOutElement(T t, double time) {
        FadeTransition ft = new FadeTransition(new Duration(time), t);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setAutoReverse(false);
        ft.setCycleCount(1);
        return ft;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mapBuffer = new Image("/pl/sda/weather/res/WeatherFX.png");
        mapViewSwap.setImage(mapBuffer);
        color1 = 0;
        anchorPane.setStyle(
                ".root{\n" +
                "    -fx-base: rgb(255, 255, 255);\n" +
                "    -fx-background: rgb(255, 255, 255);\n" +
                "}"
        );

        showCity.setVisible(false);
        lon.setVisible(false);
        lat.setVisible(false);
        humidity.setVisible(false);
        cloudCover.setVisible(false);
        temp.setVisible(false);
        windSpeed.setVisible(false);
        isDay.setVisible(false);
=======

    public void getWeather() {

        JSONDataFaster weatherService = new JSONDataFaster(
                "http://api.apixu.com/v1/current.json",
                "e62328dd3789489597281354191307");

        weatherService.getJSONData(city.getText());

        Weather weather = weatherService.getWeather();

        temp.setText(Float.toString(weather.getCurrent().getTemp_c()));
>>>>>>> 7bc9f7f37b9166336a30117b48ac23da009fb132
    }
}
